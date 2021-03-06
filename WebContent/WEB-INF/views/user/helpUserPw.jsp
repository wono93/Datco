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

.helpUserPwtitle {
	text-align: center;
}
.title label {
	font-size: 14px;
	font-weight: 600;
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
					id="password" name="password" value="<%=user.getPassword()%>"
					>

				<h3 class="title">
					<label for="password">변경할 비밀번호</label>
				</h3>
				<input type="password" id="newPassword" name="newPassword"
					maxlength="16"
					placeholder="변경할 비밀번호"> <span id="pwdMsg"></span>
				<h3 class="title">
					<label for="password2">패스워드확인</label>
				</h3>
				<input type="password" id="passwordchk" name="passwordchk"
					maxlength="16"
					placeholder="변경할 비밀번호 확인"> <span id="pwdchkMsg"></span>
				<div id="btn">
					<center>
							<input class="btn btn-primary" type="submit" value="패스워드 변경하기" onclick="return password_validate();">
					</center>
				</div>
			</div>
</div>
</form>
<script>
let pwdFlag=false;
let chkPwdFlag=false;

function password_validate(){
	var pwd_old = $("#password").val().trim();
	var pwd_new = $("#newPassword").val().trim();
	var pwd_chk = $("#passwordchk").val().trim();
	
	if(pwd_new==""||pwd_chk=="") {
		return false;
	}
	if(pwd_old==pwd_new){
		return false;
	}else{
		return true;
	}
	
	return false;	
};
$(function(){
	

$("#newPassword").keyup(function(e){
	var usePassword= $("#password").val().trim();
	var password= $("#newPassword").val().trim();
	
	if(usePassword==password){
		$("#pwdMsg").text("기존비밀번호와 일치합니다.").css("color","red");
		pwdFlag=false;
	}
	else{
	var regpass = /^(?=.*\d)(?=.*\w).{6,16}$/g;
	 if (!regpass.test(password)) {
		$("#pwdMsg").text("6~16자 영문 대 소문자, 숫자를 사용하세요.");
		$("#pwdMsg").css("color", "red");
	} 
		$("#pwdMsg").text("");
		pwdFlag=true;
	}
	
	
});

$("#passwordchk").keyup(function(){
	var password = $("#newPassword");
	var password2 = $("#passwordchk");
	
	
	if (password.val() != password2.val()) {
       $("#pwdchkMsg").text("일치하지않습니다.");
       $("#pwdchkMsg").css("color", "red");
   } else {
   	$("#pwdchkMsg").text("일치합니다.");
       $("#pwdchkMsg").css("color", "green");
      chkPwdFlag=true;
   }
});
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
