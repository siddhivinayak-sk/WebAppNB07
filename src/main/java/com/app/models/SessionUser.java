package com.app.models;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SessionUser extends User {
	private static final long serialVersionUID = 1L;
	private MyappUser myappUser;
	private Sessions sessions;
	
	public SessionUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public MyappUser getMyappUser() {
		return myappUser;
	}

	public void setMyappUser(MyappUser myappUser) {
		this.myappUser = myappUser;
	}

	public Sessions getSessions() {
		return sessions;
	}

	public void setSessions(Sessions sessions) {
		this.sessions = sessions;
	}
	
	public long getSalt() {
		return myappUser.getUserId();
	}
	
	
}
