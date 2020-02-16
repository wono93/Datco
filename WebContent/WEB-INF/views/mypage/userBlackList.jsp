<%@page import="user.model.vo.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="mypage.model.vo.BlackList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User userLoggedIn = (User) session.getAttribute("userLoggedIn");
	//쿠키확인
	Cookie[] cookies = request.getCookies();
	boolean saveIdChecked = false;
	String userId = "";
	if (cookies != null) {
		//System.out.println("cookies@header.jsp");		
		for (Cookie c : cookies) {
			String name = c.getName();
			String value = c.getValue();

			if ("saveId".equals(name)) {
				saveIdChecked = true;
				userId = value;
			}
		}
	}

	/* List<BlackList> blackList =  */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>블랙리스트 조회</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
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
  	width: 400px;
  }
</style>
</head>
<body>
	<h2><%=userLoggedIn.getNickName()%>님의 차단 유저 리스트</h2>
	<section id="content">


		<form id="BlackListAddForm" method="post">
			<input type="hidden" name="delUserId"
				value="<%=userLoggedIn.getUserId()%>" />
			<table>
				<tr>
					<td>차단 유저 추가</td>
					<td><input type="text" name="blackId" placeholder="차단할 유저 아이디" /></td>
					<td><input type="text" name="memo" placeholder="메모 입력" /></td>
					<td><button type="button" id="btn_add">추가하기</button></td>
				</tr>
			</table>
		</form>
		<script>
		
		
$("#btn_add").click(function(){
	let blackList = {
		userId:$("[name=delUserId]").val(),
		blackId:$("[name=blackId]").val(),
		regDate:"date",
		memo:$("[name=memo]").val()
	};
	let $userId = $("[name=delUserId]").val();
	console.log($userId);
	
	console.log(blackList);
	$.ajax({
		url:"<%=request.getContextPath()%>/mypage/userBlackListAdd.do",
		data: blackList,
		dataType: "json",
		success : function(data) {
			console.log(data);
			if(userLoggedin.getUserId == userId){
				alert('본인은 차단 할 수 없습니다');
			}else if(data == 'exist'){
				alert('이미 존재하는 차단유저 입니다.');
			}else if(data == 'insertfail'){
				alert('차단에 실패하였습니다 관리자에게 문의해주세요');
			}
			let blackList = "<thead><tr></tr> <tr><th>아이디</th><th class='memo-col'>메모</th><th>차단일</th><th>삭제</th></tr></thead><tbody>";
			if (!(data.length == 0)) {
				  $.each(data, function(idx, blackUser){
					  let $delLoc = "location.href='<%=request.getContextPath()%>\/mypage\/BlackListDel?userId="+blackUser.userId+"&blackUser="+blackUser.blackId+"'";
					  
					  console.log($delLoc);
					  blackList+= "<tr><td name='blackId'>"+blackUser.blackId+"</td>"+
                    "<td name='memo'>"+blackUser.memo+"</td>"+
                    "<td>"+blackUser.regDate+"</td>"+
                    "<td><input type='image' src='<%=request.getContextPath()%>/images/crossred.png' onclick=\""+$delLoc+"\" value='삭제'></td></tbody></tr>";
					  });
				  blackList+="</tbody>";
			} 
				if(data.length == 0) {
				blackList += "<tr><td colspan='4'>조회된 차단 유저가 없습니다</td></tr></tbody>";
				console.log('아아아');
			};
			$("#blackListTable").html(blackList);
		
		},
		error: function(x,s,e){
			console.log(x,s,e);
		},
		complete: function(){
			//등록폼 초기화
			$("#BlackListAddForm")[0].reset();
		}
	});
	
})

</script>

		<table id="blackListTable"></table>
	
		<script>
	
		$().ready(function(){
			$.ajax({
				url: "<%=request.getContextPath()%>/mypage/userBlackListLoading.do",
				data: "userId=<%=userLoggedIn.getUserId()%>",
					dataType : "json",
					success : function(data) {
						let blackList = "<thead><tr></tr> <tr><th>아이디</th><th class='memo-col'>메모</th><th>차단일</th><th>삭제</th></tr></thead><tbody>";
						if (!(data.length == 0)) {
							  $.each(data, function(idx, blackUser){
								  let $delLoc = "location.href='<%=request.getContextPath()%>\/mypage\/BlackListDel?userId="+blackUser.userId+"&blackUser="+blackUser.blackId+"'";
								  
								  console.log($delLoc);
								  blackList+= "<tr><td name='blackId'>"+blackUser.blackId+"</td>"+
                                  "<td name='memo'>"+blackUser.memo+"</td>"+
                                  "<td>"+blackUser.regDate+"</td>"+
                                  "<td><input type='image' src='<%=request.getContextPath()%>/images/crossred.png' onclick=\""+$delLoc+"\" value='삭제'></td></tbody></tr>";
            					  });
							  blackList+="</tbody>";
						} 
							if(data.length == 0) {
							blackList += "<tr><td colspan='4'>조회된 차단 유저가 없습니다</td></tr></tbody>";
							console.log('아아아');
						};

						$("#blackListTable").html(blackList);
						
					
					},
					error : function(x, s, e) { console.log(x, s, e); }
			});
		})
	

		
	$("#inquery").click(function(){
			location.reload();
	});

		</script>
		<br />
		<table>
		<tr>
			<td>
		<button id="inquery">조회하기</button>
		<input type="button" onclick="self.close();" value="닫기" />
			</td>
		</tr>		
		</table>
	</section>
</body>
</html>