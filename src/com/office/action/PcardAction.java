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

//通知相关的Servelt
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

	// Post方法调用
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User logonuser = (User) session.getAttribute("logonuser");
		String url = "";
		if (logonuser == null) {
			request.setAttribute("error", "invalid");
			url = "index.jsp";
		}

		String mark = request.getParameter("type");
		if (mark.equals("link")) {
			url = "/pcard/Add_pcard.jsp";
		}
		if (mark.equals("query"))
			url = executePcardLook(request, response);
		if (mark.equals("add"))
			url = executePcardAdd(request, response);
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 查询所有通知
	public String executePcardLook(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("selectmenu1", "pcard");
		session.setAttribute("selectmenu2", "pcard_look");
		String sql = "select * from " + Content.TB_PCARD + " order by "
				+ Content.PCARDTIME + " desc";

		String str_currentpage = request.getParameter("page");
		if (str_currentpage == null || str_currentpage.equals(""))
			str_currentpage = "0";
		int currentpage = Change.strtoint(str_currentpage);
		if (currentpage <= 0)
			currentpage = 1;

		DBUtil db = new DBUtil();
		ArrayList<Pcard> pcardlist = db.getallpcardlist(sql, currentpage);

		session.setAttribute("numrs", Integer.toString(db.getNum_rs()));
		session.setAttribute("numper", Integer.toString(db.getNum_per()));
		session.setAttribute("currentpage",
				Integer.toString(db.getNum_currentpage()));
		session.setAttribute("numpages", Integer.toString(db.getNum_pages()));
		session.setAttribute("pcardlist", pcardlist);

		return "/pcard/Look_pcard.jsp";
	}

	// 添加新通知
	public String executePcardAdd(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		User logonuser = (User) session.getAttribute("logonuser");

		if (!CheckUserAble.check(logonuser)) {
			request.setAttribute("error", "");
			return "index.jsp";
		}

		session.setAttribute("selectmenu2", "pcard_add");

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		String author = ((User) session.getAttribute("logonuser"))
				.getUsername();
		String time = Change.datetostr(new Date());
		String sql = "insert into " + Content.TB_PCARD + " values('" + subject
				+ "','" + author + "','" + time + "','" + content + "')";
		boolean flag = DBUtil.getExecute(sql);
		if (!flag) {
			request.setAttribute("error", "");

		}
        //查询所有通知
		return executePcardLook(request, response);
	}
}