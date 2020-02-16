<%@page import="java.util.HashMap"%>
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
	href="<%=request.getContextPath()%>/css/bootstrap.css" />
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
			<h4><%=title.get(boardCode)!=null?title.get(boardCode):"" %></h4>
			
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
						<%if(boardCode.equals("CDR")){ %>
						<th>채택</th>
						<th>포인트</th>
						<%} %>
					</tr>
				</thead>
				<tbody>
					<script>
						console.log("forEach Start");
					</script>
					<%
						for (Board b : list) {
							String boardType = b.getBoardCode();
					%>
					<tr>
						<td><%=b.getBoardOption()%></td>
						<td><a
							href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
								<%=b.getBoardTitle()%>
						</a></td>
						<td><%=b.getBoardWriter()%></td>
						<td><%=b.getBoardRegDate()%></td>
						<%if(boardCode.equals("CDR")){
							if(b.getCmtSelect().equals('Y')){ %>                              
								<td>Y</td>
								<%}else{ %>
								<td></td>
								<% } %>
								<%if(b.getPoint() < 50){ %>
								<td><%=b.getPoint()%><img src="이미지1" alt="" /></td>
								<%} else if(b.getPoint()>=50 && b.getPoint()<100){%>
								<td><%=b.getPoint()%><img src="이미지2" alt="" /></td>
								<%} else if(b.getPoint()>=100){ %>
								<td><%=b.getPoint()%><img src="이미지3" alt="" /></td>
						<% 	} 
						}
						%>
						
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
