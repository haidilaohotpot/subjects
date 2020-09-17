package com.wonder4work.shop.controller.seller;


import com.wonder4work.shop.dto.OrderDTO;
import com.wonder4work.shop.enums.ResultEnum;
import com.wonder4work.shop.exception.SellException;
import com.wonder4work.shop.service.OrderService;
import com.wonder4work.shop.utils.PagedGridResult;
import com.wonder4work.shop.utils.ResultVOUtil;
import com.wonder4work.shop.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端订单
 */
@RestController
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping(value = "/list")
    public ResultVO list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                         Map<String, Object> map) {

        PagedGridResult pagedGridResult = orderService.findList(page, size);

        return ResultVOUtil.success(pagedGridResult);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端取消订单】发生异常{}", e);
            return ResultVOUtil.error(e.getCode(),e.getMessage());
        }
        return ResultVOUtil.success(ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            return ResultVOUtil.error(ResultEnum.ORDERDETAIL_NOT_EXIST.getCode(),e.getMessage());
        }
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ResultVO finished(@RequestParam("orderId") String orderId,
                                 Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常{}", e);
            return ResultVOUtil.error(e.getCode(),e.getMessage());
        }

        return ResultVOUtil.success(ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
    }
}
