<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>


<%
   String title="";
   String hrefpath="";
   String action=request.getParameter("from");
   if(action==null||action.equals(""))
	   action="get";
   if(action.equals("get")){
	   title="查看收邮件箱";
	   hrefpath="/mail/mail_look_get.do";
   }
   if(action.equals("send")){
	   title="查看发邮件箱";
	   hrefpath="/mail/mail_look_send.do";
   }
   pageContext.setAttribute("action",action);
   String path=request.getContextPath();
%>
<html>
	<head>
		<html:base/>
		<title>查看邮件详细内容</title>
	</head>
	<body bgcolor="#075B97">
	<center>
		<table width="965" border="0" cellpadding="0" cellspacing="0">
				<tr><td colspan="4"><jsp:include page="/top.jsp"/></td></tr>
				<tr bgcolor="#EDEEFF">
					<td width="13" background="<%=path%>/image/left.jpg"></td>
					<td align="center" valign="top" bgcolor="#EDEEFF"><jsp:include page="/side.jsp"/></td>
					<td width="720" align="center" valign="top"  bgcolor="#E8F1F6">
        				<br><br>
      					<table width="85%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
        					<tr><td height="25" align="center" class="style3"><%=title%></td></tr>
        					<tr valign="middle">
          						<td align="center" valign="middle">
            						<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
              							<html:form action="/mail/mail_look_sigle.do">
                						<tr valign="middle">
                							<td width="23%" height="30" align="right" valign="middle" bgcolor="#B9D9E9">主题:&nbsp;</td>
                							<td height="30">&nbsp;<html:text property="subject" style="border:0" readonly="true"/></td>
             	 						</tr>
              							<tr valign="middle">
                   							<logic:equal value="get" name="action">
                     						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">发件人:&nbsp;</td>
                     						<td height="30" align="left">&nbsp;<html:text property="sender" style="border:0" readonly="true"/></td>
                   							</logic:equal>
                   							<logic:equal value="send" name="action">
                     						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">收件人:&nbsp;</td>
                     						<td height="30" align="left">&nbsp;<html:text property="geter" style="border:0" readonly="true"/></td>
                   							</logic:equal>
              							</tr>
              							<tr valign="middle">
                							<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">发送时间:&nbsp;</td>
                							<td height="30" align="left">&nbsp;<html:text property="stime" style="border:0" readonly="true"/></td>
              							</tr>
              							<tr valign="middle">
                							<td height="73" align="right" valign="middle" bgcolor="#B9D9E9">邮件内容:&nbsp;</td>
                							<td height="73" align="left">&nbsp;<html:textarea property="content" style="border:0" readonly="true" rows="10" cols="58"/></td>
              							</tr>
            							</html:form>
          							</table>
         						</td>
        					</tr>
      					</table>
      					<table><tr height="30"><td align="center"><a href="<%=request.getContextPath()%><%=hrefpath%>?page=<%=session.getAttribute("currentpage")%>">[返回]</a></td></tr></table>
					</td>
					<td width="13" background="<%=path%>/image/right.jpg"></td>
				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>