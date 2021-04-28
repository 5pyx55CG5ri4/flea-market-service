package cn.fleamarket.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 分页结果
 *
 * @author zhuliyou
 * @date 2021/04/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult extends PageParam {

    /**
     * 总数
     */
    private Long total;

    /**
     * 是否有下一页
     */
    private Boolean next;

    /**
     * 是否有上一页
     */
    private Boolean previous;

}
