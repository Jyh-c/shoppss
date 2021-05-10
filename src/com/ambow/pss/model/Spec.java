package com.ambow.pss.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * biz_spec
 * @author JYH
 */
@Data
public class Spec implements Serializable {
    private Integer id;

    private String spec_name;

    private String spec_val;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private static final long serialVersionUID = 1L;

}