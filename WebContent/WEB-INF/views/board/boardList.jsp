﻿<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Board> list = (List<Board>) request.getAttribute("list");
	String pageBar = (String) request.getAttribute("pageBar");
	Map<String, String> title = new HashMap<String, String>();
	title.put("CDR", "코드리플");
	title.put("FRE", "자유 게시판");
	title.put("JOB", "구인공고");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/boardList.css" />
<script>
<%
	String boardCode =(String) request.getParameter("boardCode");
%>
</script>
<section id="container">
	<div id="list" class="content" role="main">
		<div class="squareNav" role="navigation">
			<h4><%=title.get(boardCode)%></h4>
			
			<div class="div-search">
				<input type="search" placeholder="검색어  입력" class="input-search" />
				<button id="search-btn">검색</button>
			</div>
			<br />
			<div class="div-List">
					<%
				if (userLoggedIn != null) {
			%>
			<input type="button" value="글쓰기" id="btn-add"
				onclick="location.href='<%=request.getContextPath()%>/board/boardRegist" />
			<%
				}
			%>
			</div>
			<br />
			<br />
			<table class="table table-striped">
				<thead>
					<tr>
						<th style="width:70">말머리</th>
						<th></th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<%
					if(list!=null){
						for (Board b : list) {
							String boardType = b.getBoardCode();
					%>
					<tr>
						<td><span class="badge badge-secondary"><%=b.getBoardOption()%></span></td>
						<td><span class="badge badge-secondary"><%=b.getPoint()!=0?b.getPoint():""%></span></td>
						<td><a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
								<%=b.getBoardTitle()%>
						</a></td>
						<td><%=b.getBoardWriter()%></td>
						<td><%=b.getBoardRegDate()%></td>
<%
						}
					}else{
%>
						<td>게시글이 존재하지 않습니다.</td>
<%
					};
					
%>
				</tbody>
			</table>
			<hr />

		</div>

		<div id='pageBar'><%=pageBar%></div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
