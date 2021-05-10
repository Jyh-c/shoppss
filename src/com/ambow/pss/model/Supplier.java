package com.ambow.pss.model;

import lombok.Data;

import java.util.Date;

/**
 * @author JYH
 */
@Data
public class Supplier {

    private int id;
    private String supplier_name;
    private String contacts_name;
    private String tel;
    private String address;
    private Date create_time;
    private Date update_time;
    private Boolean deleted;

    public Supplier() {
    }

    public Supplier(int id, String supplier_name, String contacts_name, String tel, String address, Date create_time, Date update_time, Boolean deleted) {
        this.id = id;
        this.supplier_name = supplier_name;
        this.contacts_name = contacts_name;
        this.tel = tel;
        this.address = address;
        this.create_time = create_time;
        this.update_time = update_time;
        this.deleted = deleted;
    }

}
