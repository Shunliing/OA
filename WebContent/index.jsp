<%@ page contentType="text/html; charset=gb2312"%>

<%
	String contentPath = request.getContextPath();
%>
<html>
<head>
<link href="<%=contentPath%>/css/style.css" type="text/css"
	rel="stylesheet">
<title>ÓÃ»§µÇÂ¼</title>
</head>
<body style="margin-top: 100" background="image/bbg.jpg">
	<center>
		<table border="0" cellpadding="0" cellspacing="0" width="680"
			style="margin-top: 169">
			<tr height="107" align="right">
				<td colspan="3" background="image/logonT.jpg"
					style="padding-right: 40"><html:errors /></td>
			</tr>
			<tr>
				<td width="371" height="123"><img src="image/logonL.jpg"></td>
				<td>
					<table border="0" width="165" cellpadding="0" cellspacing="0">
						<form action="LoginServlet" focus="username" method="post">
						<tr height="42" align="right" valign="bottom">
							<td colspan="3" background="image/user.jpg"
								style="padding-bottom: 3"><input type="text"
								name="username" size="15"
								style="border: 0; background: #FAFAFA" /></td>
						</tr>
						<tr height="42" align="right" valign="bottom">
							<td colspan="3" background="image/pswd.jpg"
								style="padding-bottom: 2"><input type="password"
									name="password" size="15"
									style="border:0;background:#FAFAFA" redisplay="false" /></td>
						</tr>
						<tr>
							<td width="73" height="39"><img src="image/null.jpg"></td>
							<td><input type="submit"
									style="background:url(image/submit1.jpg);width:46;height:39;border:0;cursor:hand"
									onmouseover="this.style.background='url(image/submit2.jpg)'"
									onmouseout="this.style.background='url(image/submit1.jpg)'"
									value=" " /></td>
							<td><input type="reset"
									style="background:url('image/reset.jpg');width:46;height:39;border:0;cursor:hand"
									value=" " /></td>
						</tr>
						</form>
					</table>
				</td>
				<td width="144" height="123"><img src="image/logonR.jpg"></td>
			</tr>
			<tr height="81">
				<td colspan="3"><img src="image/logonE.jpg"></td>
			</tr>
		</table>
	</center>
</body>
</html>