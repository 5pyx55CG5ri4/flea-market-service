package cn.fleamarket.domain;

import cn.fleamarket.common.PageParam;
import cn.fleamarket.utils.GenerateSqlToBean;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("f_image")
public class Image  extends PageParam implements Serializable {

    @GenerateSqlToBean.NotGenerateSql
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private String id;
    /**
     * 图片用处（表名）
     */
    private String tableName;
    /**
     * 对应编号（对应ID）
     */
    private Integer tableId;
    /**
     * 图片URL
     */
    private String imgUrl;
    /**
     * 是否默认（0否，1默认）
     */
    private Integer isdetault;
    /**
     * 备注
     */
    private String about;



}
