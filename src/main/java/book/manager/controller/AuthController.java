package book.manager.controller;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import book.manager.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Resource
    AuthService service;
    @Resource
    BookService bookService;
    @Resource
    UserMapper mapper;
    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    public String register(@RequestParam("username")String name,
                           @RequestParam("sex")String sex,
                           @RequestParam("grade") String grade,
                           @RequestParam("password") String password){
        service.register(name, sex, grade, password);
        return "login";
    }
    @RequestMapping(value = "/add-book",method =RequestMethod.POST )
    public String addBook(@RequestParam("bid")int bid,
                          @RequestParam("bname")String bname,
                          @RequestParam("introduce") String introduce,
                          @RequestParam("Price")int Price){
//        , HttpSession session, Model model
//        AuthUser user=(AuthUser) session.getAttribute("user");
//        if(user == null){
//            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//            user=mapper.getPasswordByUsername(authentication.getName());
//            session.setAttribute("user",user);
//        }
//        model.addAttribute("user",user);
        bookService.addBook(bname, bid, introduce, Price);
        return "redirect:/adminbook";
    }
}
