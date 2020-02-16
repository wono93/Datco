<%@page import="board.model.vo.DelBoard"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<DelBoard> dlist = (List<DelBoard>) request.getAttribute("dlist");
	String pageBar = (String) request.getAttribute("pageBar");
	Map<String, String> title = new HashMap<String, String>();
	title.put("CDR", "코드리플");
	title.put("FRE", "자유 게시판");
	title.put("JOB", "구인공고");
	
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%if(adminBool){%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boardList.css" />
<script>
</script>
<section id="container">
	<div id="list" class="content" role="main">
		<div class="squareNav" role="navigation">
			<div class="div-search">
				<input type="search" placeholder="검색어  입력" class="input-search" />
				<button id="search-btn">검색</button>
			</div>
			<br />
			<div class="div-List">
			<input type="button" value="글쓰기" id="btn-add"
				onclick="location.href='<%=request.getContextPath()%>/board/boardRegist" />
			
			</div>
			<br />
			<br />
			<table class="table table-striped">
				<thead>
					<tr>
						<th style="width:70">게시판</th>
						<th style="width:70">말머리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<%
					if(dlist!=null){
						for (DelBoard b : dlist) {
							String boardType = b.getBoardCode();
					%>
					<tr>
						<td><span class="badge badge-secondary"><%=b.getBoardOption()%></span></td>
						<td><span class="badge badge-secondary"><%=b.getPoint()!=0?b.getPoint():""%></span></td>
						<td><a href="<%=request.getContextPath()%>/board/delBoardView?boardNo=<%=b.getBoardNo()%>">
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
<%
	}
%>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
