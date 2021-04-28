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
 * 订单表
 * 
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("f_order")
public class Order extends PageParam implements Serializable {
	@GenerateSqlToBean.NotGenerateSql
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 应付金额
	 */
	private Double payableFee;
	/**
	 * 实付金额
	 */
	private Double realFee;
	/**
	 * 支付方式 1微信 2支付宝 3银行转账 4 现金 5 其它
	 */
	private Integer paymentWay;
	/**
	 * 支付状态 :0到付1待付款,2已付款,3待退款,4退款成功,5退款失败
	 */
	private Integer payState;
	/**
	 * 订单状态 0:提交订单 1:库存验货 2:等待支付 3:支付完成
	 */
	private Integer orderState;
	/**
	 * 订单生成时间
	 */
	private Date createTime;
	/**
	 * 支付时间
	 */
	private Date payTime;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 商品编号
	 */
	private String productId;

}
