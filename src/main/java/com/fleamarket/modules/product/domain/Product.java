package com.fleamarket.modules.product.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fleamarket.common.annotation.TranslationValue;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 *
 * @author zhuliyou
 * @date 2023/04/28
 */
@Data
@TableName("sys_product")
public class Product implements Serializable {


    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 商品内容
     */
    private String content;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 出售价格
     */
    private BigDecimal salePrice;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    @TranslationValue(targetTable = "sys_user",
            translationField = "user_name", whereField = "id",
            alias = "createByName")
    private Long createBy;

    /**
     * 浏览次数
     */
    private Integer viewed;

    /**
     * 收藏次数
     */
    private Integer collectionCount;

    /**
     * 图片链接地址
     */
    private String imgUrl;

    /**
     * 状态 0 下架 1 上架
     */
    private String state;

    @TableField(exist = false)
    private Long favoritesId;
}
