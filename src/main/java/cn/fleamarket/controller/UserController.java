package cn.fleamarket.controller;

import java.text.ParseException;
import java.util.Date;


import cn.fleamarket.domain.Code;
import cn.fleamarket.domain.User;
import cn.fleamarket.service.CodeService;
import cn.fleamarket.service.UserService;
import cn.fleamarket.utils.EmailUtil;
import cn.fleamarket.utils.StringTool;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


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
    @ApiOperation("登录接口,用户名或者邮箱加密码即可")
    public JSONObject getUser(User user, HttpSession session) {
        JSONObject ret = new JSONObject();
        try {
            User dbUser = userService.qureyByUserName(user.getUserName());
            if (dbUser.getPassWord().equals(StringTool.getMd5(user.getPassWord()))) {
                ret.put("data", StringTool.ObjectToJSONObject(dbUser));
                ret.put("code", 0);
                ret.put("msg", "登录成功");
                session.setAttribute("user", dbUser);
            } else {
                ret.put("data", false);
                ret.put("code", -1);
                ret.put("msg", "登录失败,账号密码错误");
            }
        } catch (NullPointerException e) {
            try {
                User user1 = userService.qureyByEmail(user.getUserName());
                if (user1.getPassWord().equals(StringTool.getMd5(user.getPassWord()))) {
                    ret.put("data", StringTool.ObjectToJSONObject(user1));
                    ret.put("code", 0);
                    ret.put("msg", "登录成功");
                    session.setAttribute("user", user1);
                } else {
                    ret.put("data", false);
                    ret.put("code", -1);
                    ret.put("msg", "登录失败,账号密码错误");
                }
            } catch (NullPointerException ee) {
                ret.put("code", -2);
                ret.put("data", false);
                ret.put("msg", "登录失败,当前用户不存在");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @PostMapping("/getCode")
    @ApiOperation("获取验证码接口,参数是email:接受验证码的邮箱,sta:状态码 0表示注册，1表示忘记密码")
    public JSONObject getCode(@RequestBody JSONObject jsonObject) {
        JSONObject ret = new JSONObject();
        String email = jsonObject.getString("email");
        String sta = jsonObject.getString("sta");//0表示注册获取验证码,1表示忘记密码获取验证码
        if (email != null) {
            try {
                String code = StringTool.getCodeToString();
                Code codes = new Code();
                codes.setId(email);
                codes.setCode(code);
                codes.setCodeTime(new Date());
                int i = 0;
                if ("0".equals(sta)) {
                    i = codeService.insert(codes);
                }
                if ("1".equals(sta) && codeService.selectById(email) != null) {
                    i = codeService.update(codes);
                } else {
                    ret.put("code", -1);
                    ret.put("data", false);
                    ret.put("msg", "该账号不存在");
                }
                if (i > 0 && emailUtil.getCode(email, code)) {
                    ret.put("code", 0);
                    ret.put("data", true);
                    ret.put("msg", "验证码发送成功，请注意查收");
                }
            } catch (DataIntegrityViolationException e) {
                ret.put("code", -1);
                ret.put("data", false);
                ret.put("msg", "该邮箱已经注册过");
            } catch (Exception e) {
                e.printStackTrace();
                ret.put("code", -1);
                ret.put("data", false);
                ret.put("msg", "未知错误");
            }
        } else {
            ret.put("code", 0);
            ret.put("data", false);
            ret.put("msg", "邮箱为空");
        }
        return ret;
    }

    @PutMapping(value = "/add", produces = "application/json")
    @ApiOperation("注册接口,传入用户填写的信息，加上code:验证码")
    public JSONObject addUser(User user, @RequestBody JSONObject jsonObject) {
        JSONObject ret = new JSONObject();
        String code = jsonObject.getString("code");
        try {
            Code code1 = codeService.selectById(user.getEmail());
            if (code.equals(code1.getCode())) {
                try {
                    if (emailUtil.getTimeCJ(StringTool.dataTool(new Date()), StringTool.dataTool(code1.getCodeTime()))) {
                        user.setId(StringTool.getUUID());
                        user.setCreateTime(new Date());
                        user.setPassWord(StringTool.getMd5(user.getPassWord()));
                        int i = userService.addUser(user);
                        if (i > 0) {
                            ret.put("code", 0);
                            ret.put("data", true);
                            ret.put("msg", "注册成功");
                        }
                    } else {
                        codeService.delete(user.getEmail());
                        ret.put("code", -1);
                        ret.put("data", false);
                        ret.put("msg", "验证码过时");
                    }
                } catch (DataIntegrityViolationException e) {
                    ret.put("code", -1);
                    ret.put("data", false);
                    ret.put("msg", "该账户已经注册过");
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
        } catch (NullPointerException e) {
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "验证码不能为空");
        }
        return ret;
    }

    @PostMapping("/update")
    @ApiOperation("忘记密码接口,需要填写邮箱和密码，加上code:验证码")
    public JSONObject update(User user, @RequestBody JSONObject jsonObject) {
        JSONObject ret = new JSONObject();
        String code = jsonObject.getString("code");
        try {
            Code code1 = codeService.selectById(user.getEmail());
            if (code.equals(code1.getCode())) {
                try {
                    if (emailUtil.getTimeCJ(StringTool.dataTool(new Date()), StringTool.dataTool(code1.getCodeTime()))) {
                        User dbUser = userService.qureyByEmail(user.getEmail());
                        if (null != user.getPassWord()) {
                            dbUser.setPassWord(StringTool.getMd5(user.getPassWord()));
                        }
                        int i = userService.update(dbUser);
                        if (i > 0) {
                            ret.put("data", true);
                            ret.put("code", 0);
                            ret.put("msg", "修改成功");
                        }
                    } else {
                        code1.setCode(null);
                        codeService.update(code1);
                        ret.put("code", -1);
                        ret.put("data", false);
                        ret.put("msg", "验证码过时");
                    }
                } catch (Exception e) {
                    ret.put("data", false);
                    ret.put("code", -1);
                    ret.put("msg", "修改失败");
                    e.printStackTrace();
                }

            } else {
                ret.put("code", -1);
                ret.put("data", false);
                ret.put("msg", "验证码错误");
            }
        } catch (NullPointerException e) {
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "验证码不能为空");
        }
        return ret;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @DeleteMapping("/loginOut")
    @ApiOperation("退出登录接口，啥都不要")
    public JSONObject loginOut(HttpSession session) {
        session.removeAttribute("user");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("data", true);
        jsonObject.put("msg", "退出登录成功");
        return jsonObject;
    }

    @GetMapping("/selectById")
    @ApiOperation("根据用户id查询用户信息（不包括密码），传入id是字符串")
    public JSONObject selectById(String id) {
        JSONObject ret = new JSONObject();
        try {
            User user = userService.selectById(id);
            user.setPassWord(null);
            ret.put("data", StringTool.ObjectToJSONObject(user));
            ret.put("code", 0);
            ret.put("msg", "查询成功");
        } catch (Exception e) {
            ret.put("data", false);
            ret.put("code", -1);
            ret.put("msg", "查询失败");
            e.printStackTrace();
        }
        return ret;
    }
}
