package cn.fleamarket.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


import cn.fleamarket.domain.Code;
import cn.fleamarket.domain.User;
import cn.fleamarket.service.CodeService;
import cn.fleamarket.service.UserService;
import cn.fleamarket.utils.EmailUtil;
import cn.fleamarket.utils.StringTool;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    CodeService codeService;

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

    @PostMapping("getCode")
    @ApiOperation("获取验证码接口")
    public JSONObject getCode(User user) {
        JSONObject ret = new JSONObject();
        if (user.getEmail() == null) {
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "邮箱是必填的");
            return ret;
        }
        try {
            String code = StringTool.getCodeToString();
            Code code1 = new Code();
            user.setPassWord(StringTool.getMd5(user.getPassWord()));
            code1.setCode(code);
            code1.setCodeTime(new Date());
            user.setId(StringTool.getUUID());
            BeanUtils.copyProperties(user, code1);
            int i = codeService.insert(code1);
            if (i > 0 && emailUtil.getCode(user.getEmail(), code)) {
                ret.put("code", 0);
                ret.put("data", code1.getId());
                ret.put("msg", "验证码发送成功，请注意查收");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "未知错误");
        }
        return ret;
    }

    @PutMapping(value = "/add", produces = "application/json")
    @ApiOperation("注册接口")
    public JSONObject addUser(@RequestBody JSONObject jsonObject) {
        JSONObject ret = new JSONObject();
        String id = jsonObject.getString("cId");
        Code code1 = codeService.selectById(id);
        String code = jsonObject.getString("code");
        if (code.equals(code1.getCode())) {
            try {
                if (emailUtil.getTimeCJ(StringTool.dataTool(new Date()), StringTool.dataTool(code1.getCodeTime()))) {
                    User user = new User();
                    BeanUtils.copyProperties(code1, user);
                    user.setId(StringTool.getUUID());
                    user.setCreateTime(new Date());
                    int i = userService.addUser(user);
                    if (i > 0) {
                        ret.put("code", 0);
                        ret.put("data", true);
                        ret.put("msg", "注册成功");
                    }
                } else {
                    ret.put("code", -1);
                    ret.put("data", false);
                    ret.put("msg", "验证码过时");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                ret.put("code", -1);
                ret.put("data", false);
                ret.put("msg", "注册失败");
            }

        } else {
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "验证码错误");
        }
        return ret;
    }

    @PostMapping("/update")
    @ApiOperation("更新接口")
    public JSONObject update(User user) {
        JSONObject ret = new JSONObject();
        if (null != user.getPassWord()) {
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
