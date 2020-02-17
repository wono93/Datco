<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<style>
#updatePass-container {
	position: realative;
	width: 895px;
	min-width: 895px;
	height: 960px;
}

#btn {
	margin: 30px 0 9px;
}

.content {
	width: 532px;
	position: relative;
	margin: 164px;
}

input#newPassword, input#passwordchk, input#password {
	width: 100%;
	height: 50px;
}

#updatePass-container h1{
	text-align:center;
}
.content label{
	font-size:14px;
	font-weight:600;
}

</style>

<div id="updatePass-container">
			<h1>비밀번호 변경하기</h1>
	<form action="<%=request.getContextPath()%>/mypage/updatePassEnd"
		name="helpUserPwFrm" method="post">
		<div class="content">
			<input type="hidden" id="userId" name="userId"
				value="<%=userLoggedIn.getUserId()%>"> <input type="hidden"
				id="oldpassword" name="oldpassword"
				value="<%=userLoggedIn.getPassword()%>">
			<h4 class="title">
				<label for="password">기존 비밀번호</label>
			</h4>
			<input type="password" id="password" name="password" maxlength="16" placeholder="기존 비밀번호">
			<span id="pwdMsg"></span>
			<h4 class="title">
				<label for="password">변경할 비밀번호</label>
			</h4>
			<input type="password" id="newPassword" name="newPassword"
				maxlength="16" placeholder="비밀번호"> <span id="newpwdMsg"></span>
			<h4 class="title">
				<label for="password2">패스워드확인</label>
			</h4>
			<input type="password" id="passwordchk" name="passwordchk"
				maxlength="16" placeholder="비밀번호 확인"> <span id="pwdchkMsg"></span>
			<div id="btn">
				<center>
					<input class="btn btn-primary" type="submit" value="패스워드 변경하기"
					onclick="return password_validate();">
				</center>
			</div>
		</div>
</div>
</form>
<script>
	let oldPwdFlag=false;
	let	newPwdFlag=false;
	let chkPwdFlag=false;

	function password_validate() {
		var pwd_old = $("#password").val().trim();
		var pwd_new = $("#newPassword").val().trim();
		var pwd_chk = $("#passwordchk").val().trim();

		if (pwd_new == "" || pwd_chk == "")
			return false;
			
		return true;
	};
	
	$("#password").keyup(function() {
		var oldpassword = $("#oldpassword").val().trim();
		var password = $("#password").val().trim();

		if (password != oldpassword) {
			$("#pwdMsg").text("기존 비밀번호와 일치하지 않습니다.").css("color", "red");
		} else {
			$("#pwdMsg").text("");
			oldPwdFlag=true;
		}

	});
	$("#newPassword").keyup(function() {
		var oldpassword = $("#oldpassword").val();
		var newpassword = $("#newPassword").val();
		
		
		
		 var regpass = /^(?=.*\d)(?=.*\w).{6,16}$/g;
		if (!regpass.test(newpassword)) {
			$("#newpwdMsg").text("6~16자 영문 대 소문자, 숫자를 사용하세요.");
			$("#newpwdMsg").css("color", "red");

		}else {
			$("#newpwdMsg").text("");
			newPwdFlag=true;
		}

	});

	$("#passwordchk").keyup(function() {
		var password = $("#newPassword");
		var password2 = $("#passwordchk");

		if (password.val() != password2.val()) {
			$("#pwdchkMsg").text("비밀번호가 일치하지않습니다.");
			$("#pwdchkMsg").css("color", "red");
		} else {
			$("#pwdchkMsg").text("비밀번호가 일치합니다.");
			$("#pwdchkMsg").css("color", "green");
			chkPwdFlag=true;
		}
	
	});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>