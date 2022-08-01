package edu.ln.tour.service;

import com.github.pagehelper.Page;
import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.User;

public interface UserService {
    public Page<User> listByPage(int pageNo, int pageSize);

    PageResultDto findByUserPageQueryString(int pageNo, int pageSize, String queryString);

    PageResultDto findAllUserPage(int pageNo, int pageSize);

    int insertUser(User user);

    int deleteUser(int uid);

    int updateUser(User user);

    int updateStatus(String status, int uid);

    // 根据用户名查询用户数据
    User checkUsernameExist(String username);

    // 处理用户注册业务
    void registe(User user);

    User active(String code);

    User login(User user);
}
