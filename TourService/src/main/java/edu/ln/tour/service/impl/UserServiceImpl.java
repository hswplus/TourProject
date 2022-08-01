package edu.ln.tour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.ln.tour.dao.UserDao;
import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.User;
import edu.ln.tour.service.UserService;
import edu.ln.tour.utils.MailUtils;
import edu.ln.tour.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public Page<User> listByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<User> pageUsers =(Page<User> ) userDao.findAll();
        return pageUsers;
    }

    @Override
    public PageResultDto findByUserPageQueryString(int pageNo, int pageSize, String queryString) {
        // 分页处理
        PageHelper.startPage(pageNo, pageSize);
        // 使用通用Mapper的Example
        Example example = new Example(User.class);
        // 用example来构成查询条件
        example.createCriteria().andLike("username", "%"+queryString+"%");
        example.or().andLike("name", "%"+queryString+"%");
        example.or().andLike("telephone", "%"+queryString+"%");
        Page<User> userPage = (Page<User>) userDao.selectByExample(example);
        PageResultDto resultDto = new PageResultDto(pageNo,pageSize,(int) userPage.getTotal(),queryString,userPage.getResult());

        return resultDto;
    }

    @Override
    public PageResultDto findAllUserPage(int pageNo, int pageSize) {
        // 通过插件进行翻页
        PageHelper.startPage(pageNo, pageSize);

        Page<User> routePage = (Page<User>) userDao.findAll();
        PageResultDto resultDto = new PageResultDto();
        resultDto.setPageSize(pageSize);
        resultDto.setPageNo(pageNo);
        resultDto.setData(routePage.getResult());
        resultDto.setPageTotal((int) routePage.getTotal());
        return resultDto;
    }

    @Override
    public int insertUser(User user){
        // 日期内容截取
        user.setBirthday(user.getBirthday().substring(0, 10));
        int i=0;
        try {
            String newPassword = Md5Util.encodeByMd5(user.getPassword());
            user.setPassword(newPassword); // 将加密后的密码设置到user对象里面去
            i = userDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteUser(int uid) {
        return userDao.deleteByPrimaryKey(uid);
    }

    @Override
    public int updateUser(User user) {
        // 日期内容截取
        user.setBirthday(user.getBirthday().substring(0, 10));
        return userDao.updateByPrimaryKey(user);
    }

    @Override
    public int updateStatus(String status, int uid) {
        return userDao.updateStatus(status,uid);
    }

    // 根据用户名查询用户数据
    @Override
    public User checkUsernameExist(String username) {
        User user = userDao.selectOneByUsername(username);
        return user;
    }

    @Override
    public void registe(User user) {

        // 1. 校验用户名的唯一性 (校验是否已经被注册），必须做，异步请求返回来后？ TODO
        User checkUser = userDao.selectOneByUsername(user.getUsername());
        if (checkUser != null) {
            throw new RuntimeException();
        }

        // 2. 需要对密码进行加密操作
        try {
            String newPassword =  Md5Util.encodeByMd5(user.getPassword());
            user.setPassword(newPassword);// 将加密后的密码设置到user对象里面去
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 在保存用户之前需要涩会给你成一个UUID激活码，并且设置到用户里面
        String code = UUID.randomUUID().toString();
        user.setCode(code);
        // 设置用户激活 的状态，初始的情况下，用户的激活状态为N
        user.setStatus("N");

        // 3. 调用数据访问层的方法保存数据
        userDao.insert(user);

        // 4. 给用户发送激活邮件
        String content = "恭喜您注册成功，点击立即激活，激活账号。<a href='http://localhost:8080/tour/user/active?code="+code+"'>【立即激活】</a>";
        MailUtils.sendMail(user.getEmail(), content, "【融云旅游网】激活邮件");
    }

    @Override
    public User active(String code) {
        // 1. 根据激活码查询用户
        User user = userDao.selectByCode(code);
        if (user == null) {
            return null;
        }
        // 如果用户不为空，而且用户的状态是未激活，才需要去修改用户的状态
        if (user.getStatus().equals("N")) {
            userDao.updateStatus("Y",user.getUid());
        }
        return user;
    }

    @Override
    public User login(User user) {
        // 1. 需要对密码进行加密
        try {
            String newPassword =  Md5Util.encodeByMd5(user.getPassword());
            user.setPassword(newPassword);// 将加密后的密码设置到user对象里面去
        } catch (Exception e) {
            e.printStackTrace();
        }
        User loginUser = userDao.selectByUsernameAndPassword(user);
        return loginUser;
    }
}
