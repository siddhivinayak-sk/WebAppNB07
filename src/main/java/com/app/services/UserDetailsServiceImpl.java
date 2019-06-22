/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.services;

import com.app.models.MyappPermissions;
import com.app.models.MyappUser;
import com.app.models.SessionUser;
import com.app.repositories.MyappUserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author sandeepkumar
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    MyappUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyappUser user;
        SessionUser rUser = null;
        try {
            user = userRepository.getUserByLoginName(username);
            rUser = new  SessionUser(user.getLoginName(), user.getLoginPassword(), (null != user.getEnabled())?true:false, true, true, true, getAuthorities(user));
            if(rUser != null) {
                rUser.setMyappUser(user);
            }
        }
        catch(Exception e) {
            throw new UsernameNotFoundException(username + " not found!");
        }
        return rUser;
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(MyappUser user) {
        Collection<SimpleGrantedAuthority> out = new ArrayList<>();
        if(null != user && null != user.getRole() && null != user.getRole().getMyappPermissions()) {
            for(MyappPermissions p:user.getRole().getMyappPermissions()) {
                out.add(new SimpleGrantedAuthority(p.getPermissions()));
            }
        }
        return out;
    }
    
}
