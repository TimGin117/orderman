package com.mydemo.orderman.repository;

import com.mydemo.orderman.dto.GoodsInfoDTO;
import com.mydemo.orderman.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods,Integer> {

    Optional<Goods> findById(Integer id);

    void deleteById(Integer id);

    @Query(value = "SELECT * FROM goods ORDER BY id DESC LIMIT ?1,?2 ",nativeQuery = true)
    List<Goods> findCurrentPageGoods(Integer current,Integer pageSize);

    @Query(value = "SELECT COUNT(*) FROM goods ",nativeQuery = true)
    Integer findTotalGoods();

}
