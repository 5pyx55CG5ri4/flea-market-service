package com.fleamarket.modules.message.controller;

import com.fleamarket.code.controller.BaseController;
import com.fleamarket.code.domain.R;
import com.fleamarket.code.page.TableDataInfo;
import com.fleamarket.common.auth.UserHolder;
import com.fleamarket.modules.message.domain.Message;
import com.fleamarket.modules.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 *
 * @author zhuliyou
 * @date 2023/06/06
 */
@RestController
@RequestMapping("message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    /**
     * 列表
     *
     * @param message 消息
     * @return {@link TableDataInfo}<{@link Message}>
     */
    @GetMapping("list")
    public TableDataInfo<Message> list(Message message) {
        startPage();
        return getDataTable(messageService.lambdaQuery().list());
    }


    /**
     * 添加
     *
     * @param message 消息
     * @return {@link R}
     */
    @PostMapping
    public R add(@RequestBody Message message) {
        message.setUserId(UserHolder.getUserId());
        messageService.save(message);
        return R.success();
    }


    /**
     * 编辑器
     * 编辑
     *
     * @param message 消息
     * @return {@link R}
     */
    @PutMapping
    public R editor(@RequestBody Message message) {
        message.setUserId(UserHolder.getUserId());
        messageService.updateById(message);
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
        messageService.removeById(id);
        return R.success();
    }

}
