<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>添加车辆信息</title>
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
          				<html:form action="/money/money_cars_add.do?addtype=add" focus="number">
          				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
            				<tr><td height="25" align="center">添加新的车辆</td></tr>
            				<tr>
              					<td height="310" align="center" valign="middle">
                					<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
                  						<tr valign="middle">
                    						<td width="23%" height="30" align="right" valign="middle" bgcolor="#B9D9E9">牌号:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="number" size="50"/>&nbsp;*</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">型号:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="model" size="50"/>&nbsp;*</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">发动机型号:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="fdj" size="50"/>&nbsp;*</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">司机姓名:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="driver" size="50"/>&nbsp;*</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">生产厂家:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="maker" size="50"/>&nbsp;*</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">购买日期:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="buytime" size="40"/>&nbsp;*[格式:2008-08-08]</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">状态:&nbsp;</td>
                    						<td height="30" align="left">
                    							&nbsp;<html:select property="status">
                                                		<html:options collection="carstatustypelist" property="value" labelProperty="label"/>
                                                      </html:select>
                    						</td>
                  						</tr>
                  						<tr valign="middle">
                    						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">年检情况:&nbsp;</td>
                    						<td height="30" align="left">&nbsp;<html:text property="njinfo" size="50"/>&nbsp;*</td>
                  						</tr>
                  						<tr>
                    						<td align="right" valign="middle" bgcolor="#B9D9E9">车辆描述:&nbsp;</td>
                    						<td>&nbsp;<html:textarea property="info" rows="5" cols="50"/></td>
                  						</tr>
              						</table>
             					</td>
            				</tr>
            				<tr valign="middle">
              					<td height="23" align="center" valign="middle">
                  					<html:submit value="添加车辆"/>
                  					<html:reset value="重新填写"/>
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