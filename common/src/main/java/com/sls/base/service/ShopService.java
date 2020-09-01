package com.sls.base.service;

import com.sls.base.resultVo.ResultVo;

/**
 * @author SLS
 * @data 2019/3/1 14:30
 **/
public interface ShopService {

    /**
     * 添加门店
     *
     * @param shopName 门店名
     * @param shopNo   门店编号
     * @return ResultVo
     */
    ResultVo addShop(String shopName, String shopNo);

    /**
     * 删除门店
     *
     * @param shopId 门店Id
     * @return ResultVo
     */
    ResultVo delShop(Long shopId);

}
