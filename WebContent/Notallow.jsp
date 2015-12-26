<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>


<html>
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>系统提示</title>
</head>

<body>
<center>
<table width=967 border=1 cellpadding="0" cellspacing="0" bordercolor=gray bordercolorlight=gray bordercolordark=white>
<tr>
<td colspan=2 align=center height=80 vliagn=top>
  <table border="0" cellpadding="0" cellspacing="0">
    <tr>
     <td><jsp:include page="/top.jsp"/></td>
    </tr>
  </table>
</td>
</tr>
<tr>
<td width=100>
  <table border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td><jsp:include page="/side.jsp"/></td>
   </tr>
  </table>
</td>
<td align="center" valign="top" width=900 background="<%=request.getContextPath()%>/image/right_round.jpg">
  <br>
  <br>
  <table width=746 border=0 cellpadding="0" cellspacing="0">
   <tr>
    <td width=736 align="center" valign="top"><b><html:errors/></b></td>
   </tr>
  </table>
</td>
</tr>
<tr>
 <td colspan=2 align="center" valign="middle"><jsp:include page="end.jsp"/></td>
</tr>
</table>
</center>
</body>
</html>
