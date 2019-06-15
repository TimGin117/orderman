package com.mydemo.orderman.repository;

import com.mydemo.orderman.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Optional<Order> findById(Integer id);

    void deleteById(Integer id);

    @Query(value = "SELECT COUNT(*) FROM make_order ",nativeQuery = true)
    Integer findTotalOrders();

    @Query(value = "SELECT COUNT(*) FROM make_order WHERE status=2",nativeQuery = true)
    Integer findDoneOrders();

    @Query(value = "SELECT SUM(goods.price) " +
            "FROM (make_order JOIN stocks ON make_order.stocks_id = stocks.id) " +
            "JOIN goods ON  stocks.goods_id =goods.id",nativeQuery = true)
    Integer findOrderIncome();

    @Query(value = "SELECT SUM(goods.price) " +
            "FROM (make_order JOIN stocks ON make_order.stocks_id = stocks.id) " +
            "JOIN goods ON  stocks.goods_id =goods.id " +
            "WHERE status=2",nativeQuery = true)
    Integer findTotalIncome();

    @Query(value = "SELECT * FROM make_order ORDER BY id DESC LIMIT ?1,?2",nativeQuery = true)
    List<Order> findCurrentPageOrders(Integer current, Integer pageSize);
}
