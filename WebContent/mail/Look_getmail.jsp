<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>查看收邮件箱</title>
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
      					<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#E8F1F6" bordercolordark="#1687AF" rules="none" bgcolor="white">
        					<tr>
          						<td width="31%" height="25" align="left">&nbsp;</td>
          						<td width="47%" align="right">
                 					<logic:greaterEqual value="1" name="logonuser" property="userable">
                    				<a href="<%=request.getContextPath()%>/text/text_add.do?addtype=link">[发送新邮件]</a>
                 					</logic:greaterEqual>
          						</td>
          						<td width="21%" align="right"><b>邮件管理</b>&nbsp;</td>
        					</tr>
	      				</table>
    	  				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#F0F0F0" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
        					<tr><td height="30" colspan="5">收邮件箱</td></tr>
        					<tr align="center" bgcolor="#DFE5F5">
	          					<td width="31%" height="25">主题</td>
    	      					<td width="20%" height="25">发件人</td>
        	  					<td width="32%" height="25">发送时间</td>
          						<td width="9%" height="25">状态</td>
          						<td width="8%" height="25">操作</td>
        					</tr>
	        				<logic:present name="getmaillist">
    	    				<logic:iterate id="mailsigle" name="getmaillist">
        					<tr height="40" >
          						<td align="center"><logic:equal value="1" name="mailsigle" property="mark"><b></logic:equal><a href="<%=request.getContextPath()%>/mail/mail_look_sigle.do?id=<bean:write name='mailsigle' property='mailid'/>&from=get"><bean:write name="mailsigle" property="subject"/></a></td>
          						<td align="center"><logic:equal value="1" name="mailsigle" property="mark"><b></logic:equal><bean:write name="mailsigle" property="sender"/></td>
          						<td align="center"><logic:equal value="1" name="mailsigle" property="mark"><b></logic:equal><bean:write name="mailsigle" property="stime"/></td>
          						<td align="center"><logic:equal value="1" name="mailsigle" property="mark"><b></logic:equal>
	            	 				<logic:equal value="1" name="mailsigle" property="mark">未读</logic:equal>
    	         					<logic:equal value="0" name="mailsigle" property="mark">已读</logic:equal>
        	  					</td>
          						<td height="30" align="center">
	             					<logic:greaterEqual value="1" name="logonuser" property="userable">
    	           					<a href="<%=request.getContextPath()%>/mail/mail_del.do?id=<bean:write name='mailsigle' property='mailid'/>&from=get&page=<%=session.getAttribute("currentpage")%>" onclick="javascript:return(confirm('确定要删除该记录吗?'))">[删除]</a>
        	     					</logic:greaterEqual>
            	 					<logic:equal value="0" name="logonuser" property="userable">无权限</logic:equal>
          						</td>
	        				</tr>
			        		</logic:iterate>
        					</logic:present>
        					<logic:empty name="getmaillist">
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
