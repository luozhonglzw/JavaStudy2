package book.manager.service;

import book.manager.entity.StudentBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentBookService {
    List<StudentBook> getStudentBook();

    void deleteStudentBook(int bid);
    void addStudentBook(int sid, int bid, String bname, String sname, String states);
}
