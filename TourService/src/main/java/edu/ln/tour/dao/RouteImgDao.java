package edu.ln.tour.dao;

import edu.ln.tour.pojo.RouteImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RouteImgDao extends tk.mybatis.mapper.common.Mapper<RouteImg> {
    List<RouteImg> selectByRid(Integer rid);
}
