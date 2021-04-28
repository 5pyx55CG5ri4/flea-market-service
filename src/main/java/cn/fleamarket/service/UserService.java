package cn.fleamarket.service;


import cn.fleamarket.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
public interface UserService {

    int addUser(User user);

    User qureyByUserName(String userName);

    User qureyByEmail(String email);

    int update(User user);

    User selectById(String id);

    List<User> selectByIds(Object[] id);
}

