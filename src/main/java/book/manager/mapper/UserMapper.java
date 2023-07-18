package book.manager.mapper;

import book.manager.entity.AuthUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from buser where uname = #{username}")
    AuthUser getPasswordByUsername(String username);

    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into buser(uname,upassword) value(#{username},#{password})")
    int registerUser(AuthUser user);

    @Insert("insert into student(sname,uid,sex,grade) value(#{name},#{uid},#{sex},#{grade})")
    int addStudent(@Param("name") String name, @Param("uid")int uid,@Param("sex")String sex,@Param("grade")String grade);
}
