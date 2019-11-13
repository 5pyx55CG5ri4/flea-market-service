package cn.fleamarket.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;


import cn.fleamarket.domain.User;
import cn.fleamarket.service.UserService;
import cn.fleamarket.utils.StringTool;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 用户信息表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@RestController
@RequestMapping("/user")
@Api("用户接口")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录接口")
    public JSONObject getUser(User user) {
        JSONObject ret = new JSONObject();
        try {
            User dbUser = userService.qureyByUserName(user.getUserName());
            if (dbUser.getPassWord().equals(StringTool.getMd5(user.getPassWord()))) {
                ret.put("data", StringTool.ObjectToJSONObject(dbUser));
                ret.put("code", 0);
                ret.put("msg", "登录成功");
            } else {
                ret.put("data", false);
                ret.put("code", -1);
                ret.put("msg", "登录失败,密码错误");
            }
        } catch (NullPointerException e) {
            ret.put("code", -2);
            ret.put("data", false);
            ret.put("msg", "当前用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @PutMapping("/add")
    @ApiOperation("注册接口")
    public JSONObject addUser(User user) {
        JSONObject ret = new JSONObject();
        user.setId(StringTool.getUUID());
        user.setPassWord(StringTool.getMd5(user.getPassWord()));
        try {
            int i = userService.addUser(user);
            if (i > 0) {
                ret.put("code", 0);
                ret.put("data", true);
                ret.put("msg", "注册成功");
            }
        } catch (Exception e) {
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "注册失败");
            e.printStackTrace();
        }
        return ret;
    }

    @PostMapping("/update")
    @ApiOperation("更新接口")
    public JSONObject update(User user) {
        JSONObject ret = new JSONObject();
        if(null!=user.getPassWord()) {
            user.setPassWord(StringTool.getMd5(user.getPassWord()));
        }
        try {
            int i = userService.update(user);
            if (i > 0) {
                ret.put("data", true);
                ret.put("code", 0);
                ret.put("msg", "修改成功");
            }
        } catch (Exception e) {
            ret.put("data", false);
            ret.put("code", -1);
            ret.put("msg", "修改失败");
            e.printStackTrace();
        }
        return ret;
    }

}
