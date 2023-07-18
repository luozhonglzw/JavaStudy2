package book.manager.controller;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import book.manager.service.BookService;
import book.manager.service.StudentBookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    @Resource
    UserMapper mapper;
    @Resource
    BookService bookservice;
    @Resource
    StudentBookService studentBookService;
    //    @PreAuthorize("hasRole('admin')")

    //    @PreAuthorize("hasAnyRole('user','admin')")
    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {
        AuthUser user=(AuthUser) session.getAttribute("user");
        if(user == null){
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            user=mapper.getPasswordByUsername(authentication.getName());
            session.setAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("studentBooklist",studentBookService.getStudentBook());
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/doRegister")
    public String register(){
        return "register";
    }
    @RequestMapping("/userindex")
    public String userindex(HttpSession session, Model model){
        AuthUser user=(AuthUser) session.getAttribute("user");
        if(user == null){
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            user=mapper.getPasswordByUsername(authentication.getName());
            session.setAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("booklist",bookservice.getBook());
        model.addAttribute("studentBooklist",studentBookService.getStudentBook());
        return "userindex";
    }
    @RequestMapping("/adminbook")
    public String adminbook(HttpSession session, Model model){
        AuthUser user=(AuthUser) session.getAttribute("user");
        if(user == null){
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            user=mapper.getPasswordByUsername(authentication.getName());
            session.setAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("booklist",bookservice.getBook());
        return "adminbook";
    }
    @RequestMapping("/userbook")
    public String userbook(HttpSession session, Model model){
        AuthUser user=(AuthUser) session.getAttribute("user");
        if(user == null){
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            user=mapper.getPasswordByUsername(authentication.getName());
            session.setAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("booklist",bookservice.getStudentBooks(user.getId()));
        System.out.println(user.getId());
        return "userbook";
    }
}
