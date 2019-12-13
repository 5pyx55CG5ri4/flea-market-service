package cn.fleamarket.service.impl;

import cn.fleamarket.domain.Favorites;
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
    public Page selectListPage(Map<String, Object> map) {
        Long page = (Long) map.get("page");
        Long number = (Long) map.get("number");
        String id = (String) map.get("id");
        Page<Favorites> favoritesPage = new Page<Favorites>(page, number);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", id);
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
        favoritesServiceQueryWrapper.eq("user_id",uId);
        return favoritesMapper.selectList(favoritesServiceQueryWrapper);
    }


}