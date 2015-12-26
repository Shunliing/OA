<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>添加会议记录</title>
	</head>
	<body bgcolor="#075B97">
	<center>
		<table width="965" border="0" cellpadding="0" cellspacing="0" >
				<tr><td colspan="4"><jsp:include page="/top.jsp"/></td></tr>
				<tr>
					<td width="13" background="<%=path%>/image/left.jpg"></td>
					<td align="center" valign="top" bgcolor="#F1FAFF"><jsp:include page="/side.jsp"/></td>
					<td width="715" align="center" valign="top"  bgcolor="#E8F1F6">
        				<br><br>
        				<table><tr><td><html:errors/></td></tr></table>
         				<form action="MeetServlet?type=add" method="post">
         				<table width="80%" border="1" cellspacing="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" bgcolor="white" rules="all">
          					<tr valign="middle"><td height="25" align="center">添加会议信息</td></tr>
          					<tr valign="middle"><td height="25" align="left" bgcolor="#B9D9E9"><b>会议信息:</b></td></tr>
          					<tr>
					            <td height="135" align="center" valign="middle">
              						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                						<tr height="25">
                  							<td width="25%" align="center">时&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                  							<td width="75%" align="left">&nbsp;<input type="text" name="time" size="40"/>&nbsp;输入格式:2008-08-08</td>
                						</tr>
                						<tr height="25">
                  							<td align="center">主 持 人：</td>
                  							<td align="left">&nbsp;<input type="text" name="speaker" size="40"/></td>
                						</tr>
                						<tr height="25">
                  							<td align="center">出 席 人：</td>
                  							<td align="left">&nbsp;<input type="text" name="listener" size="40"/></td>
                						</tr>
                						<tr height="25">
                  							<td align="center">会议地点：</td>
                  							<td align="left">&nbsp;<input type="text" name="address" size="67"/></td>
                						</tr>
                						<tr height="25">
                  							<td align="center">主&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
                  							<td align="left">&nbsp;<input type="text" name="subject" size="67"/></td>
                						</tr>
            						</table>
            					</td>
          					</tr>
          					<tr><td height="25" bgcolor="#B9D9E9"><b>会议内容:</b></td></tr>
          					<tr>
            					<td height="155" align="center" valign="middle">
               						<textarea name="content" cols="77" rows="10"></textarea><br>
               						<input type="submit" value="添加会议"/>
               						<input type="reset" value="重新填写"/>
            					</td>
          					</tr>
        				</table>
					    </form>
					</td>
					<td width="13" background="<%=path%>/image/right.jpg"></td>
				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
		</table>
	</center>
	</body>
</html>