package edu.ln.tour.service.impl;

import edu.ln.tour.TourServiceApp;
import edu.ln.tour.dao.SellerDao;
import edu.ln.tour.pojo.Seller;
import edu.ln.tour.service.SellerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TourServiceApp.class)
class SelllerServiceImplTest {
    @Autowired
    SellerService sellerService;
    @Test
    void findAllSeller() {
        List<Seller> allSeller = sellerService.findAllSeller();
        System.out.println(allSeller);
    }
}