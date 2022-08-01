package edu.ln.tour.dao;

import edu.ln.tour.pojo.Favorite;
import edu.ln.tour.pojo.Route;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteDao extends tk.mybatis.mapper.common.Mapper<Favorite> {
    List<Route> selectMyFavoriteByUid(Integer uid);

    Favorite selectByRidAndUid(Integer rid, Integer uid);

    void deleteByRidAndUid(Favorite favorite);

    void addFavorite(Favorite favorite);
}
