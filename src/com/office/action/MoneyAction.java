//package com.office.action;
//
//
//import java.util.ArrayList;
//
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
//import com.office.actionform.MoneyCarForm;
//import com.office.actionform.MoneyThingForm;
//import com.office.actionform.SearchForm;
//import com.office.dao.Content;
//import com.office.dao.DB;
//import com.office.officemenu.Searchtypelist;
//import com.office.tools.Change;
//import com.office.tools.CheckUserAble;
///*************************************
//
//��Ȩ����: 	����ʡ���տƼ����޹�˾
//
//������ڣ�	2010-09-15
//
//��Ŀ������	��̴ʵ�
//
//����������	www.mingribook.com
//
//ѧϰ������	www.mrbccd.com
//
//*************************************/
//
//public class MoneyAction extends Action {
//	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		
//		LogonForm logonuser=(LogonForm)session.getAttribute("logonuser");
//	    if(logonuser==null){
//			 errors=new ActionMessages();
//			 errors.add("notAllow",new ActionMessage("office.logon.notallow"));
//			 saveErrors(request,errors);
//			 return mapping.findForward("notallowlogon");
//		}
//				
//		if(!CheckUserAble.check(logonuser)){
//			errors.add("notAllow",new ActionMessage("office.user.noallow"));
//			saveErrors(request,errors);
//			return mapping.findForward("notallow");
//		}
//		
//		if(!CheckUserAble.check1(logonuser)){
//			errors.add("notAllow",new ActionMessage("office.user.noallow"));
//			saveErrors(request,errors);
//			return mapping.findForward("notallow");
//		}
//		
//		String mark=mapping.getParameter();
//		ActionForward forward=new ActionForward();
//		if(mark.equals("money_look"))
//			forward=executeMoneyLook(mapping,form,request,response);
//		if(mark.equals("money_things_look"))
//			forward=executeMoneyThingLook(mapping,form,request,response);
//		if(mark.equals("money_things_add"))
//			forward=executeMoneyThingAdd(mapping,form,request,response);
//		if(mark.equals("money_things_update"))
//			forward=executeMoneyThingUpdate(mapping,form,request,response);
//		if(mark.equals("money_things_del"))
//			forward=executeMoneyThingDel(mapping,form,request,response);
//		if(mark.equals("money_cars_look"))
//			forward=executeMoneyCarLook(mapping,form,request,response);
//		if(mark.equals("money_cars_sigle_look"))
//			forward=executeMoneyCarSigleLook(mapping,form,request,response);
//		if(mark.equals("money_cars_add"))
//			forward=executeMoneyCarAdd(mapping,form,request,response);
//		if(mark.equals("money_cars_update"))
//			forward=executeMoneyCarUpdate(mapping,form,request,response);
//		if(mark.equals("money_cars_del"))
//			forward=executeMoneyCarDel(mapping,form,request,response);
//		return forward;
//	}
//	
//	public ActionForward executeMoneyLook(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//        HttpSession session=request.getSession();
//        
//        ArrayList carstatustypelist=Searchtypelist.getcarstatustypelist();
//        session.setAttribute("carstatustypelist", carstatustypelist);
//        
//        session.setAttribute("selectmenu1", "money");
//        session.setAttribute("selectmenu2", "");
//	    return mapping.findForward("success");
//	}
//	public ActionForward executeMoneyThingLook(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu2", "money_things");
//		session.setAttribute("selectmenu3", "money_things_look");
//	    
//		ArrayList thingtypelist=Searchtypelist.getthingsearchtypelist();
//		session.setAttribute("thingtypelist", thingtypelist);
//		
//		SearchForm searchform = (SearchForm) form;
//
//		String sql = "";
//		String subsql = searchform.getSubsql();
//		
//		String subsqlvalue = searchform.getSubsqlvalue();
//		if (subsql == null || subsql.equals(""))
//			subsql = "all";
//		if (subsql.equals("all")){
//			subsqlvalue="";
//			sql = "select * from " + Content.TB_THING;
//		}
//	    else{
//	    	 if (subsqlvalue == null || subsqlvalue.equals("")) {
//				errors.add("novalue", new ActionMessage("office.search.notiaojian.error"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			 }
//		     
//		     sql="select * from "+Content.TB_THING+" where "+subsql+"='"+subsqlvalue+"'";
//	    }
//		searchform.setSubsqlvalue(subsqlvalue);
//	    
//	    String str_currentpage=request.getParameter("page");
//        if(str_currentpage==null||str_currentpage.equals(""))
//			 str_currentpage="0";
//		int currentpage=Change.strtoint(str_currentpage);
//		if(currentpage<=0)
//			currentpage=1;
//
//		DB db=new DB();
//		ArrayList thinglist=db.getallthinglist(sql,currentpage);
//	    
//	    session.setAttribute("numrs",Integer.toString(db.getNumrs()));
//	    session.setAttribute("numper", Integer.toString(db.getNumper()));
//	    session.setAttribute("currentpage",Integer.toString(db.getCurrentPage()));
//	    session.setAttribute("numpages", Integer.toString(db.getNumPages()));
//		session.setAttribute("thinglist", thinglist);
//		
//		return mapping.findForward("success");
//	}
//	public ActionForward executeMoneyThingAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//				
//		session.setAttribute("selectmenu3", "money_things_add");
//		MoneyThingForm moneythingform=(MoneyThingForm)form;
//		String updatetype=request.getParameter("addtype");
//		 if(updatetype==null||updatetype.equals(""))updatetype="link";
//		if(updatetype.equals("link"))
//			return mapping.findForward("add");
//				
//		if(updatetype.equals("add")){
//			boolean mark=true;
//			String type=moneythingform.getType();
//			String model=moneythingform.getModel();
//			String number1=moneythingform.getNumber();
//			 int number=Change.strtoint(number1);
//			String cost1=moneythingform.getCost();
//			 float cost=Change.strtofloat(cost1);
//			String buytime=moneythingform.getBuytime();			 
//			String buybranch=moneythingform.getBuybranch();
//			if(type==null||type.equals("")){
//				mark=false;
//				errors.add("notype",new ActionMessage("office.money.thing.notype.error"));
//			}
//			if(model==null||model.equals("")){
//				mark=false;
//				errors.add("nomodel",new ActionMessage("office.money.thing.nomodel.error"));
//			}
//			if(number1==null||number1.equals("")){
//				mark=false;
//				errors.add("nonum",new ActionMessage("office.money.thing.nonumber.error"));
//			}
//			if(cost1==null||cost1.equals("")){
//				mark=false;
//				errors.add("nocost",new ActionMessage("office.money.thing.nocost.error"));
//			}
//			if(buytime==null||buytime.equals("")){
//				mark=false;
//				errors.add("nobuytime",new ActionMessage("office.money.thing.nobuytime.error"));
//			}
//			if(buybranch==null||buybranch.equals("")){
//                mark=false;			
//				errors.add("nobuybranch",new ActionMessage("office.money.thing.nobuybranch.error"));
//			}
//			if(!mark){
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//			if(number==-1){
//			    mark=false;
//			    errors.add("nonum",new ActionMessage("office.money.thing.number.notnumber.error"));
//			}
//			if(cost==-1){
//			    mark=false;
//			    errors.add("nocost",new ActionMessage("office.money.thing.cost.notnumber.error"));
//			}
//			if(!mark){
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//			String timeerrors=Change.checkdate(buytime);
//			if(!(timeerrors.equals("success"))){
//				errors.add("timesE",new ActionMessage(timeerrors));
//			    saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//			String sql="insert into "+Content.TB_THING+" values('"+type+"','"+model+"','"+number1+"','"+cost1+"','"+buytime+"','"+buybranch+"')";
//			DB db=new DB();
//			int i=db.update(sql);
//			if(i==0){
//				errors.add("addR",new ActionMessage("office.insert.false"));
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//		}
//		moneythingform.clear();
//		errors.add("addR",new ActionMessage("office.insert.success"));
//		saveErrors(request,errors);
//		return mapping.findForward("success");
//	}
//	
//	public ActionForward executeMoneyThingUpdate(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		
//		MoneyThingForm moneythingform=(MoneyThingForm)form;
//		String updatetype=request.getParameter("updatetype");
//		 if(updatetype==null||updatetype.equals(""))updatetype="link";
//		if(updatetype.equals("link")){
//			String lookid1=request.getParameter("id");
//			 if(lookid1==null||lookid1.equals(""))lookid1="0";
//			int lookid=Change.strtoint(lookid1);
//			session.setAttribute("id", lookid1);
//			ArrayList thinglist=(ArrayList)session.getAttribute("thinglist");
//			boolean flag=false;		
//			for(int i=0;i<thinglist.size();i++){
//				MoneyThingForm thingsigleform=(MoneyThingForm)thinglist.get(i);
//				if(lookid==thingsigleform.getThingid()){
//					flag=true;
//					moneythingform.setType(thingsigleform.getType());
//			    	moneythingform.setModel(thingsigleform.getModel());
//			    	moneythingform.setNumber(thingsigleform.getNumber());
//			    	moneythingform.setCost(thingsigleform.getCost());
//			        moneythingform.setBuytime(thingsigleform.getBuytime());
//			        moneythingform.setBuybranch(thingsigleform.getBuybranch());
//				    break;
//				}
//			}
//			if(!flag){
//				errors.add("lookR",new ActionMessage("office.money.thing.look.sigle.error"));
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//			return mapping.findForward("update");
//		}
//		
//		if(updatetype.equals("update")){
//			boolean mark=true;
//			String type=moneythingform.getType();
//			String model=moneythingform.getModel();
//			String number1=moneythingform.getNumber();
//			 int number=Change.strtoint(number1);
//			String cost1=moneythingform.getCost();
//			 float cost=Change.strtofloat(cost1);
//			String buytime=moneythingform.getBuytime();
//			String buybranch=moneythingform.getBuybranch();
//
//			if(buytime==null||buytime.equals("")){
//				mark=false;
//				errors.add("nobuytime",new ActionMessage("office.money.thing.nobuytime.error"));
//			}
//			if(buybranch==null||buybranch.equals("")){
//                mark=false;			
//				errors.add("nobuybranch",new ActionMessage("office.money.thing.nobuybranch.error"));
//			}
//			if(number==-1){
//			    mark=false;
//			    errors.add("nonum",new ActionMessage("office.money.thing.number.nonumber.error"));
//			}
//			if(cost==-1){
//			    mark=false;
//			    errors.add("nocost",new ActionMessage("office.money.thing.cost.nocost.error"));
//			}
//			if(!mark){
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//		    String timeerrors=Change.checkdate(buytime);
//			if(!timeerrors.equals("success")){
//				errors.add("timeR",new ActionMessage(timeerrors));
//		    	saveErrors(request,errors);
//		    	return mapping.findForward("false");
//		    }
//			
//			String id=(String)session.getAttribute("id");
//			String sql="update "+Content.TB_THING+" set "+Content.THINGNUM+"='"+number1+"',"+Content.THINGCOST+"='"+cost1+"',"+Content.THINGTIME+"='"+buytime+"',"+Content.THINGBRANCH+"='"+buybranch+"' where "+Content.THINGID+"="+id;
//			DB db=new DB();
//			int i=db.update(sql);
//			if(i==0){
//				errors.add("updateR",new ActionMessage("office.update.false"));
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//		}
//		
//		errors.add("updateR",new ActionMessage("office.update.success"));
//		saveErrors(request,errors);
//		return mapping.findForward("success");
//	}
//	public ActionForward executeMoneyThingDel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		String thingid1=request.getParameter("id");
//		 if(thingid1==null||thingid1.equals(""))thingid1="";
//        int thingid=Change.strtoint(thingid1);
//		String sql="delete from "+Content.TB_THING+" where "+Content.THINGID+"="+thingid;
//	    DB db=new DB();
//	    int i=db.del(sql);
//	    if(i==0){
//	    	errors.add("deleteR",new ActionMessage("office.del.false"));
//	    	saveErrors(request,errors);
//	    	return mapping.findForward("false");
//	    }
//		return mapping.findForward("success");
//	}
//	
//	public ActionForward executeMoneyCarLook(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		ActionMessages errors=new ActionMessages();
//		session.setAttribute("selectmenu2", "money_cars");
//		session.setAttribute("selectmenu3", "money_cars_look");
//		
//		SearchForm searchform = (SearchForm) form;
//
//		String sql = "";
//		String subsql = searchform.getSubsql();
//		String subsqlvalue = searchform.getSubsqlvalue();
//		if (subsql == null || subsql.equals(""))
//			subsql = "all";
//		if (subsql.equals("all")){
//			subsqlvalue="";
//			sql = "select * from " + Content.TB_CAR;
//		}
//	    else{
//	    	 if (subsqlvalue == null || subsqlvalue.equals("")) {
//				errors.add("novalue", new ActionMessage("office.search.notiaojian.error"));
//				saveErrors(request, errors);
//				return mapping.findForward("false");
//			 }
//		     
//		     sql="select * from "+Content.TB_CAR+" where "+subsql+"='"+subsqlvalue+"'";
//	    }
//		searchform.setSubsqlvalue(subsqlvalue);
//	    
//	    String str_currentpage=request.getParameter("page");
//        if(str_currentpage==null||str_currentpage.equals(""))
//			 str_currentpage="0";
//		int currentpage=Change.strtoint(str_currentpage);
//		if(currentpage<=0)
//			currentpage=1;
//
//		DB db=new DB();
//		ArrayList carlist=db.getallcarlist(sql,currentpage);
//	    
//	    session.setAttribute("numrs",Integer.toString(db.getNumrs()));
//	    session.setAttribute("numper", Integer.toString(db.getNumper()));
//	    session.setAttribute("currentpage",Integer.toString(db.getCurrentPage()));
//	    session.setAttribute("numpages", Integer.toString(db.getNumPages()));
//		session.setAttribute("carlist", carlist);
//		
//		return mapping.findForward("success");
//	}
//	public ActionForward executeMoneyCarSigleLook(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		HttpSession session=request.getSession();
//		MoneyCarForm moneycarform=(MoneyCarForm)form; 
//		String lookid1=request.getParameter("id");
//		 if(lookid1==null||lookid1.equals(""))lookid1="0";
//		int lookid=Change.strtoint(lookid1);
//		
//		ArrayList carlist=(ArrayList)session.getAttribute("carlist");
//		boolean flag=false;		
//		for(int i=0;i<carlist.size();i++){
//			MoneyCarForm carsigleform=(MoneyCarForm)carlist.get(i);
//			if(lookid==carsigleform.getCarid()){
//				flag=true;
//				moneycarform.setNumber(carsigleform.getNumber());
//	    		moneycarform.setModel(carsigleform.getModel());
//	    		moneycarform.setFdj(carsigleform.getFdj());
//	    		moneycarform.setDriver(carsigleform.getDriver());
//	    		moneycarform.setMaker(carsigleform.getMaker());
//	    		moneycarform.setBuytime(carsigleform.getBuytime());
//	    		moneycarform.setStatus(carsigleform.getStatus());
//	    		moneycarform.setNjinfo(carsigleform.getNjinfo());
//	    		moneycarform.setInfo(carsigleform.getInfo());
//			    break;
//			}
//		}
//		if(!flag){
//			ActionMessages errors=new ActionMessages();
//			errors.add("lookR",new ActionMessage("office.money.car.look.sigle.error"));
//			saveErrors(request,errors);
//			return mapping.findForward("looksiglewrong");
//		}
//		return mapping.findForward("success");
//	}
//	public ActionForward executeMoneyCarAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu3", "money_cars_add");
//		MoneyCarForm moneycarform=(MoneyCarForm)form;
//		String addtype=request.getParameter("addtype");
//		 if(addtype==null||addtype.equals(""))addtype="link";
//		if(addtype.equals("link"))
//			return mapping.findForward("add");
//			
//		if(addtype.equals("add")){
//			boolean mark=true;
//			String number=moneycarform.getNumber();
//			String model=moneycarform.getModel();
//			String fdj=moneycarform.getFdj();
//			String driver=moneycarform.getDriver();
//			String maker=moneycarform.getMaker();
//			String buytime=moneycarform.getBuytime();
//			String status=moneycarform.getStatus();
//			String njinfo=moneycarform.getNjinfo();
//			String info=moneycarform.getInfo();
//		
//			if(number==null||number.equals("")){
//				mark=false;
//				errors.add("nonum",new ActionMessage("office.money.car.nonumber.error"));
//			}
//			if(model==null||model.equals("")){
//				mark=false;
//				errors.add("nomodel",new ActionMessage("office.money.car.nomodel.error"));
//			}
//			if(fdj==null||fdj.equals("")){
//				mark=false;
//				errors.add("nofdj",new ActionMessage("office.money.car.nofdj.error"));
//			}
//			if(driver==null||driver.equals("")){
//				mark=false;
//				errors.add("nodriver",new ActionMessage("office.money.car.nodriver.error"));
//			}
//			if(maker==null||maker.equals("")){
//				mark=false;
//				errors.add("nomaker",new ActionMessage("office.money.car.nomaker.error"));
//			}
//			if(buytime==null||buytime.equals("")){
//				mark=false;
//				errors.add("nobuytime",new ActionMessage("office.money.car.nobuytime.error"));
//			}
//			if(njinfo==null||njinfo.equals("")){
//                mark=false;			
//				errors.add("nonjinfo",new ActionMessage("office.money.car.nonjinfo.error"));
//			}
//			if(info==null||info.equals("")){
//			    mark=false;
//			    errors.add("noinfo",new ActionMessage("office.money.car.noinfo.error"));
//			}
//			if(!mark){
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//			
//			String timeerrors=Change.checkdate(buytime);
//		    if(!timeerrors.equals("success")){
//				errors.add("timeE",new ActionMessage(timeerrors));
//			    saveErrors(request,errors);
//			    return mapping.findForward("false");
//			}			
//			String sql="insert into "+Content.TB_CAR+" values('"+number+"','"+model+"','"+fdj+"','"+driver+"','"+maker+"','"+buytime+"','"+status+"','"+njinfo+"','"+info+"')";
//			System.out.println(sql);
//			DB db=new DB();
//			int i=db.inseart(sql);
//			if(i==0){
//				errors.add("addR",new ActionMessage("office.insert.false"));
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//		}
//		moneycarform.clear();
//		errors.add("addR",new ActionMessage("office.insert.success"));
//		saveErrors(request,errors);
//		return mapping.findForward("success");
//	}
//
//	public ActionForward executeMoneyCarUpdate(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		ActionMessages errors=new ActionMessages();
//		HttpSession session=request.getSession();
//		session.setAttribute("selectmenu3", "");
//		MoneyCarForm moneycarform=(MoneyCarForm)form;
//		String updatetype=request.getParameter("updatetype");
//		 if(updatetype==null||updatetype.equals(""))updatetype="link";
//		if(updatetype.equals("link")){
//			String lookid1=request.getParameter("id");
//			 if(lookid1==null||lookid1.equals(""))lookid1="0";
//			int lookid=Change.strtoint(lookid1);
//			session.setAttribute("id", lookid1);
//			ArrayList carlist=(ArrayList)session.getAttribute("carlist");
//			boolean flag=false;		
//			for(int i=0;i<carlist.size();i++){
//				MoneyCarForm carsigleform=(MoneyCarForm)carlist.get(i);
//				if(lookid==carsigleform.getCarid()){
//					flag=true;
//					moneycarform.setNumber(carsigleform.getNumber());
//		    		moneycarform.setModel(carsigleform.getModel());
//		    		moneycarform.setFdj(carsigleform.getFdj());
//		    		moneycarform.setDriver(carsigleform.getDriver());
//		    		moneycarform.setMaker(carsigleform.getMaker());
//		    		moneycarform.setBuytime(carsigleform.getBuytime());
//		    		moneycarform.setStatus(carsigleform.getStatus());
//		    		moneycarform.setNjinfo(carsigleform.getNjinfo());
//		    		moneycarform.setInfo(carsigleform.getInfo());
//				    break;
//				}
//			}
//			if(!flag){
//				errors.add("lookR",new ActionMessage("office.money.car.look.sigle.error"));
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//			return mapping.findForward("update");
//		}
//		
//		if(updatetype.equals("update")){
//			boolean mark=true;
//			String number=moneycarform.getNumber();
//			String model=moneycarform.getModel();
//			String fdj=moneycarform.getFdj();
//			String driver=moneycarform.getDriver();
//			String maker=moneycarform.getMaker();
//			String buytime=moneycarform.getBuytime();
//			String status=moneycarform.getStatus();
//			String njinfo=moneycarform.getNjinfo();
//			String info=moneycarform.getInfo();
//
//			if(driver==null||driver.equals("")){
//				mark=false;
//				errors.add("nodriver",new ActionMessage("office.money.car.nodriver.error"));
//			}
//			if(maker==null||maker.equals("")){
//				mark=false;
//				errors.add("nomaker",new ActionMessage("office.money.car.nomaker.error"));
//			}
//			if(buytime==null||buytime.equals("")){
//				mark=false;
//				errors.add("nobuytime",new ActionMessage("office.money.car.nobuytime.error"));
//			}
//			if(njinfo==null||njinfo.equals("")){
//                mark=false;			
//				errors.add("nonjinfo",new ActionMessage("office.money.car.nonjinfo.error"));
//			}
//			if(info==null||info.equals("")){
//			    mark=false;
//			    errors.add("noinfo",new ActionMessage("office.money.car.noinfo.error"));
//			}
//			if(!mark){
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//			
//
//			String timeerrors=Change.checkdate(buytime);
//		    if(!timeerrors.equals("success")){
//				errors.add("timeE",new ActionMessage(timeerrors));
//			    saveErrors(request,errors);
//			    return mapping.findForward("false");
//			}
//		    String id=(String)session.getAttribute("id");
//			String sql="update "+Content.TB_CAR+" set "+Content.CARDRIVER+"='"+driver+"',"+Content.CARMAKER+"='"+maker+"',"+Content.CARBUYTIME+"='"+buytime+"',"+Content.CARSTATUS+"='"+status+"',"+Content.CARNJ+"='"+njinfo+"',"+Content.CARINFO+"='"+info+"' where "+Content.CARID+"="+id;
//			DB db=new DB();
//			int i=db.update(sql);
//			if(i==0){
//				errors.add("updateR",new ActionMessage("office.update.false"));
//				saveErrors(request,errors);
//				return mapping.findForward("false");
//			}
//		}
//		
//		errors.add("updateR",new ActionMessage("office.update.success"));
//		saveErrors(request,errors);
//		return mapping.findForward("success");
//	}
//
//	public ActionForward executeMoneyCarDel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
//		String carid1=request.getParameter("id");
//		 if(carid1==null||carid1.equals(""))carid1="0";
//		int carid=Change.strtoint(carid1);
//		String sql="delete from "+Content.TB_CAR+" where "+Content.CARID+"="+carid;
//	    DB db=new DB();
//	    int i=db.del(sql);
//	    if(i==0){
//	    	ActionMessages errors=new ActionMessages();
//	    	errors.add("deleteR",new ActionMessage("office.del.false"));
//	    	saveErrors(request,errors);
//	    	return mapping.findForward("false");
//	    }
//		return mapping.findForward("success");
//	}	
//}