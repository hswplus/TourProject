package edu.ln.tour.controller;

import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.dto.RespDto;
import edu.ln.tour.pojo.Category;
import edu.ln.tour.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("/findAllCategory")
    public List<Category> findAllCategory() {
       return categoryService.findAllCategory();
    }

    @RequestMapping("/findCategoryPage")
    public PageResultDto findCategoryPage(int pageNo,int pageSize) {
       return categoryService.findCategoryPage(pageNo,pageSize);
    }

    // 根据查询条件进行查询
    @PostMapping("/findCategoryPageByQueryString")
    public PageResultDto findByQueryStringPage(int pageNo, int pageSize, String queryString) {
        return categoryService.findCategoryPageByQueryString(pageNo, pageSize,queryString);
    }
    // 保存
    @PostMapping("/saveCategory")
    public RespDto saveCategory(@RequestBody Category category,Integer[] checkRouteIds){
        try {
            categoryService.add(category,checkRouteIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespDto(0, "添加失败");
        }
        return new RespDto(1, "添加成功");
    }
    // 保存
    @PostMapping("/editCategory")
    public RespDto editCategory(@RequestBody Category category,Integer[] checkRouteIds){
        try {
            categoryService.edit(category,checkRouteIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespDto(0, "修改失败");
        }
        return new RespDto(1, "修改成功");
    }


    @RequestMapping("/removeCategory")
    public RespDto removeCategory(Integer cid){
        try {
            categoryService.removeCategory(cid);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespDto(0, "处理失败");
        }
        return new RespDto(1, "处理成功");
    }
 }
