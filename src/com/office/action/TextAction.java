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
import com.office.vo.Document;
import com.office.vo.User;
@WebServlet("/TextAddServlet")
public class TextAction extends HttpServlet {
	/**
	 * @Field serialVersionUID
	 */
	private static final long serialVersionUID = 2107520274671372586L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {    
    	doPost(req, resp);
    }
    //数据库中：text收发文管理： /text/text_add
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	HttpSession session=req.getSession();
		User logonuser=(User)session.getAttribute("logonuser");
		String url = "/text/Add_text.jsp";
		if(logonuser==null){
		    req.setAttribute("error", "用户有误，请重新登录");
			url = "index.jsp";
		}
		String mark = req.getParameter("addtype");
		if(mark.equals("get"))
			url = executeTextLookGet(req, resp);
		if(mark.equals("send"))
			url = "/text/Look_sendtext.jsp";
		if(mark.equals("look"))
			url = "/text/Look_sigletext.jsp";
		if(mark.equals("add"))
			url = executeTextAdd(req, resp);
		if(mark.equals("del"))
			url = "/text/text_look_get.do";
		
		req.getRequestDispatcher(url).forward(req, resp);
    }
	
	
	public String  executeTextLookGet(HttpServletRequest request, HttpServletResponse response){
		ArrayList<Document> gettextlist=new ArrayList<>();
		HttpSession session=request.getSession();
		
		session.setAttribute("selectmenu2","text_look");
		session.setAttribute("selectmenu3","text_look_get");
		User logonuser=(User)session.getAttribute("logonuser");
		String user=logonuser.getUsername();
		String sql_get="select * from "+Content.TB_TEXT+" where "+Content.TEXTGETER+" ='"+user+"' and "+Content.TEXTDELGETER+"='0'"+" order by "+Content.TEXTMARK+" desc,"+Content.TEXTSENDTIME+" desc";
		String str_currentpage=request.getParameter("page");
        if(str_currentpage==null||str_currentpage.equals(""))
			 str_currentpage="0";
		int currentpage=Change.strtoint(str_currentpage);
		if(currentpage<=0)
			currentpage=1;	    
		DBUtil u = new DBUtil();
	    gettextlist=u.getalltextlist(sql_get,currentpage);
        
	    session.setAttribute("numrs",Integer.toString(u.getNum_rs()));
	    session.setAttribute("numper", Integer.toString(u.getNum_per()));
	    session.setAttribute("currentpage",Integer.toString(u.getNum_currentpage()));
	    session.setAttribute("numpages", Integer.toString(u.getNum_pages()));
	    session.setAttribute("gettextlist",gettextlist);
	    
		return "/text/Look_gettext.jsp";
	}
	
//	public ActionForward executeTextLookSend(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ArrayList sendtextlist=new ArrayList();
//		HttpSession session=request.getSession();
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//		String user=logonuser.getUsername();
//		session.setAttribute("selectmenu3", "text_look_send");
//	    String sql_send="select * from "+Content.TB_TEXT+" where "+Content.TEXTSENDER+" ='"+user+"' and "+Content.TEXTDELSENDER+"='0' order by "+Content.TEXTSENDTIME+" desc";
//	    String str_currentpage=request.getParameter("page");
//        if(str_currentpage==null||str_currentpage.equals(""))
//			 str_currentpage="0";
//		int currentpage=Change.strtoint(str_currentpage);
//		if(currentpage<=0)
//			currentpage=1;
//	    
//		DB db=new DB();
//	    sendtextlist=db.getalltextlist(sql_send,currentpage);
//        db.closed();
//	    session.setAttribute("numrs",Integer.toString(db.getNumrs()));
//	    session.setAttribute("numper", Integer.toString(db.getNumper()));
//	    session.setAttribute("currentpage",Integer.toString(db.getCurrentPage()));
//	    session.setAttribute("numpages", Integer.toString(db.getNumPages()));
//	    session.setAttribute("sendtextlist",sendtextlist);
//		
//	    return mapping.findForward("success");
//	}
//	
//	public ActionForward executeTextSigleLook(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		TextForm textform=(TextForm)form; 
//		ArrayList textlist=new ArrayList();
//		String lookid1=request.getParameter("id");
//		 if(lookid1==null||lookid1.equals(""))lookid1="0";
//		int lookid=Change.strtoint(lookid1);
//		String action=request.getParameter("from");
//		 if(action==null||action.equals(""))action="get";
//		if(action.equals("get"))
//			textlist=(ArrayList)session.getAttribute("gettextlist");
//		else if(action.equals("send"))
//			textlist=(ArrayList)session.getAttribute("sendtextlist");
//		boolean flag=false;
//		if(textlist!=null||textlist.size()!=0){
//			for(int i=0;i<textlist.size();i++){
//			    TextForm textsigleform=(TextForm)textlist.get(i);
//			    if(lookid==textsigleform.getTextid()){
//			    	flag=true;
//				    textform.setSubject(textsigleform.getSubject());
//	    		    textform.setSender(textsigleform.getSender());
//	    		    textform.setGeter(textsigleform.getGeter());
//	    		    textform.setContent(textsigleform.getContent());
//	    		    textform.setSendtime(textsigleform.getSendtime());
//	    		    break;
//			    }
//			}
//		}
//		if(!flag){
//			ActionMessages errors=new ActionMessages();
//			errors.add("lookR",new ActionMessage("office.text.look.sigle.error"));
//			saveErrors(request,errors);
//			return mapping.findForward("looksiglewrong");
//		}
//		String fromwhere=request.getParameter("from");
//		if(fromwhere==null)fromwhere="";
//		if(fromwhere.equals("get")){
//			DB db=new DB();
//		    String sql="update "+Content.TB_TEXT+" set "+Content.TEXTMARK+"='0' where "+Content.TEXTID+"="+lookid;
//		    int i=db.update(sql);
//		    db.closed();
//		    if(i==0){
//			    ActionMessages errors=new ActionMessages();
//			    errors.add("updateR",new ActionMessage("office.update.false"));
//			    saveErrors(request,errors);
//			    return mapping.findForward("false");
//		    }
//		}
//		return mapping.findForward("success");
//	}
//	
//	public ActionForward executeTextDel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
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
//		String from=request.getParameter("from");
//		if(!"send".equals(from))
//			from="get";
//		
//		String strid=request.getParameter("id");
//		if(strid==null)
//			strid="0";
//		int id=Change.strtoint(strid);		
//		
//		String sql="select * from "+Content.TB_TEXT+" where "+Content.TEXTID+"="+id;
//		DB db=new DB();
//		ResultSet rs=db.getRs(sql);
//		try{
//    		if(rs.next()){	    		
//			    String delgetter=rs.getString(Content.TEXTDELGETER);
//			    String delsender=rs.getString(Content.TEXTDELSENDER);
//			    if("get".equals(from)){						//���ɾ�����ǡ��ռ��䡱��Ĺ���
//                    if(delsender.equals("1"))                          	//�жϸù����Ƿ񱻷����ߴ����ķ�������ɾ�������ɾ����������ݱ���ɾ���ù���
//                        sql="delete from "+Content.TB_TEXT+" where "+Content.TEXTID+"="+id;
//                    else if(delsender.equals("0"))                    	//�жϸù����Ƿ�û�б������ߴ����ķ�������ɾ�������û��ɾ�����򽫸ù��ı��Ϊ���ѱ����Ľ�����ɾ����״̬
//                        sql="update "+Content.TB_TEXT+" set "+Content.TEXTDELGETER+"=1 where "+Content.TEXTID+"="+id;                  
//                } 
//			    else if("send".equals(from)){				//���ɾ�����ǡ������䡱��Ĺ���
//                    if(delgetter.equals("1"))                          	//�жϸù����Ƿ񱻽����ߴ������ռ�����ɾ�������ɾ����������ݱ���ɾ���ù���
//                        sql="delete from "+Content.TB_TEXT+" where "+Content.TEXTID+"="+id;
//                    else if(delgetter.equals("0"))                    	//�жϸù����Ƿ�û�б������ߴ������ռ�����ɾ�������û��ɾ�����򽫸ù��ı��Ϊ���ѱ����ķ�����ɾ����״̬
//                        sql="update "+Content.TB_TEXT+" set "+Content.TEXTDELSENDER+"=1 where "+Content.TEXTID+"="+id;                  
//                } 
//			    Statement stm=db.getStm();
//			    boolean ok=stm.execute(sql);					//����execute()������ִ��һ�����ܲ�ȷ����SQL���
//			    if(ok)											//����true�����ʾ����execute()����ִ��SQL���󣬷��ص���һ��ResultSet������ϣ�����false�����ʾ����execute()����ִ��SQL���󣬷��ص��Ǹ��¼�¼������
//			    	errors.add("deleteR",new ActionMessage("office.del.false"));
//			    else
//			    	errors.add("deleteR",new ActionMessage("office.del.success"));			    	
//		    }
//    		else
//    			errors.add("deleteR",new ActionMessage("office.del.false"));		    	
//    		saveErrors(request,errors);
//		}catch(Exception e){e.printStackTrace();}
//
//		return mapping.findForward(from);
//	}
//	
	public String executeTextAdd(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session=request.getSession();
		session.setAttribute("selectmenu1", "text");
		session.setAttribute("selectmenu2", "text_add");
		User logonuser=(User)session.getAttribute("logonuser");
		String url = "";
		if(!CheckUserAble.check(logonuser)){
			request.setAttribute("error", "");
			return "/text/Add_text.jsp";
		}
		String type=request.getParameter("addtype");
		if(type==null||type.equals(""))type="link";
		if(type.equals("link"))
			return "/text/Add_text.jsp";
		
	    String geter=request.getParameter("geter");
	     if(geter==null)geter="";
	    String sender=logonuser.getUsername();
	        
	    String subject=request.getParameter("subject");
	     if(subject==null||subject.equals(""))subject="主题为空!";
	    String content=request.getParameter("content");
	     if(content==null||content.equals(""))content="内容为空";
	    String mark="1";
	    String sendtime=Change.datetostr(new Date());
	    boolean falg=true;
	    if(geter.equals("")){
	    request.setAttribute("error", "");
	        falg=false;
	    }
	   
	   
	    
	    
	    String sql="insert into "+Content.TB_TEXT+" values('"+subject+"','"+sender+"','"+geter+"','"+mark+"','"+content+"','"+sendtime+"','0','0')";
	    boolean f=DBUtil.getExecute(sql);
	  
	    if(!f){
	    	request.setAttribute("error", "出错了");
	    	return "/text/Add_text.jsp";
	    }
	   
	    return "/text/Add_text.jsp";
	    
	}	
}