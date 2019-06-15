package com.mydemo.orderman.controller;

import com.mydemo.orderman.VO.ResultVO;
import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.form.BuyerInfoForm;
import com.mydemo.orderman.model.Buyer;
import com.mydemo.orderman.service.BuyerService;
import com.mydemo.orderman.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/add")
    public ResultVO buyerAdd(@RequestBody@Valid BuyerInfoForm buyerInfoForm,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("用户表单信息错误");
            return ResultUtil.error(ResultEnum.BUYER_INFO_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        Buyer buyer = buyerService.addBuyer(buyerInfoForm);

        return ResultUtil.success(buyer);
    }
}
