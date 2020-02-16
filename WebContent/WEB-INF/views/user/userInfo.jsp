<%@page import="java.util.List"%>
<%@page import="mypage.model.vo.Point"%>
<%@page import="board.model.vo.Board"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User user = (User)request.getAttribute("user");
	Point point = (Point)request.getAttribute("point");
	List<Board> boardList = (List)request.getAttribute("myCurrentBoard");
	String boardTotal = (String)request.getAttribute("myBoardTotal");		
	String cmtTotal = (String)request.getAttribute("myCmtTotal");		
	
	User userLoggedIn = (User) session.getAttribute("userLoggedIn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userInfo</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link href="assets/css/bootstrap.css" rel="stylesheet">
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
  color: white;
  }
  .table-user-information th{
  background-color: rgb(0, 108, 183);
  
  }
  .boardList th{
  background-color: rgba(0, 108, 183, 0.36);
  }
</style>
</head>
<body>
	<h2>testpage</h2>

	<div>
		<table class="table table-user-information">
			<tbody>
				<tr>
					<th colspan="4"> 조회된 회원 정보</th>
				</tr>
				<tr>
					<td>닉네임:</td>
					<td><%=user.getNickName() %></td>
					
					<td>아이디:</td>
					<td><%=user.getUserId() %></td>
				</tr>
				
				<tr>
					<td>보유 포인트:</td>
					<td><%=point.getPoint() %></td>
				
					<td>답글 채택된 횟수:</td>
					<td><%=point.getSelectCnt() %></td>
				</tr>
				<tr>
					<td>작성한 게시글 수:</td>
					<td><%=boardTotal%></td>
				
					<td>작성한 댓글 수:</td>
					<td><%=cmtTotal%></td>
				</tr>
				<!-- 버튼태그는 유저따라 보여주기.-->
				<tr>
					<td colspan='2'> </td>
					<%if("S".equals(userLoggedIn.getUserRole() ) ){ %>
					<td>권한변경</td>
						<td>
						<% if("A".equals(user.getUserRole() ) ){%>
						<button onclick='rolechange()' value='U'>일반유저로 변경</button>
						<%}else{ %>
						<button onclick='rolechange()' value='A'>관리자로 변경</button>
						</td>
					<%}}else{ %>
					<td>차단 유저 등록</td>
					<td><button onclick='addtoBlackList()'>추가하기</button></td>
					<%} %>
				</tr>
			</tbody>
		</table>
		
		<script>
		function addtoBlackList(){
			
			let usertoBlack = {
					blackId : '<%=user.getUserId()%>',
					userId:'<%=userLoggedIn.getUserId()%>',
					regDate:"date",
					memo:'회원정보 조회에서차단'
				};
				console.log(usertoBlack);
				$.ajax({
					url:"<%=request.getContextPath()%>/mypage/userBlackListAdd.do",
					data: usertoBlack,
					dataType: "json",
					success : function(data) {
						opener.location.reload();
					},
					error: function(x,s,e){
						console.log(x,s,e);
					}
				});
		}		
		function rolechage(){
			let rolech = {
					userId : '<%=user.getUserId()%>',
					userRole : '<%=user.getUserRole()%>'
			}
			$.ajax({
				url:"<%=request.getContextPath()%>/user/userUpgrade",
				data: rolech,
				dataType: "json",
				success : function(data) {
					
					alert('업데이트 완료, 자세한 사항은 차단회원에서 조회해주세요');
					
				},
				error: function(x,s,e){
					console.log(x,s,e);
		
			});
			
			
			
		}
		
		</script>
		
		<br />
		<table class="table table-striped boardList">
			<thead>
			<tr>
				<th colspan='5'>최근 작성된 게시글 목록</th>
			</tr>
			<tr>
				<th>게시판 코드</th>
				<th>말머리</th>
				<th>제목</th>
				<th>등록일</th>
				<th>조회하기</th>
			</tr>
			</thead>
			<% if(boardList==null || boardList.isEmpty()){ %>
            <tr>
                <td colspan="10" align="center"> 아직 작성된 글이 없습니다.. </td>
            </tr>
     	   	<% 
            } 
            else {
                for(Board b : boardList){ 
      		%>
            <tr>
                <td><%=b.getBoardCode()%></td>
				<td><%=b.getBoardOption()%></td>
                <td> <a href="<%=request.getContextPath() %>/board/boardView?boardNO=<%=b.getBoardNo()%>" target="_PARENT"><%=b.getBoardTitle()%> </a></td>
				<td><%=b.getBoardRegDate()%></td>
                <td>
				    <a href="<%=request.getContextPath() %>/board/boardView?boardNO=<%=b.getBoardNo()%>" target="_PARENT">
						게시글 보기   </a>
                </td>		
            </tr>		
       		 <%		} 
            }
       		 %>			
		</table>
	</div>

</body>
</html>