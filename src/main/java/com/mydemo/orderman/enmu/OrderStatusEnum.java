package com.mydemo.orderman.enmu;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    WAIT(0,"待处理"),
    SENDING(1,"派送中"),
    DONE(2,"已完成");

    private String msg;
    private Integer code;


    OrderStatusEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
