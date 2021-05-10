package com.ambow.pss.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * biz_stock_modify_record
 * @author JYH
 */
@Data
public class StockModifyRecord implements Serializable {

    private int id;

    private String product_bar_code;

    /**
     * 操作人员id，外键关联sys_user表
     */
    private Integer operator_id;

    /**
     * 库存变化数量，增加为正数，减少为负数
     */
    private Integer modify_count;

    /**
     * 枚举，数量变数说明：销售-SALE/退货-RETURN/进货-PURCHASE/顾客退货-CUSTOMER_RETURN
     */
    private String modify_type;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private String product_name;

    private String category_name;

    private static final long serialVersionUID = 1L;

}