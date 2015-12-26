<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<%   
   String pagepath=(String)session.getAttribute("pagepath");
   if(pagepath==null)
    	 pagepath="";
   String strnumper=(String)session.getAttribute("numper");
   if(strnumper==null||strnumper.equals(""))
	   strnumper="0";
   String strnumrs=(String)session.getAttribute("numrs");
   if(strnumrs==null||strnumrs.equals(""))
    	 strnumrs="0";
   String strcurpage=(String)session.getAttribute("currentpage");
   if(strcurpage==null||strcurpage.equals(""))
	   strcurpage="0";
   String strnumpages=(String)session.getAttribute("numpages");
   if(strnumpages==null||strnumpages.equals(""))
	   strnumpages="0";
   int numpages=Integer.parseInt(strnumpages);
   int currentpage=Integer.parseInt(strcurpage);
%>
<html>
	<head><title>Page</title></head>
	<body>
		<center>
 		<table width="90%"  border="0" cellpadding="0" cellspacing="0">
        	<tr>
          		<td width="65%">
          			每页显示:<b><%=strnumper%></b> 条记录!
              		共:<b><%=strnumrs%></b> 条记录!
              		共:<b><%=strnumpages%></b> 页!
              		当前页:<b><%=strcurpage%></b> 页!
          		</td>
          		<td height="25" align="right" valign="middle">
            		<%if(currentpage>1){%>
                		<a href="<%=request.getContextPath()%><%=pagepath%>?page=1">[第一页]</a>
                		<a href="<%=request.getContextPath()%><%=pagepath%>?page=<%=currentpage-1%>">上一页</a>
		            <%}
		              if(currentpage<numpages){
            		%>
                		<a href="<%=request.getContextPath()%><%=pagepath%>?page=<%=currentpage+1%>">下一页</a>
                		<a href="<%=request.getContextPath()%><%=pagepath%>?page=<%=numpages%>">[最后一页]</a>
            		<%}%>
          		</td>
        	</tr>
      	</table>
	</center>
	</body>
</html>
