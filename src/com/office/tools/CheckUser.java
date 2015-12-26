package com.office.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class CheckUser {
	private static ManagerTrace managertrace=new ManagerTrace();
    public static boolean check(String id,HttpServletRequest request){
    	managertrace.setId(id);
    	boolean mark=true;
    	Vector vector=managertrace.getVc();
    	if(vector.contains(id)){
    		mark=false;    		
    	}
    	else{
    		HttpSession session=request.getSession();
    	    session.setAttribute("addlogonuser",managertrace);
    	    mark=true;
    	}
    	return mark;
    }
}
