package edu.ln.tour.service;

import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.Seller;

import java.util.List;

public interface SellerService {
    List<Seller> findAllSeller();
    // 查询全部的旅游线路
    PageResultDto findAllSellerPage(int pageNo, int pageSize);
// 根据查询条件进行查询
    PageResultDto findSellerPageByQueryString(int pageNo, int pageSize, String queryString);
    // 保存
    int saveSeller(Seller seller);

    // 编辑
    int editSeller(Seller seller);

    // 删除
    int deleteSeller(int sid);
}
