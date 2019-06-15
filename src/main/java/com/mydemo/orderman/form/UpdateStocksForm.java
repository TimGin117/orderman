package com.mydemo.orderman.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateStocksForm {

    @NotNull
    @Min(value = 1,message = "库存编号不小于1")
    private Integer stocksId;

    @NotNull
    @Min(value = 0,message = "库存量不小于0")
    private Integer stockNumber;
}
