package cn.fleamarket.controller;

import java.util.*;


import cn.fleamarket.domain.Product;
import cn.fleamarket.domain.User;
import cn.fleamarket.service.MessageService;
import cn.fleamarket.service.ProductService;
import cn.fleamarket.service.UserService;
import cn.fleamarket.utils.StringTool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;

    @PostMapping(value = "/productList", produces = "application/json")
    @ApiOperation("分页查询列表,入参是page:第几页,number:每页几条，key:查询条件(可选)")
    public JSONObject productList(@RequestBody JSONObject jsonObject) {
        JSONObject ret = new JSONObject();
        try {
            Long page = jsonObject.getLong("page");
            Long number = jsonObject.getLong("number");
            String key = jsonObject.getString("key");
            JSONArray jsonArray = jsonObject.getJSONArray("pId");
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("number", number);
            map.put("key", key);
            if (page != null && number != null) {
                Page<Product> productPage = productService.selectListPage(map);
                List<Product> productList = productPage.getRecords();
                ret.put("code", 0);
                ret.put("data", StringTool.ListToJsonArray(productList));
                ret.put("total", productPage.getTotal());//总数
                ret.put("next", productPage.hasNext());//下一页
                ret.put("previous", productPage.hasPrevious());//上一页
                ret.put("msg", "查询成功");
                return ret;
            }
            if (jsonArray.size() != 0 && jsonArray != null) {
                List<Product> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    Product product = productService.selectById(jsonArray.get(i).toString());
                    list.add(product);
                }
                ret.put("code", 0);
                ret.put("data", StringTool.ListToJsonArray(list));
                ret.put("msg", "查询成功");
                return ret;
            }
        } catch (Exception e) {
            ret.put("code", -1);
            ret.put("data", null);
            ret.put("msg", "查询失败");
        }
        return ret;
    }

    @PostMapping(value = "/productListByUser", produces = "application/json")
    @ApiOperation("分页查询属于某个用户的商品列表,就是我发布的商品,入参是page:第几页,number:每页几条")
    public JSONObject productListByUser(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        try {
            Long page = jsonObject.getLong("page");
            Long number = jsonObject.getLong("number");
            Map<String, Object> map = new HashMap<>();
            User user = userService.qureyByUserName(jsonObject.getString("username"));
            map.put("page", page);
            map.put("number", number);
            map.put("userId", user.getId());
            if (page != null && number != null) {
                Page<Product> productPage = productService.selectListPageByUser(map);
                List<Product> productList = productPage.getRecords();
                ret.put("code", 0);
                ret.put("data", StringTool.ListToJsonArray(productList));
                ret.put("total", productPage.getTotal());//总数
                ret.put("next", productPage.hasNext());//下一页
                ret.put("previous", productPage.hasPrevious());//上一页
                ret.put("msg", "查询成功");
            }
        } catch (Exception e) {
            ret.put("code", -1);
            ret.put("data", null);
            ret.put("msg", "查询失败");
        }
        return ret;
    }

    @PostMapping(value = "/productListById", produces = "application/json")
    @ApiOperation("分页查询属于某个用户的商品列表,就是我发布的商品,入参是page:第几页,number:每页几条")
    public JSONObject productListById(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        try {
            Long page = jsonObject.getLong("page");
            Long number = jsonObject.getLong("number");
            Object[] pIds = jsonObject.getJSONArray("pId").toArray();
            List<Object> list = new ArrayList<Object>();
            for (int i = 0; i < pIds.length; i++) {
                if (!list.contains(pIds[i])) {
                    list.add(pIds[i]);
                }
            }
            Object[] pIdss = list.toArray();
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("number", number);
            map.put("pId", pIdss);
            if (page != null && number != null && pIds != null) {
                Page<Product> productPage = productService.selectListsPageById(map);
                List<Product> productList = productPage.getRecords();
                ret.put("code", 0);
                ret.put("data", StringTool.ListToJsonArray(productList));
                ret.put("total", productPage.getTotal());//总数
                ret.put("next", productPage.hasNext());//下一页
                ret.put("previous", productPage.hasPrevious());//上一页
                ret.put("msg", "查询成功");
            }
        } catch (Exception e) {
            ret.put("code", -1);
            ret.put("data", null);
            ret.put("msg", "查询失败");
        }
        return ret;
    }

    @PostMapping(value = "/productListByIds", produces = "application/json")
    @ApiOperation("分页查询属于某个用户的商品列表,就是我发布的商品,入参是page:第几页,number:每页几条")
    public JSONObject productListByIds(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        try {
            Object[] pIds = jsonObject.getJSONArray("pId").toArray();
            List<Product> productList = new ArrayList<>();
            for (int i = 0; i < pIds.length; i++) {
                Product product = productService.selectById(pIds[i].toString());
                productList.add(product);
            }
            ret.put("code", 0);
            ret.put("data", StringTool.ListToJsonArray(productList));
            ret.put("msg", "查询成功");

        } catch (Exception e) {
            ret.put("code", -1);
            ret.put("data", null);
            ret.put("msg", "查询失败");
        }
        return ret;
    }

    @PutMapping("/addProduct")
    @ApiOperation("增加商品,入参是要增加的商品信息,记得带上上传图片接口返回的url")
    public JSONObject addProduct(@RequestBody JSONObject par, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        try {
            Product product = new Product();
            product.setId(StringTool.getUUID());
            product.setCreateTime(new Date());
            product.setBprice(par.getDouble("bprice"));
            product.setTitle(par.getString("title"));
            product.setImgUrl(par.getString("imgUrl"));
            product.setPrice(par.getDouble("price"));
            product.setContent(par.getString("content"));
            User user = userService.qureyByUserName(par.getString("username"));
            product.setUserId(user.getId());
            int i = productService.insert(product);
            if (i > 0) {
                ret.put("code", 0);
                ret.put("data", true);
                ret.put("msg", "增加成功");
            } else {
                ret.put("code", -1);
                ret.put("data", false);
                ret.put("msg", "增加失败");
            }
        } catch (Exception e) {
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "增加失败");
        }
        return ret;
    }

    @PutMapping("/update")
    @ApiOperation("修改商品信息,传入要修改的信息，主要是商品id")
    public JSONObject update(Product product) {
        JSONObject ret = new JSONObject();
        product.setCreateTime(new Date());
        try {
            if (product.getId() != null && productService.update(product) > 0) {
                ret.put("code", "0");
                ret.put("data", true);
                ret.put("msg", "修改成功");
            } else {
                ret.put("code", "-1");
                ret.put("data", false);
                ret.put("msg", "修改失败");
            }
        } catch (Exception e) {
            ret.put("code", "-1");
            ret.put("data", false);
            ret.put("msg", "修改失败");
            e.printStackTrace();
        }
        return ret;
    }

    @PutMapping("/obtained")
    @ApiOperation("上架下架接口，主要传id即可")
    public JSONObject Obtained(@RequestBody JSONObject id) {
        JSONObject ret = new JSONObject();
        try {
            String dbId = id.getString("id");
            Product product = productService.selectById(dbId);
            if (product != null && product.getIsShow() == 1) {
                productService.updateById(dbId, true);
                ret.put("code", "0");
                ret.put("data", true);
                ret.put("msg", "下架成功");
            } else if (product != null && product.getIsShow() == 0) {
                productService.updateById(dbId, false);
                ret.put("code", "0");
                ret.put("data", true);
                ret.put("msg", "上架成功");
            } else {
                ret.put("code", "-1");
                ret.put("data", false);
                ret.put("msg", "修改失败");
            }
        } catch (Exception e) {
            ret.put("code", "-1");
            ret.put("data", false);
            ret.put("msg", "修改失败");
            e.printStackTrace();
        }
        return ret;
    }

    @GetMapping("/selectById")
    @ApiOperation("查询商品详情，传id即可,id是个字符串")
    public JSONObject selectById(String id) {
        JSONObject ret = new JSONObject();
        try {
            Product product = productService.selectById(id);
            if (product != null) {
                ret.put("code", "0");
                ret.put("data", StringTool.ObjectToJSONObject(product));
                ret.put("msg", "查询商品详情成功");
            } else {
                ret.put("code", "-1");
                ret.put("data", false);
                ret.put("msg", "查询商品详情失败");
            }
        } catch (Exception e) {
            ret.put("code", "-1");
            ret.put("data", false);
            ret.put("msg", "查询商品详情失败");
            e.printStackTrace();
        }
        return ret;
    }

    @PutMapping("/addCount")
    @ApiOperation("增加浏览次数接口，主要传id即可，当用户打开商品页的时候发这个请求")
    public JSONObject Increase(@RequestBody JSONObject id) {
        JSONObject ret = new JSONObject();
        try {
            String dbId = id.getString("id");
            Product product = productService.selectById(dbId);
            if (product != null) {
                productService.updateIncrease(product);
                ret.put("code", "0");
                ret.put("data", true);
                ret.put("msg", "增加浏览次数成功");
            } else {
                ret.put("code", "-1");
                ret.put("data", false);
                ret.put("msg", "增加浏览次数失败");
            }
        } catch (Exception e) {
            ret.put("code", "-1");
            ret.put("data", false);
            ret.put("msg", "增加浏览次数失败");
            e.printStackTrace();
        }
        return ret;
    }

    @PostMapping("/delete")
    @ApiOperation("删除商品接口，主要传商品id即可")
    public JSONObject delete(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String pId = jsonObject.getString("id");
        User user = null;
        try {
            user = userService.qureyByUserName(jsonObject.getString("username"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int i = productService.delete(user.getId(), pId);
            if (i > 0) {
                if(messageService.deletebyFid(pId)>0) {
                    ret.put("code", "0");
                    ret.put("data", true);
                    ret.put("msg", "删除商品成功");
                }
            } else {
                ret.put("code", "-1");
                ret.put("data", false);
                ret.put("msg", "删除商品失败");
            }
        } catch (Exception e) {
            ret.put("code", "-1");
            ret.put("data", false);
            ret.put("msg", "删除商品失败");
            e.printStackTrace();
        }
        return ret;
    }
}
