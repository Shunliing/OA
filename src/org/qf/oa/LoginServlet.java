package org.qf.oa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.qf.oa.util.DBUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * @Field serialVersionUID
	 */
	private static final long serialVersionUID = 7780139890027601873L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("password");

		String sql = "select * from oa_user u where u.user_name='" + name
				+ "' and u.user_pwd='" + pwd + "'";
		ResultSet rs = DBUtil.getResult(sql, "");
		try {
			if (rs.next()) {
				int right = rs.getInt("role_number");
				int number = rs.getInt("user_number");
				req.getSession().setAttribute("user", number);
				req.getSession().setAttribute("right", right);
				// return "mainmanage";
			} else {
				req.setAttribute("error", "用户名或密码有误");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

}