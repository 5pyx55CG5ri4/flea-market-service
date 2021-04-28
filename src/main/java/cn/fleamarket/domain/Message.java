package cn.fleamarket.domain;

import cn.fleamarket.common.PageParam;
import cn.fleamarket.utils.StringTool;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言接口
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("f_message")
public class Message extends PageParam implements Serializable {
    @TableId
    private String id;
    @TableField("t_id")
    private String tid;
    @TableField("f_id")
    private String fid;

    private String text;

    private Date time;

    public String getTime() {
        return StringTool.dataTool(time);
    }


}
