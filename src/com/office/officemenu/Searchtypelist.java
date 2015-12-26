//package com.office.officemenu;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//import org.apache.struts.util.LabelValueBean;
//
//import com.office.dao.Content;
//import com.office.dao.DB;
//
//public class Searchtypelist {
//
//	private static DB db = new DB();
//
//	private static ResultSet rs = null;
//
//	private static String sql = "";
//
//	private static String sql1 = "select * from " + Content.TB_LABEL+ " where " + Content.LABELTYPE + "='";
//
//	private static String sql2 = "' order by " + Content.LABELORDER;
//
//	public static ArrayList getpersonsearchtypelist() {
//		sql = sql1 + "person_search" + sql2;
//		ArrayList persontypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				persontypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:personsearchtypelist is error!!!");
//		}
//		return persontypelist;
//	}
//
//	public static ArrayList getpersonsextypelist(){
//		sql=sql1+"person_sex"+sql2;
//		ArrayList personsextypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				personsextypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:personsextypelist is error!!!");
//		}
//		return personsextypelist;
//	}
//	
//	public static ArrayList getpersonabletypelist(){
//		sql=sql1+"person_able"+sql2;
//		ArrayList personabletypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				personabletypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:personabletypelist is error!!!");
//		}
//		return personabletypelist;
//	}
//	
//	public static ArrayList getpersonjobtypelist(){
//		sql=sql1+"person_job"+sql2;
//		ArrayList personjobtypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				personjobtypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:personjobtypelist is error!!!");
//		}
//		return personjobtypelist;
//	}
//	
//	public static ArrayList getpersonbranchtypelist(){
//		sql=sql1+"person_branch"+sql2;
//		ArrayList personbranchtypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				personbranchtypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:personbranchtypelist is error!!!");
//		}
//		return personbranchtypelist;
//	}
//	
//	public static ArrayList getpersongoodtypelist(){
//		sql=sql1+"person_good"+sql2;
//		ArrayList persongoodtypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				persongoodtypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:persongoodtypelist is error!!!");
//		}
//		return persongoodtypelist;
//	}
//	
//	public static ArrayList getcarsearchtypelist() {
//		sql=sql1+"car_search"+sql2;
//		ArrayList cartypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				cartypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:cartypelist is error!!!");
//		}
//		return cartypelist;
//	}
//	public static ArrayList getcarstatustypelist() {
//		sql=sql1+"car_status"+sql2;
//		ArrayList carstatustypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				carstatustypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:carstatustypelist is error!!!");
//		}
//		return carstatustypelist;
//	}
//
//	public static ArrayList getthingsearchtypelist() {
//		sql=sql1+"thing_search"+sql2;
//		ArrayList thingtypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				thingtypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:persontypelist is error!!!");
//		}
//		return thingtypelist;
//	}
//	
//	public static ArrayList getadvicesearchtypelist() {
//		sql=sql1+"advice_search"+sql2;
//		ArrayList advicetypelist = new ArrayList();
//		try {
//			rs = db.getRs(sql);
//			while (rs.next()) {
//				LabelValueBean label = new LabelValueBean(rs.getString(Content.LABELNAME), rs.getString(Content.LABELVALUE));
//				advicetypelist.add(label);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error:advicetypelist is error!!!");
//		}
//		return advicetypelist;
//	}
//
//}
