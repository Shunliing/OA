//package com.office.action;
//
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.struts.action.Action;
//import org.apache.struts.action.ActionMessage;
//import org.apache.struts.action.ActionMessages;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//
//import com.office.actionform.LogonForm;
//import com.office.actionform.MailForm;
//import com.office.dao.Content;
//import com.office.dao.DB;
//import com.office.tools.Change;
//
//public class MailAction extends Action {
//	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		
//		HttpSession session=request.getSession();
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//		if(logonuser==null){
//			ActionMessages errors=new ActionMessages();
//			errors.add("notAllow",new ActionMessage("office.logon.notallow"));
//			saveErrors(request,errors);
//			return mapping.findForward("notallowlogon");
//		}
//		
//		String mark=mapping.getParameter();
//		ActionForward forward=new ActionForward();
//		if(mark.equals("mail_look_get"))
//			forward=executeMailLookGet(mapping,form,request,response);
//		if(mark.equals("mail_look_send"))
//			forward=executeMailLookSend(mapping,form,request,response);
//		if(mark.equals("mail_add"))
//			forward=executeMailAdd(mapping,form,request,response);
//		if(mark.equals("mail_look_sigle"))
//			forward=executeMailLookSigle(mapping,form,request,response);
//		if(mark.equals("mail_del"))
//			forward=executeMailDel(mapping,form,request,response);
//	    return forward;
//	}
//	
//	public ActionForward executeMailAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu1", "mail");
//		session.setAttribute("selectmenu2", "mail_send");
//	    MailForm mailform=(MailForm)form;
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//	    String type=request.getParameter("addtype");
//	    if(type==null||type.equals(""))type="link";
//	    if(type.equals("link"))
//	       return mapping.findForward("add");
//	    
//	    if(type.equals("add")){
//
//	       String geter=mailform.getGeter();
//	        if(geter==null)geter="";
//	       String sender=logonuser.getUsername();
//	              
//	       String subject=mailform.getSubject();
//	        if(subject==null||subject.equals(""))subject="�ޱ���!";
//
//	       String content=mailform.getContent();
//	        if(content==null||content.equals(""))content="������";
//
//	       String mark="1";
//	       String sendtime=Change.datetostr(new Date());
//	       if(geter.equals("")){
//	    	   errors.add("nogeter",new ActionMessage("office.mail.add.nogeter"));
//	           saveErrors(request,errors);
//	    	   return mapping.findForward("false");
//	       }
//           DB db=new DB();
//	       String sql="insert into "+Content.TB_MAIL+" values('"+subject+"','"+geter+"','"+sender+"','"+content+"','"+sendtime+"','"+mark+"',0,0)";
//	       int k=db.inseart(sql);
//	       if(k==0){
//    	       errors.add("addR",new ActionMessage("office.insert.false"));
//	    	   saveErrors(request,errors);
//	    	   return mapping.findForward("false");
//	       }
//	    }
//	       mailform.clear();
//	       errors.add("addR",new ActionMessage("office.insert.success"));
//	       saveErrors(request,errors);
//	       return mapping.findForward("success");
//	
//	}
//	public ActionForward executeMailLookGet(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu2", "mail_look");
//		session.setAttribute("selectmenu3", "mail_look_get");
//	    LogonForm logonform=(LogonForm)session.getAttribute("logonuser");
//		String sql="select * from "+Content.TB_MAIL+" where "+Content.MAILGETER+"='"+logonform.getUsername()+"' and "+Content.MAILDELGETER+"=0 order by "+Content.MAILMARK+" desc,"+Content.MAILSTIME+" desc";
//	    
//	    String str_currentpage=request.getParameter("page");
//        if(str_currentpage==null||str_currentpage.equals(""))
//			 str_currentpage="0";
//		int currentpage=Change.strtoint(str_currentpage);
//		if(currentpage<=0)
//			currentpage=1;
//
//		DB db=new DB();
//	    ArrayList getmaillist=db.getallmaillist(sql,currentpage);
//	    
//	    session.setAttribute("numrs",Integer.toString(db.getNumrs()));
//	    session.setAttribute("numper", Integer.toString(db.getNumper()));
//	    session.setAttribute("currentpage",Integer.toString(db.getCurrentPage()));
//	    session.setAttribute("numpages", Integer.toString(db.getNumPages()));
//	    session.setAttribute("getmaillist",getmaillist);
//		
//	    return mapping.findForward("success");
//	}
//	public ActionForward executeMailLookSend(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu2", "mail_look");
//		session.setAttribute("selectmenu3", "mail_look_send");
//	    LogonForm logonform=(LogonForm)session.getAttribute("logonuser");
//		String sql="select * from "+Content.TB_MAIL+" where "+Content.MAILSENDER+"='"+logonform.getUsername()+"' and "+Content.MAILDELSENDER+"=0 order by "+Content.MAILMARK+" desc,"+Content.MAILSTIME+" desc";
//	    
//	    String str_currentpage=request.getParameter("page");
//        if(str_currentpage==null||str_currentpage.equals(""))
//			 str_currentpage="0";
//		int currentpage=Change.strtoint(str_currentpage);
//		if(currentpage<=0)
//			currentpage=1;
//
//		DB db=new DB();
//	    ArrayList sendmaillist=db.getallmaillist(sql,currentpage);
//	    
//	    session.setAttribute("numrs",Integer.toString(db.getNumrs()));
//	    session.setAttribute("numper", Integer.toString(db.getNumper()));
//	    session.setAttribute("currentpage",Integer.toString(db.getCurrentPage()));
//	    session.setAttribute("numpages", Integer.toString(db.getNumPages()));
//		session.setAttribute("sendmaillist",sendmaillist);
//		
//		return mapping.findForward("success");
//	}
//	public ActionForward executeMailLookSigle(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//        String strid=request.getParameter("id");
//         if(strid==null||strid.equals(""))
//        	 strid="0";
//        int id=Change.strtoint(strid);
//        String sql="select * from "+Content.TB_MAIL+" where "+Content.MAILID+"="+id;
//	    DB db=new DB();
//	    ResultSet rs=db.getRs(sql);
//	    try{
//	    	if(rs.next()){
//	    		MailForm mailform=(MailForm)form;
//	    		mailform.setMailid(rs.getInt(Content.MAILID));
//	    		mailform.setSubject(rs.getString(Content.MAILSUBJECT));
//	    		mailform.setGeter(rs.getString(Content.MAILGETER));
//	    		mailform.setSender(rs.getString(Content.MAILSENDER));
//	    		mailform.setContent(rs.getString(Content.MAILCONTENT));
//	    		mailform.setStime(rs.getString(Content.MAILSTIME));
//	    		mailform.setMark(rs.getString(Content.MAILMARK));
//	    	}
//	    }
//	    catch(Exception e){e.printStackTrace();}
//	    String fromwhere=request.getParameter("from");
//	    if(fromwhere==null)fromwhere="";
//	    if(fromwhere.equals("get")){
//	    	sql="update "+Content.TB_MAIL+" set "+Content.MAILMARK+"='0' where "+Content.MAILID+"="+id;
//	        int i=db.update(sql);
//	        if(i==0){
//	        	ActionMessages errors=new ActionMessages();
//    	    	errors.add("updateR",new ActionMessage("office.update.false"));
//	        	saveErrors(request,errors);
//	        	return mapping.findForward("looksiglewrong");
//	        }
//	    }
//        return mapping.findForward("success");
//	}
//	public ActionForward executeMailDel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		String logonuser=((LogonForm)session.getAttribute("logonuser")).getUsername();
//		String from=request.getParameter("from");
//		if(from==null||!from.equals("send"))
//			 from="get";
//		String strid=request.getParameter("id");
//		 if(strid==null||strid.equals(""))
//			 strid="0";
//		int id=Change.strtoint(strid);
//		String sql="select * from "+Content.TB_MAIL+" where "+Content.MAILID+"="+id;
//		DB db=new DB();
//		ResultSet rs=db.getRs(sql);
//		try{
//    		if(rs.next()){
//			    String delgeter=rs.getString(Content.MAILDELGETER);
//			    String delsender=rs.getString(Content.MAILDELSENDER);
//			    if(from.equals("get")){					//���ɾ�����ǡ��ռ��䡱����ʼ�
//                    if(delsender.equals("1"))                          //�жϸ��ʼ��Ƿ񱻷����ߴ����ķ�������ɾ�������ɾ����������ݱ���ɾ�����ʼ�
//                        sql="delete from "+Content.TB_MAIL+" where "+Content.MAILID+"="+id;
//                    else if(delsender.equals("0"))                    //�жϸ��ʼ��Ƿ�û�б������ߴ����ķ�������ɾ�������û��ɾ�����򽫸��ʼ����Ϊ���ѱ��ʼ�������ɾ����״̬
//                        sql="update "+Content.TB_MAIL+" set "+Content.MAILDELGETER+"=1 where "+Content.MAILID+"="+id;                  
//                } 
//			    else if(from.equals("send")){			//���ɾ�����ǡ������䡱����ʼ�
//                    if(delgeter.equals("1"))                          //�жϸ��ʼ��Ƿ񱻽����ߴ������ռ�����ɾ�������ɾ����������ݱ���ɾ�����ʼ�
//                        sql="delete from "+Content.TB_MAIL+" where "+Content.MAILID+"="+id;
//                    else if(delgeter.equals("0"))                    //�жϸ��ʼ��Ƿ�û�б������ߴ������ռ�����ɾ�������û��ɾ�����򽫸��ʼ����Ϊ���ѱ��ʼ�������ɾ����״̬
//                        sql="update "+Content.TB_MAIL+" set "+Content.MAILDELSENDER+"=1 where "+Content.MAILID+"="+id;                  
//                } 
//		    }
//		    Statement stm=db.getStm();
//		    boolean ok=stm.execute(sql);				//����execute()������ִ��һ�����ܲ�ȷ����SQL���
//		    if(!ok){									//����true�����ʾ����execute()����ִ��SQL���󣬷��ص���һ��ResultSet������ϣ�����false�����ʾ����execute()����ִ��SQL���󣬷��ص��Ǹ��¼�¼������
//			    ActionMessages errors=new ActionMessages();
//			    errors.add("deleteR",new ActionMessage("office.del.false"));
//			    saveErrors(request,errors);
//		    }
//		}catch(Exception e){e.printStackTrace();}
//		return mapping.findForward(from);
//	}
//}