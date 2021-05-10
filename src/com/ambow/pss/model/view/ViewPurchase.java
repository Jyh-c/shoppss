package com.ambow.pss.model.view;

import com.ambow.pss.model.Purchase;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 15:16
 */
@Data
public class ViewPurchase extends Purchase {
    private Integer id;
    private String supplier_name;
    private String bar_code;
    private String category_name;
    private String product_name;
    private BigDecimal purchase_price;
    private Integer count;
    private BigDecimal amount;
    private String exp_date;

    public ViewPurchase() {
    }

    public ViewPurchase(Integer id, String supplier_name, String bar_code, String category_name, String product_name, BigDecimal purchase_price, Integer count, BigDecimal amount, String exp_date) {
        this.id = id;
        this.supplier_name = supplier_name;
        this.bar_code = bar_code;
        this.category_name = category_name;
        this.product_name = product_name;
        this.purchase_price = purchase_price;
        this.count = count;
        this.amount = amount;
        this.exp_date = exp_date;
    }
}
