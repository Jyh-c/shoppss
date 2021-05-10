package com.ambow.pss.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * biz_category
 * @author JYH
 */
@Data
public class Category implements Serializable {
    private Integer id;

    /**
     * 品类名称
     */
    private String category_name;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public Category() {
    }

    public Category(Integer id, String category_name, Date create_time, Date update_time, Boolean deleted) {
        this.id = id;
        this.category_name = category_name;
        this.create_time = create_time;
        this.update_time = update_time;
        this.deleted = deleted;
    }
}