<%@ page contentType="text/html; charset=gb2312" language="java"
	errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<html:base />
<title>浏览收文件箱</title>
</head>
<body bgcolor="#075B97">
	<center>
		<table width="965" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="4"><jsp:include page="/top.jsp" /></td>
			</tr>
			<tr bgcolor="#EDEEFF">
				<td width="13" background="<%=path%>/image/left.jpg"></td>
				<td align="center" valign="top" bgcolor="#EDEEFF"><jsp:include
						page="/side.jsp" /></td>
				<td width="720" align="center" valign="top" bgcolor="#E8F1F6">
					<br> <br>
					<table width="90%" border="0">
						<tr valign="bottom">
							<td width="30%" height="25" align="left" style="border: 0"><font
								color="red"><html:errors /></font></td>
							<td width="40%" align="right"><c:if
									test="${logonuser.userable==1 }">
									<a
										href="<%=request.getContextPath()%>/text/text_add.do?addtype=link">[新建发文]</a>
								</c:if></td>
							<td width="20%" align="center">收/发文管理&nbsp;</td>
						</tr>
					</table>
					<table width="90%" border="5" cellspacing="0" cellpadding="5"
						bordercolor="#F0F0F0" bordercolorlight="#1687AF"
						bordercolordark="#E8F1F6" rules="none" bgcolor="white">
						<tr>
							<td height="30" colspan="5">收文件箱</td>
						</tr>
						<tr align="center" bgcolor="#B9D9E9">
							<td width="8%">状态</td>
							<td width="24%">发文主题</td>
							<td width="25%">发送人</td>
							<td width="25%">接收时间</td>
							<td width="8%">操作</td>
						</tr>
						<c:forEach var="doc" items="${gettextlist}">


							<tr height="40">
								<td align="center"><c:if test="${doc.mark==1 }">

										<b>未读</b>
									</c:if> <c:if test="${doc.mark==0 }">已读</c:if></td>
								<td align="center"><c:if test="${doc.mark==1 }">
										<b>
									</c:if><a
									href="<%=request.getContextPath()%>/TextAddServlet?id=${doc.textid }&addtype=get">${doc.subject}
										</a></td>
								<td align="center"><c:if test="${doc.mark==1 }">
										<b>
									</c:if> ${doc.sender }</td>
								<td align="center"><c:if test="${doc.mark==1 }">
										<b>
									</c:if>${doc.sendtime }</td>
								<td align="center"><c:if test="${logonuser.userable==1 }">
										<a
											href="<%=request.getContextPath()%>/text/text_del.do?id=<bean:write name='textsigle' property='textid'/>&from=get&page=<%=session.getAttribute("currentpage")%>"
											onclick="javascript:return(confirm('确定要删除该记录吗?'))">[删除]</a>
									</c:if> <c:if test="${logonuser.userable==0 }">无权限</c:if></td>
							</tr>

						</c:forEach>
						<c:if test="${empty  gettextlist}">
						
							<tr>
								<td colspan="5" align="center"><li><b>没有记录显示！</b></li></td>
							</tr>
						</c:if>
					</table> <jsp:include page="/page.jsp" />
				</td>
				<td width="13" background="<%=path%>/image/right.jpg"></td>
			</tr>
			<tr>
				<td colspan="4"><jsp:include page="/end.jsp" /></td>
			</tr>
		</table>
	</center>
</body>
</html>
