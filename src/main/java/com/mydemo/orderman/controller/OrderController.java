package com.mydemo.orderman.controller;

import com.mydemo.orderman.VO.ResultVO;
import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.form.AddOrderForm;
import com.mydemo.orderman.model.Order;
import com.mydemo.orderman.service.OrderService;
import com.mydemo.orderman.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResultVO orderAdd(@RequestBody@Valid AddOrderForm addOrderForm,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("订单表单信息错误");
            return ResultUtil.error(ResultEnum.ADD_ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        Order order=orderService.addOrder(addOrderForm);

        log.info("新增订单：order={}",order);
        return ResultUtil.success(order);
    }

    @GetMapping("/total")
    public ResultVO orderTotal(){
        return ResultUtil.success(orderService.findTotalOrders());
    }

    @GetMapping("/showPage")
    public ResultVO orderShowPage(@RequestParam Integer current ,@RequestParam Integer pageSize){
        return ResultUtil.success(orderService.findCurrentPageOrders(current,pageSize));
    }

    @PostMapping("/delete/{id}")
    public ResultVO orderDelete(@PathVariable("id") Integer orderId){
        orderService.deleteOrder(orderId);
        return ResultUtil.success();
    }

    @GetMapping("/report")
    public ResultVO orderReport(){
        return ResultUtil.success(orderService.reportOrder());
    }
}
