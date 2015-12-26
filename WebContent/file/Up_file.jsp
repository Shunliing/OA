<%@ page contentType="text/html; charset=gb2312" language="java"%>
<% String path=request.getContextPath(); %>
<html>
	<head>
		<html:base/>
		<title>上传文件</title>
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
        				<table width="85%" border="5" cellspacing="0" cellpadding="0" bordercolor="#1687AF" bordercolorlight="#1687AF" bordercolordark="#E8F1F6" rules="none" bgcolor="white">
         			 		<tr><td height="25" align="center" bgcolor="#B9D9E9"><strong>上传文件</strong></td></tr>
          					<tr><td height="30" align="center" valign="middle">上传的文件长度不得超过2MB！</td></tr>
          					<html:form action="/file/file_up.do?uptype=up" enctype="multipart/form-data" method="post" focus="upfile">
          					<tr>
            					<td height="64" align="center" valign="middle">
            						<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
                						<tr>
                  							<td width="12%" height="30" align="right" bgcolor="#B9D9E9">文件:</td>
                  							<td width="88%" height="30" align="left">&nbsp;<html:file property="upfile" size="66"/></td>
                						</tr>
                						<tr valign="middle" bgcolor="#B9D9E9">
                  							<td height="30" align="right" valign="middle" bgcolor="#B9D9E9">描述:</td>
                  							<td height="30" align="left" bgcolor="#FFFFFF">&nbsp;<html:text property="fileinfo" size="77"/></td>
                						</tr>
            						</table>            
            					</td>
          					</tr>
          					<tr valign="middle" height="30">
            					<td align="center" valign="middle">
              						<html:submit value="上传文件"/>
              						<html:reset value="重置选择"/>
            					</td>
          					</tr>
          					</html:form>
        				</table>
					</td>
					<td width="13" background="<%=path%>/image/right.jpg"></td>
				</tr>
				<tr><td colspan="4"><jsp:include page="/end.jsp"/></td></tr>
			</table>
		</center>
	</body>
</html>