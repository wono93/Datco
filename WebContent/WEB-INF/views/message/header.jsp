<%@page import="user.model.vo.User"%>
<%@page import="message.model.vo.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	
	List<Message> msgList = (List<Message>)request.getAttribute("msgList");
	if((User)session.getAttribute("userLoggedIn")==null){
%>
	<script>
	let url = "<%=request.getContextPath()%>";
	location.href = url;
	</script>
<%  return;
	}
	User userLoggedIn = (User)session.getAttribute("userLoggedIn");
	String userId = userLoggedIn.getUserId();
	int cPage = 1;
	if(request.getAttribute("cPage")!=null)
		cPage = (int)request.getAttribute("cPage");
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageList.css">

<style>
td{padding: 6px;}
</style>
</head>
<body>
    <div id="head_button">
        <button id="sendMsg" type="button" class="btn btn-outline-primary btn-sm">쪽지 보내기</button>
        <button type="button" class="btn btn-outline-danger btn-sm">&times;</button>
    </div>