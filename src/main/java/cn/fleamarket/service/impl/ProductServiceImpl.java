package cn.fleamarket.service.impl;

import cn.fleamarket.domain.Product;
import cn.fleamarket.mapper.ProductMapper;
import cn.fleamarket.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author zining
 */
@Service("fProductService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public Page selectListPage(Map<String, Object> map) {
        Long page = (Long) map.get("page");
        Long number = (Long) map.get("number");
        String key = (String) map.get("key");
        Page<Product> productPage = new Page<Product>(page, number);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_show", 1);
        queryWrapper.eq("is_del", 0);
        if (key != null) {
            queryWrapper.like("title", key);
        }
        productMapper.selectPage(productPage, queryWrapper);
        return productPage;
    }
}