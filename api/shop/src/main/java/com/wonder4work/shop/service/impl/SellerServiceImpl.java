package com.wonder4work.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonder4work.shop.domain.SellerInfo;
import com.wonder4work.shop.mapper.SellerInfoMapper;
import com.wonder4work.shop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellerServiceImpl extends ServiceImpl<SellerInfoMapper,SellerInfo> implements SellerService {


    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("openid", openid);
        SellerInfo sellerInfo = this.getOne(queryWrapper);
        return sellerInfo;
    }
}
