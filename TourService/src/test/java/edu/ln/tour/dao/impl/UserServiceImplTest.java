package edu.ln.tour.dao.impl;

import com.github.pagehelper.Page;
import edu.ln.tour.TourServiceApp;
import edu.ln.tour.pojo.User;
import edu.ln.tour.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TourServiceApp.class)
class UserServiceImplTest {
@Autowired
UserService userService;
    @Test
    void listByPage() {
        Page<User> users = (Page<User>) userService.listByPage(1, 5);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println(users);
    }
}