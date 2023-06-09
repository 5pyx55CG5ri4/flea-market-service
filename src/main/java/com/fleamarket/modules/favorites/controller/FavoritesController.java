package com.fleamarket.modules.favorites.controller;

import com.fleamarket.code.controller.BaseController;
import com.fleamarket.code.domain.R;
import com.fleamarket.code.page.TableDataInfo;
import com.fleamarket.common.auth.UserHolder;
import com.fleamarket.modules.favorites.domain.Favorites;
import com.fleamarket.modules.favorites.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏控制器
 *
 * @author zhuliyou
 * @date 2023/06/06
 */
@RestController
@RequestMapping("favorites")
public class FavoritesController extends BaseController {

    @Autowired
    private FavoritesService favoritesService;

    /**
     * 列表
     *
     * @param favorites 最喜欢
     * @return {@link TableDataInfo}<{@link Favorites}>
     */
    @GetMapping("list")
    public TableDataInfo<Favorites> list(Favorites favorites) {
        startPage();
        return getDataTable(favoritesService.lambdaQuery().list());
    }


    /**
     * 添加
     *
     * @param favorites 最喜欢
     * @return {@link R}
     */
    @PostMapping
    public R add(@RequestBody Favorites favorites) {
        favorites.setUserId(UserHolder.getUserId());
        favoritesService.save(favorites);
        return R.success();
    }


    /**
     * 编辑
     *
     * @param favorites 最喜欢
     * @return {@link R}
     */
    @PutMapping
    public R editor(@RequestBody Favorites favorites) {
        favorites.setUserId(UserHolder.getUserId());
        favoritesService.updateById(favorites);
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
        favoritesService.removeById(id);
        return R.success();
    }

}
