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
 * 用户信息表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("f_user")
public class User extends PageParam implements Serializable {
    @GenerateSqlToBean.NotGenerateSql
    private static final long serialVersionUID = 1L;

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

    public String getCreateTime() {
        return StringTool.dataTool(createTime);
    }

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

}
