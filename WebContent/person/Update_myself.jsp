<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>更新个人信息</title>
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
          				<html:form action="/person/person_myself_update.do?updatetype=update" focus="userpassword">
          				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
            				<tr><td height="25" align="center">更新个人信息</td></tr>
            				<tr>
              					<td height="274" align="center" valign="middle">
                					<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
                  						<tr>
                    						<td width="23%" height="30" align="right" valign="middle" bgcolor="#B9D9E9">用户名：&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="username" style="border:0" readonly="true" size="40"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">密码：&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:password property="userpassword" size="40" redisplay="false"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">确认密码：&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:password property="useraginpassword" size="40" redisplay="false"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">姓名：&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="usertruename" size="40"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">性别：&nbsp;</td>
                    						<td width="19%" align="left">
                    							&nbsp;<html:select property="usersex">
                                            			<html:options collection="personsextypelist" property="value" labelProperty="label"/>
                                            		  </html:select>
                                            </td>
                    						<td width="14%" align="center" bgcolor="#B9D9E9">权限：</td>
                    						<td colspan="3" align="left">
                    							<html:select property="userable">
                                                	<html:options collection="personabletypelist" property="value" labelProperty="label"/>
                                                </html:select>
                                            </td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">职务：&nbsp;</td>
                    						<td align="left">
                    							&nbsp;<html:select property="userjob">
                                                		<html:options collection="personjobtypelist" property="value" labelProperty="label"/>
                                                	  </html:select>
                                            </td>
                    						<td align="center" bgcolor="#B9D9E9">部门：</td>
                    						<td width="14%" align="left">
                    							<html:select property="userbranch">
                                                	<html:options collection="personbranchtypelist" property="value" labelProperty="label"/>
                                                </html:select>
                    						</td>
                    						<td width="13%" align="center" bgcolor="#B9D9E9">称号：</td>
                    						<td width="17%" align="left">
                    							<html:select property="usergood">
                                                	<html:options collection="persongoodtypelist" property="value" labelProperty="label"/>
                                                </html:select>
                                            </td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">电话：&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="usertel" size="40"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">E-mail：&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="useremail" size="40"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">地址：&nbsp;</td>
                    						<td colspan="5" align="left">&nbsp;<html:text property="useraddress" size="65"/></td>
                  						</tr>
                					</table>
              					</td>
            				</tr>
            				<tr valign="middle">
              					<td height="23" align="center" valign="middle">
                 					<html:submit value="更新信息"/>
                 					<html:reset  value="重新填写"/>
              					</td>
            				</tr>
          				</table>
          				</html:form>
					</td>
					<td width="13" background="<%=path%>/image/right.jpg"></td>
				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>