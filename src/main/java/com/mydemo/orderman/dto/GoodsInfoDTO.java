package com.mydemo.orderman.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsInfoDTO {

    private Integer goodsId;//商品编号

    private String goodsName;//商品名称

    private Integer price;//商品价格

}
