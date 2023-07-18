package book.manager.service.impl;

import book.manager.entity.AuthBook;
import book.manager.mapper.BookMapper;
import book.manager.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;
    public List<AuthBook> getBook(){
        return mapper.getBook();
    }

    @Override
    public void deleteBook(int bid) {
        mapper.deleteBook(bid);
    }

    @Override
    public void addBook(String bname, int bid, String introduce, int Price) {
        mapper.addBook(bname, bid, introduce, Price);
    }

    @Override
    public void update(int bid) {
        mapper.update(bid);
    }

    @Override
    public void Return(int bid) {
        mapper.Return(bid);
    }

    @Override
    public List<AuthBook> getStudentBooks(int sid) {
        return mapper.getStudentBooks(sid);
    }

    @Override
    public String getStatus(int bid) {
        return mapper.getStatus(bid);
    }
}
