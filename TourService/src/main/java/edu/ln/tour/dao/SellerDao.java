package edu.ln.tour.dao;

import edu.ln.tour.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerDao extends tk.mybatis.mapper.common.Mapper<Seller> {
    public int deleteSeller(int sid);
}
