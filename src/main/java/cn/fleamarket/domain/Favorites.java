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
 * 产品收藏表
 * 
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("f_favorites")
public class Favorites extends PageParam implements Serializable {
	@GenerateSqlToBean.NotGenerateSql
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private String id;
	/**
	 * 用户编号名
	 */
	private String userId;
	/**
	 * 产品编号
	 */
	private String productId;
	/**
	 * 收藏时间
	 */
	private Date createTime;

	public String getCreateTime() {
		return StringTool.dataTool(createTime);
	}

	/**
	 * 状态
	 */
	private Integer state;

}
