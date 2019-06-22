package com.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.app.models.Sessions;

public interface SessionsRepository extends CrudRepository<Sessions, Long>  {
	@Query("select s from Sessions s where s.id = ?1")
	public Sessions findSessionsBySessionsId(String sessionsId);
}
