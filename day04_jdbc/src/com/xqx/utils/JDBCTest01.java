package com.xqx.utils;

import java.sql.*;

/**
 * createAuthor：xingquanxiang
 * createTime：2019/8/28 11:46
 * description:
 */
public class JDBCTest01 {
    private String url = "utils:mysql://localhost:3306/db3";
    private String username = "root";
    private String password = "root";
    //加载驱动类
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动类未找到");
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
    public void close(ResultSet rs, PreparedStatement ps, Connection con){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps!=null){
            try {
                ps.close();
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

    public int add(String name, double balance){
        JDBCTest01 jdbc = new JDBCTest01();
        Connection connection = jdbc.getConnection();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = connection.prepareStatement("insert into account(NAME,balance) values(?,?);");
            ps.setString(1, name);
            ps.setDouble(2, balance);
            //执行sql语句并获得返回值 1行数据受影响，则返回1
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            jdbc.close(null,ps,connection);
        }
        return rows;
    }
    //删除数据
    public int delete(int id){
        JDBCTest01 jdbc = new JDBCTest01();
        Connection connection = jdbc.getConnection();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = connection.prepareStatement("delete from account where id=?");
            ps.setInt(1,id);
            //执行sql语句并获得返回值 1行数据受影响，则返回1
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            jdbc.close(null,ps,connection);
        }
        return rows;
    }
    //查询数据
    public int select(){
        JDBCTest01 jdbc = new JDBCTest01();
        Connection connection = jdbc.getConnection();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = connection.prepareStatement("select id, NAME, balancefrom account");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            jdbc.close(null,ps,connection);
        }
        return rows;
    }

    public static void main(String[] args) {
        JDBCTest01 jdbc = new JDBCTest01();
        //添加数据 测试
        int rows = jdbc.add("倖权祥",18000);
        if (rows==1){
            System.out.println("添加数据成功！");
        }else{
            System.out.println("添加数据失败！");
        }
        //删除数据
        rows = jdbc.delete(1);
        if (rows==1){
            System.out.println("添加数据成功！");
        }else{
            System.out.println("添加数据失败！");
        }
        //查询数据
        rows = jdbc.delete(1);
        if (rows==1){
            System.out.println("添加数据成功！");
        }else{
            System.out.println("添加数据失败！");
        }


    }
}
