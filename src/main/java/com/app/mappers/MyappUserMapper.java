package com.app.mappers;

import com.app.models.MyappUser;

public interface MyappUserMapper {
	public MyappUser getUserByUserId(Integer userId);
	public MyappUser getUserByLoginName(String loginName);

}
