package com.dlb.lemon_bank.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersExcelDto {
    @ExcelProperty("Месяц")
    @ColumnWidth(20)
    private String month;
    @ExcelProperty("Кол-во обработанных заявок")
    @ColumnWidth(25)
    private Integer countOrders;

}
