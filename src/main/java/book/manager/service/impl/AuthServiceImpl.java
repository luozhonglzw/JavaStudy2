package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    UserMapper mapper;
    @Transactional
    @Override
    public void register(String name, String sex, String grade, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AuthUser user=new AuthUser(0,name, encoder.encode(password),"user" );
        if(mapper.registerUser(user)<=0){
            throw new RuntimeException("用户基本信息添加失败");
        }
        if(mapper.addStudent(user.getUsername(),user.getId(),sex,grade)<=0){
            throw new RuntimeException("用户学生信息添加失败");
        }
    }
}
