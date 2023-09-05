package com.fleamarket.modules.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fleamarket.modules.product.domain.Product;

import java.util.List;

/**
 * 商品服务
 *
 * @author zhuliyou
 * @date 2023/06/06
 */
public interface ProductService extends IService<Product> {
    List<Product> myList(String title, Long userId);
}
