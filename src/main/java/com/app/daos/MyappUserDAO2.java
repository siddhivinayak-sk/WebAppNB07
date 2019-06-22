package com.app.daos;

import com.app.models.MyappUser;

public interface MyappUserDAO2 {
	public MyappUser getUserByUserId(Integer userId);
	public MyappUser getUserByLoginName(String loginName);
}
