package cn.fleamarket.domain;

import cn.fleamarket.common.PageParam;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("f_code")
public class Code extends PageParam implements Serializable {
    /**
     * 验证码id就是用户输入的邮箱
     */
    @TableId
    private String id;

    private String code;

    private Date codeTime;
}
