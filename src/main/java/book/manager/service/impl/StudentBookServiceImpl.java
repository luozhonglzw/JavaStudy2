package book.manager.service.impl;

import book.manager.entity.StudentBook;
import book.manager.mapper.StudentbookMapper;
import book.manager.service.StudentBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentBookServiceImpl implements StudentBookService {
    @Resource
    StudentbookMapper mapper;
    @Override
    public List<StudentBook> getStudentBook() {
        return mapper.getStudentBook();
    }

    @Override
    public void deleteStudentBook(int bid) {
        mapper.deleteStudentBook(bid);
    }

    @Override
    public void addStudentBook(int sid, int bid, String bname, String sname, String states) {
       mapper.addStudentBook(sid, bid, bname, sname, states);
    }
}
