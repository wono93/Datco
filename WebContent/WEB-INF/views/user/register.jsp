<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<style>


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

#userId,#password,
#password2,#userName,
#email,#phone,#address,#nickName{
	width: 100%;
	height: 50px;
}

#submit {
	width: 460px;
	font-size: 20px;
	background-color:rgb(0, 108, 183); 
	color: white;
	height: 50px;
}

#btn {
	margin: 30px 0 9px;
}
#header img{
	width:240px;
	height:44px;

}
h1{
	text-align:center;
}


</style>

	<form action="<%=request.getContextPath()%>/user/registerEnd"
		name="userRegisterFrm" method="post"
		onsubmit="return registerValidate();">
		<div id="registerContainer">
			<div class="content">

				<h3 class="title">
					<label for="id">아이디</label>
				</h3>
				<input type="text" id="userId" name="userId" maxlength="13"> <span
					id="idMsg"></span>
		
				<h3 class="title">
					<label for="password">패스워드</label>
				</h3>
				<input type="password" id="password" name="password" maxlength="16"> <span
					id="pwdMsg"></span>
				<h3 class="title">
					<label for="password2">패스워드확인</label>
				</h3>
				<input type="password" id="password2" name="password2" maxlength="16"> <span
					id="pwdchkMsg"></span>
				<h3 class="title">
					<label for="name">이름</label>
				</h3>
				<input type="text" id="userName" name="userName" maxlength="4"> <span
					id="nameMsg"></span>
				<h3 class="title">
					<label for="nickname">별명</label>
				</h3>
				<input type="text" id="nickName" name="nickName"> <span
					id="nickMsg"></span>
				<h3 class="title">
					<label for="email">이메일</label>
				</h3>
				<input type="text" id="email" name="email"> <span
					id="mailMsg"></span>
				<h3 class="title">
					<label for="phone">핸드폰번호</label>
				</h3>
				<input type="text" id="phone" name="phone" maxlength="11" placeholder="(-없이)01012345678"> <span
					id="phoneMsg"></span>
				<h3 class="title">
					<label for="address">주소</label>
				</h3>
				<input type="text" id="address" name="address" placeholder="서울시 강남구"> 
				<div id="btn">
					<input type="submit" id="submit" value="가입하기">
				</div>
			</div>
		</div>


	</form>
	<script>
		function registerValidate() {
			if ($("#userId").val().trim() == "" || $("#password").val().trim() == ""
					|| $("#password2").val().trim() == ""
					|| $("#userName").val().trim() == "" || $("#nickName").val().trim() == ""
					|| $("#email").val().trim() == ""
					|| $("#phone").val().trim() == "" || $("#address").val().trim() == "") {

				return false;
			}

			return true;

		}
		
		$("#userId").keyup(function() {
			var userId = $("#userId").val();

			var regId = /^[a-z0-9]{5,11}$/;
			
			if (!regId.test(userId)) {
				 $("#idMsg").text("6~12자의 영문 소문자, 숫자만 사용 가능합니다."); 
				$("#idMsg").css("color", "red");

				
			}else{
				
			  $.ajax({
		            type:"post",
		            url: "<%=request.getContextPath()%>/user/checkId.do",
		            data: {userId:userId},
		            dataType:"text",
		            success : function(data){
		            	var result = data
		            	console.log(data);
		            	if("아이디가 존재합니다."==result){
		            		
		            	$("#idMsg").text(data).css("color","red");
		            	}else{
		            		
		            	$("#idMsg").text(data).css("color","green");
		            	}
	             
		            },error(x,s,e){
		            	console.log(x,s,e);
		            }
		        }); 
			}
	
		});
		$("#password").keyup(function(){
			 var password = $("#password").val();
			
			var regpass = /^(?=.*\d)(?=.*\w).{6,16}$/g;
			if (!regpass.test(password)) {
				$("#pwdMsg").text("6~16자 영문 대 소문자, 숫자를 사용하세요.");
				$("#pwdMsg").css("color", "red");
				
			}else{
				$("#pwdMsg").hide();
			}
			
			
		});
		
		$("#password2").keyup(function(){
			var password = $("#password");
			var password2 = $("#password2");
			
			
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
		
		
		$("#userName").keyup(function() {
			var userName = $("#userName").val();
		
			var regName = /[가-힣]{2,4}/gi;
			if (!regName.test(userName)) {
				$("#nameMsg").text("한글2글자이상 사용하세요.");
				$("#nameMsg").css("color", "red");
				
			}else{
				$("#nameMsg").hide();
			}
			
	
		});
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
<%@ include file="/WEB-INF/views/common/footer.jsp" %>