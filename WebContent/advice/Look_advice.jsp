<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>查看建议</title>
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
        				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#1687AF" bordercolorlight="#E8F1F6" bordercolordark="#1687AF" rules="none" bgcolor="white">
          					<tr bgcolor="white">
            					<td width="65%" height="25" align="right">&nbsp;
                   					<logic:greaterEqual value="1" name="logonuser" property="userable">
                    				<a href="<%=request.getContextPath()%>/advice/advice_add.do?addtype=link">[发送建议]&nbsp;</a>
                   					</logic:greaterEqual>
            					</td>
            					<td width="15%" align="right"><strong>意见箱管理&nbsp;&nbsp;</strong></td>
          					</tr>
        				</table>
        				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#1687AF" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
          					<tr align="left">
            					<td>浏览意见箱</td>
           						<html:form action="/advice/advice_look" focus="subsqlvalue">
            					<td colspan="3" align="center">
            						快速搜索：<html:text property="subsqlvalue" size="35"/>
                					类型：
                					<html:select property="subsql">
                  						<html:options collection="advicetypelist" property="value" labelProperty="label"/>
                					</html:select>
                					<html:submit value="搜索"/>
            					</td>
            					</html:form>
          					</tr>
          					<tr align="center" bgcolor="#B9D9E9" height="25">
              					<td width="18%">建议主题</td>
              					<td width="22%">建议人</td>
              					<td width="24%">发送时间</td>
              					<td width="15%">操作</td>
          					</tr>
          					<logic:present name="advicelist">
           					<logic:iterate id="advicesigle" name="advicelist"> 
             				<tr height="40">
              					<td align="center"><html:link page="/advice/advice_look_sigle.do" paramId="id" paramName="advicesigle" paramProperty="adviceid"><bean:write name="advicesigle" property="subject"/></html:link></td>
              					<td align="center"><bean:write name="advicesigle" property="sender"/></td>
              					<td align="center"><bean:write name="advicesigle" property="stime"/></td>
              					<td align="center">
               						<logic:greaterEqual value="1" name="logonuser" property="userable">
                					<a href="<%=request.getContextPath()%>/advice/advice_del.do?id=<bean:write name='advicesigle' property='adviceid'/>&page=<%=session.getAttribute("currentpage")%>" onclick="return(confirm('确认要删除该记录吗?'))">[删除]</a>                
               						</logic:greaterEqual>
               						<logic:equal value="0" name="logonuser" property="userable">无权限</logic:equal>
              					</td>
             				</tr>
           					</logic:iterate>
          					</logic:present>
          					<logic:empty name="advicelist">
             				<tr><td align="center" colspan="4"><li><b>没有记录显示!</b></li></td></tr>          
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