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
			
			<div id="searchArea">
				<form action="<%=request.getContextPath()%>/board/boardSearch" method="POST" >
				<!-- onsubmit="return searchOnsubmit();" --> 
					<div class="form-row align-items-center">
					  <div class="col-auto">
					    <div class="input-group mb-2">
					      <div class="input-group-prepend">
					        <div class="input-group-text">
						      <select name="searchType" class="mr-sm-2" id="inlineFormCustomSelect">
						        <option value="boardTitle" selected>제목</option>
						        <option value="boardContent">내용</option>
						        <option value="boardWriter">작성자</option>
						      </select>
					        </div>
					      </div>
					      <input name="searchText" type="text" class="form-control" id="searchInputValue" placeholder="검색어를 입력하세요">
					    </div>
					  </div>
					  <div class="col-auto">
						  <input type="hidden" name="boardCode" value="<%=boardCode%>">
					      <button type="submit" class="btn btn-primary mb-2">검색</button>
					  </div>
					</div>
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
						<th>조회수</th>
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
						<td><%=b.getReadCnt()%></td>
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
/* function searchOnsubmit(){
	let $inputText = $("#searchInputValue");
	alert($inputText.val().trim().length());
	if($inputText.val().trim().length() >= 2)
		return true;
	$inputText.attr("placeholder","검색어는 2자리 이상입력해주세요!");
	return false;
} */
$("#btn-add").click(function(){
	location.href='<%=request.getContextPath()%>/board/boardRegist';
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
