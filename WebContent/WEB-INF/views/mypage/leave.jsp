<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
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

/* #submit{
	width: 45%;
	font-size: 20px;
	background-color:rgb(0, 108, 183); 
	color: white;
	margin-left:15px;
}
#cancel{
	width: 45%;
	font-size: 20px;
	background-color:red; 
	color: white;
	margin-left:10px;
} */

#btn {
	margin: 30px 0 9px;
}
#btn input, #btn button{
	width:45%;
}
#btn button{
	float:right;
}
.leave_title{
	text-align:center;
}

</style>
</head>
<body>
<form action="<%=request.getContextPath()%>/mypage/leaveEnd"
		name="leaveFrm" method="post">
		<div id="registerContainer">
			<div class="content">
			<h1 class="leave_title">회원탈퇴</h1>
				<h3 class="title">
					<label for="password">비밀번호</label>
				</h3>
				<input type="password" id="password" name="password" maxlength="16"
				placeholder="비밀번호"> <span
					id="pwdMsg" ></span>
				<div id="btn">
					<input class="btn btn-primary" type="submit" value="탈퇴하기" onclick="return deleteUser();" >
					<button type="button" class="btn btn-danger" onclick="return deleteCancel();">취소</button>
				</div>
			</div>
		</div>
</form>

<script>
function deleteUser(){
	let password = $("#password").val().trim();
	
	if(password == ""){
		$("#password").focus();
		return false
	}
	if(!confirm("정말로 탈퇴하시겠습니까?"))
		return;
		
		$("[name=leaveFrm]").submit();
		return true;
}
function deleteCancel(){
	location.href="<%=request.getContextPath()%>/mypage/userEdit?userId=<%=userLoggedIn.getUserId()%>";
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>