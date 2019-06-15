package com.mydemo.orderman.exception;

import com.mydemo.orderman.enmu.ResultEnum;
import lombok.Data;
import lombok.Getter;

@Getter
public class OrdermanException extends RuntimeException{

    private Integer code;
    private String msg;

    public OrdermanException(ResultEnum resultEnum){
        super();
        this.code=resultEnum.getCode();
        this.msg=resultEnum.getMsg();
    }

}
