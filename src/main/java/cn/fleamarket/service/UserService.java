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

    int addUser(User user) throws Exception;

    User qureyByUserName(String userName) throws Exception;

    User qureyByEmail(String email) throws Exception;

    int update(User user) throws Exception;

    User selectById(String id);

    List<User> selectByIds(Object[] id);
}

