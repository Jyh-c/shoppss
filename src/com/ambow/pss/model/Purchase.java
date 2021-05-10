package com.ambow.pss.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * biz_purchase
 * @author JYH
 */
@Data
public class Purchase implements Serializable {

    private Integer id;

    /**
     * 商品条形码
     * private int product_id;
     */

    private String product_barcode;

    /**
     * 采购日期
     */
    private String purchase_date;

    /**
     * 生产日期
     */
    private String pro_date;

    /**
     * 保质期
     */
    private String exp_date;

    /**
     * 进货单价
     */
    private BigDecimal purchase_price;

    /**
     * 进货数量，最小库存单位
     */
    private Integer count;

    /**
     * 这一批货，进货花费总金额
     */
    private BigDecimal amount;

    private String receipt_img;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public Purchase() {
    }

    public Purchase(Integer id, String product_barcode, String purchase_date, String pro_date, String exp_date, BigDecimal purchase_price, Integer count, BigDecimal amount, String receipt_img, Date create_time, Date update_time, Boolean deleted) {
        this.id = id;
        this.product_barcode = product_barcode;
        this.purchase_date = purchase_date;
        this.pro_date = pro_date;
        this.exp_date = exp_date;
        this.purchase_price = purchase_price;
        this.count = count;
        this.amount = amount;
        this.receipt_img = receipt_img;
        this.create_time = create_time;
        this.update_time = update_time;
        this.deleted = deleted;
    }
}