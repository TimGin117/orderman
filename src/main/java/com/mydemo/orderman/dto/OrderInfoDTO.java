package com.mydemo.orderman.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderInfoDTO {

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private Integer sellerId;

    private String shopName;

    private Integer buyerId;

    private String buyerName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String orderStatus;
}
