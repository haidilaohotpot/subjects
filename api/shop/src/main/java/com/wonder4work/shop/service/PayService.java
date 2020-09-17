package com.wonder4work.shop.service;


import com.wonder4work.shop.dto.OrderDTO;

public interface PayService {

    boolean create(OrderDTO orderDTO);

    boolean notify(OrderDTO orderDTO);

    boolean refund(OrderDTO orderDTO);
}
