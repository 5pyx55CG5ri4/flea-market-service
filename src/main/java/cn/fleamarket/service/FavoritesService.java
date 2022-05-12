package cn.fleamarket.service;

import cn.fleamarket.domain.Favorites;
import cn.fleamarket.domain.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 产品收藏表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
public interface FavoritesService{

    Page<Favorites>  selectListPage(Map<String,Object> user);

    Integer addFavorites(Favorites favorites);
    Integer deleteFavorites(String fid);
    List<Favorites> selectByUid(String uId);
}

