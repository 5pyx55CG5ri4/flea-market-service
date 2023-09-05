package com.fleamarket.modules.favorites.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fleamarket.common.annotation.TranslationValue;
import com.fleamarket.modules.product.domain.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 *
 * @author zhuliyou
 * @date 2023/04/28
 */
@Data
@TableName("sys_favorites")
public class Favorites implements Serializable {


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

    @TableField(exist = false)
    private Product product;

}
