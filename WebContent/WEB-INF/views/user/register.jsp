<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


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

#userId, #password, #password2, #userName, #email, #phone, #address,
	#nickName {
	width: 100%;
	height: 50px;
}

#submit {
	width: 460px;
	font-size: 20px;
	background-color: rgb(0, 108, 183);
	color: white;
	height: 50px;
}

#btn {
	margin: 30px 0 9px;
}

#header img {
	width: 240px;
	height: 44px;
}

h1 {
	text-align: center;
}

#sample6_postcode, #sample6_address, #sample6_detailAddress {
	height: 50px;
}

#sample6_address, #sample6_detailAddress {
	width: 100%;
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
			<input type="text" id="userId" name="userId" maxlength="13" placeholder="아이디">
			<span id="idMsg"></span>

			<h3 class="title">
				<label for="password">비밀번호</label>
			</h3>
			<input type="password" id="password" name="password" maxlength="16" placeholder="비밀번호">
			<span id="pwdMsg"></span>
			<h3 class="title">
				<label for="password2">비밀번호 확인</label>
			</h3>
			<input type="password" id="password2" name="password2" maxlength="16" placeholder="비밀번호 확인">
			<span id="pwdchkMsg"></span>
			<h3 class="title">
				<label for="name">이름</label>
			</h3>
			<input type="text" id="userName" name="userName" maxlength="4" placeholder="이름">
			<span id="nameMsg"></span>
			<h3 class="title">
				<label for="nickname">별명</label>
			</h3>
			<input type="text" id="nickName" name="nickName" maxlength="6" placeholder="별명">
			<span id="nickMsg"></span>
			<h3 class="title">
				<label for="email">이메일</label>
			</h3>
			<input type="text" id="email" name="email" placeholder="이메일"> <span
				id="emailMsg"></span>
			<h3 class="title">
				<label for="phone">핸드폰번호</label>
			</h3>
			<input type="text" id="phone" name="phone" maxlength="11"
				placeholder="(-없이)01012345678"> <span id="phoneMsg"></span>
			<h3 class="title">
				<label for="address">주소</label>
			</h3>
			<input type="hidden" id="address" name="address"> <input
				type="text" id="sample6_postcode" placeholder="우편번호" readonly> <input
				type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="sample6_address" placeholder="주소" readonly><br>
			<input type="text" id="sample6_detailAddress" placeholder="상세주소" >
			<input type="hidden" id="sample6_extraAddress" placeholder="참고항목" readonly>
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

				return false;
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
			  return false;
			}
		return true;
		});
		$("#password").keyup(function(){
			 var password = $("#password").val();
			
			var regpass = /^(?=.*\d)(?=.*\w).{6,16}$/g;
			if (!regpass.test(password)) {
				$("#pwdMsg").text("6~16자 영문 대 소문자, 숫자를 사용하세요.");
				$("#pwdMsg").css("color", "red");
				return false;
			}else{
				$("#pwdMsg").text("");
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
				return false;
			}else{
				$("#nameMsg").text("");
			}
			
	
		});
		$("#nickName").keyup(function() {
			var nickName = $("#nickName").val();
			
			var regnick = /[가-힣]{2,6}/gi;;
			if (!regnick.test(nickName)) {
				$("#nickMsg").text("한글로 2글자이상 6글자까지 사용하세요.");
				$("#nickMsg").css("color", "red");
				
			}else{
				$("#nickMsg").text("");
			}
	
			

		});
		$("#email").keyup(function(){
			var email = $("#email").val();

			var regemail = 	/^(([^<>()[\]\\.,;:\s@\"]+(\.[^()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			if (!regemail.test(email)) {
				$("#emailMsg").text("이메일을 확인해주세요.");
				$("#emailMsg").css("color", "red");
				return false;
			}else{
				$("#emailMsg").text("");
			}
	
			
		});
		$("#phone").keyup(function(){
			var phone = $("#phone").val();
			
			var regphone = /^((01[1|6|7|8|9])[1-9][0-9]{6,7})$|(010[1-9][0-9]{7})$/;
			if (!regphone.test(phone)) {
				$("#phoneMsg").text("번호를 확인해주세요.");
				$("#phoneMsg").css("color", "red");
				return false;
			}else{
				$("#phoneMsg").text("");
			}
	
			
		});
		function sample6_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("sample6_extraAddress").value = extraAddr;
	                
	                } else {
	                    document.getElementById("sample6_extraAddress").value = '';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample6_postcode').value = data.zonecode;
	                document.getElementById("sample6_address").value = addr;
	                document.getElementById("address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("sample6_detailAddress").focus();
	            }
	        }).open();
	    }
	</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>