package com.office.officemenu;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.office.dao.Content;
import com.office.util.DBUtil;
import com.office.vo.User;

public class OfficeMenu extends TagSupport {
	/**
	 * @Field serialVersionUID
	 */
	private static final long serialVersionUID = 5461330963729704430L;

	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		HttpSession session = request.getSession();
		ArrayList<MenuSigle> menulist1 = new ArrayList<>();
		menulist1 = (ArrayList<MenuSigle>) session.getAttribute("menulist");
		LoadMenu(menulist1);
		return SKIP_BODY;
	}

	private void LoadMenu(ArrayList<MenuSigle> menulist1) {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		JspWriter jspwriter = pageContext.getOut();
		String selectmenu1 = (String) session.getAttribute("selectmenu1");
		if (selectmenu1 == null)
			selectmenu1 = "";
		try {
			if (menulist1 == null || menulist1.size() == 0) {
				jspwriter
						.write("<table width='214' bgcolor='#1387AC'><tr><td height='300' align='center' valign='middle' colspan='2'>û�в˵�����ʾ��</td></tr></table>");
			} else {
				jspwriter
						.write("<table width='214' bgcolor='#1387AC'><tr><td heigth='200' align='center'><table width='201' border='2' cellspacing='0' cellpadding='0' bordercolor='white' rules='rows'>");
				for (int i = 0; i < menulist1.size(); i++) {
					MenuSigle menu1sigle = (MenuSigle) menulist1.get(i);
					if (selectmenu1.equals(menu1sigle.getMenuid())) {
						jspwriter
								.write("<tr bgcolor='#E8F1F6' height='26'><td width='22%' align='center'><img src='"
										+ contextPath
										+ "/image/click.jpg'></td><td align='left' style='text-indent:3'><b>"
										+ menu1sigle.getMenuname()
										+ "</b></td></tr>");
						LoadMenu2(menu1sigle.getMenuid());
					} else {

						String menuico = "menuico" + i;
						jspwriter
								.write("<tr bgcolor='#E8F1F6' height='26' onMouseOver=\"javascript:"
										+ menuico
										+ ".src='"
										+ contextPath
										+ "/image/on.jpg'\" onMouseOut=\"javascript:"
										+ menuico
										+ ".src='"
										+ contextPath
										+ "/image/init.jpg'\"><td width='22%' align='center'><img id='"
										+ menuico
										+ "' src='"
										+ contextPath
										+ "/image/init.jpg'></td><td align='left' style='text-indent:3'><a href='"
										+ contextPath
										+ menu1sigle.getMenuaction()
										+ "'>"
										+ menu1sigle.getMenuname()
										+ "</a></td></tr>");
						// 添加下一级菜单,正确？？？？？
						LoadMenu2(menu1sigle.getMenuid());
					}
				}
				jspwriter.write("</table></td></tr></table>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void LoadMenu2(String menuparentid) {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		int userable = ((User) session.getAttribute("logonuser")).getUserable();
		JspWriter jspwriter = pageContext.getOut();
		String selectmenu2 = (String) session.getAttribute("selectmenu2");
		if (selectmenu2 == null)
			selectmenu2 = "";
		ArrayList<MenuSigle> menulist2 = new ArrayList<>();
		String sql = "select * from " + Content.TB_MENU + " where "
				+ Content.MENUPARENTID + "='" + menuparentid + "' and "
				+ Content.MENUJIBIE + "='2' and " + Content.USERABLE + " <= "
				+ userable + " order by " + Content.MENUORDER;
		ResultSet rs = DBUtil.getResult(sql);
		try {
			while (rs.next()) {
				MenuSigle menusigle = new MenuSigle();
				menusigle.setMenuid(rs.getString(Content.MENUID));
				menusigle.setMenuname(rs.getString(Content.MENUNAME));
				menusigle.setMenuaction(rs.getString(Content.MENUACTION));
				menusigle.setMenuorder(rs.getInt(Content.MENUORDER));
				menulist2.add(menusigle);
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		try {
			if (menulist2 != null && menulist2.size() != 0) {
				jspwriter
						.write("<tr><td align='center' colspan='2' style='padding-top:5;padding-bottom:5;padding-left:5;padding-right:5' background='"
								+ contextPath
								+ "/image/menu2b.jpg'><table border='0' width='187' cellpadding='0' cellspacing='1' bordercolor='#6DB0CB' rules='none'>");
				for (int i = 0; i < menulist2.size(); i++) {
					MenuSigle menu2sigle = (MenuSigle) menulist2.get(i);
					if (selectmenu2.equals((menu2sigle).getMenuid())) {
						jspwriter
								.write("<tr bgcolor='#FFFCED'><td height='28' style='text-indent:20' valign='bottom' nowrap><b>&nbsp;<a href='"
										+ contextPath
										+ menu2sigle.getMenuaction()
										+ "'>"
										+ menu2sigle.getMenuname()
										+ "</b></td></tr>");
						session.setAttribute("pagepath",
								menu2sigle.getMenuaction());
						LoadMenu3(menu2sigle.getMenuid());
					} else {
						jspwriter
								.write("<tr bgcolor='#FFFCED'><td height='28' style='text-indent:20' valign='bottom' nowrap>&nbsp;<a href='"
										+ contextPath
										+ menu2sigle.getMenuaction()
										+ "'>"
										+ menu2sigle.getMenuname()
										+ "</a></td></tr>");
					}
				}
				jspwriter.write("</table></td></tr>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void LoadMenu3(String menuparentid) {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		int userable = ((User) session.getAttribute("logonuser")).getUserable();
		JspWriter jspwriter = pageContext.getOut();
		String selectmenu3 = (String) session.getAttribute("selectmenu3");
		if (selectmenu3 == null)
			selectmenu3 = "";
		ArrayList<MenuSigle> menulist3 = new ArrayList<>();
		String sql = "select * from " + Content.TB_MENU + " where "
				+ Content.MENUPARENTID + "='" + menuparentid + "' and "
				+ Content.MENUJIBIE + "='3' and " + Content.USERABLE + "<="
				+ userable + " order by " + Content.MENUORDER;
		// DB db = new DB();
		// menulist3 = db.getmenulist(sql);

		ResultSet rs = DBUtil.getResult(sql);
		try {
			while (rs.next()) {
				MenuSigle menusigle = new MenuSigle();
				menusigle.setMenuid(rs.getString(Content.MENUID));
				menusigle.setMenuname(rs.getString(Content.MENUNAME));
				menusigle.setMenuaction(rs.getString(Content.MENUACTION));
				menusigle.setMenuorder(rs.getInt(Content.MENUORDER));
				menulist3.add(menusigle);
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		try {
			if (menulist3 == null || menulist3.size() == 0) {
			} else {
				jspwriter
						.write("<tr><td align='center' nowrap><table width='100%' border='0' cellpadding='0' cellspacing='0'>");
				for (int i = 0; i < menulist3.size(); i++) {
					MenuSigle menu3sigle = (MenuSigle) menulist3.get(i);
					if (selectmenu3.equals((menu3sigle).getMenuid())) {
						jspwriter
								.write("<tr bgcolor='#F6FF94'><td height='30' valign='bottom' style='text-indent:37' nowrap><b><a href='"
										+ contextPath
										+ menu3sigle.getMenuaction()
										+ "'>"
										+ menu3sigle.getMenuname()
										+ "</b></td></tr>");
						session.setAttribute("pagepath",
								menu3sigle.getMenuaction());
					} else {
						jspwriter
								.write("<tr bgcolor='#F6FF94'><td height='30' valign='bottom' style='text-indent:37' nowrap><a href='"
										+ contextPath
										+ menu3sigle.getMenuaction()
										+ "'>"
										+ menu3sigle.getMenuname()
										+ "</a></td></tr>");
					}
				}
				jspwriter.write("</table></td></tr>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
