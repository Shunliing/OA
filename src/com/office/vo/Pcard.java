package com.office.vo;

import java.io.Serializable;

public class Pcard implements Serializable{
	private int pcardid;
	private String time;
	private String content;
	private String subject;
	private String author;
	public int getPcardid() {
		return pcardid;
	}
	public void setPcardid(int pcardid) {
		this.pcardid = pcardid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
