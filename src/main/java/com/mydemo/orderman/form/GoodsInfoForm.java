package com.mydemo.orderman.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//用于添加和修改商品信息
@Data
public class GoodsInfoForm {

    @NotBlank
    private String goodsName;//商品名称

    @NotNull
    @Min(value = 0,message = "价格不低于0元")
    private Integer price;//商品价格

}
