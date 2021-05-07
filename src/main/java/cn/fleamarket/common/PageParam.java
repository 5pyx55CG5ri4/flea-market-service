package cn.fleamarket.common;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 分页参数参数
 *
 * @author zhuliyou
 * @date 2021/04/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {

    /**
     * 当前页
     */
    @TableField(exist = false)
    private Long page;

    /**
     * 每页条数
     */
    @TableField(exist = false)
    private Long number;

    public Long getPage() {
        return page == null ? 0 : this.page;
    }

    public Long getNumber() {
        return number == null ? 10 : this.number;
    }
}
