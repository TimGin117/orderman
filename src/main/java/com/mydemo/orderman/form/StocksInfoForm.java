package com.mydemo.orderman.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class StocksInfoForm {

    @NotNull
    @Min(value = 1,message = "商家编号至少为1")
    private Integer sellerId;

    @NotNull
    @Min(value = 1,message = "商品编号至少为1")
    private Integer goodsId;

    @NotNull
    @Min(value = 0,message = "库存量不小于0")
    private Integer stockNumber;
}
