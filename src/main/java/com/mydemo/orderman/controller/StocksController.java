package com.mydemo.orderman.controller;

import com.mydemo.orderman.VO.ResultVO;
import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.form.StocksInfoForm;
import com.mydemo.orderman.form.UpdateStocksForm;
import com.mydemo.orderman.model.Stocks;
import com.mydemo.orderman.service.StocksService;
import com.mydemo.orderman.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/stocks")
public class StocksController {

    @Autowired
    private StocksService stocksService;

    @PostMapping("/add")
    public ResultVO stocksAdd(@RequestBody@Valid StocksInfoForm stocksInfoForm,
                            BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            log.info("库存表单信息错误");
            return ResultUtil.error(ResultEnum.STOCKS_INFO_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        Stocks stocks = stocksService.addStocks(stocksInfoForm);

        log.info("新增库存：stocks={}",stocks);
        return ResultUtil.success(stocks);
    }

    @GetMapping("/total")
    public ResultVO stocksTotal(){
        return ResultUtil.success(stocksService.findTotalStocks());
    }

    @GetMapping("/showPage")
    public ResultVO stocksShowPage(@RequestParam Integer current,@RequestParam Integer pageSize){
        return ResultUtil.success(stocksService.findCurrentPageStocks(current,pageSize));
    }

    @PostMapping("/delete/{id}")
    public ResultVO stocksDelete(@PathVariable("id") Integer stocksId){
        stocksService.deleteStocks(stocksId);
        return ResultUtil.success();
    }

    @PostMapping("/update")
    public ResultVO stocksUpdata(@RequestBody UpdateStocksForm updateStocksForm){
        stocksService.updateStocks(updateStocksForm.getStocksId(),updateStocksForm.getStockNumber());
        return ResultUtil.success();
    }

}
