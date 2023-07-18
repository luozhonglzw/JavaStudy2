package book.manager.mapper;

import book.manager.entity.AuthBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    List<AuthBook> getBook();
    @Delete("delete from book where bid =#{bid}")
    void deleteBook(int bid);

    @Insert("insert into book(bname,bid,introduce,Price) value(#{bname},#{bid},#{introduce},#{Price})")
    void addBook(@Param("bname") String bname, @Param("bid")int bid, @Param("introduce")String introduce, @Param("Price")int Price);

    @Update("update book set status ='已借' where bid=#{bid}")
    void update(int bid);
    @Update("update book set status ='未借' where bid=#{bid}")
    void Return(int bid);

    @Select("select book.bid,book.bname,introduce,Price,status from book inner join student_book  on student_book.bid = book.bid where student_book.sid=#{sid};")
    List<AuthBook> getStudentBooks(int sid);

    @Select("select status from book where bid=#{bid}")
    String getStatus(int bid);
}
