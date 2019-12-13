package cn.fleamarket.service.impl;

import cn.fleamarket.domain.Product;
import cn.fleamarket.mapper.ProductMapper;
import cn.fleamarket.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public Page selectListPageByUser(Map<String, Object> map) {
        Long page = (Long) map.get("page");
        Long number = (Long) map.get("number");
        String userId = (String) map.get("userId");
        Page<Product> productPage = new Page<Product>(page, number);
        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("is_show", 1);
        queryWrapper.eq("is_del", 0);
        queryWrapper.eq("user_id",userId);
        productMapper.selectPage(productPage,queryWrapper);
        return productPage;
    }
    @Override
    public Page selectListsPageById(Map<String, Object> map) {
        Long page = (Long) map.get("page");
        Long number = (Long) map.get("number");
        Object[] ids = (Object[]) map.get("pId");
        Page<Product> productPage = new Page<Product>(page, number);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_show", 1);
        queryWrapper.eq("is_del", 0);
        queryWrapper.in("id",ids);
        productMapper.selectPage(productPage,queryWrapper);
        return productPage;
    }

    @Override
    public int insert(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int update(Product product) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", product.getId());
        return productMapper.update(product, updateWrapper);
    }

    @Override
    public int updateById(String dbId, boolean sta) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", dbId);
        Product product = new Product();
        if (sta) {
            product.setIsShow(0);
        } else {
            product.setIsShow(1);
        }
        product.setCreateTime(new Date());
        return productMapper.update(product, updateWrapper);
    }

    @Override
    public Product selectById(String dbId) {
        return productMapper.selectById(dbId);
    }

    @Override
    public int updateIncrease(Product product) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", product.getId());
        product.setCount(product.getCount()+1);
        return productMapper.update(product,updateWrapper);
    }

    @Override
    public int delete(String uid, String pId) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",pId);
        updateWrapper.eq("user_id",uid);
        Product product = new Product();
        product.setIsDel(1);
        product.setCreateTime(new Date());
        return productMapper.update(product,updateWrapper);
    }
}