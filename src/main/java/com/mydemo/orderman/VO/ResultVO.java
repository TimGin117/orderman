package com.mydemo.orderman.VO;

import lombok.Data;
import org.springframework.format.number.PercentStyleFormatter;

import java.io.Serializable;
import java.util.concurrent.ScheduledFuture;

@Data
public class ResultVO {

    private Integer code;

    private String msg;

    private Object data;

}
