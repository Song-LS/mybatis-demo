package com.sls.base.controller;

import com.sls.base.resultVo.ResultVo;
import com.sls.base.service.ShopService;
import com.sls.base.util.ColinParamUtil;
import io.swagger.annotations.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author SLS
 * @data 2019/3/2 15:41
 **/
@RestController
@Api(description = "门店")
public class ShopController {

    @Resource
    private ShopService shopService;

    @PostMapping("/addShop")
    @ApiOperation(value = "添加门店", httpMethod = "POST", response = ApiResponse.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "shopName", value = "门店名", required = true, dataType = "String", paramType = "Query"),
            @ApiImplicitParam(name = "shopNo", value = "门店编号", required = true, dataType = "String", paramType = "Query")})
    public ResultVo addShop(String shopName, String shopNo) {
        if (ColinParamUtil.paramCheck(Arrays.asList(shopName, shopNo))) {
            return ResultVo.error("参数不能为空");
        }
        return shopService.addShop(shopName, shopNo);
    }

    @PostMapping("/delShop")
    @ApiOperation(value = "删除门店", httpMethod = "POST", response = ApiResponse.class)
    @ApiImplicitParams(@ApiImplicitParam(name = "shopId", value = "门店ID", required = true, dataType = "String", paramType = "Query"))
    public ResultVo delShop(String shopId) {
        if (StringUtils.isEmpty(shopId)) {
            return ResultVo.error("参数不能为空");
        }
        return shopService.delShop(Long.valueOf(shopId));
    }

}
