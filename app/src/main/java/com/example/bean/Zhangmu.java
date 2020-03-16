package com.example.bean;

import java.sql.Date;
/*
 * 账目
 */
public class Zhangmu {

	private int id;
	private int jine;
	private String date;

	private String fangshi;
	private String leimu;
	private String beizhu;
	private String status;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getJine() {
		return jine;
	}
	public void setJine(int jine) {
		this.jine = jine;
	}
	
	public String getFangshi() {
		return fangshi;
	}
	public void setFangshi(String fangshi) {
		this.fangshi = fangshi;
	}
	public String getLeimu() {
		return leimu;
	}
	public void setLeimu(String leimu) {
		this.leimu = leimu;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
