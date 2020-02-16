<%@page import="user.model.vo.User"%>
<%@page import="message.model.vo.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	
	Message message = (Message)request.getAttribute("message");
	User userLoggedIn = (User)session.getAttribute("userLoggedIn");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>

</head>
<body>
	<p>
	<%=message.getMsgTitle()%>
	</p>
    <p>
    <%=message.getMsgContent()%>
    </p>
</body>
</html>