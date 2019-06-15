package com.mydemo.orderman.controller;

import com.mydemo.orderman.VO.ResultVO;
import com.mydemo.orderman.dto.GoodsInfoDTO;
import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.form.GoodsInfoForm;
import com.mydemo.orderman.model.Goods;
import com.mydemo.orderman.service.GoodsService;
import com.mydemo.orderman.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @PostMapping("/add")
    public ResultVO goodsAdd(@RequestBody@Valid GoodsInfoForm goodsInfoForm,
    BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("商品表单信息错误");
            return ResultUtil.error(ResultEnum.GOODS_INFO_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        Goods goods=goodsService.addGoods(goodsInfoForm);

        return ResultUtil.success(goods);
    }

    @GetMapping("/showPage")
    public ResultVO goodsShowPage(@RequestParam Integer current,@RequestParam Integer pageSize){
        return ResultUtil.success(goodsService.findCurrentPageGoods(current,pageSize));
    }

    @GetMapping("/total")
    public ResultVO goodsTotal(){
        return ResultUtil.success(goodsService.findTotalGoods());
    }

    @PostMapping("/update")
    public ResultVO goodsUpdate(@RequestBody Goods goodsInfo){
        goodsService.updateGoods(goodsInfo);
        return ResultUtil.success();
    }

    @PostMapping("/delete/{id}")
    public ResultVO goodsDelete(@PathVariable("id") Integer id){
        goodsService.deleteGoods(id);
        return ResultUtil.success();
    }
}
