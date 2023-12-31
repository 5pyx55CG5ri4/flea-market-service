package com.fleamarket.modules.product.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fleamarket.modules.product.domain.Product;
import com.fleamarket.modules.product.mapper.ProductMapper;
import com.fleamarket.modules.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Override
    public List<Product> myList(String title, Long userId) {
        return baseMapper.myList(title,userId);
    }
}
