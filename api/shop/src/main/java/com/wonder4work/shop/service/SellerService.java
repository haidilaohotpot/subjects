package com.wonder4work.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.shop.domain.ProductInfo;
import com.wonder4work.shop.domain.SellerInfo;


public interface SellerService extends IService<SellerInfo> {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
