package com.thymelef.entity;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.servlet.annotation.MultipartConfig;

@Entity
public class Resign {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date resignSubmitDate;
	private Date leavingDate;
	private Date settlementDate;
	private String reason;
	private String text;
	private String resignationLetterPdf;
	private Boolean resign;
	
	@OneToOne
	@JsonBackReference
	private Employee employee;

	public Resign(int id, Date resignSubmitDate, Date leavingDate, Date settlementDate, String reason, String text,
			String resignationLetterPdf, Boolean resign, Employee employee) {
		super();
		this.id = id;
		this.resignSubmitDate = resignSubmitDate;
		this.leavingDate = leavingDate;
		this.settlementDate = settlementDate;
		this.reason = reason;
		this.text = text;
		this.resignationLetterPdf = resignationLetterPdf;
		this.resign = resign;
		this.employee = employee;
	}

	public Resign() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getResignSubmitDate() {
		return resignSubmitDate;
	}

	public void setResignSubmitDate(Date resignSubmitDate) {
		this.resignSubmitDate = resignSubmitDate;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getResignationLetterPdf() {
		return resignationLetterPdf;
	}

	public void setResignationLetterPdf(String resignationLetterPdf) {
		this.resignationLetterPdf = resignationLetterPdf;
	}

	public Boolean getResign() {
		return resign;
	}

	public void setResign(Boolean resign) {
		this.resign = resign;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Resign [id=" + id + ", resignSubmitDate=" + resignSubmitDate + ", leavingDate=" + leavingDate
				+ ", settlementDate=" + settlementDate + ", reason=" + reason + ", text=" + text
				+ ", resignationLetterPdf=" + resignationLetterPdf + ", resign=" + resign + ", employee=" + employee
				+ "]";
	}

	
	
	
}
