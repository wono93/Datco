<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<%
User user = (User)request.getAttribute("user");
%>
</head>
<style>
body {
	margin: 0;
	padding: 0;
}

form {
	display: block;
	background-color: white;
	font-size: 12px;
}

.title {
	font-size: 14px;
	font-weight: 700;
}

#registerContainer {
    position: relative;
    width: 895px;
    min-width: 895px;

}

.content {
	width: 460px;
	margin: 0 auto;
}

input {
	width: 100%;
	height: 50px;
}

#button {
	width: 30%;
	font-size: 20px;
	background-color:rgb(0, 108, 183); 
	color: white;
	margin-left:10px;
}

#btn {
	margin: 30px 0 9px;
}
#userId,#userName{
	background:lightgray;
}
</style>
<body>
	<form id="userEditFrm" method="post"
		onsubmit="return editValidate();">
		<div id="registerContainer">
			<div class="content">

				<h3 class="title">
					<label for="id">아이디</label>
				</h3>
				<input type="text" id="userId" name="userId" value="<%=user.getUserId() %>" readonly> <span
					id="idMsg"></span>
				<h3 class="title">
					<label for="name">이름</label>
				</h3>
				<input type="text" id="userName" name="userName" value="<%=user.getUserName() %>" readonly> <span
					id="nameMsg"></span>
				<h3 class="title">
					<label for="nickname">별명</label>
				</h3>
				<input type="text" id="nickName" name="nickName" value="<%=user.getNickName() %>" > <span
					id="nickMsg"></span>
				<h3 class="title">
					<label for="email">이메일</label>
				</h3>
				<input type="text" id="email" name="email" value="<%=user.getEmail() %>" > <span
					id="mailMsg"></span>
				<h3 class="title">
					<label for="phone">핸드폰번호</label>
				</h3>
				<input type="text" id="phone" name="phone" maxlength="11" value="<%=user.getPhone() %>"> <span
					id="phoneMsg"></span>
				<h3 class="title">
					<label for="address">주소</label>
				</h3>
				<input type="text" id="address" name="address" value="<%=user.getAddress() %>"> 
				<div id="btn">
					<input type="button" onclick="updateUser();" id="button" value="정보수정">
					<input type="button" onclick="passupdateUser();" id="button" value="비밀번호변경">
					<input type="button" onclick="deleteUser();" id="button" value="회원탈퇴">
				</div>
			</div>
		</div>
		


	</form>
	
	<script>
	function updateUser(){
		
		$("#userEditFrm").attr("action", "<%=request.getContextPath()%>/mypage/userEditEnd")
						.submit();
			
		
	}
	function editValidate(){
		if ( $("#nickName").val().trim() == ""|| $("#email").val().trim() == ""
			|| $("#phone").val().trim() == "" || $("#address").val().trim() == "") {

		return false;
		}
		return true;
	}
	function passupdateUser(){
		location.href = "<%=request.getContextPath()%>/mypage/updatePass?userId=<%=userLoggedIn.getUserId()%>";
	}
	function deleteUser(){
		location.href = "<%=request.getContextPath()%>/mypage/leave?userId=<%=userLoggedIn.getUserId()%>";
	}
	$("#nickName").keyup(function() {
		var nickName = $("#nickName").val();
		
		var regnick = "";
		if (!regnick.test(nickName)) {
			$("#nickMsg").text("닉네임.");
			$("#nickMsg").css("color", "red");
			
		}else{
			$("#nickMsg").hide();
		}

		

	});
	$("#email").keyup(function(){
		var email = $("#email").val();

		var regemail = 	/^[A-Za-z0-9\_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z]{2,}/;
		if (!regemail.test(email)) {
			$("#emailMsg").text("이메일을 확인해주세요.");
			$("#emailMsg").css("color", "red");
			
		}else{
			$("#emailMsg").hide();
		}

		
	});
	$("#phone").keyup(function(){
		var phone = $("#phone").val();
		
		var regphone = /^((01[1|6|7|8|9])[1-9][0-9]{6,7})$|(010[1-9][0-9]{7})$/;
		if (!regphone.test(phone)) {
			$("#phoneMsg").text("번호를 확인해주세요.");
			$("#phoneMsg").css("color", "red");
			
		}else{
			$("#phoneMsg").hide();
		}

		
	});
	</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>