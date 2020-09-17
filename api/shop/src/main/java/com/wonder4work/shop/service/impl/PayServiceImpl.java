package com.wonder4work.shop.service.impl;


import com.wonder4work.shop.dto.OrderDTO;
import com.wonder4work.shop.enums.ResultEnum;
import com.wonder4work.shop.exception.SellException;
import com.wonder4work.shop.service.OrderService;
import com.wonder4work.shop.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "点餐订单";


    @Autowired
    private OrderService orderService;

    @Override
    public boolean create(OrderDTO orderDTO) {
        return true;
    }

    @Override
    public boolean notify(OrderDTO orderDTO) {


        //修改订单的支付状态
        orderService.paid(orderDTO);

        return true;
    }

    /**
     * 退款
     * @param orderDTO
     */
    @Override
    public boolean refund(OrderDTO orderDTO) {
        return true;
    }
}
