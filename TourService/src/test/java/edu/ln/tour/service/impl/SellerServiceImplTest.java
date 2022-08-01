package edu.ln.tour.service.impl;

import edu.ln.tour.TourServiceApp;
import edu.ln.tour.pojo.Seller;
import edu.ln.tour.service.SellerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TourServiceApp.class)
class SellerServiceImplTest {

    @Autowired
    SellerService sellerService;
    @Test
    void saveSeller() {
        int i = sellerService.saveSeller(new Seller(null, "火星研究院", "19124839231", "外太空火星研究"));
        System.out.println(i);
    }
}