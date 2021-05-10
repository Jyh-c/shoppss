package com.ambow.pss.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author JYH
 */
@Data
public class Product implements Serializable {
    /**
     * bar_code，商品条形码作为主键
     */
    private int id;

    private String bar_code;

    /**
     * 供应商id，关联biz_supplier表
     */
    private Integer supplier_id;

    /**
     * 供应商名称
     */
    private String supplier_name;

    /**
     * 品类id，关联biz_category表
     */
    private Integer category_id;
    /**
     * 品类名称
     */
    private String category_name;

    private List<Category> categoryList;

    /**
     * 商品名称
     */
    private String product_name;

    private BigDecimal sale_price;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private List<Supplier> supplierList;

    private int stock_count;

    /**
     * 保质期
     */
    private String exp_date;

    /**
     * 进货批次号
     */
    private String batch_code;

    private BigDecimal purchase_price;

    private String purchase_count;

    private BigDecimal purchase_amount;

    private static final long serialVersionUID = 1L;

    public Product() {
    }

    public Product(int id, String bar_code, Integer supplier_id, String supplier_name, Integer category_id, String category_name, List<Category> categoryList, String product_name, BigDecimal sale_price, Date create_time, Date update_time, Boolean deleted, List<Supplier> supplierList, int stock_count, String exp_date, String batch_code, BigDecimal purchase_price, String purchase_count, BigDecimal purchase_amount) {
        this.id = id;
        this.bar_code = bar_code;
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.category_id = category_id;
        this.category_name = category_name;
        this.categoryList = categoryList;
        this.product_name = product_name;
        this.sale_price = sale_price;
        this.create_time = create_time;
        this.update_time = update_time;
        this.deleted = deleted;
        this.supplierList = supplierList;
        this.stock_count = stock_count;
        this.exp_date = exp_date;
        this.batch_code = batch_code;
        this.purchase_price = purchase_price;
        this.purchase_count = purchase_count;
        this.purchase_amount = purchase_amount;
    }
}