package edu.ln.tour.service;

import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();
    void add(Category category, Integer[] checkRouteIds);
    void edit(Category category, Integer[] checkRouteIds);

    PageResultDto findCategoryPage(int pageNo, int pageSize);


    void removeCategory(Integer cid);
    // 根据查询条件进行查询
    PageResultDto findCategoryPageByQueryString(int pageNo, int pageSize, String queryString);
}
