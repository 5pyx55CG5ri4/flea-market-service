package cn.fleamarket.controller;

import java.util.*;


import cn.fleamarket.domain.Favorites;
import cn.fleamarket.domain.User;
import cn.fleamarket.service.FavoritesService;
import cn.fleamarket.utils.StringTool;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 产品收藏表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @PostMapping(value = "/favoriteList" , produces = "application/json")
    @ApiOperation("收藏分页查询列表")
    public JSONObject favoritesList(@RequestBody JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();
        User user = (User)request.getSession().getAttribute("user");
        try {
            if (user != null){
                Long page = jsonObject.getLong("page");
                Long number = jsonObject.getLong("number");
                Map<String, Object> map = new HashMap<>();
                map.put("page", page);
                map.put("number", number);
                map.put("id",user.getId());
                if (page != null && number != null) {
                    Page<Favorites> favoritesPage = favoritesService.selectListPage(map);
                    List<Favorites> favoritesList = favoritesPage.getRecords();
                    ret.put("code", 0);
                    ret.put("data", JSON.toJSONString(favoritesList));
                    ret.put("total", favoritesPage.getTotal());//总数
                    ret.put("next", favoritesPage.hasNext());//下一页
                    ret.put("previous", favoritesPage.hasPrevious());//上一页
                    ret.put("msg", "查询成功");
                }
            }else {
                ret.put("msg","用户未登录");
            }

        }catch (Exception e){
            ret.put("code", 0);
            ret.put("data", null);
            ret.put("msg", "查询失败");
        }
        return ret;
    }

    @PostMapping(value = "/addFavorites", produces = "application/json" )
    @ApiOperation("添加收藏")
    public JSONObject addFavorites(@RequestBody JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();
        User user = (User)request.getSession().getAttribute("user");
        try {
            if (user != null){
                String productId = jsonObject.getString("pid");
                Integer state = jsonObject.getInteger("state");
                Favorites favorites = new Favorites();
                favorites.setId(StringTool.getUUID());
                favorites.setUserId(user.getId());
                favorites.setProductId(productId);
                favorites.setCreateTime(new Date());
                favorites.setState(state);
                Integer isS = favoritesService.addFavorites(favorites);
                if (isS > 0) {
                    ret.put("data", true);
                    ret.put("code", 0);
                    ret.put("msg", "添加成功");
                } else {
                    ret.put("code", -1);
                    ret.put("data", false);
                    ret.put("msg", "注册失败");
                }
            }else {
                ret.put("msg","用户未登录");
            }

        }catch (Exception e){
            e.printStackTrace();
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "未知错误");
        }
        return ret;
    }

    @DeleteMapping(value = "/deleteFavorites",produces = "application/json")
    @ApiOperation("删除收藏")
    public JSONObject deleteFavorites(@RequestBody JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response){
        JSONObject ret = new JSONObject();
        User user = (User)request.getSession().getAttribute("user");
        try {
            if (user != null) {
                String fid = jsonObject.getString("fid");
                Integer isS = favoritesService.deleteFavorites(fid);
            }else {
                ret.put("msg","用户未登录");
            }
        }catch (Exception e){
            e.printStackTrace();
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "删除失败");
        }
        return ret;
    }


}
