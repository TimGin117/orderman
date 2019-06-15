package com.mydemo.orderman.service;

import com.mydemo.orderman.converter.OrderToOrderInfoDTOConverter;
import com.mydemo.orderman.dto.OrderInfoDTO;
import com.mydemo.orderman.dto.OrderReportDTO;
import com.mydemo.orderman.enmu.ResultEnum;
import com.mydemo.orderman.exception.OrdermanException;
import com.mydemo.orderman.form.AddOrderForm;
import com.mydemo.orderman.model.Buyer;
import com.mydemo.orderman.model.Goods;
import com.mydemo.orderman.model.Order;
import com.mydemo.orderman.model.Stocks;
import com.mydemo.orderman.repository.OrderRepository;
import com.mydemo.orderman.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private StocksService stocksService;

    public Order findOne(Integer id){
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(AddOrderForm addOrderForm){

        Buyer buyer=buyerService.findOne(addOrderForm.getBuyerId());

        Stocks stocks=stocksService.findOne(addOrderForm.getStocksId());

        if(buyer==null){
            log.error("用户编号不存在");
            throw new OrdermanException(ResultEnum.NO_SUCH_BUYER);
        }
        if(stocks==null){
            log.error("库存编号不存在");
            throw new OrdermanException(ResultEnum.NO_SUCH_STOCKS);
        }
        if(stocks.getStockNumber()==0){
            log.error("商家库存量不足");
            throw new OrdermanException(ResultEnum.STOCKS_NOT_ENOUGH);
        }

        //下单后库存量-1
        Stocks newStocks=stocksService.updateStocks(stocks.getId(),stocks.getStockNumber()-1);

        Order order=new Order();
        order.setBuyer(buyer);
        order.setStocks(newStocks);
        order.setStatus(0);//下单后状态为待处理

        return orderRepository.save(order);
    }

    public Integer findTotalOrders(){
        return orderRepository.findTotalOrders();
    }

    public List<OrderInfoDTO> findCurrentPageOrders(Integer current,Integer pageSize){

        return OrderToOrderInfoDTOConverter.convert(
                orderRepository.findCurrentPageOrders(
                        (current-1)*pageSize,pageSize));

    }

    public void deleteOrder(Integer id){
        Order order =findOne(id);
        if(order.getStatus()==0){
            stocksService.updateStocks(order.getStocks().getId(),
                    order.getStocks().getStockNumber()+1);
        }
        orderRepository.deleteById(id);
        log.info("删除订单信息成功：id={}",id);
    }

    public OrderReportDTO reportOrder(){

        OrderReportDTO orderReportDTO=new OrderReportDTO();

        orderReportDTO.setTotalOrders(orderRepository.findTotalOrders());
        orderReportDTO.setDoneOrders(orderRepository.findDoneOrders());
        orderReportDTO.setOrderIncome(orderRepository.findOrderIncome());
        orderReportDTO.setTotalIncome(orderRepository.findTotalIncome());

        return orderReportDTO;
    }

}
