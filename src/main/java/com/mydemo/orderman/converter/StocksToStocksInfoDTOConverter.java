package com.mydemo.orderman.converter;

import com.mydemo.orderman.dto.StocksInfoDTO;
import com.mydemo.orderman.model.Goods;
import com.mydemo.orderman.model.Stocks;

import java.util.List;
import java.util.stream.Collectors;

public class StocksToStocksInfoDTOConverter {

    public static StocksInfoDTO convert(Stocks stocks){

        StocksInfoDTO StocksInfoDTO=new StocksInfoDTO();

        StocksInfoDTO.setStocksId(stocks.getId());
        StocksInfoDTO.setGoodsId(stocks.getGoods().getId());
        StocksInfoDTO.setGoodsName(stocks.getGoods().getName());
        StocksInfoDTO.setPrice(stocks.getGoods().getPrice());
        StocksInfoDTO.setSellerId(stocks.getSeller().getId());
        StocksInfoDTO.setShopName(stocks.getSeller().getShopName());
        StocksInfoDTO.setStockNumber(stocks.getStockNumber());

        return StocksInfoDTO;
    }

    public static List<StocksInfoDTO> convert(List<Stocks> stocksList){
        return stocksList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
