package com.mydemo.orderman.repository;

import com.mydemo.orderman.model.Goods;
import com.mydemo.orderman.model.Seller;
import com.mydemo.orderman.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StocksRepository extends JpaRepository<Stocks,Integer> {

    Optional<Stocks> findById(Integer id);

    List<Stocks> findAll();

    void deleteById(Integer id);

    Stocks findStocksByGoodsIdAndSellerId(Integer goodsId , Integer sellerId);

    @Query(value = "SELECT * FROM stocks ORDER BY id DESC LIMIT ?1,?2 ",nativeQuery = true)
    List<Stocks> findCurrentPageStocks(Integer current,Integer pageSize);

    @Query(value = "SELECT COUNT(*) FROM stocks",nativeQuery = true)
    Integer findTotalStocks();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM stocks WHERE goods_id =?1",nativeQuery = true)
    void deleteByGoodsId(Integer goodsId);

}
