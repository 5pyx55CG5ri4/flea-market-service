package cn.fleamarket.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.fleamarket.domain.Product;
import cn.fleamarket.service.ProductService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 商品表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@RestController
@RequestMapping("/product")
@Api("商品接口")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/productList", produces = "application/json")
    @ApiOperation("分页查询列表")
    public JSONObject productList(@RequestBody JSONObject jsonObject) {
        JSONObject ret = new JSONObject();
        try {
            Long page = jsonObject.getLong("page");
            Long number = jsonObject.getLong("number");
            String key = jsonObject.getString("key");
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("number", number);
            map.put("key",key);
            if (page != null && number != null) {
                Page<Product> productPage = productService.selectListPage(map);
                List<Product> productList = productPage.getRecords();
                ret.put("code", 0);
                ret.put("data", JSON.toJSONString(productList));
                ret.put("total", productPage.getTotal());//总数
                ret.put("next", productPage.hasNext());//下一页
                ret.put("previous", productPage.hasPrevious());//上一页
                ret.put("msg", "查询成功");
            }
        } catch (Exception e) {
            ret.put("code", 0);
            ret.put("data", null);
            ret.put("msg", "查询失败");
        }
        return ret;
    }

    @PutMapping("/addProduct")
    public JSONObject addProduct(Product product){
         return null;
    }
}
