<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-office" prefix="office" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String contentPath=request.getContextPath(); %>

<html>
	<head><title>功能栏</title></head>
	<body>
	<table width="224" cellspacing="0" cellpadding="0" bordercolor="#7CADD6" bgcolor="#F2FBFF">
		<!-- 显示登录用户信息 -->
		<tr height="30">
			<td style="text-indent:5">
				<img src="<%=contentPath%>/image/welcome.jpg"> 欢迎登录!
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=request.getContextPath()%>/person/person_myself_look.do">[个人信息]</a>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table background="<%=request.getContextPath()%>/image/myinfo.jpg" width="214" height="100"  border="0" cellspacing="0" cellpadding="0">
      				<c:if test="${logonuser!=null }">
       				<tr height="5"><td colspan="2"></td></tr>
       				<tr>
        				<td height="20" width="40%" align="right">欢 迎 您：</td>
	        			<td height="20">${logonuser.username}</td>
    	   			</tr>
       				<tr bordercolor="#CCCCCC">
        				<td height="20" align="right">身&nbsp;&nbsp;&nbsp;&nbsp;份：</td>
        				<td height="20">
        				    <c:if test="${logonuser.userable==2 }">系统管理员</c:if>
        					  <c:if test="${logonuser.userable==1 }">普通管理员</c:if>
        					  <c:if test="${logonuser.userable==0 }">游客</c:if> 
        				</td>     
       				</tr>
       				<tr bordercolor="#CCCCCC">
	        			<td height="20" align="right"><nobr>职&nbsp;&nbsp;&nbsp;&nbsp;务：</nobr></td>
				        <td height="20">${logonuser.userjob}</td>
				    </tr>
       				<tr bordercolor="#CCCCCC">
        				<td height="20" align="right"><font color="black"><nobr>访问次数：</nobr></td>
        				<td height="20">${logonuser.useraccesstimes} 次</td>
	       			</tr>
    	   			<tr height="5"><td colspan="2"></td></tr>
      			</c:if>
				   	<c:if test="${logonuser==null }">
      				<tr>
       					<td height="70" align="center">
	       					<logic:equal value="exit" parameter="type">您已经安全退出，请关闭浏览器！</logic:equal>
    	   					<logic:notEqual value="exit" parameter="type"> 非法登录！<a href="<%=request.getContextPath()%>/index.jsp">[请正常登录]！</a></logic:notEqual>
       					</td>
      				</tr>
      				</c:if>
	    		</table>
   			</td>
	  	</tr>
	  	<!-- 显示功能菜单 -->  
  		<tr><td height="30" style="text-indent:5"><img src="<%=contentPath%>/image/whatdo.jpg"> 我能做什么？</td></tr>
  		<tr><td align="center"><office:Menu/></td></tr>
	  	<tr height="5"><td></td></tr>
	</table>
	</body>
</html>
