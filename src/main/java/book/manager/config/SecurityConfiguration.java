package book.manager.config;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.impl.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javax.annotation.Resource;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    PersistentTokenRepository repository;

    @Resource
    UserMapper mapper;
    @Bean
    public PersistentTokenRepository jdbcRepository(@Autowired DataSource dataSource){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();  //使用基于JDBC的实现
        repository.setDataSource(dataSource);   //配置数据源
//        repository.setCreateTableOnStartup(true);   //启动时自动创建用于存储Token的表（建议第一次启动之后删除该行）
        return repository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()   //首先需要配置哪些请求会被拦截，哪些请求必须具有什么角色才能访问
                .antMatchers("/static/**","/login","/register","/doRegister","/userindex","/userbook","/index","/adminbook").permitAll()    //静态资源，使用permitAll来运行任何人访问（注意一定要放在前面）
//                .antMatchers("/index").hasAnyRole("user","admin")  //所有请求必须登陆并且是user角色才可以访问（不包含上面的静态资源）
                .antMatchers("/userbook","/userindex").hasRole("user")
                .antMatchers("/index","/adminbook","/del-book","/add-book").hasRole("admin")
                .anyRequest().hasAnyRole("user","admin")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .successHandler(this::onAuthenticationSuccess)
//                .defaultSuccessUrl("/index")
//                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable()
                .rememberMe()   //开启记住我功能
                .rememberMeParameter("remember")  //登陆请求表单中需要携带的参数，如果携带，那么本次登陆会被记住
                .tokenValiditySeconds(60*60*24*7)
                .tokenRepository(repository);  //这里使用的是直接在内存中保存的TokenRepository实现
    }

    @Resource
    UserAuthService service;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        HttpSession session=httpServletRequest.getSession();
        AuthUser user=mapper.getPasswordByUsername(authentication.getName());
        session.setAttribute("user",user);
        if(user.getRole().equals("admin")){
            httpServletResponse.sendRedirect("/BookManager_war_exploded/index");
        }
        else {
            httpServletResponse.sendRedirect("/BookManager_war_exploded/userindex");
        }
    }
}
