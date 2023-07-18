package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthBook {
    int bid;
    String bname;
    String introduce;
    String Price;
    String status;
//    StudentBook studentBook;
}
