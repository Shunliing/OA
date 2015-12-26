package com.office.officemenu;

import java.io.Serializable;

public class MenuSigle implements Serializable{
	
	/**
	 * @Field serialVersionUID
	 */
	private static final long serialVersionUID = 3903719230547757330L;
	private int menujibie;
	private String menuparentid;
	private int menuorder;
	private String menuid;
	private int menuable;
	private String menuname;
	private String menuaction;

	public int getMenujibie() {
		return menujibie;
	}
	public void setMenujibie(int menujibie) {
		this.menujibie = menujibie;
	}

	public String getMenuparentid() {
		return menuparentid;
	}
	public void setMenuparentid(String menuparentid) {
		this.menuparentid = menuparentid;
	}

	public int getMenuorder() {
		return menuorder;
	}
	public void setMenuorder(int menuorder) {
		this.menuorder = menuorder;
	}

	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public int getMenuable() {
		return menuable;
	}
	public void setMenuable(int menuable) {
		this.menuable = menuable;
	}

	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuaction() {
		return menuaction;
	}
	public void setMenuaction(String menuaction) {
		this.menuaction = menuaction;
	}
}