package edu.ln.tour.dao;

import edu.ln.tour.TourServiceApp;
import edu.ln.tour.mapper.UserMapper;
import edu.ln.tour.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TourServiceApp.class)
class UserDaoTest {
    @Autowired
    UserDao userDao;
    @Autowired
    UserMapper userMapper;

    @Test
    public void findAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    //====== userMapper 集成mapper的测试
    @Test
    public void MapperFindAll(){
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    //====== 集成PageHelperr 的测试

}