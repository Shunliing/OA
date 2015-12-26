<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>更新公告信息</title>
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
        				<table width="80%" border="1" cellspacing="0" cellpadding="2" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
          					<tr><td height="25" align="center" colspan="2">修改公告</td></tr>
         			 		<html:form action="/pcard/pcard_update.do?updatetype=update" focus="subject">
          					<tr height="30">
          						<td width="20%" align="center" bgcolor="#B9D9E9">发 布 人：</td>
                  				<td width="60%"><html:text property="author" size="45" disabled="true"/></td>
                			</tr>
                			<tr valign="middle">
                  				<td height="30" align="center" bgcolor="#B9D9E9">发布时间：</td>
                  				<td height="30"><html:text property="time" size="45" disabled="true"/></td>
                			</tr>
                			<tr valign="middle">
                  				<td height="30" align="center" bgcolor="#B9D9E9">主&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
                  				<td height="30"><html:text property="subject" size="67"/></td>
                			</tr>
                			<tr valign="middle">
                  				<td height="73" align="center" bgcolor="#B9D9E9">内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
                  				<td height="73"><html:textarea property="content" cols="56" rows="8"/></td>
                			</tr>
          					<tr valign="middle" align="center">
            					<td colspan="2">
               						<html:submit value="更新"/>
               						<html:reset value="重置"/>
            					</td>
          					</tr>
          				</html:form>
          				</table>
        				<table><tr><td align="center"><html:errors/></td></tr></table>
      				</td>
      				<td width="13" background="<%=path%>/image/right.jpg"></td>
    			</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
  			</table>
		</center>
	</body>
</html>