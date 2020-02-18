<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Board> list = (List<Board>) request.getAttribute("list");
	String pageBar = (String) request.getAttribute("pageBar");
	Map<String, String> boardType = new HashMap<String, String>();
	boardType.put("CDR", "코드리플 게시판");
	boardType.put("FRE", "자유 게시판");
	boardType.put("JOB", "구인공고 게시판");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/boardList.css" />
<section id="container">
	<div id="list" class="content" role="main">
		<div class="squareNav" role="navigation">
			<h4>내글 보기</h4>
			
			<div class="div-search">
				<input type="search" placeholder="검색어  입력" class="input-search" />
				<button id="search-btn">검색</button>
			</div>
			<br />
		
			<br />
			<br />
			<table class="table table-striped">
				<thead>
					<tr>
						<th>게시판 종류</th>
						<th>말머리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<script>
						console.log("forEach Start");
					</script>
					<%
						for (Board b : list) {
							String boardCode = b.getBoardCode().toUpperCase();
					%>
						
					<script>
						console.log("<%=boardCode %>");
					</script>
						<tr>
							<td><%=boardType.get(boardCode) %></td>
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
					<script>
						console.log("forEach End");
					</script>
				</tbody>
			</table>
			<hr />

		</div>

		<div id='pageBar'><%=pageBar%></div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
