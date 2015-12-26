<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>查看公告详细内容</title>
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
  						<table width="80%" border="1" cellspacing="0" cellpadding="2" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" bgcolor="white">
      						<tr align="center" bgcolor="#B9D9E9"><td height="25" colspan="2"><strong>查看公告</strong></td></tr>
      						<html:form action="/pcard/pcard_sigle_look.do">
      						<tr height="30">
        						<td width="20%" align="center" bgcolor="#B9D9E9"> 主&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
        						<td width="60%"><html:text property="subject" style="border:0" size="45" readonly="true"/></td>
      						</tr>		
      						<tr height="30">
        						<td align="center" bgcolor="#B9D9E9"> 公 布 人：</td>
        						<td><html:text property="author" style="border:0" size="45" readonly="true"/></td>
      						</tr>
      						<tr height="30">
        						<td align="center" bgcolor="#B9D9E9"> 公布时间：</td>
        						<td><html:text property="time" style="border:0" size="45" readonly="true"/></td>
      						</tr>
      						<tr height="30">
        						<td rowspan="2" align="center" bgcolor="#B9D9E9"> 内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
        						<td><html:textarea property="content" cols="56" rows="10" style="border:0" readonly="true"/>
        						</td>
      						</tr>
      						</html:form>
    					</table>
    					<table><tr height="30"><td align="center"><a href="<%=request.getContextPath()%>/pcard/pcard_look.do?page=<%=session.getAttribute("currentpage")%>">[返回]</a></td></tr></table>      
  					</td>
  					<td width="13" background="<%=path%>/image/right.jpg"></td>
  				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>