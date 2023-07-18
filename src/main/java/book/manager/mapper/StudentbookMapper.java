package book.manager.mapper;

import book.manager.entity.StudentBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentbookMapper {
    @Select("select * from student_book")
    List<StudentBook> getStudentBook();

    @Delete("delete from student_book where bid =#{bid}")
    void deleteStudentBook(int bid);

    @Insert("insert into student_book(sid,bid,bname,sname,states) value(#{sid},#{bid},#{bname},#{sname},#{states})")
    void addStudentBook(@Param("sid") int sid, @Param("bid")int bid, @Param("bname")String bname, @Param("sname")String sname,@Param("states")String states);
}
