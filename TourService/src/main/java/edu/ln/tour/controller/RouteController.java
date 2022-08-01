package edu.ln.tour.controller;

import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.dto.RespDto;
import edu.ln.tour.pojo.Route;
import edu.ln.tour.pojo.RouteImg;
import edu.ln.tour.pojo.User;
//import edu.ln.tour.service.FavoriteService;
import edu.ln.tour.service.FavoriteService;
import edu.ln.tour.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/route")
@CrossOrigin // 允许跨域访问
public class RouteController {
    // 注入 service
    @Autowired
    RouteService routeService;
    @Autowired
    FavoriteService favoriteService;
//    @Autowired
//    private FavoriteService favoriteService;
    @RequestMapping("/queryPage")
    public PageResultDto<Route> queryPage(Integer cid, Integer currentPage,String queryString) {
        // 1. 对获取的参数进行非空处理 cid为null ，说明没有cid，那么应该查询所有分类下面所有的产品
        if (cid == null) {
            cid = 0;
        }
        if (currentPage == null) {
            currentPage = 1;
        }
        // 2. 调用业务逻辑层的分页查询方法
        PageResultDto<Route> routePage = routeService.queryPage(cid, currentPage,queryString);
        return routePage;
    }

    @RequestMapping("/queryHot")
    public RespDto queryHot(@RequestParam(defaultValue = "5") Integer count) {
        // 1. 对数据进行非空处理 这里使用springMVC提供的注解来实现
        // 2. 调用业务逻辑层查询数据
        List<Route> routeList = routeService.queryHot(count);
        // 3. 响应数据
        return new RespDto(1, "", routeList);
    }

    @RequestMapping("/queryCount")
    public RespDto queryCount(@RequestParam(defaultValue = "8") Integer count) {
        List<Route> routeList = routeService.queryHot(count);
        return new RespDto(1, "", routeList);
    }

    @RequestMapping("/queryRouteAndSeller")
    public RespDto queryRouteAndSeller(Integer rid) {
        // 1. 调用业务逻辑层查询数据
        Route route = routeService.queryRouteAndSeller(rid);
        return new RespDto(1, "", route);
    }

    @RequestMapping("/queryRouteImages")
    public RespDto queryRouteImages(Integer rid) {
        List<RouteImg> routeImgList = routeService.queryRouteImages(rid);
        return new RespDto(1,"",routeImgList);
    }

    @RequestMapping("/queryMyFavorite")
    public RespDto queryMyFavorite(HttpSession session) {
        // 1.1 从session对象中获取登录的用户
        User user = (User) session.getAttribute("loginUser");
        // 1.2 判断用户是否登录
        if (user == null) {
            // 说明户没有登录
            return new RespDto(0, "");
        }
        // 2. 调用业务逻辑层查询数据
        List<Route> routeList = favoriteService.selectMyFavoriteByUid(user.getUid());
        return new RespDto(1, "", routeList);
    }







    // 查询全部的旅游线路
    @RequestMapping("/findAll")
    public List<Route> findAll() {
//        return routeService.findAllWithSeller();
        return routeService.findAllWithSeller();
    }

    // 查询全部的旅游线路
    @RequestMapping("/findAllPage")
    public PageResultDto findAllPage(int pageNo, int pageSize) {
        return routeService.findAllPage(pageNo, pageSize);
    }

    // 根据查询条件进行查询
    @PostMapping("/findByQueryStringPage")
    public PageResultDto findByQueryStringPage(int pageNo, int pageSize, String queryString) {
        return routeService.findByQueryStringPage(pageNo, pageSize,queryString);
    }
    // 保存
    @PostMapping("/saveRoute")
    public RespDto saveRoute(@RequestBody Route route) {
        int res = routeService.saveRoute(route);
        return new RespDto(1, res > 0 ? "处理成功" : "处理失败");
    }

    // 根据cid查找产品线路
    @RequestMapping("/findRouteByCid")
    public List<Route> findRouteByCid(int cid) {
        return routeService.findRouteByCid(cid);
    }

    // 修改
    @PostMapping("/updateRoute")
    public RespDto updateRoute(@RequestBody Route route){
        int updateRet = routeService.updateRoute(route);
        return new RespDto(updateRet,updateRet>0?"处理成功":"处理失败");
    }


    // lyw删除
    @RequestMapping("/removeRouteByRid")
    public RespDto removeRouteByRid(Integer rid){
        try {
            routeService.removeRouteByRid(rid);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespDto(0, "处理失败");
        }
        return new RespDto(1, "处理成功");
    }

    //修改是否上架
    @RequestMapping("/changeStatus")
    public int changeStatus(String rflag,int rid) {
        return routeService.changeStatus(rflag,rid);

    }

}
