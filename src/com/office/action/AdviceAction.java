//package com.office.action;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.office.actionform.AdviceForm;
//import com.office.actionform.LogonForm;
//import com.office.actionform.SearchForm;
//import com.office.dao.Content;
//import com.office.dao.DB;
//import com.office.tools.Change;
//import com.office.tools.CheckUserAble;
//
//public class AdviceAction extends Action {
//	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();		
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//	    
//		/* �ж��û��Ƿ��¼  */
//		if(logonuser==null){
//			 ActionMessages errors=new ActionMessages();
//	    	 errors.add("notAllow",new ActionMessage("office.logon.notallow"));
//			 saveErrors(request,errors);
//			 return mapping.findForward("notallowlogon");
//		}
//		
//		String mark=mapping.getParameter();
//		ActionForward forward=new ActionForward();
//		if(mark.equals("advice_look"))
//			forward=executeAdviceLook(mapping,form,request,response);
//		if(mark.equals("advice_look_sigle"))
//			forward=executeAdviceLookSigle(mapping,form,request,response);
//		if(mark.equals("advice_add"))
//			forward=executeAdviceAdd(mapping,form,request,response);
//		if(mark.equals("advice_del"))
//			forward=executeAdviceDel(mapping,form,request,response);
//	    return forward;
//	}
//	
//	public ActionForward executeAdviceLook(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu1", "advice");
//		session.setAttribute("selectmenu2", "advice_look");
//		SearchForm searchform = (SearchForm) form;
//
//		String sql = "";
//		String subsql = searchform.getSubsql();
//		String subsqlvalue = searchform.getSubsqlvalue();
//		if (subsql == null || subsql.equals(""))
//			subsql = "all";
//		if (subsql.equals("all")){
//			subsqlvalue="";
//			sql = "select * from " + Content.TB_ADVICE+" order by "+Content.ADVICESTIME+" desc";
//		}
//	    else{
//	    	 if (subsqlvalue == null || subsqlvalue.equals("")) {
//				errors.add("novalue", new ActionMessage("office.search.notiaojian.error"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			 }
//		     sql="select * from "+Content.TB_ADVICE+" where "+subsql+"='"+subsqlvalue+"'";
//	    }
//		searchform.setSubsqlvalue(subsqlvalue);
//	    
//		String str_currentpage=request.getParameter("page");
//        if(str_currentpage==null||str_currentpage.equals(""))
//			 str_currentpage="0";
//		int currentpage=Change.strtoint(str_currentpage);
//		if(currentpage<=0)
//			currentpage=1;
//	    
//		DB db=new DB();
//		ArrayList advicelist=db.getalladvicelist(sql,currentpage);
//
//	    session.setAttribute("numrs",Integer.toString(db.getNumrs()));
//	    session.setAttribute("numper", Integer.toString(db.getNumper()));
//	    session.setAttribute("currentpage",Integer.toString(db.getCurrentPage()));
//	    session.setAttribute("numpages", Integer.toString(db.getNumPages()));
//	    session.setAttribute("advicelist",advicelist);
//	    
//		return mapping.findForward("success");
//	}
//	public ActionForward executeAdviceLookSigle(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		String strid=request.getParameter("id");
//		 if(strid==null||strid.equals(""))
//			 strid="0";
//		int id=Change.strtoint(strid);
//		String sql="select * from "+Content.TB_ADVICE+" where "+Content.ADVICEID+"="+id;
//		DB db=new DB();
//		ResultSet rs=db.getRs(sql);
//		try{
//		    if(rs.next()){
//		       AdviceForm adviceform=(AdviceForm)form;
//		       adviceform.setAdviceid(rs.getInt(Content.ADVICEID));
//		       adviceform.setSubject(rs.getString(Content.ADVICESUBJECT));
//		       adviceform.setSender(rs.getString(Content.ADVICESENDER));
//		       adviceform.setContent(rs.getString(Content.ADVICECONTENT));
//		       adviceform.setStime(rs.getString(Content.ADVICESTIME));
//		       adviceform.setSbranch(rs.getString(Content.ADVICESBRANCH));
//		    }
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			ActionMessages errors=new ActionMessages();
//			errors.add("lookR",new ActionMessage("office.advice.looksigle.error"));
//			saveErrors(request,errors);
//			return mapping.findForward("false");
//		}
//		return mapping.findForward("success");
//	}
//	public ActionForward executeAdviceDel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		String strid=request.getParameter("id");
//		 if(strid==null||strid.equals(""))
//			 strid="0";
//		int id=Change.strtoint(strid);
//		String sql="delete from "+Content.TB_ADVICE+" where "+Content.ADVICEID+"="+id;
//		DB db=new DB();
//		int i=db.del(sql);
//		if(i==0){
//			ActionMessages errors=new ActionMessages();
//			errors.add("deleteR",new ActionMessage("office.del.false"));
//			saveErrors(request,errors);
//			return mapping.findForward("false");
//		}
//		return mapping.findForward("success");
//	}
//	public ActionForward executeAdviceAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu1", "advice");
//		
//		AdviceForm adviceform=(AdviceForm)form;
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//		
//		/* ����û�Ȩ�� */
//		if(!CheckUserAble.check(logonuser)){
//			session.setAttribute("selectmenu2", "");
//			errors.add("notAllow",new ActionMessage("office.user.noallow"));
//			saveErrors(request,errors);
//			return mapping.findForward("notallow");
//		}
//				
//		session.setAttribute("selectmenu2", "advice_add");
//		String addtype=request.getParameter("addtype");
//		if(addtype==null||addtype.equals(""))
//			 addtype="link";
//		if(addtype.equals("link"))
//			return mapping.findForward("add");
//		if(addtype.equals("add")){
//			boolean flag=true;
//			String subject=adviceform.getSubject();
//			String content=adviceform.getContent();			
//			
//			if(subject==null||subject.equals("")){
//				 flag=false;
//				 errors.add("nosubject",new ActionMessage("office.advice.nosubject.error"));
//			}
//			if(content==null||content.equals("")){
//				 flag=false;
//				 errors.add("nocontent",new ActionMessage("office.advice.nocontent.error"));
//			}
//			if(!flag){
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//		    
//			String sender=((LogonForm)session.getAttribute("logonuser")).getUsername();
//			String stime=Change.datetostr(new Date());
//			String sbranch=((LogonForm)session.getAttribute("logonuser")).getUserbranch();
//			String sql="insert into "+Content.TB_ADVICE+" values('"+subject+"','"+sender+"','"+content+"','"+stime+"','"+sbranch+"')";
//			DB db=new DB();
//			int i=db.inseart(sql);
//			if(i==0){
//				errors.add("addR",new ActionMessage("office.insert.false"));
//				return mapping.findForward("false");
//			}
//		}
//		adviceform.clear();
//		errors.add("addR",new ActionMessage("office.insert.success"));
//		saveErrors(request,errors);
//		return mapping.findForward("success");
//	}
//}