package com.ambow.pss.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * biz_stock
 * @author JYH
 */
@Data
public class Stock implements Serializable {

    private String product_bar_code;

    /**
     * 库存数量
     */
    private Integer stock_count;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private String lock_key;

    private String category_name;

    private String product_name;

    private String supplier_name;

    private String contacts_name;

    private String tel;

    private static final long serialVersionUID = 1L;

    public Stock() {
    }

    public Stock(String product_bar_code, Integer stock_count, Date create_time, Date update_time, Boolean deleted, String lock_key, String category_name, String product_name, String supplier_name, String contacts_name, String tel) {
        this.product_bar_code = product_bar_code;
        this.stock_count = stock_count;
        this.create_time = create_time;
        this.update_time = update_time;
        this.deleted = deleted;
        this.lock_key = lock_key;
        this.category_name = category_name;
        this.product_name = product_name;
        this.supplier_name = supplier_name;
        this.contacts_name = contacts_name;
        this.tel = tel;
    }
}