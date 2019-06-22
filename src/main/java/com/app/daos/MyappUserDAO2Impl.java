/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.models.MyappUser;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sandeepkumar
 */
@Component
public class MyappUserDAO2Impl implements MyappUserDAO2 {

    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public MyappUser getUserByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
        MyappUser user = (MyappUser)session.get(MyappUser.class, userId);
        tran.commit();
        session.close();
        return user;
    }

    @Override
    public MyappUser getUserByLoginName(String loginName) {
        Session session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
        Query query = session.createQuery("from MyappUser u where u.loginName = ?");
        query.setString(0, loginName);
        List result = query.list();
        MyappUser user = (null != result && !result.isEmpty())?(MyappUser)result.get(0):null;
        tran.commit();
        session.close();
        return user;
    }
    
}
