package cn.fleamarket.domain;

import cn.fleamarket.common.PageParam;
import cn.fleamarket.utils.GenerateSqlToBean;
import cn.fleamarket.utils.StringTool;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("f_product")
public class Product extends PageParam implements Serializable {
    @GenerateSqlToBean.NotGenerateSql
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private String id;
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
    private Double bprice;
    /**
     * 出售价格
     */
    private Double price;
    /**
     * 销售次数
     */
    private Integer times;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 添加人
     */
    private String userId;
    /**
     * 上下架（0否 1是）
     */
    private Integer isShow;
    /**
     * 是否删除（0否 1是）
     */
    private Integer isDel;
    /**
     * 浏览次数
     */
    private Integer count;
    /**
     * 收藏次数
     */
    private Integer sc;
    /**
     * 图片地址
     */
    private String imgUrl;

    public String getCreateTime() {
        return StringTool.dataTool(createTime);
    }
}
