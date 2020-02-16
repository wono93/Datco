<%@page import="java.util.List"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Board> list = (List<Board>) request.getAttribute("list");
	String pageBar = (String) request.getAttribute("pageBar");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/boardList.css" />
<section id="container">
	<div id="list" class="content" role="main">
		<div class="squareNav" role="navigation">
			<h4>자유게시판</h4>
			
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
						<th>말머리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Board b : list) {
					%>
					<tr>
						<td><%=b.getBoardOption()%></td>
						<td><a
							href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
								<%=b.getBoardTitle()%>
						</a></td>
						<td><%=b.getBoardWriter()%></td>
						<td><%=b.getBoardRegDate()%></td>
					<%
						}
					%>
				</tbody>
			</table>
			<hr />

		</div>

		<div id='pageBar'><%=pageBar%></div>
</section>
<script>
$("#btn-add").click(function(){
	location.href = "<%=request.getContextPath()%>/board/boardRegist";
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
