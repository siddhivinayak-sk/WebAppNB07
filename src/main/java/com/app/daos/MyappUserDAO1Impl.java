/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.models.MyappUser;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sandeepkumar
 */
//@Component
public class MyappUserDAO1Impl implements MyappUserDAO1 {
    
    //@PersistenceUnit
    //EntityManagerFactory emf;
    
    //@PersistenceContext
    //private EntityManager em;    

    @Override
    //@Transactional
    public MyappUser getUserByUserId(Integer userId) {
        //System.out.println(em);
        //System.out.println(emf);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    //@Transactional
    public MyappUser getUserByLoginName(String loginName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
