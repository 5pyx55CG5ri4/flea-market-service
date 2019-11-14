package cn.fleamarket.service;

import cn.fleamarket.domain.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 *
 * @author zining
 * @email ${email}
 * @date 2019-11-12 10:46:22
 */
public interface ProductService{

    Page selectListPage(Map<String, Object> map);
}

