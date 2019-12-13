package cn.fleamarket.controller;

import cn.fleamarket.domain.Message;
import cn.fleamarket.domain.User;
import cn.fleamarket.service.MessageService;
import cn.fleamarket.service.UserService;
import cn.fleamarket.utils.StringTool;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 留言接口
 */
@RestController
@RequestMapping("/message")
@Api("留言接口")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @PostMapping(value = "/messageList", produces = "application/json")
    @ApiOperation("分页查询留言列表,入参是page:第几页,number:每页几条,pId:属于哪个商品的id")
    public JSONObject messageList(@RequestBody JSONObject jsonObject) {
        JSONObject ret = new JSONObject();
        try {
            Long page = jsonObject.getLong("page");
            Long number = jsonObject.getLong("number");
            String pId = jsonObject.getString("pId");
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("number", number);
            map.put("pId", pId);
            if (page != null && number != null) {
                Page<Message> messagePage = messageService.selectListPage(map);
                List<Message> messagesList = messagePage.getRecords();
                ret.put("code", 0);
                ret.put("data", StringTool.ListToJsonArray(messagesList));
                ret.put("total", messagePage.getTotal());//总数
                ret.put("next", messagePage.hasNext());//下一页
                ret.put("previous", messagePage.hasPrevious());//上一页
                ret.put("msg", "查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret.put("code", -1);
            ret.put("data", null);
            ret.put("msg", "查询失败");
        }
        return ret;
    }

    @PostMapping("/addMessage")
    @ApiOperation("新增留言接口，text:留言内容,tid:发送人(不用填),fid:接受人(填商品id)")
    public JSONObject addMessage(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        Message message = new Message();
        try {
            User user = userService.qureyByUserName(jsonObject.getString("username"));
            message.setTid(user.getId());
            message.setTime(new Date());
            message.setId(StringTool.getUUID());
            message.setFid(jsonObject.getString("fid"));
            message.setText(jsonObject.getString("text"));
            if (messageService.addMessage(message) > 0) {
                ret.put("code", 0);
                ret.put("data", true);
                ret.put("msg", "新增留言成功");
            } else {
                ret.put("code", -1);
                ret.put("data", false);
                ret.put("msg", "新增留言失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            ret.put("code", -1);
            ret.put("data", false);
            ret.put("msg", "新增留言失败");
        }
        return ret;
    }

    @PostMapping(value = "/messageListByUser", produces = "application/json")
    @ApiOperation("分页查询我的留言,入参是page:第几页,number:每页几条")
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
                Page<Message> messagePage = messageService.selectListPageByUser(map);
                List<Message> messageList = messagePage.getRecords();
                ret.put("code", 0);
                  ret.put("data", StringTool.ListToJsonArray(messageList));
                ret.put("total", messagePage.getTotal());//总数
                ret.put("next", messagePage.hasNext());//下一页
                ret.put("previous", messagePage.hasPrevious());//上一页
                ret.put("msg", "查询成功");
            }
        } catch (Exception e) {
            ret.put("code", -1);
            ret.put("data", null);
            ret.put("msg", "查询失败");
            e.printStackTrace();
        }
        return ret;
    }

    @PostMapping("/delete")
    @ApiOperation("删除留言接口，主要传留言id即可")
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
            int i = messageService.delete(user.getId(), pId);
            if (i > 0) {
                ret.put("code", "0");
                ret.put("data", true);
                ret.put("msg", "删除留言成功");
            } else {
                ret.put("code", "-1");
                ret.put("data", false)        ;
                ret.put("msg", "删除留言失败");
            }
        } catch (Exception e) {
            ret.put("code", "-1");
            ret.put("data", false);
            ret.put("msg", "删除留言失败");
            e.printStackTrace();
        }
        return ret;
    }
}
