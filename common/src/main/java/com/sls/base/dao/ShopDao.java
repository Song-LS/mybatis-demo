package com.sls.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sls.base.entity.Shop;

/**
 * @author SLS
 * @data 2019/3/1 15:36
 **/
public interface ShopDao extends BaseMapper<Shop> {

    /**
     * 分页查询门店
     * @param start 页数
     * @param end 条数
     * @return 门店列表
     */
//    List<Shop> findShopList(Integer start, Integer end);

    /**
     * 统计门店数量
     * @return 数量
     */
//    int countShopSize();

}



