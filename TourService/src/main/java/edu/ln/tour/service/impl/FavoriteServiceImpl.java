package edu.ln.tour.service.impl;

import edu.ln.tour.dao.FavoriteDao;
import edu.ln.tour.dao.RouteDao;
import edu.ln.tour.pojo.Favorite;
import edu.ln.tour.pojo.Route;
import edu.ln.tour.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteDao favoriteDao;
    @Autowired
    RouteDao routeDao;
    @Override
    public List<Route> selectMyFavoriteByUid(Integer uid) {
        List<Route> routeList = favoriteDao.selectMyFavoriteByUid(uid);
        return routeList;
    }

    @Override
    public Favorite queryFavorite(Integer rid, Integer uid) {
        // 1. 直接把查询的数据返回
        return favoriteDao.selectByRidAndUid(rid,uid);
    }

    @Override
    public void addFavorite(Integer rid, Integer uid) {
// 1.往tab_favorite表中添加一条记录
        // 构建一个Favorite对象
        Favorite favorite = new Favorite();
        favorite.setRid(rid);
        favorite.setUid(uid);
        // 需要对日期进行格式化yyyy-MM-dd HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataStr = format.format(new Date());
        favorite.setDate(dataStr);
        favoriteDao.addFavorite(favorite);
        // 2.更新tab_route表中该产品的收藏记录
        routeDao.updateCountAddByRid(rid);
    }

    @Override
    public void subFavorite(Integer rid, Integer uid) {
        // 1.往tab_favorite表中删除一条记录
        // 构建一个Favorite对象
        Favorite favorite = new Favorite();
        favorite.setRid(rid);
        favorite.setUid(uid);
        favoriteDao.deleteByRidAndUid(favorite);
        // 2.更新tab_route表中该产品的收藏记录
        routeDao.updateCountSubByRid(rid);
    }
}
