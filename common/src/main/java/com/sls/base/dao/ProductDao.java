package com.sls.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sls.base.entity.Product;

import java.util.List;

/**
 * @author SLS
 * @data 2019/3/1 15:36
 **/
public interface ProductDao extends BaseMapper<Product> {

    List<Product> findProductListByShopId(Long shopId);
}
