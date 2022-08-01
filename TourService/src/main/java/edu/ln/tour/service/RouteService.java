package edu.ln.tour.service;

import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.Route;
import edu.ln.tour.pojo.RouteImg;

import java.util.List;

public interface RouteService {
    public List<Route> findAll();
    public List<Route> findAllWithSeller();
    public Route selectByIdWithSeller(Integer rid);

    int saveRoute(Route route);// 保存Route

    // 根据cid查找产品线路
    List<Route> findRouteByCid(int cid);

    PageResultDto findAllPage(int pageNo, int pageSize);

    PageResultDto findByQueryStringPage(int pageNo, int pageSize, String queryString);

    void removeRouteByRid(Integer rid);

    int updateRoute(Route route);
    //修改是否上架
    int changeStatus(String status, int rid);

    List<Route> queryHot(Integer count);

    Route queryRouteAndSeller(Integer rid);

    List<RouteImg> queryRouteImages(Integer rid);

    PageResultDto<Route> queryPage(Integer cid, Integer currentPage, String queryString);
}

