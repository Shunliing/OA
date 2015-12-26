package com.office.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.office.officemenu.MenuSigle;
/*************************************

��Ȩ����: 	����ʡ���տƼ����޹�˾

������ڣ�	2010-09-15

��Ŀ������	��̴ʵ�

����������	www.mingribook.com

ѧϰ������	www.mrbccd.com

*************************************/

//public class DB {
//    private ResultSet rs;
//    private Statement stm;
//    private Connection con;
//    private String url=Content.URL;
//    private String classname=Content.CLASSNAME;
//    private int num_per=0;
//    private int num_rs=0;
//    private int num_currentpage=1;
//    private int num_pages=1;
//    public DB(){
//    	try{
//    		Class.forName(classname);
//    	}
//    	catch(ClassNotFoundException e){
//    		e.printStackTrace();
//    	}
//    }
//    
//    public Connection getCon(){    	  
//    	  try{
//    	      con=DriverManager.getConnection(url,"sa","");
//    	  }catch(Exception e){e.printStackTrace(System.err);}
//    	  return con;
//    }
//    public Statement getStm(){
//    	  try{
//    	     con=getCon();
//    	     stm=con.createStatement();
//    	  }catch(Exception e){e.printStackTrace(System.err);}
//    	   return stm;
//    	 }
//    	 public Statement getStmed(){
//    	  try{
//    	     con=getCon();
//    	     stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//    	  }catch(Exception e){e.printStackTrace(System.err);}
//    	  return stm;
//    }
//    	 
//   	public ResultSet getRs(String sql){
//		System.out.println(sql);
//   		if(sql==null)sql="";
//		try{
//			stm=getStmed();
//			rs=stm.executeQuery(sql);
//		}
//		catch(SQLException e){e.printStackTrace();}
//		return rs;
//	}
//   	
//   	public int update(String sql){
//    	System.out.println(sql);
//		int num=0;
//    	if(sql==null)sql="";
//    	try{
//    	    stm=getStmed();
//    	    num=stm.executeUpdate(sql);
//    	}
//    	catch(SQLException e){e.printStackTrace();num=0;}
//    	return num;
//    }
//    
//    public int inseart(String sql){
//    	int num=0;
//    	if(sql==null)sql="";
//    	System.out.println(sql);
//    	try{
//    	    stm=getStmed();
//    	    num=stm.executeUpdate(sql);
//    	}
//    	catch(Exception e){e.printStackTrace();num=0;}
//    	return num;
//    }
//     
//    public int del(String sql){
//    	int num=0;
//    	if(sql==null)sql="";
//    	System.out.println(sql);
//    	try{
//    		stm=getStmed();
//    		num=stm.executeUpdate(sql);
//    	}
//    	catch(SQLException e){e.printStackTrace();}
//    	return num;
//    }
//	
//    public ArrayList getalltextlist(String sql,int pos){
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);       //ͨ��ʵ����һ��Page���������ÿҳ��¼��(num_per)���ܼ�¼��(num_rs)����ҳ��(num_pages)����ǰҳ��(current_page)��
//    		setPage(page);                    //����DB���setPage(Page page)������������ʵ������Page���������ֵ����DB���е����ԡ�
//    		int i=1;
//    		rs=page.getRs();                  //����Page���getRs()��������õ�ǰҳ�ļ�¼ָ�롣
//    		while(rs.next()&&i<=num_per){    //����i������ʾ�ļ�¼����
//    		   TextForm textform=new TextForm();
//    		   textform.setTextid(rs.getInt(Content.TEXTID));
//    		   textform.setSubject(rs.getString(Content.TEXTSUBJECT));
//    		   textform.setSender(rs.getString(Content.TEXTSENDER));
//    		   textform.setGeter(rs.getString(Content.TEXTGETER));
//    		   textform.setMark(rs.getString(Content.TEXTMARK));
//    		   textform.setContent(rs.getString(Content.TEXTCONTENT));
//    		   textform.setSendtime(rs.getString(Content.TEXTSENDTIME));
//    		   list.add(textform);
//    	       i++;
//    		}
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }   
//    
//   	
//    public ArrayList getallmeetlist(String sql,int pos){
//    	System.out.println(sql);
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);
//    		setPage(page);
//    		int i=1;
//    		rs=page.getRs();
//    		while(rs.next()&&i<=num_per){
//    		   MeetForm meetform=new MeetForm();
//    		   meetform.setMeetid(rs.getInt(Content.MEETID));
//    		   meetform.setSubject(rs.getString(Content.MEETSUBJECT));
//    		   meetform.setSpeaker(rs.getString(Content.MEETSPEAKER));
//    		   meetform.setListener(rs.getString(Content.MEETLIST));
//    		   meetform.setTime(rs.getString(Content.MEETTIME));
//    		   meetform.setAddress(rs.getString(Content.MEETADDRESS));
//    		   meetform.setContent(rs.getString(Content.MEETCONTENT));
//    		   list.add(meetform);
//    		   i++;
//    	    }
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }
//    
//    public ArrayList getallpcardlist(String sql,int pos){
//    	System.out.println(sql);
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);
//    		setPage(page);
//    		int i=1;
//    		rs=page.getRs();
//    		while(rs.next()&&i<=num_per){
//    		   PcardForm pcardform=new PcardForm();
//    		   pcardform.setPcardid(rs.getInt(Content.PCARDID));
//    		   pcardform.setSubject(rs.getString(Content.PCARDSUBJECT));
//    		   pcardform.setAuthor(rs.getString(Content.PCARDAUTHOR));
//    		   pcardform.setTime(rs.getString(Content.PCARDTIME));
//    		   pcardform.setContent(rs.getString(Content.PCARDCONTENT));
//    		   list.add(pcardform);
//    		   i++;
//    	    }
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }
//    
//    public ArrayList getallpersonlist(String sql,int pos){
//    	System.out.println(sql);
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);
//    		setPage(page);
//    		int i=1;
//    		rs=page.getRs();
//    		while(rs.next()&&i<=num_per){
//    		   UserForm userform=new UserForm();
//    		   userform.setUserid(rs.getInt(Content.USERID));
//    		   userform.setUsername(rs.getString(Content.USERNAME));
//    		   userform.setUsertruename(rs.getString(Content.USERTRUENAME));
//    		   userform.setUserable(rs.getInt(Content.USERABLE));
//    		   userform.setUsergood(rs.getString(Content.USERGOOD));
//    		   userform.setUserbranch(rs.getString(Content.USERBRANCH));
//    		   userform.setUserjob(rs.getString(Content.USERJOB));
//    		   userform.setUsersex(rs.getString(Content.USERSEX));
//    		   userform.setUseremail(rs.getString(Content.USEREMAIL));
//    		   userform.setUsertel(rs.getString(Content.USERTEL));
//    		   userform.setUseraddress(rs.getString(Content.USERADDRESS));
//    		   userform.setUseraccesstimes(rs.getInt(Content.USERACCESSTIMES));
//    		   userform.setUserfoundtime(rs.getString(Content.USERFOUNDTIME));
//    		   list.add(userform);
//    		   i++;
//    		}
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }   
//    
//    public ArrayList getallthinglist(String sql,int pos){
//    	System.out.println(sql);
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);
//    		setPage(page);
//    		int i=1;
//    		rs=page.getRs();
//    		while(rs.next()&&i<=num_per){
//    		   MoneyThingForm thingform=new MoneyThingForm();
//    		   thingform.setThingid(rs.getInt(Content.THINGID));
//    		   thingform.setType(rs.getString(Content.THINGTYPE));
//    		   thingform.setModel(rs.getString(Content.THINGMOD));
//    		   thingform.setNumber(rs.getString(Content.THINGNUM));
//    		   thingform.setCost(rs.getString(Content.THINGCOST));
//    		   thingform.setBuytime(rs.getString(Content.THINGTIME));
//    		   thingform.setBuybranch(rs.getString(Content.THINGBRANCH));
//    		   list.add(thingform);
//    		   i++;
//          	}
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }   
//    
//    public ArrayList getallcarlist(String sql,int pos){
//    	System.out.println(sql);
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);
//    		setPage(page);
//    		int i=1;
//    		rs=page.getRs();
//    		while(rs.next()&&i<=num_per){
//    		   MoneyCarForm carform=new MoneyCarForm();
//    		   carform.setCarid(rs.getInt(Content.CARID));
//    		   carform.setNumber(rs.getString(Content.CARNUM));
//    		   carform.setModel(rs.getString(Content.CARMODEL));
//    		   carform.setFdj(rs.getString(Content.CARFDJ));
//    		   carform.setDriver(rs.getString(Content.CARDRIVER));
//    		   carform.setMaker(rs.getString(Content.CARMAKER));
//    		   carform.setBuytime(rs.getString(Content.CARBUYTIME));
//    		   carform.setStatus(rs.getString(Content.CARSTATUS));
//    		   carform.setNjinfo(rs.getString(Content.CARNJ));
//    		   carform.setInfo(rs.getString(Content.CARINFO));
//    		   list.add(carform);
//    		   i++;
//          	}
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }   
//    
//    public ArrayList getallfilelist(String sql,int pos){
//    	System.out.println(sql);
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);
//    		setPage(page);
//    		int i=1;
//    		rs=page.getRs();
//    		while(rs.next()&&i<=num_per){
//    		   FileForm fileform=new FileForm();
//    		   fileform.setFileid(rs.getInt(Content.FILEID));
//    		   fileform.setFilename(rs.getString(Content.FILENAME));
//    		   fileform.setFileuper(rs.getString(Content.FILEUPER));
//    		   fileform.setFilesize(rs.getString(Content.FILESIZE));
//    		   fileform.setFileuptime(rs.getString(Content.FILEUPTIME));
//    		   fileform.setFileinfo(rs.getString(Content.FILEINFO));
//    		   list.add(fileform);
//    		   i++;
//          	}
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }
//    
//    public ArrayList getallmaillist(String sql,int pos){
//    	System.out.println(sql);
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);
//    		setPage(page);
//    		int i=1;
//    		rs=page.getRs();
//    		while(rs.next()&&i<=num_per){
//    		   MailForm mailform=new MailForm();
//    		   mailform.setMailid(rs.getInt(Content.MAILID));
//    		   mailform.setSubject(rs.getString(Content.MAILSUBJECT));
//    		   mailform.setGeter(rs.getString(Content.MAILGETER));
//    		   mailform.setSender(rs.getString(Content.MAILSENDER));
//    		   mailform.setContent(rs.getString(Content.MAILCONTENT));
//    		   mailform.setStime(rs.getString(Content.MAILSTIME));
//    		   mailform.setMark(rs.getString(Content.MAILMARK));
//    		   list.add(mailform);
//    		   i++;
//    		}
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }
//    
//    public ArrayList getalladvicelist(String sql,int pos){
//    	System.out.println(sql);
//    	ArrayList list=new ArrayList();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		Page page=new Page(rs,pos);
//    		setPage(page);
//    		int i=1;
//    		rs=page.getRs();
//    		while(rs.next()&&i<=num_per){
//    		   AdviceForm adviceform=new AdviceForm();
//    		   adviceform.setAdviceid(rs.getInt(Content.ADVICEID));
//    		   adviceform.setSubject(rs.getString(Content.ADVICESUBJECT));
//    		   adviceform.setSender(rs.getString(Content.ADVICESENDER));
//    		   adviceform.setContent(rs.getString(Content.ADVICECONTENT));
//    		   adviceform.setStime(rs.getString(Content.ADVICESTIME));
//    		   adviceform.setSbranch(rs.getString(Content.ADVICESBRANCH));
//    		   list.add(adviceform);
//    		   i++;
//    		}
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }
//    
//    public ArrayList<MenuSigle> getmenulist(String sql){
//    	System.out.println(sql);
//    	ArrayList<MenuSigle> list=new ArrayList<MenuSigle>();
//    	if(sql==null)sql="";
//    	stm=getStmed();
//    	try{
//    		rs=stm.executeQuery(sql);
//    		while(rs.next()){
//    		   MenuSigle menusigle=new MenuSigle();
//    		   menusigle.setMenuid(rs.getString(Content.MENUID));
//    		   menusigle.setMenuname(rs.getString(Content.MENUNAME));
//    		   menusigle.setMenuaction(rs.getString(Content.MENUACTION));
//    		   menusigle.setMenuorder(rs.getInt(Content.MENUORDER));
//    		   list.add(menusigle);
//    	    }
//    	}
//    	catch(Exception e){e.printStackTrace();}
//        return list;
//    }
//    
//	
//	
//    public void closed(){
//    	try{
//    		if(rs!=null)rs.close();
//    	}
//    	catch(Exception e){e.printStackTrace();}
//    	try{
//    		if(stm!=null)stm.close();
//    	}
//    	catch(Exception e){e.printStackTrace();}
//    	try{
//    		if(con!=null)con.close();
//    	}
//    	catch(Exception e){e.printStackTrace();}    	
//    }
//    public void setPage(Page page){
//    	this.num_per=page.getNumper();
//        this.num_rs=page.getNumrs();
//    	this.num_pages=page.getNumPages();
//    	this.num_currentpage=page.getCurrentPage();
//    }
//    public int getNumper(){
//    	return this.num_per;
//    }
//    public int getNumrs(){
//    	return this.num_rs;
//    }
//    public int getCurrentPage(){
//    	return this.num_currentpage;
//    }
//    public int getNumPages(){
//    	return this.num_pages;
//    }
//}
