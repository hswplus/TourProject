package edu.ln.tour.dao;

import edu.ln.tour.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserDao extends tk.mybatis.mapper.common.Mapper<User> {
    public List<User> findAll();

    int updateStatus(String status, int uid);

    User selectOneByUsername(String username);

    User selectByCode(String code);

    User selectByUsernameAndPassword(User user);
}
