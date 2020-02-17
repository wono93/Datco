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
			
			<form action="<%=request.getContextPath()%>/board/boardSearch">
				<input type="hidden" name="boardCode" value="<%=boardCode%>"/>
				<input type="hidden" name="searchType" value="boardTitle"/>
				<input type="search" name="searchText" placeholder="검색어  입력" class="input-search" id="input_Src_Val"/>
				<button id="search-btn" style="height: 38px;">검색</button>
			</form>
			</div>
			<br />
			<div class="div-List">
					<%
				if (userLoggedIn != null) {
			%>
			<input type="button" value="글쓰기" id="btn-add"/>
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
<script>
$("#btn-add").click(function(){
	location.href='<%=request.getContextPath()%>/board/boardRegist';
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
