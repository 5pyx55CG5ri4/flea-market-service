package cn.fleamarket.service.impl;

import cn.fleamarket.domain.Favorites;
import cn.fleamarket.domain.User;
import cn.fleamarket.mapper.FavoritesMapper;
import cn.fleamarket.service.FavoritesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zining
 */
@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    FavoritesMapper favoritesMapper;

    @Override
    public Page<Favorites> selectListPage(User user) {
        Page<Favorites> favoritesPage = new Page<>(user.getPage(), user.getNumber());
        QueryWrapper<Favorites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        favoritesMapper.selectPage(favoritesPage, queryWrapper);
        return favoritesPage;
    }

    @Override
    public Integer addFavorites(Favorites favorites) {
        return favoritesMapper.insert(favorites);
    }

    @Override
    public Integer deleteFavorites(String fid) {
        return favoritesMapper.deleteById(fid);
    }

    @Override
    public List<Favorites> selectByUid(String uId) {
        QueryWrapper<Favorites> favoritesServiceQueryWrapper = new QueryWrapper<>();
        favoritesServiceQueryWrapper.eq("user_id", uId);
        return favoritesMapper.selectList(favoritesServiceQueryWrapper);
    }


}