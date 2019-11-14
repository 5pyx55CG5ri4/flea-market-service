package cn.fleamarket.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("f_code")
public class Code implements Serializable {
    /**
     *
     */
    @TableId
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 信用度
     */
    private Integer credit;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 账号状态1正常，0冻结
     */
    private Integer state;
    /**
     *
     */
    private String wxopenid;
    /**
     *
     */
    private String qqopenid;
    /**
     *
     */
    private String aliopenid;
    /**
     * 钱
     */
    private Double money;

    /**
     * 宿舍号
     */
    private String roomId;


    private String code;

    @TableField("code_time")
    private Date codeTime;
}
