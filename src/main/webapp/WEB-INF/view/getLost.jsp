<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.labassistant.constants.AppConfig" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ser = request.getParameter("ser");
String domain = AppConfig.DOMAIN_PAGE;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Find Password</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <link href="static/css/ui-dialog.css" rel="stylesheet" type="text/css" charset="UTF-8"/>
    <script type="text/javascript" src="static/js/jquery-2.1.1.js" charset="UTF-8"></script>
    <script type="text/javascript" src="static/js/jquery.form.js" charset="UTF-8"></script>
    <script type="text/javascript" src="static/js/dialog-plus.js" charset="UTF-8"></script>
    <script type="text/javascript" src="static/js/utils.js" charset="UTF-8"></script>
    <script type="text/javascript" src="static/js/findPassword.js" charset="UTF-8"></script>
	<style type="text/css">
		body {margin:0 auto}
        #findPassword {
            margin: 10px 0px;
            padding-left: 700px;
        }
        input {
            margin: 5px 0px;
        }
        #pwd {
            margin-left: 32px;
        }
	</style>
  </head>
  
  <body>
    <form method="post" id="findPassword">
    	<label>密码：</label><input type="password" name="pwd" id="pwd"/></br>
        <label>确认密码：</label><input type="password" name="confirm" id="confirm"/></br>
    	<input type="hidden" name="ser" value="<%= ser%>">
        <input type="hidden" name="domain" value="<%= domain%>" id="domain">
    	<input type="submit" value="提交" id="submit_button"/>
    </form>
  </body>
</html>
