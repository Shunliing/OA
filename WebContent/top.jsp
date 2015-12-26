<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<% String contentPath=request.getContextPath(); %>
<html>
	<head>
		<title>页头</title>
		<link href="<%=contentPath%>/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table height="119" width="100%" cellpadding="0" cellspacing="0">
			<tr><td><img height="119" src="<%=contentPath%>/image/top.jpg" usemap="#Map"></td></tr>
		</table>
		<map name="Map">
			<area shape="circle" coords="925,19,14" onMouseOver="javascript:exitIco.style.visibility='visible'" title="退出系统"/>
		</map>
		<div id="exitIco" style="margin-top:-115;margin-left:910;visibility:hidden"  onMouseOut="javascript:this.style.visibility='hidden'"><a href="<%=contentPath%>/logoff.do?type=exit"><img src="<%=contentPath%>/image/exit.jpg" title="退出系统"></a></div>
	</body>
</html>