package edu.ln.tour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.ln.tour.dao.SellerDao;
import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.Seller;
import edu.ln.tour.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerDao sellerDao;
    @Override
    public List<Seller> findAllSeller() {
        return sellerDao.selectAll();
    }

    // 根据查询条件进行查询
    @Override
    public PageResultDto findSellerPageByQueryString(int pageNo, int pageSize, String queryString) {
        // 分页处理
        PageHelper.startPage(pageNo, pageSize);
        // 使用通用Mapper的Example
        Example example = new Example(Seller.class);
        // 用example来构成查询条件
        example.createCriteria().andLike("sname", "%"+queryString+"%");
        example.or().andLike("consphone", "%"+queryString+"%");
        Page<Seller> routePage = (Page<Seller>) sellerDao.selectByExample(example);
        PageResultDto resultDto = new PageResultDto(pageNo,pageSize,(int) routePage.getTotal(),queryString,routePage.getResult());

        return resultDto;
    }
    // 保存
    @Override
    public int saveSeller(Seller seller) {
       return sellerDao.insert(seller);
    }

    // 编辑
    @Override
    public int editSeller(Seller seller) {
        return sellerDao.updateByPrimaryKey(seller);
    }

    // 删除
    @Override
    public int deleteSeller(int sid) {
        return sellerDao.deleteSeller(sid);
        
    }

    // 查询全部的旅游线路
    @Override
    public PageResultDto findAllSellerPage(int pageNo, int pageSize) {
        // 通过插件进行翻页
        PageHelper.startPage(pageNo, pageSize);

        Page<Seller> sellerPage = (Page<Seller>) sellerDao.selectAll();
        PageResultDto resultDto = new PageResultDto();
        resultDto.setPageSize(pageSize);
        resultDto.setPageNo(pageNo);
        resultDto.setData(sellerPage.getResult());
        resultDto.setPageTotal((int) sellerPage.getTotal());
        return resultDto;
    }


}
