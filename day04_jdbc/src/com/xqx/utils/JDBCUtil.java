package com.xqx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * createAuthor：xingquanxiang
 * createTime：2019/8/28 11:46
 * description:
 */
public class JDBCUtil {
    private static String url = null;
    private static String username = null;
    private static String password = null;
    //加载驱动类
    static {
        //加载配置文件
        //创建properties
        Properties properties = new Properties();
        //使用类加载器获取src路径中文件输入流对象
        // ClassLoader loader = JDBCUtil.class.getClassLoader();
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            //加载配置文件
            properties.load(is);
            //加载驱动
            Class.forName(String.valueOf(properties.get("driver")));
            // 获取数据库配置
            url = String.valueOf(properties.get("url"));
            username = String.valueOf(properties.getProperty("username"));
            password = String.valueOf(properties.getProperty("password"));
        } catch (ClassNotFoundException e) {
            System.out.println("驱动类未找到");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取数据连接对象
    public Connection getConnection(){
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("未连接到数据库！");
            e.printStackTrace();
        }
        return connection;
    }
    //释放资源
    public void close(ResultSet rs, Statement st, Connection con){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //释放资源
    public void close(Statement st, Connection con){
        close(null, st,con);
    }

}
