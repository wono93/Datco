<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="content_main">
	<div class="contents_main">
		<div class="section_contents">
			<div class="section_list searchlist">
				<h2 class="section_title">
					<a href="" class="square">자유게시판</a>
				</h2>
					<table id="board_FRE">
					
					</table>
			</div>
			<div class="section_list squarelist ">
				<h2 class="section_title">
					<a href="" class="codereple">코드리플</a>
				</h2>
				<table id="board_CDR">
				</table>
			</div>
		</div>
		<div class="section_contents">
			<div class="section_list findjob">
				<h2 class="section_title">
					<a href="" class="findjob">구인구직</a>
				</h2>
				<table id="board_JOB">
				</table>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	$.ajax({
		url : "<%=request.getContextPath()%>/main",
		dataType:"json",
		success: function(data){
			console.log(data.FRE);
			$.each(data.FRE,function(idx, board){
				$("#board_FRE").append("<tr><td><a href='<%=request.getContextPath()%>/board/boardView?boardNo="+board.boardNo+"'>"+board.boardTitle+"</a></td></tr>");
			});
			$.each(data.CDR,function(idx, board){
				$("#board_CDR").append("<tr><td><a href='<%=request.getContextPath()%>/board/boardView?boardNo="+board.boardNo+"'>"+board.boardTitle+"</a></td></tr>");
			});
			$.each(data.JOB,function(idx, board){
				$("#board_JOB").append("<tr><td><a href='<%=request.getContextPath()%>/board/boardView?boardNo="+board.boardNo+"'>"+board.boardTitle+"</a></td></tr>");
			});
		},
		error: function(x,s,e){
			console.log("ee");
		}
	});
	
});
</script>	

<%@ include file="/WEB-INF/views/common/footer.jsp"%>


