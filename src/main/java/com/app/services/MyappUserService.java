package com.app.services;

import com.app.models.MyappUser;
import java.util.List;

public interface MyappUserService {
	public MyappUser getUserByUserId(Integer userId);
	public MyappUser getUserByLoginName(String loginName);
	public String getBaseMessage();
        public List<MyappUser> getAllMyappUser();
        public MyappUser getMyappUserByLoginId(String loginId);
        public void evictAppuserByLoginId();
        public void evictAppuserList();
        public void evict();
}
