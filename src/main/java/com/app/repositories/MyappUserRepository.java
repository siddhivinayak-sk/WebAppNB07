package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.models.MyappUser;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface MyappUserRepository extends JpaRepository<MyappUser, Long> {

	@Query("from MyappUser mu where mu.userId = ?1")
	public MyappUser getUserByUserId(Integer userId);

	@Query("from MyappUser mu where mu.loginName = ?1")
	public MyappUser getUserByLoginName(String loginName);
	
}
