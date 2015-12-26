<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>查看会议详细内容</title>
	</head>
	<body bgcolor="#075B97">
	<center>
		<table width="965" border="0" cellpadding="0" cellspacing="0">
				<tr><td colspan="4"><jsp:include page="/top.jsp"/></td></tr>
				<tr>
					<td width="13" background="<%=path%>/image/left.jpg"></td>
					<td align="center" valign="top" bgcolor="#F1FAFF"><jsp:include page="/side.jsp"/></td>
					<td width="715" align="center" valign="top"  bgcolor="#E8F1F6">
        				<br><br>
				      	<table><tr><td><html:errors/></td></tr></table>
      					<html:form action="/meet/meet_sigle_look.do">
        				<table width="80%" border="1" cellspacing="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" bgcolor="white" rules="all">
          					<tr valign="middle"><td height="25" align="center">查看会议信息</td></tr>
          					<tr valign="middle"><td height="25" align="left" bgcolor="#B9D9E9"><b>会议信息:</b></td></tr>
          					<tr valign="middle">
            					<td align="center">
              						<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                						<tr height="25">
                  							<td width="30%" align="center">时&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                  							<td width="70%" align="left">&nbsp;<html:text property="time" size="40" style="border:0" readonly="true"/></td>
                						</tr>
                						<tr height="25">	
                  							<td align="center">主 持 人：</td>
                  							<td align="left">&nbsp;<html:text property="speaker" size="40" style="border:0" readonly="true"/></td>
                						</tr>
                						<tr height="25">
                  							<td align="center">出 席 人：</td>
                  							<td align="left">&nbsp;<html:text property="listener" size="40" style="border:0" readonly="true"/></td>
                						</tr>
                						<tr height="25">
                  							<td align="center">会议地点：</td>
                  							<td align="left">&nbsp;<html:text property="address" size="67" style="border:0" readonly="true"/></td>
                						</tr>
                						<tr height="25">
                  							<td align="center">主&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
                  							<td align="left">&nbsp;<html:text property="subject" size="67" style="border:0" readonly="true"/></td>
                						</tr>
            						</table>
            					</td>
          					</tr>
          					<tr><td height="25" bgcolor="#B9D9E9"><b>会议内容:</b></td></tr>
          					<tr><td align="center"><html:textarea property="content" cols="78" rows="10" readonly="true" style="border:0"/></td></tr>
          				</table>
        				</html:form>
        				<a href="<%=request.getContextPath()%>/meet/meet_look.do?page=<%=session.getAttribute("currentpage")%>">[返回]</a>
        			</td>
        			<td width="13" background="<%=path%>/image/right.jpg"></td>
				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>