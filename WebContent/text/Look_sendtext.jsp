<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>浏览发文件箱</title>
	</head>
	<body bgcolor="#075B97">
	<center>
		<table width="965" border="0" cellpadding="0" cellspacing="0">
				<tr><td colspan="4"><jsp:include page="/top.jsp"/></td></tr>
				<tr bgcolor="#EDEEFF">
					<td width="13" background="<%=path%>/image/left.jpg"></td>
					<td align="center" valign="top" bgcolor="#EDEEFF"><jsp:include page="/side.jsp"/></td>
					<td width="725" align="center" valign="top"  bgcolor="#E8F1F6">
        				<br><br>
      					<table width="90%"  border="0">
        					<tr valign="bottom">
          						<td width="31%" height="25" align="left" style="border:0"><font color="red"><html:errors/></font></td>
          						<td width="47%" align="right">
                 					<logic:greaterEqual value="1" name="logonuser" property="userable">
                    				<a href="<%=request.getContextPath()%>/text/text_add.do?addtype=link">[新建发文]</a>
                 					</logic:greaterEqual>
          						</td>
          						<td width="21%" align="right">收/发文管理&nbsp;</td>
       						</tr>
      					</table>
      					<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
        					<tr><td height="30" colspan="4">发文件箱</td></tr>
       					 	<tr align="center"  bgcolor="#B9D9E9">
          						<td width="34%">发文主题</td>
          						<td width="25%">收件人</td>
         					 	<td width="25%">发送时间</td>
          						<td width="8%">操作</td>
        					</tr>
        					<logic:present name="sendtextlist">
        					<logic:iterate id="textsigle" name="sendtextlist">
        					<tr height="40" >
          						<td align="center"><a href="<%=request.getContextPath()%>/text/text_sigle_look.do?id=<bean:write name='textsigle' property='textid'/>&from=send"><bean:write name="textsigle" property="subject"/></a></td>
          						<td align="center"><bean:write name="textsigle" property="geter"/></td>
          						<td align="center"><bean:write name="textsigle" property="sendtime"/></td>
          						<td align="center">
             						<logic:greaterEqual value="1" name="logonuser" property="userable">
               						<a href="<%=request.getContextPath()%>/text/text_del.do?id=<bean:write name='textsigle' property='textid'/>&from=send&page=<%=session.getAttribute("currentpage")%>" onclick="return(confirm('确定要删除该记录吗?'))">[删除]</a>
             						</logic:greaterEqual>
             						<logic:equal value="0" name="logonuser" property="userable">无权限</logic:equal>
          						</td>
        					</tr>
			        		</logic:iterate>
        					</logic:present>
        					<logic:empty name="sendtextlist">
          					<tr><td colspan="5" align="center"><li><b>没有记录显示！</b></li></td></tr>
        					</logic:empty>
      					</table>
      					<jsp:include page="/page.jsp"/>
			   		</td>
			   		<td width="13" background="<%=path%>/image/right.jpg"></td>
  				</tr>
  				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>