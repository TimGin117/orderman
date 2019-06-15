package com.mydemo.orderman.utils;

import com.mydemo.orderman.VO.ResultVO;
import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.exception.OrdermanException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ResultUtil {



    public static ResultVO success(Object object){
        ResultVO result=new ResultVO();
        result.setCode(0);
        result.setMsg("返回成功");
        result.setData(object);
        return result;
    }

    //返回但无数据
    public static ResultVO success(){return success(null);}

    public static ResultVO error(Object object){
        ResultVO result=new ResultVO();
        if(object instanceof ResultEnum){
            result.setCode(((ResultEnum)object).getCode());
            result.setMsg(((ResultEnum)object).getMsg());
        }else if(object instanceof OrdermanException){
            result.setCode(((OrdermanException) object).getCode());
            result.setMsg(((OrdermanException) object).getMsg());
        }else{
            log.error("[系统异常]:通用返回类传入对象类型错误");
            throw new OrdermanException(ResultEnum.TYPE_ERROR);
        }
        result.setData(null);
        return result;
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO result=new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

}
