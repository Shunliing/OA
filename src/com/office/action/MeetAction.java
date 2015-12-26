package com.office.action;

import java.io.IOException;
import java.util.ArrayList;

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
import com.office.vo.Meet;
import com.office.vo.User;

@WebServlet("/MeetServlet")
public class MeetAction extends HttpServlet {

	/**
	 * @Field serialVersionUID
	 */
	private static final long serialVersionUID = 7785258265632436871L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "index.jsp";
		User logonuser = (User) session.getAttribute("logonuser");
		if (logonuser == null) {
			request.setAttribute("error", "invalide login");
		}
		String mark = request.getParameter("type");

		if (mark.equals("query"))
			url = executeMeetLook(request, response);
		// if(mark.equals("meet_sigle_look"))
		// url=executeMeetSigleLook(request,response);
		// if(mark.equals("meet_del"))
		// url=executeMeetDel(request,response);
		if (mark.equals("link")) {
			url = "/meet/Add_meet.jsp";
		}
		if (mark.equals("add"))
			url = executeMeetAdd(request, response);
		request.getRequestDispatcher(url).forward(request, response);
	}

	public String executeMeetLook(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Meet> meetlist = new ArrayList<>();
		HttpSession session = request.getSession();
		session.setAttribute("selectmenu1", "meet");
		session.setAttribute("selectmenu2", "meet_look");
		String sql = "select * from " + Content.TB_MEET + " order by "
				+ Content.MEETTIME + " desc";

		String str_currentpage = request.getParameter("page");
		if (str_currentpage == null || str_currentpage.equals(""))
			str_currentpage = "0";
		int currentpage = Change.strtoint(str_currentpage);
		if (currentpage <= 0)
			currentpage = 1;

		DBUtil db = new DBUtil();
		meetlist = db.getallmeetlist(sql, currentpage);

		session.setAttribute("numrs", Integer.toString(db.getNum_rs()));
		session.setAttribute("numper", Integer.toString(db.getNum_per()));
		session.setAttribute("currentpage",
				Integer.toString(db.getNum_currentpage()));
		session.setAttribute("numpages", Integer.toString(db.getNum_pages()));
		session.setAttribute("meetlist", meetlist);

		return "/meet/Look_meet.jsp";
	}

	// public ActionForward executeMeetSigleLook(ActionMapping mapping,
	// ActionForm form,HttpServletRequest request, HttpServletResponse
	// response){
	// ArrayList meetlist=new ArrayList();
	// HttpSession session=request.getSession();
	// MeetForm textform=(MeetForm)form;
	// String lookid1=request.getParameter("id");
	// if(lookid1==null||lookid1.equals(""))lookid1="0";
	// int lookid=Change.strtoint(lookid1);
	// meetlist=(ArrayList)session.getAttribute("meetlist");
	// boolean flag=false;
	// if(meetlist!=null||meetlist.size()!=0){
	// for(int i=0;i<meetlist.size();i++){
	// MeetForm meetsigleform=(MeetForm)meetlist.get(i);
	// if(lookid==meetsigleform.getMeetid()){
	// flag=true;
	// textform.setSubject(meetsigleform.getSubject());
	// textform.setSpeaker(meetsigleform.getSpeaker());
	// textform.setListener(meetsigleform.getListener());
	// textform.setAddress(meetsigleform.getAddress());
	// textform.setContent(meetsigleform.getContent());
	// textform.setTime(meetsigleform.getTime());
	// break;
	// }
	// }
	// }
	// if(!flag){
	// ActionMessages errors=new ActionMessages();
	// errors.add("lookR",new ActionMessage("office.meet.look.sigle.error"));
	// saveErrors(request,errors);
	// return mapping.findForward("false");
	// }
	// return mapping.findForward("success");
	// }
	// public ActionForward executeMeetDel(ActionMapping mapping, ActionForm
	// form,HttpServletRequest request, HttpServletResponse response){
	// ActionMessages errors=new ActionMessages();
	// HttpSession session=request.getSession();
	// LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
	// if(!CheckUserAble.check(logonuser)){
	// session.setAttribute("selectmenu2", "");
	// errors.add("notAllow",new ActionMessage("office.user.noallow"));
	// saveErrors(request,errors);
	// return mapping.findForward("notallow");
	// }
	//
	// String id1=request.getParameter("id");
	// int id=Change.strtoint(id1);
	// String
	// sql="delete from "+Content.TB_MEET+" where "+Content.MEETID+"="+id;
	// DB db=new DB();
	// int i=db.del(sql);
	// if(i==0){
	// errors.add("deleteR",new ActionMessage("office.del.false"));
	// saveErrors(request,errors);
	// return mapping.findForward("false");
	// }
	// return mapping.findForward("success");
	// }
	public String executeMeetAdd(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		User logonuser = (User) session.getAttribute("logonuser");

		if (!CheckUserAble.check(logonuser)) {
			request.setAttribute("error", "notallow");
			return "index.jsp";
		}

		session.setAttribute("selectmenu2", "meet_add");
		boolean flag = true;
		String mark = request.getParameter("type");
		if (mark == null || mark.equals(""))
			mark = "link";
		if (mark.equals("link"))
			return "/meet/Look_meet.jsp";
		if (mark.equals("add")) {
			String time = request.getParameter("time");
			String speaker = request.getParameter("speaker");
			String listener =request.getParameter("listener");
			String address = request.getParameter("address");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			if (time == null || time.equals("")) {			
				flag = false;
			}
			if (speaker == null || speaker.equals("")) {
				
				flag = false;
			}
			if (listener == null || listener.equals("")) {
				
				flag = false;
			}
			if (address == null || address.equals("")) {
				
				flag = false;
			}
			if (subject == null || subject.equals("")) {
			
				flag = false;
			}
			if (content == null || content.equals("")) {
				
				flag = false;
			}
			
			

			String sql = "insert into " + Content.TB_MEET + " values('"
					+ subject + "','" + speaker + "','" + listener + "','"
					+ time + "','" + address + "','" + content + "')";
			boolean f =DBUtil.getExecute(sql);
			if (!f) {
				request.setAttribute("error", "");				
			}
		}
		return executeMeetLook(request,response);
	}
}