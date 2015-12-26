<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>查看车辆详细信息</title>
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
        					<tr><td height="25" align="center">查看车辆信息</td></tr>
	        				<tr valign="middle">
    	      					<td height="317" align="center" valign="middle">
        	    					<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
            	  						<html:form action="/money/money_cars_sigle_look.do">
              							<tr valign="middle">
                							<td width="23%" height="30" align="right" valign="middle" bgcolor="#B9D9E9">牌号:&nbsp;</td>
                							<td height="30">&nbsp;<html:text property="number" style="border:0" readonly="true"/></td>
              							</tr>
              							<tr valign="middle">
	                						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">型号:&nbsp;</td>
    	            						<td height="30" align="left">&nbsp;<html:text property="model" style="border:0" readonly="true"/></td>
        	      						</tr>
            	  						<tr valign="middle">
                							<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">发动机型号:&nbsp;</td>
                							<td height="30" align="left">&nbsp;<html:text property="fdj" style="border:0" readonly="true"/></td>
              							</tr>
              							<tr valign="middle">
                							<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">司机姓名:&nbsp;</td>
                							<td height="30" align="left">&nbsp;<html:text property="driver" style="border:0" readonly="true"/></td>
	              						</tr>
    	          						<tr valign="middle">
        	        						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">生产厂家:&nbsp;</td>
            	    						<td height="30" align="left">&nbsp;<html:text property="maker" style="border:0" readonly="true"/></td>
              							</tr>
              							<tr valign="middle">
                							<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">购买日期:&nbsp;</td>
                							<td height="30" align="left">&nbsp;<html:text property="buytime" style="border:0" readonly="true"/></td>
              							</tr>
              							<tr valign="middle">
	                						<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">状态:&nbsp;</td>
    	            						<td height="30" align="left">
        	         							<logic:equal value="0" name="moneycarform" property="status">&nbsp;异常</logic:equal>
            	     							<logic:equal value="1" name="moneycarform" property="status">&nbsp;正常</logic:equal>
                							</td> 
              							</tr>
              							<tr valign="middle">
                							<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">年检情况:&nbsp;</td>
                							<td height="30" align="left">&nbsp;<html:text property="njinfo" style="border:0" readonly="true" size="30"/></td>
              							</tr>
	              						<tr valign="middle">
    	            						<td height="73" align="right" valign="middle" bgcolor="#B9D9E9">车辆描述:&nbsp;</td>
        	        						<td height="73" align="left">&nbsp;<html:text property="info" style="border:0" readonly="true" size="50"/></td>
            	  						</tr>
              							</html:form>
          							</table>
         						</td>
	        				</tr>
    	  				</table>
						<a href="<%=request.getContextPath()%>/money/money_cars_look.do?page=<%=session.getAttribute("currentpage")%>">[返回]</a>      					
					</td>
					<td width="13" background="<%=path%>/image/right.jpg"></td>
				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>