package com.sls.base.service.impl;

import com.sls.base.dao.ProductDao;
import com.sls.base.dao.ShopDao;
import com.sls.base.entity.Shop;
import com.sls.base.resultVo.ResultVo;
import com.sls.base.service.ShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author SLS
 * @data 2019/3/1 14:29
 **/
@Service
public class ShopServiceImpl implements ShopService {

    @Resource // @Autowired按byType自动注入，而@Resource...
    private ShopDao shopDao;
    @Resource
    private ProductDao productDao;

    @Override
    public ResultVo addShop(String shopName, String shopNo) {
        Shop shop = new Shop();
        shop.setShopName(shopName);
        shop.setShopNo(shopNo);
        int b = shopDao.insert(shop);
        if (b > 0) {
            return ResultVo.success("", "", null);
        }
        return ResultVo.error("添加失败");
    }

    @Override
    public ResultVo delShop(Long shopId) {
        return null;
    }
}
