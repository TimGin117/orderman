package com.mydemo.orderman.dto;

import lombok.Data;

@Data
public class OrderReportDTO {

    private Integer totalOrders;

    private Integer doneOrders;

    private Integer orderIncome;

    private Integer totalIncome;
}
