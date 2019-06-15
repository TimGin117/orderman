package com.mydemo.orderman.service;

import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.exception.OrdermanException;
import com.mydemo.orderman.form.GoodsInfoForm;
import com.mydemo.orderman.model.Goods;
import com.mydemo.orderman.repository.GoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StocksService stocksService;

    public Goods findOne(Integer id){
        Optional<Goods> goods = goodsRepository.findById(id);
        return goods.orElse(null);
    }

    public Goods addGoods(GoodsInfoForm goodsInfoForm){

        Goods goods = new Goods();
        goods.setName(goodsInfoForm.getGoodsName());
        goods.setPrice(goodsInfoForm.getPrice());

        return goodsRepository.save(goods);
    }

    public Goods updateGoods(Goods goodsInfo){

        Goods goods = findOne(goodsInfo.getId());

        if(goods==null){
            log.error("商品不存在");
            throw new OrdermanException(ResultEnum.NO_SUCH_GOODS);
        }

        goods.setName(goodsInfo.getName());
        goods.setPrice(goodsInfo.getPrice());

        return goodsRepository.save(goods);
    }

    public void deleteGoods(Integer id){

        stocksService.deleteStocksByGoodsId(id);

        goodsRepository.deleteById(id);
        log.info("删除商品成功，id={}",id);
    }

    public List<Goods> findCurrentPageGoods(Integer current,Integer pageSize){
        return goodsRepository.findCurrentPageGoods((current-1)*pageSize,pageSize);
    }

    public Integer findTotalGoods(){
        return goodsRepository.findTotalGoods();
    }

}
