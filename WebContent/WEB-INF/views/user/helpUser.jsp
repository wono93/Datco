
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User user = (User) request.getAttribute("user");
%>
<style>
#helpUser-container {
	position: realative;
	width: 895px;
	min-width: 895px;
}

.findbox {
	width: 532px;
	position: relative;
	margin: 164px;
}

#btn {
	margin: 5px 0 9px;
}

#helpUsertitle {
	text-align: center;
}

input#name, input#email, input#userId, input#email_ {
	width: 100%;
	height: 50px;
}

.title label {
	font-size: 14px;
	font-weight:600;
	
}
</style>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div id="helpUser-container">
	<h1 id="helpUsertitle">아이디/비밀번호찾기</h1>
	<div class="findbox">
		<form action="<%=request.getContextPath()%>/user/helpUserId"
			name="helpUserId" method="post">
			<h4 id=helpUsertitle>아이디찾기</h4>
			<h3 class="title">
				<label for="name">이름</label>
			</h3>
			<input type="text" id="name" name="name" maxlength="4"
				placeholder="이름">
			<h3 class="title">
				<label for="email">이메일</label>
			</h3>
			<input type="text" id="email" name="email" placeholder="이메일">
			<br /> <span>* 회원가입시 작성하였던 이름과 이메일을 입력해주세요.</span>
			<div id="btn">
				<center>
					<input class="btn btn-primary" type="submit" value="확인"
						onclick="return helpId();">
				</center>
			</div>
		</form>
	</div>
	<div class="findbox">
		<form action="<%=request.getContextPath()%>/user/helpUserPw"
			name="helpUserpw" method="post">
			<h4 id=helpUsertitle>비밀번호 찾기</h4>
			<h3 class="title">
				<label for="name">아이디</label>
			</h3>
			<input type="text" id="name" name="userId" placeholder="아이디">
			<h3 class="title">
				<label for="email">이메일</label>
			</h3>
			<input type="text" id="email_" name="email_" placeholder="이메일">
			<br /> <span>* 회원가입시 작성하였던 아이디와 이메일을 입력해주세요.</span>
			<div id="btn">
				<center>
					<input class="btn btn-primary" type="submit" value="확인"
						onclick="return helpPw();">
				</center>
			</div>
		</form>
	</div>
</div>
<script>
	function helpPw() {
		let userId = $("#userId").val().trim();
		let email_ = $("#email_").val().trim();
		if (userId == "" || email_ == "") {
			return false;
		}
		return true;
	}
	function helpId() {
		let name = $("#name").val().trim();
		let email = $("#email").val().trim();
		if (name == "" || email == "") {
			return false;
		}
		return true;

	}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>