package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentBook {
    int sid;
    int bid;
    String bname;
    String sname;
    String states;
}
