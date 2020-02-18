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
	<h2> &gt&gt<%=user.getNickName() %>&lt&lt님의 회원정보</h2>

	<div>
		<table class="table table-user-information">
			<tbody>
				<tr>
					<th colspan="4"> 조회된 회원 정보</th>
				</tr>
				<tr>
					<td>닉네임:</td>
					<td><img src='<%=request.getContextPath()%>/images/<%=point.getUserGrade() %>' alt='' style='width:20px;' class='message_box'><%=user.getNickName() %></td>
					
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
					<%if("S".equals(userLoggedIn.getUserRole() ) ){ %>
					<td>유저권한 :</td>
					<td><%=user.getUserRole() %></td>
						<td colspan='2'>
						<% if("A".equals(user.getUserRole() ) ){%>
						<button onclick='rolechange()'> 일반유저로변경</button>
						<input type="hidden" value="U" />
						<%}else{ %>
						<button onclick='rolechange()'>관리자로변경</button>
						<input type="hidden" value="A" />
						</td>
					<%}}else{ %>
					<td colspan='2'> </td>
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
		function rolechange(){
			let rolech = {
					userId : '<%=user.getUserId()%>',
					userRole :'<%=user.getUserRole()%>'
			}
		
			console.log(rolech);
			$.ajax({
				url:"<%=request.getContextPath()%>/user/userUpgrade",
				data: rolech,
				dataType: "json",
				success : function(data) {
					
					alert('업데이트 완료');
					location.reload();

				},
				error: function(x,s,e){
					console.log(x,s,e);
				}
			});
		}
		$(function(){
			boardInquey();
		})


		function boardInquey(){
			$(".boardInquery").css("cursor","pointer").css("background-color", "rgba(0, 108, 183, 0.1)")
			  .on("click",function(e){
				  
					let boardNo = $(this).children(['[name=openNo]']).val();
					console.log(boardNo);
				  let url = '<%=request.getContextPath() %>/board/boardView?boardNo='+boardNo;
				  if(opener.closed) {   //부모창이 닫혔는지 여부 확인
				      window.open(url, "openWin");
				   } else {
				      opener.location.href = url;
				      opener.focus();
				   }
				  
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
				<td><span class="boardInquery"><input type="hidden" name="openNo" value="<%=b.getBoardNo()%>"><%=b.getBoardTitle()%></span></td>
				<td><%=b.getBoardRegDate()%></td>
                <td>
				   <span class="boardInquery"><input type="hidden" name="openNo" value="<%=b.getBoardNo()%>"><%=b.getBoardTitle()%> 게시글 보기 </span>
                </td>		
            </tr>		
       		 <%		} 
            }
       		 %>			
		</table>
	</div>

</body>
</html>