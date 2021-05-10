package com.ambow.pss.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author JYH
 */
@Data
public class SysUser {
    private Integer id;

    private Integer role_id;

    private String user_name;

    private String login_account;

    private String password;

    private String tel;

    private String wx_account;

    private String id_no;

    private String address;

    private Date create_time;

    private Date update_time;

    private Boolean deleted;

    private String role_name;

    private Role role;

    private List<Role> roleList;

    public SysUser() {
    }

    public SysUser(Integer id, Integer role_id, String user_name, String login_account, String password, String tel, String wx_account, String id_no, String address, Date create_time, Date update_time, Boolean deleted, String role_name, Role role, List<Role> roleList) {
        this.id = id;
        this.role_id = role_id;
        this.user_name = user_name;
        this.login_account = login_account;
        this.password = password;
        this.tel = tel;
        this.wx_account = wx_account;
        this.id_no = id_no;
        this.address = address;
        this.create_time = create_time;
        this.update_time = update_time;
        this.deleted = deleted;
        this.role_name = role_name;
        this.role = role;
        this.roleList = roleList;
    }
}