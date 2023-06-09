package com.fleamarket.modules.message.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息
 *
 * @author zhuliyou
 * @date 2023/04/28
 */
@Data
@TableName("sys_message")
public class Message implements Serializable {


    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 消息内容
     */
    private String message;


}
