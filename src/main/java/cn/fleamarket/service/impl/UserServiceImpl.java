package cn.fleamarket.service.impl;

import cn.fleamarket.domain.User;
import cn.fleamarket.mapper.UserMapper;
import cn.fleamarket.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author zining
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public int addUser(User user) throws Exception {
        return userMapper.insert(user);
    }

    @Override
    public User qureyByUserName(String userName) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User qureyByEmail(String email) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email",email);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public int update(User user) throws Exception {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("user_name",user.getUserName());
        return userMapper.update(user,updateWrapper);
    }
}