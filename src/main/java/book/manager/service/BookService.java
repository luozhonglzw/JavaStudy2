package book.manager.service;

import book.manager.entity.AuthBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService {
    List<AuthBook> getBook();
    void deleteBook(int bid);

    void addBook(String bname, int bid, String introduce, int Price);

    void update(int bid);

    void Return(int id);

    List<AuthBook> getStudentBooks(int sid);

    String getStatus(int bid);
}