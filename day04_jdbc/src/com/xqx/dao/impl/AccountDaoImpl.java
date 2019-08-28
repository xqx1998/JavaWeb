package com.xqx.dao.impl;

import com.xqx.dao.AccountDao;
import com.xqx.domain.Account;
import com.xqx.utils.JDBCUtil;
import org.junit.Test;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * createAuthor：xingquanxiang
 * createTime：2019/8/28 14:42
 * description:
 */
public class AccountDaoImpl implements AccountDao {
    private JDBCUtil jdbc = new JDBCUtil();
    //添加数据
    public int add(Account account) {
        Connection connection = jdbc.getConnection();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = connection.prepareStatement("insert into account(name ,balance) values(?,?);");
            ps.setString(1, account.getName());
            ps.setDouble(2, account.getBalance());
            //执行sql语句并获得返回值 1行数据受影响，则返回1
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            jdbc.close(ps, connection);
        }
        return rows;
    }

    //删除数据
    public int delete(int id) {
        Connection connection = jdbc.getConnection();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = connection.prepareStatement("delete from account where id=?");
            ps.setInt(1, id);
            //执行sql语句并获得返回值 1行数据受影响，则返回1
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            jdbc.close(ps, connection);
        }
        return rows;
    }

    //查询数据
    public ArrayList<Account> select() {
        Connection connection = jdbc.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            ps = connection.prepareStatement("select id, name, balance from account");
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                accounts.add(new Account(resultSet.getInt("id"),
                        resultSet.getString("name"),resultSet.getDouble("balance")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            jdbc.close(resultSet, ps, connection);
        }
        return accounts;
    }

    @Override
    public Account select(int id) {
        Account account = null;
        Connection connection = jdbc.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement("select id, name, balance from account");
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                account = new Account(resultSet.getInt("id"),
                        resultSet.getString("name"),resultSet.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            jdbc.close(resultSet, ps, connection);
        }
        return account;
    }

    //更新账户信息
    public int update(Account account){
        Connection connection = jdbc.getConnection();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = connection.prepareStatement("update account set name = ?, balance = ? where id = ?");
            ps.setString(1, account.getName());
            ps.setDouble(2, account.getBalance());
            ps.setInt(3, account.getId());
            //执行sql语句并获得返回值 1行数据受影响，则返回1
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            jdbc.close(ps, connection);
        }
        return rows;
    }

}
