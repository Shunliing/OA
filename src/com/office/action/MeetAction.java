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

/*
 * 会议相关的Servlet
 */
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

	// Post方法调用
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
		if (mark.equals("link")) {
			url = "/meet/Add_meet.jsp";
		}
		if (mark.equals("add"))
			url = executeMeetAdd(request, response);
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 查询所有会议信息
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

	// 添加新会议信息
	public String executeMeetAdd(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		User logonuser = (User) session.getAttribute("logonuser");

		if (!CheckUserAble.check(logonuser)) {
			request.setAttribute("error", "notallow");
			return "index.jsp";
		}

		session.setAttribute("selectmenu2", "meet_add");
		String mark = request.getParameter("type");
		if (mark == null || mark.equals(""))
			mark = "link";
		if (mark.equals("link"))
			return "/meet/Look_meet.jsp";
		if (mark.equals("add")) {
			String time = request.getParameter("time");
			String speaker = request.getParameter("speaker");
			String listener = request.getParameter("listener");
			String address = request.getParameter("address");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");

			String sql = "insert into " + Content.TB_MEET + " values('"
					+ subject + "','" + speaker + "','" + listener + "','"
					+ time + "','" + address + "','" + content + "')";
			boolean f = DBUtil.getExecute(sql);
			if (!f) {
				request.setAttribute("error", "");
			}
		}
		//重新查询一次
		return executeMeetLook(request, response);
	}
}