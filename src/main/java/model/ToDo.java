package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {

    private long id;
    private String title;
    private Date deadline;
    private ToDoStatus status;
//    private User user;
    private int userId;
    private Date createdDate;

}
