<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>查看车辆信息</title>
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
      					 <table><tr><td align=center><html:errors/></td></tr></table>
       					<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#E8F1F6" bordercolordark="#1687AF" rules="none" bgcolor="white">
          					<tr bgcolor="white">
            					<td width="31%" height="25" align="left">&nbsp;</td>
            					<td width="24%" align="center"><a href="<%=request.getContextPath()%>/money/money_things_look.do">[浏览办公用品信息]</a></td>
            					<td width="24%" align="center"><a href="<%=request.getContextPath()%>/money/money_cars_add.do?addtype=link">[添加新的车辆]</a></td>
            					<td width="21%" align="right"><strong>资产管理&nbsp;&nbsp;</strong></td>
          					</tr>
        				</table>
        				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
          					<tr align="left">
            					<td height="20" colspan="1" align="center" class="style3">车辆信息</td>
            					<html:form action="/money/money_cars_look.do" focus="subsqlvalue">
            					<td height="20" colspan=7 align="center">
            						快速搜索:<html:text property="subsqlvalue" size="40"/>
                 					类型: 
                 					<html:select property="subsql">
                  						<html:options collection="cartypelist" property="value" labelProperty="label"/>
                					</html:select>
                					<html:submit value="搜索"/>
            					</td>
            					</html:form>
          					</tr>
          					<tr align="center" bgcolor="#DFE5F5">
            					<td width="14%" height="25">牌号</td>
            					<td width="16%">型号</td>
            					<td width="14%">司机姓名</td>
            					<td width="16%" height="25">生产厂家</td>
            					<td height="25">购买日期</td>
            					<td height="25">状态</td>
            					<td height="25" colspan="2">操作</td>
          					</tr>
          					<logic:present name="carlist">
           					<logic:iterate id="carsigle" name="carlist">
            				<tr>
             					<td height="30" align="center"><html:link page="/money/money_cars_sigle_look.do" paramId="id" paramName="carsigle" paramProperty="carid"><bean:write name="carsigle" property="number"/></html:link></td>
	             				<td height="30" align="center"><bean:write name="carsigle" property="model"/></td>
    	         				<td height="30" align="center"><bean:write name="carsigle" property="driver"/></td>
        	     				<td height="30" align="center"><bean:write name="carsigle" property="maker"/></td>
            	 				<td width="17%" align="center"><bean:write name="carsigle" property="buytime"/></td>
             					<td width="8%" align="center">
              						<logic:equal value="0" name="carsigle" property="status">异常</logic:equal>
              						<logic:equal value="1" name="carsigle" property="status">正常</logic:equal>
	             				</td>
    	         				<td height="30" align="center">
        	      					<logic:equal value="1" name="carsigle" property="status">
            	   					<a href="<%=request.getContextPath()%>/money/money_cars_update.do?id=<bean:write name='carsigle' property='carid'/>&updattype=link">[修改]</a>
              						</logic:equal>  
              						<logic:equal value="0" name="carsigle" property="status">无</logic:equal>  
             					</td>
             					<td height="30" align="center"><a href="<%=request.getContextPath()%>/money/money_cars_del.do?id=<bean:write name='carsigle' property='carid'/>&page=<%=session.getAttribute("currentpage")%>" onclick="return(confirm('确认要删除该记录吗?'))">[删除]</a></td>
	           				</tr>
    	      				</logic:iterate>
        	 				</logic:present>
         					<logic:empty name="carlist">
           					<tr><td align="center" colspan=8><li><b>没有记录显示!</b></li></td></tr>
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