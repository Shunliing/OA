package com.office.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.office.dao.Content;
import com.office.officemenu.MenuSigle;
import com.office.util.DBUtil;
import com.office.vo.User;

/*
 * 登录相关的Servlet：
 *   涉及校验事宜
 */
@WebServlet("/LoginServlet")
public class LogonAction extends HttpServlet {
	/**
	 * @Field serialVersionUID
	 */
	private static final long serialVersionUID = -2598581559336754688L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		super.doGet(req, resp);
	}

	// post方法调用
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String userpassword = req.getParameter("password");
		HttpSession session = req.getSession();
		// 用户是否存在
		String sql = "select * from " + Content.TB_USER + " where "
				+ Content.USERNAME + "='" + username + "' and "
				+ Content.USERPASSWORD + "='" + userpassword + "'";
		ResultSet rs = DBUtil.getResult(sql);
		try {
			if (rs.next()) {
				//Session中保存
				session.setAttribute("selectmenu1", "index");
				int userable = rs.getInt(Content.USERABLE);
				Loadmenu(userable, req);
				User user = new User();
				user.setUsername(rs.getString(Content.USERNAME));
				user.setUsertruename(rs.getString(Content.USERTRUENAME));
				user.setUserable(rs.getInt(Content.USERABLE));
				user.setUsergood(rs.getString(Content.USERGOOD));
				user.setUserbranch(rs.getString(Content.USERBRANCH));
				user.setUserjob(rs.getString(Content.USERJOB));
				user.setUsersex(rs.getString(Content.USERSEX));
				user.setUseremail(rs.getString(Content.USEREMAIL));
				user.setUsertel(rs.getString(Content.USERTEL));
				user.setUseraddress(rs.getString(Content.USERADDRESS));
				user.setUseraccesstimes(rs.getInt(Content.USERACCESSTIMES) + 1);
				user.setUserfoundtime(rs.getString(Content.USERFOUNDTIME));

				session.setAttribute("logonuser", user);
				req.getRequestDispatcher("default.jsp").forward(req, resp);

			} else {
				req.setAttribute("error", "invalid username or password");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	// 加载用户一级菜单
	private void Loadmenu(int userable, HttpServletRequest request) {
		ArrayList<MenuSigle> menuList = new ArrayList<MenuSigle>();
		String sql = "select * from " + Content.TB_MENU + " where "
				+ Content.USERABLE + " <= " + userable + " and "
				+ Content.MENUJIBIE + "='1' order by " + Content.MENUORDER;

		ResultSet rs = DBUtil.getResult(sql);
		try {
			while (rs.next()) {
				MenuSigle menusigle = new MenuSigle();
				menusigle.setMenuid(rs.getString(Content.MENUID));
				menusigle.setMenuname(rs.getString(Content.MENUNAME));
				menusigle.setMenuaction(rs.getString(Content.MENUACTION));
				menusigle.setMenuorder(rs.getInt(Content.MENUORDER));
				menuList.add(menusigle);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("menulist", menuList);

	}
}
