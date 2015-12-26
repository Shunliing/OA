<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>更新办公用品信息</title>
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
          				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
	            			<tr><td height="25" align="center" class="style3">更新办公用品信息</td></tr>
            				<html:form action="/money/money_things_update?updatetype=update" focus="number">
            				<tr>
              					<td height="184" align="center" valign="middle">
                					<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
                  						<tr valign="middle">
                    						<td width="23%" height="30" align="right" valign="middle" bgcolor="#B9D9E9">类型:&nbsp;</td>
						                    <td height="30">&nbsp;<html:text property="type" style="border:0" readonly="true"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">品牌:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="model" style="border:0" readonly="true"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">数量:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="number" size="40"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">金额:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="cost" size="40"/></td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">购买日期:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="buytime" size="40"/>&nbsp;[格式:2008-08-08]</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9"><nobr>购买部门:&nbsp;</nobr></td>
                    						<td height="30" align="left">&nbsp;<html:text property="buybranch" size="60"/></td>
                  						</tr>
                					</table>
              					</td>
            				</tr>
            				<tr valign="middle">
              					<td height="23" align="center" valign="middle">
                					<html:submit value="更新"/>
                					<html:reset value="重置"/>
              					</td>
            				</tr>
            				</html:form>
          				</table>
          				<table><tr><td><html:errors/></td></tr></table>
					</td> 
					<td width="13" background="<%=path%>/image/right.jpg"></td>
				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>