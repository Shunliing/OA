//package com.office.action;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
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
//
//public class LogoffAction extends HttpServlet {
//	/**
//	 * @Field serialVersionUID
//	 */
//	private static final long serialVersionUID = -6108336634246814603L;
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		// TODO 自动生成的方法存根
//		super.doGet(req, resp);
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		// TODO 自动生成的方法存根
//		super.doPost(req, resp);
//	}
//	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
////		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
////	    if(logonuser==null){
////			 errors=new ActionMessages();
////			 errors.add("notAllow",new ActionMessage("office.logon.notallow"));
////			 saveErrors(request,errors);
////			 return mapping.findForward("notallowlogon");
////		}
//		session.invalidate();
//		String mark=mapping.getParameter();
//		if(mark.equals("logon_agin"))
//			return mapping.findForward("logon_agin");
//		else
//			return mapping.findForward("logoff");
//	}
//}