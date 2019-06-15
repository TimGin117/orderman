package com.mydemo.orderman.converter;

import com.mydemo.orderman.dto.OrderInfoDTO;
import com.mydemo.orderman.enmu.OrderStatusEnum;
import com.mydemo.orderman.model.Goods;
import com.mydemo.orderman.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderToOrderInfoDTOConverter {

    private final List<String> status=new ArrayList<>();

    public static OrderInfoDTO convert(Order order){
        OrderInfoDTO orderInfoDTO=new OrderInfoDTO();

        orderInfoDTO.setOrderId(order.getId());
        orderInfoDTO.setGoodsId(order.getStocks().getGoods().getId());
        orderInfoDTO.setGoodsName(order.getStocks().getGoods().getName());
        orderInfoDTO.setSellerId(order.getStocks().getSeller().getId());
        orderInfoDTO.setShopName(order.getStocks().getSeller().getShopName());
        orderInfoDTO.setBuyerId(order.getBuyer().getId());
        orderInfoDTO.setBuyerName(order.getBuyer().getName());
        orderInfoDTO.setCreateTime(order.getCreateTime());

        Integer statusIndex=order.getStatus();

        String status=(statusIndex==0)? OrderStatusEnum.WAIT.getMsg() :
                ((statusIndex==1)?OrderStatusEnum.SENDING.getMsg():
                OrderStatusEnum.DONE.getMsg());

        orderInfoDTO.setOrderStatus(status);

        return orderInfoDTO;
    }

    public static List<OrderInfoDTO> convert(List<Order> orderList){
        return orderList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
