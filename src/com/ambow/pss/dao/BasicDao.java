package com.ambow.pss.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * @author JYH
 */
public class BasicDao<T> {

	/**
	 * 查询单条数据
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public T queryOne(String sql, Object[] params, Class<T> clazz) throws Exception {
		T t = runner.query(conn, sql, new BeanHandler<>(clazz), params);
		return t;
	}
	
	/**
	 * 查询数据列表
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public List<T> listQuery(String sql, Object[] params, Class<T> clazz) throws Exception {
		List<T> list = runner.query(conn, sql, new BeanListHandler<T>(clazz), params);
		return list;
	}
	
	/**
	 * 更新数据
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int update(String sql, Object[] params) throws Exception {
		int count = runner.update(conn, sql, params);
		return count;
	}
	
	/**
     * 添加数据，返回主键
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public int addWithBackPK(String sql, Object[] params) throws Exception {
        PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        for (Object obj : params) {
            preparedStatement.setObject(i, obj);
            i++;
        }
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        long generateKey = 0;
        while (generatedKeys.next()) {
            generateKey = generatedKeys.getLong(1);
        }
        return (int)generateKey;
    }
    
    /**
     * 批量更新，批量对数据库写操作
     * @param sql
     * @param params
     * @return 更改条数
     * @throws Exception
     */
    public int updateBatch(String sql, Object[][] params) throws Exception {
        QueryRunner runner = new QueryRunner();
        int cou = runner.batch(conn, sql, params).length;
        return cou;
    }
	
	public BasicDao(Connection conn) {
		this.conn = conn;
	}
	
	private Connection conn;
	private QueryRunner runner = new QueryRunner();
	
}
