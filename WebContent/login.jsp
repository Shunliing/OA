<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<title>登录页面</title>
		<link href="css/login.css" rel="stylesheet" type="text/css"/>
		<link rel="Shortcut Icon" href="images/chinaz1.ico" type="image/x-icon" />
		<script  src="javascript/login.js"></script>
		<script  src="javascript/position.js"></script>
	</head>
	<body>
	<div id="container">
		<div id="topimg">
			<img src="images/login_main.jpg">
		</div>
		
		<div id="loginmain">
		<form action="LoginServlet"  method="post" onsubmit="return check();">
			<table align="center">

				<tr>
					<td>用户名:</td>
					<td><input type="text" id="uid" name="name"/></td>
					
				</tr>
				<tr>
					<td>密&nbsp&nbsp码:</td>
					<td><input type='password' id="pwd" name="password"/></td>
					
				</tr>
				
				<tr>
					<td>验证码:</td>
					<td valign="middle">
						<input type="text" id="yanzhengma" name="yanzhengma"/>
						<a href="#" onclick="change();"><img id="yanzhengmatu" src="yanzhengma.jsp" style="border:0px;" alt="点击换一张"></a>
						<a href="#" onclick="change();" style="position:absolute;margin-left:60px;margin-top:2px;">换一张</a>
					</td>
					
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="登 录" id="submit" cssClass="loginbutton"/>&nbsp&nbsp
						<input type="reset" value="重 置" cssClass="resetbutton"/>
					</td>
					
				</tr>
			</table>
			<input type="text" id="result" name="result" style="display:none"/>
		</form>
		</div>
	</div>
	<!-- 用来浮动显示提示信息的容器 -->
	  <table id="myerr" style="background-color:red;" width="120" border="0" class="jd" bgcolor="black" cellspacing="1" onclick="hideErr();">
		  <tr bgcolor="white" style="border-color:red;"  valign="bottom">
		    	<td id="myerrs" align="left"></td>
		  </tr>
	  </table>
	  <!-- 错误信息气球的尖角 -->
	  <img class="jd" src="images/up.gif" id="myup" onclick="hideErr();" /> 	  
	  <!-- 错误信息气球逐渐出现的遮挡物 -->
	  <span id="errzd" style="z-index:2;visibility:hidden;position:absolute;left:20;top:30;font-size:1px;background-color:white"/>	
		
		<script>
			errMsg="${error}";			
	        if(errMsg!="")
	        {//若有错误消息则调用错误信息气球显示
	           showErrMsg(errMsg,"submit");                   
	        }
			var result=document.getElementById("result").value;
			if(result!="")
			{	
				showErrMsg(result,"yanzhengma");
			}
		</script>	
	</body>
</html>