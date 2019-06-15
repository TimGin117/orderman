package com.mydemo.orderman.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddOrderForm {

    @NotNull
    @Min(value = 0,message = "买家编号不得小于0")
    private Integer buyerId;

    @NotNull
    @Min(value = 0,message = "库存编号不得小于0")
    private Integer stocksId;

}
