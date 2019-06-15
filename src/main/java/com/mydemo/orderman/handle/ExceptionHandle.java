package com.mydemo.orderman.handle;

import com.mydemo.orderman.VO.ResultVO;
import com.mydemo.orderman.exception.OrdermanException;
import com.mydemo.orderman.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = OrdermanException.class)
    public ResultVO handle(Exception e){
        OrdermanException ordermanException =(OrdermanException)e;
        return ResultUtil.error(ordermanException);
    }
}
