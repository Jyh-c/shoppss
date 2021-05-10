package com.ambow.pss.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_role
 * @author 
 */
@Data
public class Role implements Serializable {
    private Integer id;

    private String role_name;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private static final long serialVersionUID = 1L;
    
}