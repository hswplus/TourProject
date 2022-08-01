package edu.ln.tour.service.impl;

import edu.ln.tour.TourServiceApp;
import edu.ln.tour.dao.CategoryDao;
import edu.ln.tour.dao.UserDao;
import edu.ln.tour.pojo.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TourServiceApp.class)
class CategoryServiceImplTest {

    @Autowired
    CategoryDao categoryDao;
    @Test
    void findAllCategory() {
        List<Category> allCategory = categoryDao.findAllCategory();
        for (Category category : allCategory) {
            System.out.println(category);
        }
    }
}