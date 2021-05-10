package com.ambow.pss.service.impl;

import com.ambow.pss.dao.SysUserDao;
import com.ambow.pss.init.InitServlet;
import com.ambow.pss.model.SysUser;
import com.ambow.pss.service.SysUserService;
import com.ambow.pss.util.JDBCUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * @author JYH
 */
public class SysUserServiceImpl implements SysUserService {

	@Override
	public SysUser login(String loginAccount, String password) {
		SysUser user = null;
        try {
            String sql = "select u.*, r.role_name from sys_user u, sys_role r where u.role_id=r.id and u.login_account = ? and u.password = ? and u.deleted=1";
            Object[] params = {loginAccount, password};
            user = userDao.queryOne(sql, params, SysUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
	}

	@Override
	public List<SysUser> queryAll(String keyword) {
		List<SysUser> userList = null;
		String sql = "select u.*, r.role_name from sys_user u, sys_role r where u.role_id=r.id and u.deleted=1 and user_name like ?";
		Object[] params = {("%"+keyword+"%")};
		try {
			userList = userDao.listQuery(sql,params,SysUser.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public int addUser(SysUser u) {
		int pk = 0;
		String sql = "INSERT INTO sys_user (role_id,user_name,login_account,PASSWORD," +
				"tel,wx_account,id_no,address,create_time,update_time,deleted) VALUES(?,?,?,?,?,?,?,?,?,?,1)";
		Object[] params = {u.getRole_id(),u.getUser_name(),u.getLogin_account(),u.getPassword(),u.getTel(),u.getWx_account(),u.getId_no(),u.getAddress(),u.getCreate_time(),u.getUpdate_time()};
		try {
			pk = userDao.addWithBackPK(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	@Override
	public SysUser queryLoginAccount(String loginAccount) {
		SysUser sysUser = null;
		String sql = "SELECT id,role_id,user_name,login_account,PASSWORD,tel,wx_account," +
				"id_no,address,create_time,update_time,deleted FROM sys_user where login_account = ?";
		Object[] params = {loginAccount};
		try {
			sysUser = userDao.queryOne(sql,params,SysUser.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysUser;
	}

	@Override
	public SysUser queryById(int id) {
		SysUser sysUser = null;
		String sql = "SELECT id,role_id,user_name,login_account,PASSWORD,tel,wx_account," +
				"id_no,address,create_time,update_time,deleted FROM sys_user where id = ?";
		Object[] params = {id};
		try {
			sysUser = userDao.queryOne(sql,params,SysUser.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysUser;
	}

	@Override
	public int delete(int id) {
		int row = 0;
		String sql = "UPDATE sys_user SET deleted = 0 WHERE id=?";
		Object[] params = {id};
		try {
			row = userDao.update(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int update(SysUser u) {
		int row = 0;
		String sql = "update sys_user set user_name = ?," +
				"login_account = ? , password = ?,tel = ?,wx_account = ? ," +
				"id_no = ? ,address = ? ,role_id = ? where id = ?";
		Object[] params = {u.getUser_name(),u.getLogin_account(),u.getPassword(),u.getTel(),u.getWx_account(),u.getId_no(),u.getAddress(),u.getRole_id(),u.getId()};
		try {
			row = userDao.update(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}


	public SysUserServiceImpl() {
		conn = JDBCUtil.getInstance().getConnection();
		userDao = new SysUserDao(conn);
	}

	private Connection conn;
	private SysUserDao userDao;

	public static void main(String[] args) {
		try {
			System.out.println("开始加载数据库配置资源...");
			InputStream is = InitServlet.class.getResourceAsStream("/db.properties");
			Properties pro = new Properties();
			pro.load(is);
			JDBCUtil jdbcUtil = JDBCUtil.getInstance();
			jdbcUtil.initFromProperties(pro);
			System.out.println("完成加载数据库配置资源...");
		} catch(Exception e) {
			e.printStackTrace();
		}
		SysUserServiceImpl u = new SysUserServiceImpl();
		SysUser login = u.login("1", "1");
		System.out.println(login);

		List<SysUser> userList = u.queryAll("");
		for(SysUser u1 : userList){
			System.out.println(u1);
		}
	}
	
}
