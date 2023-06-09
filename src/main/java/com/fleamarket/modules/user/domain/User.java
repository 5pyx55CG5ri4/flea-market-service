package com.fleamarket.modules.user.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author zhuliyou
 * @date 2023/04/28
 */
@Data
@TableName("sys_user")
public class User implements Serializable {


    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户名/邮箱号/手机号
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态 0 冻结 1 有效
     */
    private String state;

}
