package cn.fleamarket.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 */
@Data
@TableName("f_code")
public class Code implements Serializable {
    /**
     * 验证码id就是用户输入的邮箱
     */
    @TableId
    private String id;

    private String code;

    private Date codeTime;
}
