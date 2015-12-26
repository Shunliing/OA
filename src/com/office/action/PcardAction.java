package com.office.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.office.dao.Content;
import com.office.tools.Change;
import com.office.tools.CheckUserAble;
import com.office.util.DBUtil;
import com.office.vo.Pcard;
import com.office.vo.User;

@WebServlet("/NoticeServlet")
public class PcardAction extends HttpServlet {
	/**
	 * @Field serialVersionUID
	 */
	private static final long serialVersionUID = -5734055240487000580L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {	
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		User logonuser=(User)session.getAttribute("logonuser");
		 String url = "";
		 if(logonuser==null){
			 request.setAttribute("error", "invalid");
			url = "index.jsp";
		 }
		
		String mark = request.getParameter("type");
		if(mark.equals("link")){
			url = "/pcard/Add_pcard.jsp";
		}	
		if(mark.equals("query"))
			url=executePcardLook(request,response);
//		if(mark.equals("sigleLook"))
//			url=executePcardSigleLook(request,response);
//		if(mark.equals("del"))
//			url=executePcardDel(request,response);
		if(mark.equals("add"))
			url=executePcardAdd(request,response);
//		if(mark.equals("update"))
//			url=executePcardUpdate(request,response);
		request.getRequestDispatcher(url).forward(request, response);
	}	
	public String executePcardLook(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("selectmenu1", "pcard");
		session.setAttribute("selectmenu2", "pcard_look");
	    String sql="select * from "+Content.TB_PCARD+" order by "+Content.PCARDTIME+" desc";
	    
	    String str_currentpage=request.getParameter("page");
        if(str_currentpage==null||str_currentpage.equals(""))
			 str_currentpage="0";
		int currentpage=Change.strtoint(str_currentpage);
		if(currentpage<=0)
			currentpage=1;

	    DBUtil db=new DBUtil();
	    ArrayList<Pcard> pcardlist=db.getallpcardlist(sql,currentpage);
	    
	    session.setAttribute("numrs",Integer.toString(db.getNum_rs()));
	    session.setAttribute("numper", Integer.toString(db.getNum_per()));
	    session.setAttribute("currentpage",Integer.toString(db.getNum_currentpage()));
	    session.setAttribute("numpages", Integer.toString(db.getNum_pages()));
	    session.setAttribute("pcardlist",pcardlist);
	  	
	    return "/pcard/Look_pcard.jsp";
	}
	
//	public ActionForward executePcardSigleLook(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		PcardForm pcardform=(PcardForm)form; 
//		String lookid1=request.getParameter("id");
//		 if(lookid1==null||lookid1.equals(""))lookid1="0";
//		int lookid=Change.strtoint(lookid1);
//		
//		ArrayList pcardlist=(ArrayList)session.getAttribute("pcardlist");
//		boolean flag=false;		
//		if(pcardlist!=null||pcardlist.size()!=0){
//			for(int i=0;i<pcardlist.size();i++){
//			    PcardForm pcardsigleform=(PcardForm)pcardlist.get(i);
//			    if(lookid==pcardsigleform.getPcardid()){
//				    flag=true;
//				    pcardform.setSubject(pcardsigleform.getSubject());
//	    		    pcardform.setAuthor(pcardsigleform.getAuthor());
//	    		    pcardform.setTime(pcardsigleform.getTime());
//	    		    pcardform.setContent(pcardsigleform.getContent());
//	    		    break;
//			    }
//			}
//		}
//		if(!flag){
//			ActionMessages errors=new ActionMessages();
//			errors.add("lookR",new ActionMessage("office.pcard.look.sigle.error"));
//			saveErrors(request,errors);
//			return mapping.findForward("false");
//		}
//		return mapping.findForward("success");
//	}
//	
//	public ActionForward executePcardDel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//		
//		if(!CheckUserAble.check(logonuser)){
//			session.setAttribute("selectmenu2", "");
//			errors.add("notAllow",new ActionMessage("office.user.noallow"));
//			saveErrors(request,errors);
//			return mapping.findForward("notallow");
//		}
//		
//		String id1=request.getParameter("id");
//		int id=Change.strtoint(id1);
//		String sql="delete from "+Content.TB_PCARD+" where "+Content.PCARDID+"="+id;
//		DB db=new DB();
//		int i=db.del(sql);
//		if(i==0){
//			errors=new ActionMessages();
//			errors.add("deleteR",new ActionMessage("office.delete.false"));
//			saveErrors(request,errors);
//			return mapping.findForward("false");
//		}
//	    return mapping.findForward("success");
//	}
	public String executePcardAdd(HttpServletRequest request, HttpServletResponse response){
	
		HttpSession session=request.getSession();
		
		User logonuser=(User)session.getAttribute("logonuser");
				
		if(!CheckUserAble.check(logonuser)){
		request.setAttribute("error", "");
			return "index.jsp";
		}
		
		session.setAttribute("selectmenu2", "pcard_add");
	    
	    
	     String  	subject=request.getParameter("subject");
	     String   	content=request.getParameter("content");
	     
	    
	  
		 
		 String author=((User)session.getAttribute("logonuser")).getUsername();
		 String time=Change.datetostr(new Date());
		 String sql="insert into "+Content.TB_PCARD+" values('"+subject+"','"+author+"','"+time+"','"+content+"')";
		 boolean flag=DBUtil.getExecute(sql);
		 if(!flag){
		  	request.setAttribute("error", "");
		  
		 }
		
		 return executePcardLook(request, response);
	}

//	public ActionForward executePcardUpdate(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		PcardForm pcardform=(PcardForm)form;
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//			
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//		
//		if(!CheckUserAble.check(logonuser)){
//			session.setAttribute("selectmenu2", "");
//			errors.add("notAllow",new ActionMessage("office.user.noallow"));
//			saveErrors(request,errors);
//			return mapping.findForward("notallow");
//		}
//		
//		session.setAttribute("selectmenu2", "pcard_update");
//		String subject="";
//		String content="";
//		DB db=new DB();
//		
//		String mark=request.getParameter("updatetype");
//		if(mark==null|mark.equals(""))mark="link";
//	    if(mark.equals("link")){
//			String lookid1=request.getParameter("id");
//			 if(lookid1==null||lookid1.equals(""))lookid1="0";
//			int lookid=Change.strtoint(lookid1);
//			session.setAttribute("id", lookid1);
//			
//			ArrayList pcardlist=(ArrayList)session.getAttribute("pcardlist");
//			boolean flag=false;		
//			for(int i=0;i<pcardlist.size();i++){
//				PcardForm pcardsigleform=(PcardForm)pcardlist.get(i);
//				if(lookid==pcardsigleform.getPcardid()){
//					flag=true;
//					pcardform.setSubject(pcardsigleform.getSubject());
//		    		pcardform.setAuthor(pcardsigleform.getAuthor());
//		    		pcardform.setTime(pcardsigleform.getTime());
//		    		pcardform.setContent(pcardsigleform.getContent());
//		    		break;
//				}
//			}
//			if(!flag){
//				errors.add("lookR",new ActionMessage("office.pcard.look.sigle.error"));
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//		  return mapping.findForward("update");
//	     }
//	     
//	     if(mark.equals("update")){
//	       	subject=pcardform.getSubject();
//	       	content=pcardform.getContent();
//	     }
//	     if(subject==null||subject.equals("")){
//	    	 errors.add("nosubject",new ActionMessage("office.pcard.nosubject.error"));
//	    	 saveErrors(request,errors);
//	    	 return mapping.findForward("false");
//	     }
//	     String id1=(String)session.getAttribute("id");
//	     int id=Change.strtoint(id1);
//	     
//	     String sql="update "+Content.TB_PCARD+" set "+Content.PCARDSUBJECT+"='"+subject+"',"+Content.PCARDCONTENT+"='"+content+"' where "+Content.PCARDID+"="+id;
//		 int k=db.update(sql);
//		 if(k==0){
//		  	errors.add("updateR",new ActionMessage("office.update.false"));
//		  	saveErrors(request,errors);
//		  	return mapping.findForward("false");
//		 }
//		 pcardform.clear();
//		 errors.add("updateR",new ActionMessage("office.update.success"));
//		 saveErrors(request,errors);
//		 return mapping.findForward("success");
//	}
	
}