<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    <div class="aside">
	<%if(userLoggedIn != null){ %>
	<div class="side_account">

		<span> <%=userLoggedIn.getUserName() %>님, 안녕하세요.
		</span>
		<img src="<%=request.getContextPath()%>/images/message.svg" alt="" style="width:15px;" class="message_box">
		<div class="account_submit">
			<button
				onclick="location.href='<%=request.getContextPath()%>/mypage/mypageList?userId=<%=userLoggedIn.getUserId()%>'">내정보보기</button>
			<button
				onclick="location.href='<%=request.getContextPath()%>/user/logout'">로그아웃</button>
		</div>

		<%}else { %>
		<form action="<%=request.getContextPath()%>/user/login" method="post"
			id="loginForm">
			<input type="text" placeholder="아이디" name="userId"
				value="<%=saveIdChecked?userId:"" %>"> <input
				type="password" placeholder="비밀번호" name="password"
				id="login-password">
			<div class="account_submit">
				<label class="checkbox"> <input type="checkbox"
					name="saveId" id="saveId" <%=saveIdChecked?"checked":"" %>>
					<span class="checkbox"></span> 아이디저장
				</label>
				<button type="submit" value="로그인" id="loginbtn">로그인</button>
			</div>
			<div class="account-signup">
				<a href="<%=request.getContextPath() %>/user/helpUserIdPw">아이디 / 비번
					찾기</a> &nbsp; &nbsp; &nbsp;<a
					href="<%=request.getContextPath() %>/user/register">회원가입</a>
			</div>
		</form>
<%  }%>

	</div>
</div>
</div>
		  <div class="footer">
              <hr>
              <p>(04003)서울특별시 구로구 구로동 107-4번지 TEL(02)502-8282
                   FAX(02)303-1818</p>
                   <p>Copyright(c)2020 닷코코퍼레이션(주) DacKo Co.,Ltd. All Rights Reserved</p>            
          </div>
          
<%if(userLoggedIn != null){ %>
<script>
$(".message_box").on("click",function(){
 open("<%=request.getContextPath()%>/message/list?getUser=<%=userLoggedIn.getUserId()%>","waefw","width=600, height=700, top=300, left=200");
});
</script>
<%}%>
</body>
</html>