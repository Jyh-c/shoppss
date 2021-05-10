package com.ambow.pss.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * biz_sale
 * @author 
 */
@Data
public class Sale implements Serializable {
    private Integer id;

    /**
     * 商品id，外键关联biz_product表
     */
    private String product_bar_code;

    private BigDecimal sale_price;

    /**
     * 销售日期
     */
    private String sale_date;

    /**
     * 销售数量
     */
    private Integer sale_count;

    private BigDecimal sale_amount;

    /**
     * 毛利
     */
    private Long gross_profit;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private String product_name;

    private static final long serialVersionUID = 1L;

    public Sale() {
    }

    public Sale(Integer id, String product_bar_code, BigDecimal sale_price, String sale_date, Integer sale_count, BigDecimal sale_amount, Long gross_profit, Date create_time, Date update_time, Boolean deleted, String product_name) {
        this.id = id;
        this.product_bar_code = product_bar_code;
        this.sale_price = sale_price;
        this.sale_date = sale_date;
        this.sale_count = sale_count;
        this.sale_amount = sale_amount;
        this.gross_profit = gross_profit;
        this.create_time = create_time;
        this.update_time = update_time;
        this.deleted = deleted;
        this.product_name = product_name;
    }
}