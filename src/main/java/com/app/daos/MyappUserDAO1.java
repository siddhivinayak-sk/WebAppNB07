package com.app.daos;

import com.app.models.MyappUser;

public interface MyappUserDAO1 {
	public MyappUser getUserByUserId(Integer userId);
	public MyappUser getUserByLoginName(String loginName);

}
