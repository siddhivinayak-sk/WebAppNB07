package com.app.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

@Entity
@Table(name = "SESSIONS")
public class Sessions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GenericGenerator(name = "generator", strategy = "guid", parameters = {  })
//    @GeneratedValue(generator = "generator")
    @Column(name = "SESSION_ID", nullable = false)
    private String id;

    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    @ManyToOne(optional = false)
//    @Transient
    private MyappUser user;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENT_STEP", nullable = false)
    private SessionStep currentStep = SessionStep.NONE;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_CREATED")
    private Date timeCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRY_TIME")
    private Date expiryTime;
    
    @Column(name = "DIRECT_REQUEST_URL")
    private String directRequestedUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MyappUser getUser() {
		return user;
	}

	public void setUser(MyappUser user) {
		this.user = user;
	}

	public SessionStep getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(SessionStep currentStep) {
		this.currentStep = currentStep;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getDirectRequestedUrl() {
		return directRequestedUrl;
	}

	public void setDirectRequestedUrl(String directRequestedUrl) {
		this.directRequestedUrl = directRequestedUrl;
	}

    public static Sessions getNewSessions(HttpServletRequest request, int expiry, MyappUser user) {
    	UUID uuid = UUID.randomUUID();
    	Sessions sessions = new Sessions();
    	sessions.setId(uuid.toString());
    	sessions.setCurrentStep(SessionStep.NONE);
    	sessions.setDirectRequestedUrl("/");
    	Calendar calendar = Calendar.getInstance();
    	sessions.setTimeCreated(calendar.getTime());
    	calendar.add(Calendar.MINUTE, expiry);
    	sessions.setExpiryTime(calendar.getTime());
    	sessions.setIpAddress(request.getRemoteAddr());
    	sessions.setUser(user);
    	return(sessions);
    }
}
