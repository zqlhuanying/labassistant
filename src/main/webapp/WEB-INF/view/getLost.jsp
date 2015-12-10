<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.labassistant.constants.AppConfig, com.labassistant.utils.EncryptUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ser = request.getParameter("ser");
String domain = AppConfig.DOMAIN_PAGE;
String nickName = EncryptUtil.decode(request.getParameter("un"));
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
        #all {
            margin: 0 auto;
            width: 100%;
            height: 100%;
        }
        #findPassword {
            position: relative;
            left: 37%;
            top: 40%;
        }
        #findPassword div {
            width: 300px;
            height: 32px;
            line-height: 32px;
        }
        input {
            margin-top: -10px;
            vertical-align: middle;
            background-color: transparent;
            border: 0;
            border-bottom: 1px solid #000000;
            outline: none;
        }
        #pwd {
            margin-left: 32px;
        }
        #nickName {
        	margin-left: 16px;
        }
        #submit_button {
            margin-top: 10px;
            border: none;
        }
	</style>
  </head>
  
  <body>
    <div id="all">
        <img src="static/img/bg.jpg" alt="" height="100%" width="100%" style="position: absolute; z-index: -1000">
        <form method="post" id="findPassword">
            <div><label>用户名：</label><label id="nickName"><%= nickName%></label></div>
            <div><label>密码：</label><input type="password" name="pwd" id="pwd"/></div>
            <div><label>确认密码：</label><input type="password" name="confirm" id="confirm"/></div>
            <input type="hidden" name="ser" value="<%= ser%>">
            <input type="hidden" name="domain" value="<%= domain%>" id="domain">
            <input type="image" src="static/img/submit.jpg" value="提交" id="submit_button"/>
        </form>
    </div>
  </body>
</html>
