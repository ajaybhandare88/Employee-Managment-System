package com.thymelef.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LeaveDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date fromLeaveDate;
	private Date toLeaveDate;
	private String leaveType;
	private String leaveDay;
	private String reason;
	private boolean LeaveIs;
	
	@ManyToOne
	@JsonBackReference
	private Employee employee;
	
	
	
	public LeaveDetails(int id, Date fromLeaveDate, Date toLeaveDate, String leaveType, String leaveDay, String reason,
			boolean leaveIs, Employee employee) {
		super();
		this.id = id;
		this.fromLeaveDate = fromLeaveDate;
		this.toLeaveDate = toLeaveDate;
		this.leaveType = leaveType;
		this.leaveDay = leaveDay;
		this.reason = reason;
		LeaveIs = leaveIs;
		this.employee = employee;
	}

	public boolean isLeaveIs() {
		return LeaveIs;
	}

	public void setLeaveIs(boolean leaveIs) {
		LeaveIs = leaveIs;
	}


	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFromLeaveDate() {
		return fromLeaveDate;
	}
	public void setFromLeaveDate(Date fromLeaveDate) {
		this.fromLeaveDate = fromLeaveDate;
	}
	public Date getToLeaveDate() {
		return toLeaveDate;
	}
	public void setToLeaveDate(Date toLeaveDate) {
		this.toLeaveDate = toLeaveDate;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveDay() {
		return leaveDay;
	}
	public void setLeaveDay(String leaveDay) {
		this.leaveDay = leaveDay;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "LeaveDetails [id=" + id + ", fromLeaveDate=" + fromLeaveDate + ", toLeaveDate=" + toLeaveDate
				+ ", leaveType=" + leaveType + ", leaveDay=" + leaveDay + ", reason=" + reason + ", LeaveIs=" + LeaveIs
				+ ", employee=" + employee + "]";
	}

	
	
}
