<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>浏览员工</title>
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
      					<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#E8F1F6" bordercolordark="#1687AF" rules="none" bgcolor="white">
        					<tr bgcolor="white">
          						<td width="31%" height="25" align="left">&nbsp;</td>
          						<td width="24%" align="center"><a href="<%=request.getContextPath()%>/person/person_myself_update.do?updatetype=link">[修改个人信息]</a></td>
          						<td width="24%" align="center"><a href="<%=request.getContextPath()%>/person/person_add.do?addtype=link">[添加新员工]</a></td>
          						<td width="21%" align="right"><strong>人力资源管理&nbsp;&nbsp;</strong></td>
        					</tr>
      					</table>
			      		<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
        					<html:form action="person/person_look.do" focus="subsqlvalue">
          					<tr height="20">
            					<td>浏览员工信息</td>
            					<td colspan="5" align="center">快速搜索:
              						<html:text property="subsqlvalue" size="35"/>&nbsp;类型：
              						<html:select property="subsql">
                						<html:options collection="personsearchtypelist" property="value" labelProperty="label"/>
              						</html:select>
              						<html:submit value="搜索"/>
            					</td>
        					</tr>
        					</html:form>
           					<tr height="25" align="center" bgcolor="#B9D9E9">
            					<td width="23%" height="25">用户名</td>
            					<td width="15%">权限</td>
            					<td width="19%">部门</td>
            					<td width="19%" height="25">职务</td>
            					<td colspan="2">操作</td>
           					</tr>
         					<logic:present name="personlist">
          					<logic:iterate id="usersigle" name="personlist">
           					<tr align="center" height="30">
            					<td><html:link page="/person/person_sigle_look.do" paramId="id" paramName="usersigle" paramProperty="userid"><bean:write name="usersigle" property="username"/></html:link></td>
            					<td>
               						<logic:equal value="2" name="usersigle" property="userable">系统管理员</logic:equal>
               						<logic:equal value="1" name="usersigle" property="userable">普通管理员</logic:equal>
            	   					<logic:equal value="0" name="usersigle" property="userable">游客</logic:equal>
            					</td>
            					<td><bean:write name="usersigle" property="userbranch"/></td>
            					<td><bean:write name="usersigle" property="userjob"/></td>
            					<td><a href="<%=request.getContextPath()%>/person/person_update.do?id=<bean:write name="usersigle" property="userid"/>&updatetype=link">[修改]</a></td>
            					<td><a href="<%=request.getContextPath()%>/person/person_del.do?id=<bean:write name='usersigle' property='userid'/>&page=<%=session.getAttribute("currentpage")%>" onclick="return(confirm('确认要删除该记录吗?'))" >[删除]</a></td>               
           					</tr>
          					</logic:iterate>
         					</logic:present>
         					<logic:empty name="personlist">
          						<tr><td colspan=6 align=center><b><li>没有记录显示!</li></b></td></tr>
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