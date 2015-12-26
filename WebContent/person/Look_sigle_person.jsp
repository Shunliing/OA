<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>查看员工详细信息</title>
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
          				<table><tr><td><html:errors/></td></tr></table>
          				<html:form action="/person/person_sigle_look.do">
          				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
            				<tr><td height="25" align="center">查看员工信息</td></tr>
            				<tr>
              					<td height="278" align="center" valign="middle">
                					<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
                  						<tr valign="middle">
                    						<td width="23%" height="30" align="right" valign="middle" bgcolor="#B9D9E9">用户名:&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="username" style="border:0" readonly="true" size="40"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">姓名:&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="usertruename" style="border:0" size="10" readonly="true"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">性别:&nbsp;</td>
                    						<td width="19%" align="left">&nbsp;<html:text property="usersex" style="border:0" size="5" readonly="true"/></td>
						                    <td width="14%" align="center" bgcolor="#B9D9E9">权限:</td>
						                    <td colspan="3" align="left"><logic:equal value="0" name="userform" property="userable">&nbsp;游客</logic:equal>
                                                 <logic:equal value="1" name="userform" property="userable">&nbsp;普通管理员</logic:equal>
                                                 <logic:equal value="2" name="userform" property="userable">&nbsp;系统管理员</logic:equal>
						                    </td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">职务:&nbsp;</td>
                    						<td align="left">&nbsp;<html:text property="userjob" style="border:0" size="10" readonly="true"/></td>
                    						<td align="center" bgcolor="#B9D9E9">部门:</td>
                    						<td width="14%" align="left">&nbsp;<html:text property="userbranch" style="border:0" size="10" readonly="true"/></td>
                    						<td width="13%" align="center" bgcolor="#B9D9E9">称号:</td>
                    						<td width="17%" align="left">&nbsp;<logic:equal value="0" name="userform" property="usergood">普通员工</logic:equal>
                                            	<logic:equal value="1" name="userform" property="usergood">优秀员工</logic:equal>
                    						</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">电话:&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="usertel" style="border:0" size="30" readonly="true"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">E-mail:&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="useremail" style="border:0" size="30" readonly="true"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">地址:&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="useraddress" style="border:0" size="66" readonly="true"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">访问次数:&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="useraccesstimes" style="border:0" size="66" readonly="true"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">创建时间:&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="userfoundtime" style="border:0" size="66" readonly="true"/></td>
                  						</tr>
              						</table> 
              					</td>
            				</tr>
          				</table>
          				</html:form>
          				<table><tr height="30"><td align="center"><a href="<%=request.getContextPath()%>/person/person_look.do?page=<%=session.getAttribute("currentpage")%>">[返回]</a></td></tr></table>
					</td>
					<td width="13" background="<%=path%>/image/right.jpg"></td>
				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>