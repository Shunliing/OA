package com.office.tools;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
/*************************************

版权所有: 	吉林省明日科技有限公司

完成日期：	2010-09-15

项目出处：	编程词典

服务社区：	www.mingribook.com

学习社区：	www.mrbccd.com

*************************************/

public class ManagerTrace implements javax.servlet.http.HttpSessionBindingListener {
  private String id;
  private Vector vc=new Vector();

  public ManagerTrace() {
    id = "";
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }
  
  public Vector getVc(){
	  return this.vc;
  }

  public void valueBound(HttpSessionBindingEvent arg0) {
	  HttpSession session=arg0.getSession();
	  ServletContext sct=session.getServletContext();
	  vc=(Vector)sct.getAttribute("userlist");
	  if(vc==null||vc.size()==0){
		  vc=new Vector();
		  vc.add(id);
	  }
	  else{
		  if(!vc.contains(id)){
			 vc.add(id);
		  }		  
	  }
	  sct.setAttribute("userlist",vc);
	  System.out.println(this.id+":"+Change.datetostr(new Date())+" 上线!");

  }

  public void valueUnbound(HttpSessionBindingEvent arg0) {
    if (id !=null||id!="") {
    	HttpSession session=arg0.getSession();
    	ServletContext sct=session.getServletContext();
    	vc=(Vector)sct.getAttribute("userlist");
    	if(vc!=null||vc.size()!=0)
    		vc.removeElement(id);
        sct.setAttribute("userlist",vc);
        System.out.println(this.id+":"+Change.datetostr(new Date())+" 下线!");
    }
  }
}
