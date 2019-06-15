package com.mydemo.orderman.enmu;

import lombok.Getter;

@Getter
public enum ResultEnum {
    TYPE_ERROR(-1,"传入对象类型错误"),
    SUCCESS(0,"返回成功"),
    NO_SUCH_BUYER(1,"该用户不存在"),
    NO_SUCH_GOODS(2,"该商品不存在"),
    NO_SUCH_SELLER(3,"该商家不存在"),
    NO_SUCH_ORDER(4,"该订单不存在"),
    NO_SUCH_STOCKS(5,"该库存记录不存在"),
    GOODS_INFO_FORM_ERROR(6,"商品信息表单错误"),
    ADD_ORDER_FORM_ERROR(7,"添加订单表单错误"),
    BUYER_INFO_FORM_ERROR(8,"用户信息表单错误"),
    SELLER_INFO_FORM_ERROR(9,"商家信息表单错误"),
    STOCKS_INFO_FORM_ERROR(10,"库存信息表单错误"),
    STOCKS_INFO_HAS_EXISTED(11,"库存信息已存在"),
    STOCKS_NOT_ENOUGH(12,"商家库存量不足");


    private String msg;
    private Integer code;


    ResultEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
