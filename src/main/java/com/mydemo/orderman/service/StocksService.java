package com.mydemo.orderman.service;

import com.mydemo.orderman.converter.StocksToStocksInfoDTOConverter;
import com.mydemo.orderman.dto.StocksInfoDTO;
import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.exception.OrdermanException;
import com.mydemo.orderman.form.StocksInfoForm;
import com.mydemo.orderman.model.Goods;
import com.mydemo.orderman.model.Order;
import com.mydemo.orderman.model.Seller;
import com.mydemo.orderman.model.Stocks;
import com.mydemo.orderman.repository.StocksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.PrinterAbortException;
import java.util.List;

@Slf4j
@Service
public class StocksService {

    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private GoodsService goodsService;

    public Stocks findOne(Integer id){
         return stocksRepository.findById(id).orElse(null);
    }

    public Stocks findOne(Integer goodsId, Integer sellerId){
        return stocksRepository.findStocksByGoodsIdAndSellerId(goodsId,sellerId);
    }

    public Stocks addStocks(StocksInfoForm stocksInfoForm){

        Seller seller=sellerService.findOne(stocksInfoForm.getSellerId());
        Goods goods=goodsService.findOne(stocksInfoForm.getGoodsId());

        if(seller==null){
            log.error("商家编号不存在");
            throw new OrdermanException(ResultEnum.NO_SUCH_SELLER);
        }
        if(goods==null){
            log.error("商品编号不存在");
            throw new OrdermanException(ResultEnum.NO_SUCH_GOODS);
        }

        if(findOne(stocksInfoForm.getGoodsId(),stocksInfoForm.getSellerId())!=null){
            log.error("库存信息已存在");
            throw new OrdermanException(ResultEnum.STOCKS_INFO_HAS_EXISTED);
        }

            Stocks stocks = new Stocks();
            stocks.setSeller(seller);
            stocks.setGoods(goods);
            stocks.setStockNumber(stocksInfoForm.getStockNumber());

            return stocksRepository.save(stocks);
    }

    public Stocks updateStocks(Integer id,Integer stockNumber){

        Stocks stocks = findOne(id);

        if(stocks==null){
            log.error("库存信息不存在");
            throw new OrdermanException(ResultEnum.NO_SUCH_STOCKS);
        }

        stocks.setStockNumber(stockNumber);

        return stocksRepository.save(stocks);
    }

    public List<StocksInfoDTO> findCurrentPageStocks(Integer current, Integer pageSize){

        return StocksToStocksInfoDTOConverter.convert(
                stocksRepository.findCurrentPageStocks(
                        (current-1)*pageSize,pageSize));

    }

    public Integer findTotalStocks(){
        return stocksRepository.findTotalStocks();
    }

    public void deleteStocks(Integer id){
        stocksRepository.deleteById(id);
        log.info("删除库存信息成功：id={}",id);
    }


    public void deleteStocksByGoodsId(Integer goodsId){
        stocksRepository.deleteByGoodsId(goodsId);
        log.info("删除库存信息成功：goodsId={}",goodsId);
    }
}
