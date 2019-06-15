package com.mydemo.orderman.controller;

import com.mydemo.orderman.VO.ResultVO;
import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.form.SellerInfoForm;
import com.mydemo.orderman.model.Seller;
import com.mydemo.orderman.service.SellerService;
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
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/add")
    public ResultVO sellerAdd(@RequestBody@Valid SellerInfoForm sellerInfoForm,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("商家表单信息错误");
            return ResultUtil.error(ResultEnum.SELLER_INFO_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        Seller seller=sellerService.addSeller(sellerInfoForm);

        return ResultUtil.success(seller);
    }
}
