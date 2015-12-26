<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<%
  	session.setAttribute("username","yxq");
	String path=request.getContextPath();
%>
<html>
	<head>
		<html:base/>
		<title>查看所有文件</title>
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
        				<table width="90%" border="5" cellspacing="0" cellpadding="5" bordercolor="#E8F1F6" bordercolorlight="#E8F1F6" bordercolordark="#1687AF" rules="none" bgcolor="white">
          					<tr>
          						<td height="25" width="70%"><html:errors/></td>
					        	<logic:greaterEqual value="1" name="logonuser" property="userable">
            					<td align="right"><logic:greaterEqual value="1" name="logonuser" property="userable"><html:link page="/file/file_up.do?uptype=link">[上传文件]</html:link></logic:greaterEqual></td>
          						</logic:greaterEqual>
            					<td align="right"><strong>文件管理&nbsp;&nbsp;</strong></td>
          					</tr>
        				</table>
        				<table width="90%" border="5" cellspacing="0" cellpadding="0" bordercolor="#1687AF" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
          					<tr height="30"><td colspan="6">浏览文件</td></tr>
          					<tr align="center" bgcolor="#B9D9E9" height="40">
            					<td width="20%">文件名</td>
            					<td width="10%">上传人</td>
            					<td width="15%">文件大小</td>
					            <td width="13%">上传时间</td>
            					<td width="7%">下载</td>
            					<td width="23%">操作</td>
          					</tr>
          					<logic:present name="filelist">
           					<logic:iterate id="filesigle" name="filelist">
           					<html:form action="/file/file_del.do">
            				<html:hidden name="filesigle" property="fileid"/>
            				<input type="hidden" name="page" value="<%=session.getAttribute("currentpage")%>">
           					<tr height="40">
            					<td height="30" align="center" title="查看详细信息"><a href="<%=request.getContextPath()%>/file/Look_siglefile.jsp?id=<bean:write name='filesigle' property='fileid'/>"><html:text name="filesigle" property="filename" size="25" style="border:0;cursor:hand" readonly="true" /></a></td>
            					<td height="30" align="center"><bean:write name="filesigle" property="fileuper"/></td>
            					<td align="center"><bean:write name="filesigle" property="filesize"/></td>
            					<td height="30" align="center"><bean:write name="filesigle" property="fileuptime"/></td>
            					<td align="center"><a href="<%=request.getContextPath()%>/file/file_download.do?id=<bean:write name='filesigle' property='fileid'/>&filename=<bean:write name='filesigle' property='filename'/>">下载</a></td>
            					<td height="30" align="center" width="8%">
             						<logic:equal value="0" name="logonuser" property="userable">无权限</logic:equal>
             						<logic:greaterEqual value="1" name="logonuser" property="userable">
             						<html:submit value="删除" onclick="return(confirm('确认要删除该记录吗?'))"/>&nbsp;<html:checkbox name="filesigle" property="delete" value="true">&nbsp;删除磁盘文件!</html:checkbox>
              						</logic:greaterEqual>
            					</td>
	          				</tr>
    	      				</html:form>
        	  				</logic:iterate>
         					</logic:present>
         					<logic:empty name="filelist">
          					<tr><td colspan="6" align="center"><li><b>没有记录显示!</b></li></td></tr>
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