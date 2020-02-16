<%@page import="user.model.vo.User"%>
<%@page import="message.model.vo.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	
	List<Message> msgList = (List<Message>)request.getAttribute("msgList");
	User userLoggedIn = (User)session.getAttribute("userLoggedIn");
	int cPage = 1;
	boolean getMsgBool = false;
	if(request.getAttribute("cPage")!=null)
		cPage = (int)request.getAttribute("cPage");
	if(msgList !=null && msgList.size()>0){
		//로그인한 유저가 받은 메세지일 경우 
		if(userLoggedIn.getUserId().equals(msgList.get(0).getGetUser()))
			getMsgBool = true;
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>쪽지함</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/messageList.css">

<style>
td{padding: 6px;}
</style>
</head>
<body>
	<div class="btn-group btn-group-toggle" data-toggle="buttons">
	  <label class="btn btn-secondary active">
	    <input type="radio" name="options" id="getMessage" autocomplete="off" style="background-color:blue;" <%=getMsgBool?"":"" %>> 받은편지함
	  </label>
	  <label class="btn btn-secondary">
	    <input type="radio" name="options" id="sentMessage" autocomplete="off" <%=getMsgBool?"":"checked" %>> 보낸편지함
	  </label>
	</div>	
    <div id="head_button">
        <button id="sendMsg" type="button" class="btn btn-outline-primary btn-sm">쪽지 보내기</button>
        <button id="close" type="button" class="btn btn-outline-danger btn-sm">&times;</button>
    </div>
    <table id="table_header" class="table">
        <thead>
        <tr>
        
            <th>
       		         내용
            </th>
            <th style="width: 120px;">
				상대
            </th>
            <th style="width: 120px;">
				전송 날짜
            </th>
            <th style="width: 120px;">
				읽은 날짜
            </th>
            <th style="width: 20px;">
                
            </th>
        </tr>
        </thead>
        
        <tbody>
<%

        if(msgList != null && msgList.size() > 0) {
        	for(Message m : msgList){ 
%>
            <tr>
                <td class="list_content" style="padding:6px; height: 20px">
           		   <a href="<%=request.getContextPath()%>/message/view?messageNo=<%=m.getMessageNo()%>"><%=m.getMsgTitle()%></a>
                </td>
                <td class="list_center list_userid" style="padding:6px; height: 20px">
          		   <%=m.getGetUser()%>
                </td>
                <td class="list_center list_date" style="padding:6px; height: 20px">
                   <%=m.getSendDate()%>
                </td>
                <td class="list_center list_date" style="padding:6px; height: 20px">
                   <%=m.getReadDate()!=null?m.getReadDate():""%>
                </td>
                <td style="padding:6px;">
                    <a href="<%=request.getContextPath()%>/message/delete?messageNo=<%=m.getMessageNo()%>">
                    <img src="<%=request.getContextPath()%>/images/crossred.png" alt="삭제" id="delete_icon">
                    </a>
                </td>
            </tr>
<%
         }
        }else{
%>
			<tr>
				<td>
				<p id="empty">쪽지가 없습니다.</p>
				</td>
			</tr>
<%
        }
%>
        </tbody>
        </table>
        <script>
        $("#close").click(function(){
        	window.close();
        });
        $("#sentMessage").on("click",function(){
        	location.href = "<%=request.getContextPath()%>/message/list?sendUser=<%=userLoggedIn.getUserId()%>";
        });
        $("#getMessage").on("click",function(){
        	location.href = "<%=request.getContextPath()%>/message/list?getUser=<%=userLoggedIn.getUserId()%>";
        });
        $("#sendMsg").click(function(){
        });
        </script>
</body>
</html>