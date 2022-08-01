package edu.ln.tour.controller;

import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.dto.RespDto;
import edu.ln.tour.pojo.Seller;
import edu.ln.tour.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @RequestMapping("/findAllSeller")
    public List<Seller> findAllSeller() {
         return sellerService.findAllSeller();
    }

    // 查询全部的旅游线路
    @RequestMapping("/findAllSellerPage")
    public PageResultDto findAllSellerPage(int pageNo, int pageSize) {
        return sellerService.findAllSellerPage(pageNo,pageSize);
    }
    // 根据查询条件进行查询
    @PostMapping("/findSellerPageByQueryString")
    public PageResultDto findSellerPageByQueryString(int pageNo, int pageSize, String queryString) {
      return sellerService.findSellerPageByQueryString(pageNo, pageSize,queryString);
        }

    // 保存
    @PostMapping("/saveSeller")
    public RespDto saveSeller(@RequestBody Seller seller) {
        int result = sellerService.saveSeller(seller);
        return new RespDto(result, result > 0 ? "1" : "0");
    }
  // 编辑
    @PostMapping("/editSeller")
    public RespDto editSeller(@RequestBody Seller seller) {
        int result = sellerService.editSeller(seller);
        return new RespDto(result, result > 0 ? "1" : "0");
    }
  // 删除
    @RequestMapping("/deleteSeller")
    public RespDto deleteSeller(int sid) {
        int result = sellerService.deleteSeller(sid);
        return new RespDto(result, result > 0 ? "1" : "0");
    }
}
