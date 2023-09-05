package com.fleamarket.modules.product.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.fleamarket.code.controller.BaseController;
import com.fleamarket.code.domain.R;
import com.fleamarket.code.page.TableDataInfo;
import com.fleamarket.common.annotation.UnAuth;
import com.fleamarket.common.auth.UserHolder;
import com.fleamarket.modules.product.domain.Product;
import com.fleamarket.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    @UnAuth
    public TableDataInfo<Product> list(Product product) {
        startPage();
        LambdaQueryChainWrapper<Product> productLambdaQueryChainWrapper = productService.lambdaQuery();
        List<Product> products = productLambdaQueryChainWrapper.eq(StrUtil.isNotBlank(product.getState()), Product::getState, product.getState())
                .like(StrUtil.isNotBlank(product.getTitle()), Product::getTitle, product.getTitle())
                .eq(Objects.nonNull(product.getCreateBy()), Product::getCreateBy, product.getCreateBy())
                .orderByDesc(Product::getCreateTime).list();
        return getDataTable(products);
    }

    @GetMapping("info/{id}")
    @UnAuth
    public R info(@PathVariable Long id) {
        return R.success(productService.getById(id));
    }


    @GetMapping("myList")
    public TableDataInfo<Product> myList(String title) {
        startPage();
        Long userId = UserHolder.getUserId();
        return getDataTable(productService.myList(title,userId));
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


    @GetMapping("addUpTheNumberOfViews/{id}")
    @UnAuth
    public R addUpTheNumberOfViews(@PathVariable Long id) {
        Product p = productService.getById(id);
        p.setViewed(p.getViewed() + 1);
        productService.updateById(p);
        return R.success();
    }


    @GetMapping("addCollectionTimes/{id}")
    public R addCollectionTimes(@PathVariable Long id) {
        Product p = productService.getById(id);
        p.setCollectionCount(p.getCollectionCount() + 1);
        productService.updateById(p);
        return R.success();
    }

}
