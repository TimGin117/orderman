package com.mydemo.orderman.dto;

import lombok.Data;

@Data
public class StocksInfoDTO {

    private Integer stocksId;//库存编号

    private Integer goodsId;//商品编号

    private String goodsName;//商品名称

    private Integer sellerId;//商品所属商家

    private String shopName;//店铺名称

    private Integer price;//商品价格

    private Integer stockNumber;//库存量
}
