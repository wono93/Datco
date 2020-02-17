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

.content>input {
	width: 100%;
	height: 50px;
}

#btn {
	margin: 30px 0 9px;
}
#btn>input{
	width:31%;
	margin-left:6px;
	
}
#userId,#userName{
	background:lightgray;
}
.content h1{
	text-align:center;
}
</style>
<body>
	<form id="userEditFrm" method="post"
		onsubmit="return editValidate();">
		<div id="registerContainer">
			<div class="content">
				<h1>회원정보 수정</h1>
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
					<input class="btn btn-primary" type="button" value="정보수정" onclick="updateUser();">
					<input class="btn btn-primary" type="button" value="비밀번호변경" onclick="passupdateUser();">
					<input class="btn btn-primary" type="button" value="회원탈퇴" onclick="deleteUser();">
					
				</div>
			</div>
		</div>
		


	</form>
	
	<script>
	
	let nickFlag=false;
	let emailFlag=false;
	let phoneFlag=false;
	
	function updateUser(){
		
		$("#userEditFrm").attr("action", "<%=request.getContextPath()%>/mypage/userEditEnd")
						.submit();
	}
	function editValidate(){
		if ( $("#nickName").val().trim() == ""|| $("#email").val().trim() == ""
			|| $("#phone").val().trim() == "" || $("#address").val().trim() == "") {
			
		return false;
		}
		if(nickFlag==true || emailFlag==true || phoneFlag==true){
			return true;
		}
		return false;
	}
	function passupdateUser(){
		location.href = "<%=request.getContextPath()%>/mypage/updatePass?userId=<%=userLoggedIn.getUserId()%>";
	}
	function deleteUser(){
		location.href = "<%=request.getContextPath()%>/mypage/leave?userId=<%=userLoggedIn.getUserId()%>";
	}
	$("#nickName").keyup(function() {
		var nickName = $("#nickName").val();
		
		var regnick = /^[가-힣]{2,6}$/gi;
		if (!regnick.test(nickName)) {
			$("#nickMsg").text("한글로 2글자이상 6글자까지 사용하세요.");
			$("#nickMsg").css("color", "red");
			
		}else{
			$("#nickMsg").text("");
			nickFlag=true;

		}

		

	});
	$("#email").keyup(function(){
		var email = $("#email").val();

		var regemail = 	/^(([^<>()[\]\\.,;:\s@\"]+(\.[^()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!regemail.test(email)) {
			$("#mailMsg").text("이메일을 확인해주세요.");
			$("#mailMsg").css("color", "red");
			
		}else{
			$("#mailMsg").text("");
			emailFlag=true;
			
		}

		
	});
	$("#phone").keyup(function(){
		var phone = $("#phone").val();
		
		var regphone = /^((01[1|6|7|8|9])[1-9][0-9]{6,7})$|(010[1-9][0-9]{7})$/;
		if (!regphone.test(phone)) {
			$("#phoneMsg").text("번호를 확인해주세요.");
			$("#phoneMsg").css("color", "red");
			
		}else{
			$("#phoneMsg").text("");
			phoneFlag=true;
		}

		
	});
	</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>