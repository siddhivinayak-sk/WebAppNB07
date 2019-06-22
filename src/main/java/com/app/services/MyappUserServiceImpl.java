package com.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.configured.BaseConfigured;
import com.app.models.MyappRoles;
import com.app.models.MyappUser;
import com.app.repositories.MyappUserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

//@Transactional
@Service
public class MyappUserServiceImpl implements MyappUserService {

	@Autowired
	private MyappUserRepository myappUserRepository;
	
	@Value("${driverClassName}")
	private String dbDriver;

	@Value("${version}")
	private String version;
	
	
	public MyappUser getUserByUserId(Integer userId) {
		return(myappUserRepository.getUserByUserId(userId));
	}

	public MyappUser getUserByLoginName(String loginName) {
		System.out.println(dbDriver + " -- " + version); 
		MyappUser user = myappUserRepository.getUserByLoginName(loginName);
		return(user);
	}

	public String getBaseMessage() {
		BaseConfigured base = new BaseConfigured();
		return(base.getBaseMessage());
	}
	
        @Override
        @Cacheable("appuserlist")
        public List<MyappUser> getAllMyappUser() {
            List<MyappUser> al = myappUserRepository.findAll();
            return al;
        }
        
        @Override
        @Cacheable(value = "appuserbyloginid", key = "#loginId")
        public MyappUser getMyappUserByLoginId(String loginId) {
            MyappUser mu = myappUserRepository.getUserByLoginName(loginId);
            return mu;
        }

        @Override
        @CacheEvict(value = "appuserlist", allEntries = true)
        public void evictAppuserList() {}

        @Override
        @CacheEvict(value = "appuserbyloginid", allEntries = true)
        public void evictAppuserByLoginId() {}
        
        @Override
        @Caching(evict = {@CacheEvict(value = "appuserlist", allEntries = true), @CacheEvict(value = "appuserbyloginid", allEntries = true)})
        public void evict() {}
}
