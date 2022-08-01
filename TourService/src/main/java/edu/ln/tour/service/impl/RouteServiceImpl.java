package edu.ln.tour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.ln.tour.dao.RouteDao;
import edu.ln.tour.dao.RouteImgDao;
import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.Route;
import edu.ln.tour.pojo.RouteImg;
import edu.ln.tour.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteDao routeDao;
    @Autowired
    RouteImgDao routeImgDao;



    @Override
    public List<Route> findAll() {
        return routeDao.findAll();
    }

    @Override
    public List<Route> findAllWithSeller() {
        return routeDao.findAllWithSeller();
    }


    @Override
    public Route selectByIdWithSeller(Integer rid) {
        return routeDao.selectByIdWithSeller(rid);
    }

    @Override
    public int saveRoute(Route route) {
        // 日期内容截取
        route.setRdate(route.getRdate().substring(0, 10));
        return routeDao.insert(route);
    }

    // 根据cid查找产品线路
    @Override
    public List<Route> findRouteByCid(int cid) {
        return routeDao.findRouteByCid(cid);
    }

    @Override
    public PageResultDto findAllPage(int pageNo, int pageSize) {
        // 通过插件进行翻页
        PageHelper.startPage(pageNo, pageSize);
        Page<Route> routePage = (Page<Route>) routeDao.findAll();
        PageResultDto resultDto = new PageResultDto();
        resultDto.setPageSize(pageSize);
        resultDto.setPageNo(pageNo);
        resultDto.setData(routePage.getResult());
        resultDto.setPageTotal((int) routePage.getTotal());
        return resultDto;
    }

    // 根据查询条件进行查询
    @Override
    public PageResultDto findByQueryStringPage(int pageNo, int pageSize, String queryString) {
        // 分页处理
        PageHelper.startPage(pageNo, pageSize);
        // 使用通用Mapper的Example
        Example example = new Example(Route.class);
        // 用example来构成查询条件
        example.createCriteria().andLike("rname", "%"+queryString+"%");
        Page<Route> routePage = (Page<Route>) routeDao.selectByExample(example);
        PageResultDto resultDto = new PageResultDto(pageNo,pageSize,(int) routePage.getTotal(),queryString,routePage.getResult());
        return resultDto;
    }

    // 删除旅游产品线路
    @Override
    public void removeRouteByRid(Integer rid) {
        routeDao.removeRouteAndFavoriteRelationsByRid(rid);
        routeDao.removeRouteAndRouteImgRelationsByRid(rid);
        routeDao.removeRouteAndCategoryRouteRelationsByRid(rid);
        routeDao.removeRouteByRid(rid);
    }

    // 更新旅游产品线路
    @Override
    public int updateRoute(Route route) {
        // 日期内容截取
        route.setRdate(route.getRdate().substring(0, 10));
        return routeDao.updateByPrimaryKey(route);
    }

    //修改是否上架
    @Override
    public int changeStatus(String rflag, int rid) {
        return routeDao.changeStatus(rflag,rid);
    }

    @Override
    public List<Route> queryHot(Integer count) {
        return routeDao.selectByCountDescLimit(count);
    }

    @Override
    public Route queryRouteAndSeller(Integer rid) {
        return routeDao.selectRouteAndSellerByRid(rid);
    }

    @Override
    public List<RouteImg> queryRouteImages(Integer rid) {
        return routeImgDao.selectByRid(rid);
    }

    @Override
    public PageResultDto<Route> queryPage(Integer cid, Integer currentPage, String queryString) {
        // 1. 分页使用的是Mybatis的分页插件，pageHelper
        // 2. 开启分页
        int pageSize = 10;
        /**
         * 开启分页方法需要两个参数：
         * pageNum:当前页码 及时currentPage
         * pageSize: 每页记录数 pageSize
         */
        PageHelper.startPage(currentPage, pageSize);
        // 调用数据访问层查询数据
        // 组装模糊查询的参数
        queryString = "%" + queryString +"%";
        Page<Route> pages = routeDao.selectPageByCid(cid,queryString);
        // 4. 构建返回对象
        return new PageResultDto<Route>(currentPage,pageSize,(int)pages.getTotal(),queryString,pages.getResult());
    }
}
