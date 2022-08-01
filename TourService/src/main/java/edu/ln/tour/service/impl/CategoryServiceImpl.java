package edu.ln.tour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.ln.tour.dao.CategoryDao;
import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.Category;
import edu.ln.tour.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDao categoryDao;
    @Override
    public List<Category> findAllCategory() {
        return categoryDao.findAllCategory();
    }

    @Override
    public PageResultDto findCategoryPage(int pageNo, int pageSize) {
        // 通过插件进行翻页
        PageHelper.startPage(pageNo, pageSize);
        Page<Category> categoryList = (Page<Category>) categoryDao.findAllCategory();
        PageResultDto resultDto = new PageResultDto();
        resultDto.setPageSize(pageSize);
        resultDto.setPageNo(pageNo);
        resultDto.setData(categoryList.getResult());
        resultDto.setPageTotal((int) categoryList.getTotal());
        return resultDto;

    }

    @Override
    public void removeCategory(Integer cid) {
        categoryDao.deleteCategoryAndRouteRelationsByCid(cid);
        categoryDao.deleteByCid(cid);
    }
    // 根据查询条件进行查询
    @Override
    public PageResultDto findCategoryPageByQueryString(int pageNo, int pageSize, String queryString) {
        // 分页处理
        PageHelper.startPage(pageNo, pageSize);
        // 使用通用Mapper的Example
        Example example = new Example(Category.class);
        // 用example来构成查询条件
        example.createCriteria().andLike("cname", "%"+queryString+"%");
        Page<Category> routePage = (Page<Category>) categoryDao.selectByExample(example);

        PageResultDto resultDto = new PageResultDto(pageNo,pageSize,(int) routePage.getTotal(),queryString,routePage.getResult());
        return resultDto;
    }

    @Override
    public void add(Category category, Integer[] checkRouteIds) {

        //存入数据库
        //这里在插入一条新的记录的同时，并把数据库中自动生成的分类ID自动封装在category对象中，请看mapper.xml文件的操作
        categoryDao.insert(category);

        if(checkRouteIds != null && checkRouteIds.length > 0){
            for (Integer rid: checkRouteIds){
                Map<String,Integer> map= new HashMap<String,Integer>();
                map.put("rid",rid);
                map.put("cid",category.getCid());
                categoryDao.insertCategoryAndRouteRelation(map);
            }
        }
     }
    @Override
    public void edit(Category category, Integer[] checkRouteIds) {

        categoryDao.updateByPrimaryKey(category);

        categoryDao.deleteCategoryAndRouteRelationsByCid(category.getCid());

        for(int rid :checkRouteIds){
            Map<String,Integer> map =new HashMap<String,Integer>();
            map.put("rid",rid);
            map.put("cid",category.getCid());
            categoryDao.insertCategroyAndRouteRelations(map);
        }

    }



}
