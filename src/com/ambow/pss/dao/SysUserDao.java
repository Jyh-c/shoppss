package com.ambow.pss.dao;

import java.sql.Connection;

import com.ambow.pss.model.SysUser;

/**
 * 用户表，数据访问层
 * @author JYH
 */
public class SysUserDao extends BasicDao<SysUser> {

	public SysUserDao(Connection conn) {
		super(conn);
	}

}
