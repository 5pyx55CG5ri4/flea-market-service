package com.fleamarket.modules.favorites.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fleamarket.modules.favorites.domain.Favorites;
import com.fleamarket.modules.favorites.mapper.FavoritesMapper;
import com.fleamarket.modules.favorites.service.FavoritesService;
import org.springframework.stereotype.Service;

@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {
}
