package com.xqx.test;

import com.xqx.dao.impl.AccountDaoImpl;
import com.xqx.domain.Account;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * createAuthor：xingquanxiang
 * createTime：2019/8/28 15:03
 * description:
 */
public class AccountDaoTest {
    private AccountDaoImpl accountDao = null;
    @Before
    public void before(){
        this.accountDao = new AccountDaoImpl();
    }

    @Test
    public void testAdd() {
        //添加数据 测试
        int rows = accountDao.add(new Account(0, "何永辉", 20000));
        if (rows == 1) {
            System.out.println("添加数据成功！");
        } else {
            System.out.println("添加数据失败！");
        }

    }

    @Test
    public void testDelete() {
        //删除数据
        int rows = accountDao.delete(1);
        if (rows == 1) {
            System.out.println("删除数据成功！");
        } else {
            System.out.println("删除数据失败！");
        }
    }

    @Test
    public void testSelect() {
        //查询数据
        ArrayList<Account> accounts = accountDao.select();
        if (accounts != null) {
            accounts.forEach(account -> System.out.println(account));
        } else {
            System.out.println("未查询到数据");
        }
    }

    // 测试单个查询
    @Test
    public void testSelectOne(){
        Account account = accountDao.select(3);
        if(account!=null){
            System.out.println(account);
        }else{
            System.out.println("未查询到数据！");
        }
    }
    //更新数据
    @Test
    public void testUpdate(){
        int rows = accountDao.update(new Account(2, "张亚鑫", 20000));
        if (rows==1){
            System.out.println("更新数据成功！");
        }else{
            System.out.println("更新数据失败！");
        }
    }
}
