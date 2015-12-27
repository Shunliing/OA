package com.office.vo;
/*
 * 会议通知VO
 */
public class Meet {
	private int meetid;
	private String time;
	private String address;
	private String content;
	private String listener;
	private String subject;
	private String speaker;
	public int getMeetid() {
		return meetid;
	}
	public void setMeetid(int meetid) {
		this.meetid = meetid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getListener() {
		return listener;
	}
	public void setListener(String listener) {
		this.listener = listener;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	
	
}
