package book.manager.controller;

import book.manager.entity.AuthBook;
import book.manager.entity.AuthUser;
import book.manager.mapper.BookMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.BookService;
import book.manager.service.StudentBookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Resource
    UserMapper mapper;
    @Resource
    BookService service;
    @Resource
    BookMapper bookMapper;
    @Resource
    StudentBookService studentbookservice;
    @RequestMapping(value = "/del-book", method = RequestMethod.GET)
    public String deleteBook(@RequestParam("id") int id) {
        service.deleteBook(id);
        return "redirect:/adminbook";
    }
    @RequestMapping(value = "/add-book")
    public String addBook(HttpSession session, Model model){
        AuthUser user=(AuthUser) session.getAttribute("user");
        if(user == null){
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            user=mapper.getPasswordByUsername(authentication.getName());
            session.setAttribute("user",user);
        }
        model.addAttribute("user",user);
//        service.addBook();
        return "addbook";
    }
    @RequestMapping(value = "/del-studentbook", method = RequestMethod.GET)
    public String deleteStudentBook(@RequestParam("bid") int bid) {
        studentbookservice.deleteStudentBook(bid);
        return "redirect:/index";
    }
    @RequestMapping(value = "/borrow-book")
    public String borrowbook(@RequestParam("sid")int sid,@RequestParam("bid")int bid,@RequestParam("bname")String bname,@RequestParam("sname")String sname,@RequestParam("states")String states){
        service.update(bid);
        states=service.getStatus(bid);
        studentbookservice.addStudentBook(sid,bid, bname, sname, states);
        return "redirect:/userbook";
    }
    @RequestMapping(value = "/Return-book")
    public String Returnbook(@RequestParam("bid")int bid){
        service.Return(bid);
        studentbookservice.deleteStudentBook(bid);
        return "redirect:/userbook";
    }
    @RequestMapping(value = "/adminReturn-book")
    public String AdminReturnbook(@RequestParam("bid")int bid){
        service.Return(bid);
        studentbookservice.deleteStudentBook(bid);
        return "redirect:/index";
    }
}
