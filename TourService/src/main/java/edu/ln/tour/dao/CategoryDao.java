package edu.ln.tour.dao;

import edu.ln.tour.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryDao extends tk.mybatis.mapper.common.Mapper<Category> {
    List<Category> findAllCategory();
    int insert(Category category);
    void insertCategoryAndRouteRelation(Map<String, Integer> map);

    void deleteCategoryAndRouteRelationsByCid(Integer cid);

    void insertCategroyAndRouteRelations(Map<String, Integer> map);

    void deleteByCid(Integer cid);
}
