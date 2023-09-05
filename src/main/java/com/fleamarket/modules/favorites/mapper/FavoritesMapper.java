package com.fleamarket.modules.favorites.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fleamarket.modules.favorites.domain.Favorites;
import com.fleamarket.modules.product.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收藏映射器
 *
 * @author zhuliyou
 * @date 2023/06/06
 */
public interface FavoritesMapper extends BaseMapper<Favorites> {
}
