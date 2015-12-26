<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>浏览办公用品</title>
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
        				<table><tr><td align="center"><html:errors/></td></tr></table>
        				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#E8F1F6" bordercolordark="#1687AF" rules="none" bgcolor="white">
          					<tr bgcolor="white">
            					<td width="31%" height="25" align="left">&nbsp;</td>
            					<td width="24%" align="center"><a href="<%=request.getContextPath()%>/money/money_cars_look.do">[浏览车辆信息]</a></td>
            					<td width="24%" align="center"><html:link page="/money/money_things_add.do?addtype=link">[添加办公用品]</html:link></td>
            					<td width="21%" align="right"><strong>资产管理&nbsp;&nbsp;</strong></td>
          					</tr>
        				</table>
        				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
          					<html:form action="/money/money_things_look" focus="subsqlvalue">
          					<tr align="left">
            					<td height="20" colspan="1" class="style3">浏览办公用品信息</td>
            					<td height="20" colspan=7 align="center">
            						快速搜索：<html:text property="subsqlvalue" size="40"/>
                 					类型:
                 					<html:select property="subsql">
                 						<html:options  collection="thingtypelist" property="value" labelProperty="label"/>
                					</html:select>
                					<html:submit value="搜索"/>
                				</td>
          					</tr>
          					</html:form>
          					<tr align="center" bgcolor="#B9D9E9">
            					<td width="18%" height="25">类别</td>
            					<td width="18%">品牌规格</td>
            					<td width="9%">数量</td>
            					<td width="10%" height="25">金额(元)</td>
            					<td height="25">购买日期</td>
            					<td height="25">购买部门</td>
            					<td height="25" colspan="2">操作</td>
          					</tr>
           					<logic:notEmpty name="thinglist">
           					<logic:iterate id="thingsigle" name="thinglist">
           					<tr height="30">
            					<td align="center"><bean:write name="thingsigle" property="type"/></td>
            					<td align="center"><bean:write name="thingsigle" property="model"/></td>
            					<td align="center"><bean:write name="thingsigle" property="number"/></td>
            					<td align="center"><bean:write name="thingsigle" property="cost"/></td>
            					<td align="center"><bean:write name="thingsigle" property="buytime"/></td>
            					<td align="center"><bean:write name="thingsigle" property="buybranch"/></td>
            					<td align="center"><a href="<%=request.getContextPath()%>/money/money_things_update.do?id=<bean:write name='thingsigle' property='thingid'/>&updatetype=link">[修改]</a></td>
            					<td align="center"><a href="<%=request.getContextPath()%>/money/money_things_del.do?id=<bean:write name='thingsigle' property='thingid'/>&page=<%=session.getAttribute("currentpage")%>" onclick="return(confirm('确认要删除该记录吗?'))">[删除]</a></td>
           					</tr>
           					</logic:iterate>
           					</logic:notEmpty>
           					<logic:empty name="thinglist">
           					<tr><td colspan="8" align="center"><li><b>没有记录显示!</b></li></td></tr>
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