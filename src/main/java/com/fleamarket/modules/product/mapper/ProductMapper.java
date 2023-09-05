package com.fleamarket.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fleamarket.modules.product.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品映射器
 *
 * @author zhuliyou
 * @date 2023/06/06
 */
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> myList(@Param("title") String title, @Param("userId") Long userId);

}
