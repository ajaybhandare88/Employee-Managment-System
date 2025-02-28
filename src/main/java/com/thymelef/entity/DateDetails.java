package com.thymelef.entity;

import java.sql.Date;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class DateDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date date;
	private LocalTime inTime;
	private LocalTime outTime;
	private boolean present;
	private boolean leaves;
	private boolean halfDay;
	private int overTime;
	private int dayTotal;
	
	@ManyToOne
	@JsonBackReference
	private Employee employee;

	public DateDetails(int id, Date date, LocalTime inTime, LocalTime outTime, boolean present, boolean leaves,
			boolean halfDay, int overTime, int dayTotal, Employee employee) {
		super();
		this.id = id;
		this.date = date;
		this.inTime = inTime;
		this.outTime = outTime;
		this.present = present;
		this.leaves = leaves;
		this.halfDay = halfDay;
		this.overTime = overTime;
		this.dayTotal = dayTotal;
		this.employee = employee;
	
	}

	public DateDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LocalTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}

	public LocalTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalTime outTime) {
		this.outTime = outTime;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public boolean isLeaves() {
		return leaves;
	}

	public void setLeaves(boolean leaves) {
		this.leaves = leaves;
	}

	public boolean isHalfDay() {
		return halfDay;
	}

	public void setHalfDay(boolean halfDay) {
		this.halfDay = halfDay;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	public int getDayTotal() {
		return dayTotal;
	}

	public void setDayTotal(int dayTotal) {
		this.dayTotal = dayTotal;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	

	@Override
	public String toString() {
		return "DateDetails [id=" + id + ", date=" + date + ", inTime=" + inTime + ", outTime=" + outTime + ", present="
				+ present + ", leaves=" + leaves + ", halfDay=" + halfDay + ", overTime=" + overTime + ", dayTotal="
				+ dayTotal + ", employee=" + employee + "]";
	}
	
	

	

}
