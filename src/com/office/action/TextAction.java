package com.office.action;

/*
 * 收发文相关Servlet
 */
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

	// Post相关
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		User logonuser = (User) session.getAttribute("logonuser");
		String url = "/text/Add_text.jsp";
		if (logonuser == null) {
			req.setAttribute("error", "用户有误，请重新登录");
			url = "index.jsp";
		}
		String mark = req.getParameter("addtype");
		if (mark.equals("get"))
			url = executeTextLookGet(req, resp);
		if (mark.equals("send"))
			url = "/text/Look_sendtext.jsp";
		if (mark.equals("look"))
			url = "/text/Look_sigletext.jsp";
		if (mark.equals("add"))
			url = executeTextAdd(req, resp);
		if (mark.equals("del"))
			url = "/text/text_look_get.do";

		req.getRequestDispatcher(url).forward(req, resp);
	}

	// 查询所有发文
	public String executeTextLookGet(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Document> gettextlist = new ArrayList<>();
		HttpSession session = request.getSession();

		session.setAttribute("selectmenu2", "text_look");
		session.setAttribute("selectmenu3", "text_look_get");
		User logonuser = (User) session.getAttribute("logonuser");
		String user = logonuser.getUsername();
		String sql_get = "select * from " + Content.TB_TEXT + " where "
				+ Content.TEXTGETER + " ='" + user + "' and "
				+ Content.TEXTDELGETER + "='0'" + " order by "
				+ Content.TEXTMARK + " desc," + Content.TEXTSENDTIME + " desc";
		String str_currentpage = request.getParameter("page");
		if (str_currentpage == null || str_currentpage.equals(""))
			str_currentpage = "0";
		int currentpage = Change.strtoint(str_currentpage);
		if (currentpage <= 0)
			currentpage = 1;
		DBUtil u = new DBUtil();
		gettextlist = u.getalltextlist(sql_get, currentpage);

		session.setAttribute("numrs", Integer.toString(u.getNum_rs()));
		session.setAttribute("numper", Integer.toString(u.getNum_per()));
		session.setAttribute("currentpage",
				Integer.toString(u.getNum_currentpage()));
		session.setAttribute("numpages", Integer.toString(u.getNum_pages()));
		session.setAttribute("gettextlist", gettextlist);

		return "/text/Look_gettext.jsp";
	}

	public String executeTextAdd(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.setAttribute("selectmenu1", "text");
		session.setAttribute("selectmenu2", "text_add");
		User logonuser = (User) session.getAttribute("logonuser");
		String url = "";
		if (!CheckUserAble.check(logonuser)) {
			request.setAttribute("error", "");
			return "/text/Add_text.jsp";
		}
		String type = request.getParameter("addtype");
		if (type == null || type.equals(""))
			type = "link";
		if (type.equals("link"))
			return "/text/Add_text.jsp";

		String geter = request.getParameter("geter");
		if (geter == null)
			geter = "";
		String sender = logonuser.getUsername();

		String subject = request.getParameter("subject");
		if (subject == null || subject.equals(""))
			subject = "主题为空!";
		String content = request.getParameter("content");
		if (content == null || content.equals(""))
			content = "内容为空";
		String mark = "1";
		String sendtime = Change.datetostr(new Date());
		boolean falg = true;
		if (geter.equals("")) {
			request.setAttribute("error", "");
			falg = false;
		}

		String sql = "insert into " + Content.TB_TEXT + " values('" + subject
				+ "','" + sender + "','" + geter + "','" + mark + "','"
				+ content + "','" + sendtime + "','0','0')";
		boolean f = DBUtil.getExecute(sql);

		if (!f) {
			request.setAttribute("error", "出错了");
			return "/text/Add_text.jsp";
		}

		return "/text/Add_text.jsp";

	}
}