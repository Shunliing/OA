package com.office.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sqlite.core.DB;

import com.office.dao.Content;
import com.office.tools.Change;
import com.office.tools.CheckUserAble;
import com.office.vo.User;

@WebServlet("/UserServlet")
public class UserAction extends HttpServlet {
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
		User logonuser = (User) session.getAttribute("logonuser");
		String url = "";
		if (logonuser == null) {
			request.setAttribute("error", "");
			url = "index.jsp";
		}

		String mark = request.getParameter("type");

		if (mark.equals("query"))
			url = executePersonLook(request, response);
//		if (mark.equals("person_sigle_look"))
//			url = executePersonSigleLook(request, response);
		if (mark.equals("myself"))
			url = executePersonMyselfLook(request, response);
//		if (mark.equals("person_add"))
//			url = executePersonAdd(request, response);
//		if (mark.equals("person_update"))
//			url = executePersonUpdate(request, response);
//		if (mark.equals("person_myself_update"))
//			url = executeMyUpdate(request, response);
//		if (mark.equals("person_del"))
//			url = executePersonDel(request, response);
//		if (mark.equals("person_good"))
//			url = executePersonGood(request, response);
		request.getRequestDispatcher(url).forward(request, response);
	}

	public String executePersonLook(HttpServletRequest request,
			HttpServletResponse response) {
	
		HttpSession session = request.getSession();

		User logonuser = (User) session.getAttribute("logonuser");
		if (!CheckUserAble.check(logonuser)) {
			request.setAttribute("error", "");
			return "index.jsp";
		}

		session.setAttribute("selectmenu2", "person_look");
		SearchForm searchform = (SearchForm) form;

		String sql = "";
		String subsql = searchform.getSubsql();
		String subsqlvalue = searchform.getSubsqlvalue();
		if (subsql == null || subsql.equals(""))
			subsql = "all";
		if (subsql.equals("all")) {
			subsqlvalue = "";
			sql = "select * from " + Content.TB_USER;
		} else if (subsql.equals("user_good")) {
			session.setAttribute("selectmenu2", "person_good");
			searchform.setSubsql(subsql);
			if (subsqlvalue == null || subsqlvalue.equals("")) {
				sql = "select * from " + Content.TB_USER
						+ " where user_good='1'";
			} else {
				sql = "select * from " + Content.TB_USER + " where "
						+ Content.USERNAME + "='" + subsqlvalue
						+ "' and user_good='1'";
			}
		} else if (subsql.equals("user_able")) {
			if (subsqlvalue == null || subsqlvalue.equals("")) {
				errors.add("novalue", new ActionMessage(
						"office.search.notiaojian.error"));
				saveErrors(request, errors);
				return mapping.findForward("false");
			}
			int subsqlvalue1 = -1;
			if (subsqlvalue.equals("�ο�"))
				subsqlvalue1 = 0;
			else if (subsqlvalue.equals("��ͨ����Ա"))
				subsqlvalue1 = 1;
			else if (subsqlvalue.equals("ϵͳ����Ա"))
				subsqlvalue1 = 2;
			sql = "select * from " + Content.TB_USER + " where user_able="
					+ subsqlvalue1;
		} else {
			if (subsqlvalue == null || subsqlvalue.equals("")) {
				errors.add("novalue", new ActionMessage(
						"office.search.notiaojian.error"));
				saveErrors(request, errors);
				return mapping.findForward("false");
			}
			sql = "select * from " + Content.TB_USER + " where " + subsql
					+ "='" + subsqlvalue + "'";
		}
		String str_currentpage = request.getParameter("page");
		if (str_currentpage == null || str_currentpage.equals(""))
			str_currentpage = "0";
		int currentpage = Change.strtoint(str_currentpage);
		if (currentpage <= 0)
			currentpage = 1;

		DB db = new DB();
		ArrayList personlist = db.getallpersonlist(sql, currentpage);

		session.setAttribute("numrs", Integer.toString(db.getNumrs()));
		session.setAttribute("numper", Integer.toString(db.getNumper()));
		session.setAttribute("currentpage",
				Integer.toString(db.getCurrentPage()));
		session.setAttribute("numpages", Integer.toString(db.getNumPages()));
		session.setAttribute("personlist", personlist);
		searchform.setSubsqlvalue(subsqlvalue);

		return mapping.findForward("success");
	}

//	public ActionForward executePersonGood(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		ActionMessages errors = new ActionMessages();
//		HttpSession session = request.getSession();
//
//		LogonForm logonuser = (LogonForm) session.getAttribute("logonuser");
//
//		if (!CheckUserAble.check(logonuser)) {
//			session.setAttribute("selectmenu2", "");
//			errors.add("notAllow", new ActionMessage("office.user.noallow"));
//			saveErrors(request, errors);
//			return mapping.findForward("notallow");
//		}
//
//		SearchForm searchform = (SearchForm) form;
//		searchform.setSubsql(Content.USERGOOD);
//		executePersonLook(mapping, form, request, response);
//		return mapping.findForward("success");
//	}
//
//	public ActionForward executePersonSigleLook(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		ActionMessages errors = new ActionMessages();
//		HttpSession session = request.getSession();
//
//		LogonForm logonuser = (LogonForm) session.getAttribute("logonuser");
//
//		if (!CheckUserAble.check(logonuser)) {
//			session.setAttribute("selectmenu2", "");
//			errors.add("notAllow", new ActionMessage("office.user.noallow"));
//			saveErrors(request, errors);
//			return mapping.findForward("notallow");
//		}
//
//		UserForm userform = (UserForm) form;
//		String lookid1 = request.getParameter("id");
//		if (lookid1 == null || lookid1.equals(""))
//			lookid1 = "0";
//		int lookid = Change.strtoint(lookid1);
//
//		ArrayList personlist = (ArrayList) session.getAttribute("personlist");
//		boolean flag = false;
//		for (int i = 0; i < personlist.size(); i++) {
//			UserForm personsigleform = (UserForm) personlist.get(i);
//			if (lookid == personsigleform.getUserid()) {
//				flag = true;
//				userform.setUsername(personsigleform.getUsername());
//				userform.setUsertruename(personsigleform.getUsertruename());
//				userform.setUserable(personsigleform.getUserable());
//				userform.setUsergood(personsigleform.getUsergood());
//				userform.setUserbranch(personsigleform.getUserbranch());
//				userform.setUserjob(personsigleform.getUserjob());
//				userform.setUsersex(personsigleform.getUsersex());
//				userform.setUseremail(personsigleform.getUseremail());
//				userform.setUsertel(personsigleform.getUsertel());
//				userform.setUseraddress(personsigleform.getUseraddress());
//				userform.setUseraccesstimes(personsigleform
//						.getUseraccesstimes());
//				userform.setUserfoundtime(personsigleform.getUserfoundtime());
//				break;
//			}
//		}
//		if (!flag) {
//			errors.add("lookR", new ActionMessage(
//					"office.person.look.sigle.error"));
//			saveErrors(request, errors);
//			return mapping.findForward("looksiglewrong");
//		}
//		return mapping.findForward("success");
//	}

	public String executePersonMyselfLook(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("selectmenu1", "person");
		session.setAttribute("selectmenu2", "person_myself");
//		ArrayList personsextypelist = Searchtypelist.getpersonsextypelist();
//		ArrayList personabletypelist = Searchtypelist.getpersonabletypelist();
//		ArrayList personjobtypelist = Searchtypelist.getpersonjobtypelist();
//		ArrayList personbranchtypelist = Searchtypelist
//				.getpersonbranchtypelist();
//		ArrayList persongoodtypelist = Searchtypelist.getpersongoodtypelist();
//		session.setAttribute("personabletypelist", personabletypelist);
//		session.setAttribute("personjobtypelist", personjobtypelist);
//		session.setAttribute("personbranchtypelist", personbranchtypelist);
//		session.setAttribute("persongoodtypelist", persongoodtypelist);
//		session.setAttribute("personsextypelist", personsextypelist);

//		LogonForm logonform = (LogonForm) session.getAttribute("logonuser");
//		LogonForm myform = (LogonForm) form;
//		myform.setUsername(logonform.getUsername());
//		myform.setUsertruename(logonform.getUsertruename());
//		myform.setUserable(logonform.getUserable());
//		myform.setUsergood(logonform.getUsergood());
//		myform.setUserbranch(logonform.getUserbranch());
//		myform.setUserjob(logonform.getUserjob());
//		myform.setUsersex(logonform.getUsersex());
//		myform.setUseremail(logonform.getUseremail());
//		myform.setUsertel(logonform.getUsertel());
//		myform.setUseraddress(logonform.getUseraddress());
//		myform.setUseraccesstimes(logonform.getUseraccesstimes());
//		myform.setUserfoundtime(logonform.getUserfoundtime());
		return "/person/Look_myself.jsp";

	}

//	public String executePersonAdd(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//		ActionMessages errors = new ActionMessages();
//		HttpSession session = request.getSession();
//
//		LogonForm logonuser = (LogonForm) session.getAttribute("logonuser");
//
//		if (!CheckUserAble.check(logonuser)) {
//			session.setAttribute("selectmenu2", "");
//			errors.add("notAllow", new ActionMessage("office.user.noallow"));
//			saveErrors(request, errors);
//			return mapping.findForward("notallow");
//		}
//
//		session.setAttribute("selectmenu2", "person_add");
//
//		UserForm userform = (UserForm) form;
//		errors = new ActionMessages();
//
//		String addtype = request.getParameter("addtype");
//		if (addtype == null || addtype.equals(""))
//			addtype = "link";
//		if (addtype.equals("link")) {
//			return mapping.findForward("add");
//		}
//
//		if (addtype.equals("add")) {
//
//			boolean mark = true;
//			String username = userform.getUsername();
//			String password1 = userform.getUserpassword();
//			String password2 = userform.getUseraginpassword();
//			String truename = userform.getUsertruename();
//			String tel = userform.getUsertel();
//			String address = userform.getUseraddress();
//			String sex = userform.getUsersex();
//			String job = userform.getUserjob();
//			int able = userform.getUserable();
//			String branch = userform.getUserbranch();
//			String usergood = userform.getUsergood();
//			String email = userform.getUseremail();
//
//			if (username == null || username.equals("")) {
//				mark = false;
//				errors.add("noname", new ActionMessage(
//						"office.person.nousername.error"));
//			}
//			if (password1 == null || password1.equals("")) {
//				mark = false;
//				errors.add("nopswd", new ActionMessage(
//						"office.person.nopassword1.error"));
//			}
//			if (password2 == null || password2.equals("")) {
//				mark = false;
//				errors.add("nopswd1", new ActionMessage(
//						"office.person.nopassword2.error"));
//			}
//			if (truename == null || truename.equals("")) {
//				mark = false;
//				errors.add("notruename", new ActionMessage(
//						"office.person.notruename.error"));
//			}
//			if (tel != null && !(tel.equals(""))) {
//				try {
//					Long.parseLong(tel);
//				} catch (Exception e) {
//					e.printStackTrace();
//					errors.add("nonum", new ActionMessage(
//							"office.person.telnotnum.error"));
//					mark = false;
//				}
//			}
//			if (!mark) {
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//			if (!password1.equals(password2)) {
//				errors.add("nosame", new ActionMessage(
//						"office.person.password.nosame.error"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//			String sql = "select * from " + Content.TB_USER + " where "
//					+ Content.USERNAME + "='" + username + "'";
//			DB db = new DB();
//			ResultSet rs = db.getRs(sql);
//			try {
//				if (rs.next()) {
//					userform.setUsertel(tel);
//					userform.setUseremail(email);
//					userform.setUsername(username);
//					userform.setUsertruename(truename);
//					userform.setUseraddress(address);
//					userform.setUsersex(sex);
//					userform.setUserable(able);
//					userform.setUserbranch(branch);
//					userform.setUsergood(usergood);
//					userform.setUserjob(job);
//					errors.add("exist", new ActionMessage(
//							"office.person.add.have.exist.error"));
//					saveErrors(request, errors);
//					return mapping.findForward("false");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("cun zai gai ji lu !");
//			}
//
//			String foundtime = Change.datetostr(new Date());
//			sql = "insert into " + Content.TB_USER + " values('" + username
//					+ "','" + password1 + "','" + truename + "'," + able + ",'"
//					+ usergood + "','" + branch + "','" + job + "','" + sex
//					+ "','" + email + "','" + tel + "','" + address + "'," + 0
//					+ ",'" + foundtime + "')";
//
//			int i = db.inseart(sql);
//			if (i == 0) {
//				errors.add("addR", new ActionMessage("office.insert.false"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//		}
//		errors.add("addR", new ActionMessage("office.insert.success"));
//		saveErrors(request, errors);
//		userform.clear();
//		return mapping.findForward("success");
//
//	}
//
//	public ActionForward executePersonUpdate(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//		UserForm userform = (UserForm) form;
//
//		ActionMessages errors = new ActionMessages();
//		HttpSession session = request.getSession();
//
//		LogonForm logonuser = (LogonForm) session.getAttribute("logonuser");
//
//		if (!CheckUserAble.check(logonuser)) {
//			session.setAttribute("selectmenu2", "");
//			errors.add("notAllow", new ActionMessage("office.user.noallow"));
//			saveErrors(request, errors);
//			return mapping.findForward("notallow");
//		}
//
//		String updatetype = request.getParameter("updatetype");
//		if (updatetype == null || updatetype.equals(""))
//			updatetype = "link";
//		if (updatetype.equals("link")) {
//			String lookid1 = request.getParameter("id");
//			if (lookid1 == null || lookid1.equals(""))
//				lookid1 = "0";
//			int lookid = Change.strtoint(lookid1);
//			session.setAttribute("id", lookid1);
//			ArrayList personlist = (ArrayList) session
//					.getAttribute("personlist");
//			boolean flag = false;
//			for (int i = 0; i < personlist.size(); i++) {
//				UserForm personsigleform = (UserForm) personlist.get(i);
//				if (lookid == personsigleform.getUserid()) {
//					flag = true;
//					userform.setUsername(personsigleform.getUsername());
//					userform.setUsertruename(personsigleform.getUsertruename());
//					userform.setUserable(personsigleform.getUserable());
//					userform.setUsergood(personsigleform.getUsergood());
//					userform.setUserbranch(personsigleform.getUserbranch());
//					userform.setUserjob(personsigleform.getUserjob());
//					userform.setUsersex(personsigleform.getUsersex());
//					userform.setUseremail(personsigleform.getUseremail());
//					userform.setUsertel(personsigleform.getUsertel());
//					userform.setUseraddress(personsigleform.getUseraddress());
//					userform.setUseraccesstimes(personsigleform
//							.getUseraccesstimes());
//					userform.setUserfoundtime(personsigleform
//							.getUserfoundtime());
//					break;
//				}
//			}
//			if (!flag) {
//				errors.add("lookR", new ActionMessage(
//						"office.meet.look.sigle.error"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//			return mapping.findForward("update");
//		}
//
//		if (updatetype.equals("update")) {
//			boolean mark = true;
//			String username = userform.getUsername();
//			String password1 = userform.getUserpassword();
//			String password2 = userform.getUseraginpassword();
//			String truename = userform.getUsertruename();
//			String tel = userform.getUsertel();
//			String address = userform.getUseraddress();
//			String sex = userform.getUsersex();
//			String job = userform.getUserjob();
//			int able = userform.getUserable();
//			String branch = userform.getUserbranch();
//			String usergood = userform.getUsergood();
//			String email = userform.getUseremail();
//
//			if (password1 == null || password1.equals("")) {
//				mark = false;
//				errors.add("nopswd1", new ActionMessage(
//						"office.person.nopassword1.error"));
//			}
//			if (password2 == null || password2.equals("")) {
//				mark = false;
//				errors.add("nopswd2", new ActionMessage(
//						"office.person.nopassword2.error"));
//			}
//			if (truename == null || truename.equals("")) {
//				mark = false;
//				errors.add("notruename", new ActionMessage(
//						"office.person.notruename.error"));
//			}
//			if (tel != null && !(tel.equals(""))) {
//				try {
//					Long.parseLong(tel);
//				} catch (Exception e) {
//					e.printStackTrace();
//					errors.add("nonum", new ActionMessage(
//							"office.person.telnotnum.error"));
//					mark = false;
//				}
//			}
//			if (!mark) {
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//			if (!password1.equals(password2)) {
//				errors.add("nosame", new ActionMessage(
//						"office.person.password.nosame.error"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//			String sql = "update " + Content.TB_USER + " set "
//					+ Content.USERTRUENAME + "='" + truename + "',"
//					+ Content.USERABLE + "=" + able + "," + Content.USERGOOD
//					+ "='" + usergood + "'," + Content.USERBRANCH + "='"
//					+ branch + "'," + Content.USERJOB + "='" + job + "',"
//					+ Content.USERPASSWORD + "='" + password1 + "',"
//					+ Content.USERSEX + "='" + sex + "'," + Content.USEREMAIL
//					+ "='" + email + "'," + Content.USERTEL + "='" + tel + "',"
//					+ Content.USERADDRESS + "='" + address + "' where "
//					+ Content.USERNAME + "='" + username + "'";
//			DB db = new DB();
//			int i = db.update(sql);
//			if (i == 0) {
//				errors.add("updaeR", new ActionMessage("office.update.false"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//		}
//		return mapping.findForward("success");
//	}
//
//	public ActionForward executeMyUpdate(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//		ActionMessages errors = new ActionMessages();
//		HttpSession session = request.getSession();
//		session.setAttribute("selectmenu2", "person_myself");
//		LogonForm myform = (LogonForm) form;
//		String updatetype = request.getParameter("updatetype");
//		if (updatetype == null || updatetype.equals(""))
//			updatetype = "link";
//		if (updatetype.equals("link")) {
//			LogonForm logonform = (LogonForm) session.getAttribute("logonuser");
//			myform.setUsername(logonform.getUsername());
//			myform.setUsertruename(logonform.getUsertruename());
//			myform.setUserable(logonform.getUserable());
//			myform.setUsergood(logonform.getUsergood());
//			myform.setUserbranch(logonform.getUserbranch());
//			myform.setUserjob(logonform.getUserjob());
//			myform.setUsersex(logonform.getUsersex());
//			myform.setUseremail(logonform.getUseremail());
//			myform.setUsertel(logonform.getUsertel());
//			myform.setUseraddress(logonform.getUseraddress());
//			myform.setUseraccesstimes(logonform.getUseraccesstimes());
//			myform.setUserfoundtime(logonform.getUserfoundtime());
//			return mapping.findForward("update");
//		}
//
//		if (updatetype.equals("update")) {
//
//			boolean mark = true;
//			String username = myform.getUsername();
//			String password1 = myform.getUserpassword();
//			String password2 = myform.getUseraginpassword();
//			String truename = myform.getUsertruename();
//			String tel = myform.getUsertel();
//			String address = myform.getUseraddress();
//			String sex = myform.getUsersex();
//			String job = myform.getUserjob();
//			int able = myform.getUserable();
//			String branch = myform.getUserbranch();
//			String usergood = myform.getUsergood();
//			String email = myform.getUseremail();
//
//			if (password1 == null || password1.equals("")) {
//				mark = false;
//				errors.add("nopswd1", new ActionMessage(
//						"office.person.nopassword1.error"));
//			}
//			if (password2 == null || password2.equals("")) {
//				mark = false;
//				errors.add("nopswd2", new ActionMessage(
//						"office.person.nopassword2.error"));
//			}
//			if (truename == null || truename.equals("")) {
//				mark = false;
//				errors.add("notruename", new ActionMessage(
//						"office.person.notruename.error"));
//			}
//			if (tel != null || !(tel.equals(""))) {
//				try {
//					Long.parseLong(tel);
//				} catch (Exception e) {
//					e.printStackTrace();
//					errors.add("nonum", new ActionMessage(
//							"office.person.telnotnum.error"));
//					mark = false;
//				}
//			}
//			if (!mark) {
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//			if (!password1.equals(password2)) {
//				errors.add("nosame", new ActionMessage(
//						"office.person.password.nosame.error"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//			String sql = "update " + Content.TB_USER + " set "
//					+ Content.USERTRUENAME + "='" + truename + "',"
//					+ Content.USERABLE + "=" + able + "," + Content.USERGOOD
//					+ "='" + usergood + "'," + Content.USERBRANCH + "='"
//					+ branch + "'," + Content.USERJOB + "='" + job + "',"
//					+ Content.USERPASSWORD + "='" + password1 + "',"
//					+ Content.USERSEX + "='" + sex + "'," + Content.USEREMAIL
//					+ "='" + email + "'," + Content.USERTEL + "='" + tel + "',"
//					+ Content.USERADDRESS + "='" + address + "' where "
//					+ Content.USERNAME + "='" + username + "'";
//			DB db = new DB();
//			int i = db.update(sql);
//			if (i == 0) {
//				errors.add("updateR", new ActionMessage("office.update.false"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			}
//		}
//		errors.add("updateR", new ActionMessage(
//				"office.update.success.logon.agin"));
//		saveErrors(request, errors);
//		return mapping.findForward("success");
//	}
//
//	public ActionForward executePersonDel(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		ActionMessages errors = new ActionMessages();
//		HttpSession session = request.getSession();
//
//		LogonForm logonuser = (LogonForm) session.getAttribute("logonuser");
//
//		if (!CheckUserAble.check(logonuser)) {
//			session.setAttribute("selectmenu2", "");
//			errors.add("notAllow", new ActionMessage("office.user.noallow"));
//			saveErrors(request, errors);
//			return mapping.findForward("notallow");
//		}
//
//		String id1 = request.getParameter("id");
//		if (id1 == null || id1.equals(""))
//			id1 = "0";
//		int id = Change.strtoint(id1);
//		String sql = "delete from " + Content.TB_USER + " where "
//				+ Content.USERID + "=" + id;
//		DB db = new DB();
//		int i = db.del(sql);
//		if (i == 0) {
//			errors = new ActionMessages();
//			errors.add("deleteR", new ActionMessage("office.del.false"));
//			saveErrors(request, errors);
//			return mapping.findForward("false");
//		}
//		return mapping.findForward("success");
//	}
}