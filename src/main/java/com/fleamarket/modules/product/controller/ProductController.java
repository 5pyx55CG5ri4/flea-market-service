package com.fleamarket.modules.product.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.fleamarket.code.controller.BaseController;
import com.fleamarket.code.domain.R;
import com.fleamarket.code.page.TableDataInfo;
import com.fleamarket.common.auth.UserHolder;
import com.fleamarket.modules.product.domain.Product;
import com.fleamarket.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品控制器
 *
 * @author zhuliyou
 * @date 2023/06/06
 */
@RestController
@RequestMapping("product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    /**
     * 列表
     *
     * @param product 产品
     * @return {@link TableDataInfo}<{@link Product}>
     */
    @GetMapping("list")
    public TableDataInfo<Product> list(Product product) {
        startPage();
        LambdaQueryChainWrapper<Product> productLambdaQueryChainWrapper = productService.lambdaQuery();
        List<Product> products = productLambdaQueryChainWrapper.eq(Product::getState, BigDecimal.ONE.toString()).like(StrUtil.isNotBlank(product.getTitle()), Product::getTitle, product.getTitle()).orderByDesc(Product::getCreateTime).list();
        return getDataTable(products);
    }


    /**
     * 添加
     *
     * @param product 产品
     * @return {@link R}
     */
    @PostMapping
    public R add(@RequestBody Product product) {
        product.setCreateBy(UserHolder.getUserId());
        productService.save(product);
        return R.success();
    }


    /**
     * 编辑
     *
     * @param product 产品
     * @return {@link R}
     */
    @PutMapping
    public R editor(@RequestBody Product product) {
        product.setCreateBy(UserHolder.getUserId());
        productService.updateById(product);
        return R.success();
    }


    /**
     * 删除
     *
     * @param id 编号
     * @return {@link R}
     */
    @DeleteMapping("{id}")
    public R delete(@PathVariable(value = "id") Long id) {
        productService.removeById(id);
        return R.success();
    }

}
