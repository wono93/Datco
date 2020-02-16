<%@page import="java.util.List"%>
<%@page import="mypage.model.vo.Scrap"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User userLoggedIn = (User)session.getAttribute("userLoggedIn");
	List<Scrap> scrapList = (List<Scrap>)request.getAttribute("scrapList");
		
		%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page for ScrapList</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<style>
h2 {
	text-align: center;
}

#content {
	text-align: center;
	align-items: center;
}
form {
	display: inline-block;
}
form#eachuser {
	display: inline;
}

 table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
    width: 800px;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }
  th{
  text-align: center;
  background-color: rgb(0, 108, 183);
  color: white;
  }
  
  .memo-col{
  	width: 100px;
  }
</style>
</head>
<body>

<h2><%=userLoggedIn.getNickName()%>님의 게시글 스크랩 목록</h2>

<div id="container">
	<table class="table table-striped scrapList">
			<thead>
			<tr>
				<th>스크랩 등록일</th>
				<th class='memo'>메모</th>
				<th>게시글 번호</th>
				<th>게시글 제목</th>
				<th>게시글 작성자</th>
				<th>목록에서 제거</th>
			</tr>
			</thead>
			<% if(scrapList==null || scrapList.isEmpty()){ %>
            <tr>
                <td colspan="10" align="center"> 스크랩 된 게시글이 없습니다. </td>
            </tr>
     	   	<% 
            } 
            else {
                for(Scrap s : scrapList){ 
      		%>
            <tr>
                <td><%=s.getScrapRegDate()%></td>
				<td class="memo-col"><%=s.getMemo()%></td>
				<td><%=s.getBoardNo()%></td>
				<td><span class="boardInquery"><input type="hidden" name="delNo" value="<%=s.getBoardNo()%>"><%=s.getBoardTitle()%></span></td>
                <td><%=s.getBoardWriter() %></td>		
                <td><span class="delScrap">삭제하기<input type="hidden" name="delNo" value="<%=s.getBoardNo()%>"></span></td>		
            </tr>		
       		 <%		} 
            }
       		 %>			
		</table>
</div>
<script>

$(function(){
	delcursor();
	boardInquey();
})


function boardInquey(){
	$(".boardInquery").css("cursor","pointer").css("background-color", "rgba(0, 108, 183, 0.1)")
	  .on("click",function(e){
		  
			let boardNo = $(this).children(['[name=openNo]']).val();
		  let url = '<%=request.getContextPath() %>/board/boardView?boardNo='+boardNo;
		  if(opener.closed) {   //부모창이 닫혔는지 여부 확인
		      window.open(url, "openWin");
		   } else {
		      opener.location.href = url;
		      opener.focus();
		   }
		  
	  });
		
}
function delcursor(){
	$(".delScrap").css("cursor","pointer").css("background-color", "rgba(0, 108, 183, 0.36)")
					  .on("click",function(e){
			let delScrap = {
				userId:'<%=userLoggedIn.getUserId()%>',
				boardNo:$(this).children(['[name=delNo]']).val()
			};
			console.log(delScrap);
			$.ajax({
				url:"<%=request.getContextPath()%>/mypage/scrapDel",
				data: delScrap,
				dataType: "json",
				success : function(data) {
					alert('스크랩이 해제되었습니다.')
					location.reload();
				},
				error: function(x,s,e){
					console.log(x,s,e);
				}
			});
 	 });
}


</script>
<input type="button" onclick="self.close();" value="닫기" />
</body>
</html>