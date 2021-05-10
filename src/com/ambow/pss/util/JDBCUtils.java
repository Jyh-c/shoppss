package com.ambow.pss.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/10 11:15
 */
public class JDBCUtils {

    private void init() throws IOException {
        InputStream is = JDBCUtils.class.getResourceAsStream("/db.properties");
            Properties properties = new Properties();
            properties.load(is);
            getJdbcUtils();
            initFormProperties(properties);
    }

    private void initConnection(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFormProperties(Properties properties){
        DRIVER = properties.getProperty("jdbc-driver");
        URL = properties.getProperty("jdbc-url");
        USER_NAME = properties.getProperty("jdbc-username");
        PASSWORD = properties.getProperty("jdbc-password");
        initConnection();
    }

    public Connection getConnection(){
        return conn;
    }

    public JDBCUtils getJdbcUtils(){
        if(jdbcUtils == null){
            synchronized (JDBCUtils.class){
                if(jdbcUtils == null){
                    jdbcUtils = new JDBCUtils();
                }
            }
        }
       return jdbcUtils;
    }

    private JDBCUtils() {

    }

    private static String DRIVER;
    private static String URL;
    private static String USER_NAME;
    private static String PASSWORD;
    private Connection conn;
    private JDBCUtils jdbcUtils;

    public static void main(String[] args) throws IOException {
        JDBCUtils jdbc = new JDBCUtils();
        jdbc.init();
    }
}
