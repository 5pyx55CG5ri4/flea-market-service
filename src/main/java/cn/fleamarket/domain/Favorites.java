package cn.fleamarket.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 产品收藏表
 * 
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@Data
@TableName("f_favorites")
public class Favorites implements Serializable {
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
	/**
	 * 状态
	 */
	private Integer state;

}
