package com.office.tools;

import com.office.vo.User;
/*************************************

��Ȩ����: 	����ʡ���տƼ����޹�˾

������ڣ�	2010-09-15

��Ŀ������	��̴ʵ�

����������	www.mingribook.com

ѧϰ������	www.mrbccd.com

*************************************/

public class CheckUserAble {
  private static boolean allow=false;
  
  public static boolean check(User logonuser){
	  int able=logonuser.getUserable();
	  if(able!=0)
		  allow=true;
	  else
		  allow=false;
	  return allow;
  }
	
  public static boolean check1(User logonuser){
	  int able=logonuser.getUserable();
	  if(able!=1)
		  allow=true;
	  else
		  allow=false;
	 
	  return allow;
  }
}
