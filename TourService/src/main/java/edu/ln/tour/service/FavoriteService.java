package edu.ln.tour.service;

import edu.ln.tour.pojo.Favorite;
import edu.ln.tour.pojo.Route;

import java.util.List;

public interface FavoriteService {
    List<Route> selectMyFavoriteByUid(Integer uid);

    Favorite queryFavorite(Integer rid, Integer uid);

    void addFavorite(Integer rid, Integer uid);

    void subFavorite(Integer rid, Integer uid);
}
