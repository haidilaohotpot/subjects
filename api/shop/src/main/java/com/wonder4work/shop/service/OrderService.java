package com.wonder4work.shop.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.shop.domain.OrderMaster;
import com.wonder4work.shop.dto.OrderDTO;
import com.wonder4work.shop.utils.PagedGridResult;

/**
 * 订单Service
 * @author xiezengcheng
 */
public interface OrderService  {

    /** 创建订单. */
    OrderDTO create(OrderDTO orderDTO);

    /** 查询单个订单. */
    OrderDTO findOne(String orderId);

    /** 查询订单列表. */
    PagedGridResult findList(String buyerOpenid, Integer page,Integer pageSize);

    /** 取消订单. */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单. */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单. */
    OrderDTO paid(OrderDTO orderDTO);

    /** 查询订单列表. */
    PagedGridResult findList(Integer page,Integer pageSize);

}
