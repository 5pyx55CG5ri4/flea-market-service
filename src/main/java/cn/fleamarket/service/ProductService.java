package cn.fleamarket.service;

import cn.fleamarket.domain.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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

    Page selectListPageByUser(Map<String, Object> map);

    Page selectListsPageById(Map<String, Object> map);

    int insert(Product product);

    int update(Product product);

    int updateById(String dbId,boolean sta);

    Product selectById(String dbId);

    int updateIncrease(Product product);

    int delete(String id, String pId);
}

