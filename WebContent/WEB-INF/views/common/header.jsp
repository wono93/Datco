<%@page import="board.model.vo.Board"%>
<%@ page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	User userLoggedIn = (User)session.getAttribute("userLoggedIn");
		
	
	boolean squBool = false;
	boolean cdrBool = false;
	boolean jobBool = false;
	boolean userListBool = false;
	boolean repBool = false;
	boolean delBool = false;
	
	String requestUri = request.getRequestURI();
	
	if(requestUri.contains("/board/boardList") || requestUri.contains("/board/boardSearch")){
		String boardCode = (String)request.getParameter("boardCode");
		if(boardCode.equals("FRE")) squBool=true;
		else if(boardCode.equals("CDR")) cdrBool=true;
		else if(boardCode.equals("JOB")) jobBool=true;
		else if(boardCode.equals("REP")) repBool=true;
		else if(boardCode.equals("DEL")) delBool=true;
	}else if (requestUri.contains("/board/boardView")){
		Board board = (Board)request.getAttribute("board");
		
		if(repBool || delBool){
			if(board.getReportedCnt() >= 10) repBool = true;
			else delBool = true;
		}else{
			if(board.getBoardCode().equals("FRE")) squBool=true;
			else if(board.getBoardCode().equals("CDR")) cdrBool=true;
			else if(board.getBoardCode().equals("JOB")) jobBool=true;
		}
		
	}else if(requestUri.contains("/board/userList")){
		userListBool = true;
	}
	
	boolean adminBool = false;
	
	if(userLoggedIn != null)
		if(userLoggedIn.getUserRole().equals("A") || userLoggedIn.getUserRole().equals("S"))
			adminBool = true;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Datco</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/index.css" />


</head>
<!--메인메뉴 시작  -->
</head>
<body>
	<nav class="header_wrap">
		<div class="nav_header">
			<h1 class="header_logo">
				<a href="<%=request.getContextPath()%>"><img
					src="<%=request.getContextPath() %>/images/datcologo.PNG" alt="" id="DatcoLogo"></a>
			</h1>
		</div>
	</nav>
	<div class="nav_container main">
        <div class="snb" id="menuTop">
            <div class="side_nav" id="menu-scroll">
                <div class="navmenu">
                <ul class="list-group">
					<li class="list-group-item <%=squBool?"active":""%>">
						<a href="<%=request.getContextPath()%>/board/boardList?boardCode=FRE">자유게시판</a>
					</li>
					<li class="list-group-item <%=cdrBool?"active":""%>">
					    <a href="<%=request.getContextPath()%>/board/boardList?boardCode=CDR">코드리플</a>
					</li>
					<li class="list-group-item <%=jobBool?"active":""%>">
					    <a href="<%=request.getContextPath()%>/board/boardList?boardCode=JOB">구인공고</a>
					</li>
					<li class="list-group-item <%=userListBool?"active":""%>">
					    <a href="<%=request.getContextPath()%>/board/userList">회원목록</a>
					</li>
					<%if(adminBool){ %>
					<% %>
					<li class="list-group-item <%=repBool?"active":""%>">
					    <a href="<%=request.getContextPath()%>/board/boardList?boardCode=REP">신고게시판</a>
					</li>
					<li class="list-group-item <%=delBool?"active":""%>">
					    <a href="<%=request.getContextPath()%>/board/boardList?boardCode=DEL">삭제게시판</a>
					</li>
					<%} %>
				</ul>
                </div>
            </div>
        </div>
		
	
	<!--메인메뉴끝  -->