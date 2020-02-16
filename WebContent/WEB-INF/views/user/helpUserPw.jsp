<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	User user = (User) request.getAttribute("user");
%>

<style>
#helpUserPw-container {
	position: realative;
	width: 895px;
	min-width: 895px;
	height: 960px;
}

#btn {
	margin: 30px 0 9px;
}

.findbox {
	width: 532px;
	position: relative;
	margin: 164px;
}

input#newPassword, input#passwordchk {
	width: 100%;
	height: 50px;
}

#btn input {
	background-color: #516B82;
	color: white;
	cursor: pointer;
}

.helpUserPwtitle {
	text-align: center;
}
</style>

<div id="helpUserPw-container">
	<form action="<%=request.getContextPath()%>/user/helpUserPwCha"
		name="helpUserPwFrm" method="post">
		<h1 class="helpUserPwtitle">
			비밀번호 변경하기
			</h4>
			<div class="findbox">

				<input type="hidden" id="userId" name="userId"
					value="<%=user.getUserId()%>"> 
					<input type="hidden"
					id="password" name="password" value="<%=user.getPassword()%>">

				<h3 class="title">
					<label for="password">변경할 비밀번호</label>
				</h3>
				<input type="password" id="newPassword" name="newPassword"
					maxlength="16"> <span id="pwdMsg"></span>
				<h3 class="title">
					<label for="password2">패스워드확인</label>
				</h3>
				<input type="password" id="passwordchk" name="passwordchk"
					maxlength="16"> <span id="pwdchkMsg"></span>
				<div id="btn">
					<center>
						<input type="submit" id="submit" value="패스워드 변경하기"
							onclick="return password_validate();">
					</center>
				</div>
			</div>
</div>
</form>
<script>
function password_validate(){
	var pwd_old = $("#password").val().trim();
	var pwd_new = $("#newPassword").val().trim();
	var pwd_chk = $("#passwordchk").val().trim();
	
	if(pwd_new==""||pwd_chk=="") {
		return false
	}
	if(pwd_old==pwd_new){
		return false
	}
	
	return true;	
};

$("#newPassword").keyup(function(){
	var oldPassword = $("#password").val().trim();
	var password= $("#newPassword").val().trim();
	
	if(oldPassword==password){
		$("#pwdMsg").text("기존비밀번호와 같습니다.").css("color","red");
	}
	 
	var regpass = /^(?=.*\d)(?=.*\w).{6,16}$/g;
	if (!regpass.test(password)) {
		$("#pwdMsg").text("6~16자 영문 대 소문자, 숫자를 사용하세요.");
		$("#pwdMsg").css("color", "red");
		return false;
	}else{
		$("#pwdMsg").text("");
	}
	
	
});

$("#passwordchk").keyup(function(){
	var password = $("#newPassword");
	var password2 = $("#passwordchk");
	
	
	if (password.val() != password2.val()) {
       $("#pwdchkMsg").text("일치하지않습니다.");
       $("#pwdchkMsg").css("color", "red");
       return false
   } else {
   	$("#pwdchkMsg").text("일치합니다.");
       $("#pwdchkMsg").css("color", "green");
       
       
       return true;
   }
	return true;
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>