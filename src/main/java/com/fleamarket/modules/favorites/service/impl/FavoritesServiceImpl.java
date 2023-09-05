package com.fleamarket.modules.favorites.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fleamarket.modules.favorites.domain.Favorites;
import com.fleamarket.modules.favorites.mapper.FavoritesMapper;
import com.fleamarket.modules.favorites.service.FavoritesService;
import com.fleamarket.modules.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {

}
