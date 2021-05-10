package com.ambow.pss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * jdbc工具类，单例模式
 * @author bethanwang
 *
 */
public class JDBCUtil {
	
	/**
	 * 返回数据库链接对象
	 * @return
	 */
	public Connection getConnection() {
		return this.conn;
	}
	
	/**
	 * 通过properties对象，获取并设置数据库连接属性
	 */
	public void initFromProperties(Properties pro) {
		driverClass = pro.getProperty("jdbc-driver");
		url = pro.getProperty("jdbc-url");
		userName = pro.getProperty("jdbc-username");
		password = pro.getProperty("jdbc-password");

		System.out.println("url=" + url);
		initConnection();
	}
	
	/**
	 * 初始化数据库连接
	 */
	private void initConnection() {
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取实例
	 * @return
	 */
	public static JDBCUtil getInstance() {
		if(jdbcUtil == null) {
			synchronized(JDBCUtil.class) {
				if (jdbcUtil == null) {
					jdbcUtil = new JDBCUtil();
				}
			}
		}
		return jdbcUtil;
	}

	/**
	 * 私有化构造器，用于单例模式
	 */
	public JDBCUtil() {
		
	}
	
	//数据库链接
	private Connection conn;
	//单例对象
	private static JDBCUtil jdbcUtil;
	//数据库连接属性
	private String driverClass;
	private String url;
	private String userName;
	private String password;
	
}
