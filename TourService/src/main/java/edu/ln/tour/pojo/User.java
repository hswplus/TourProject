package edu.ln.tour.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_user")
public class User {
    @Id
    Integer uid;

    @Column
    String username;
    @Column
    String password;
    @Column
    String name;
    @Column
    String birthday;
    @Column
    Integer sex;
    @Column
    String telephone;
    @Column
    String email;
    @Column
    String status;
    @Column
    String code;

}

