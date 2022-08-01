package edu.ln.tour.service.impl;

import com.github.pagehelper.Page;
import edu.ln.tour.TourServiceApp;
import edu.ln.tour.dao.RouteDao;
import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.Route;
import edu.ln.tour.service.RouteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TourServiceApp.class)
class RouteServiceImplTest {

    @Autowired
    RouteService routeService;
    @Autowired
    RouteDao routeDao;
    @Test
    void findAll() {
        List<Route> routes = routeService.findAll();
        for (Route route : routes) {
            System.out.println(route);
        }
    }

    // 测试翻页
    @Test
    void findAllPage() {

        PageResultDto allPage = routeService.findAllPage(2, 3);
        System.out.println(allPage);
    }

    // 测试保存Route
    @Test
    void saveRoute() {

        Route route = new Route();
        route.setRname("金沙湾豪华游");
        route.setPrice(19800.0);
        route.setRflag("1");
        int result = routeService.saveRoute(route);
        System.out.println(result);

    }

    @Test
    public void findRouteByCid() {
        int cid = 5;
        List<Route> routeByCid = routeService.findRouteByCid(cid);
        System.out.println(routeByCid);
    }

}