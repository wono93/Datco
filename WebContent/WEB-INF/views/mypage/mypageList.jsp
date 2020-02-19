<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<style>
#topMenu { 	height: 55px; width: 100%; display: inline-block; }

#topMenu ul { text-align: center; display: inline-block; }

#topMenu ul li { padding: 10px 15px; background-color :rgb(51, 122, 183); list-style: none; float: left; line-height: 30px; 
				vertical-align: middle; text-align: center; position: relative; color:white; }
				
#topMenu ul li:hover { background-color: rgba(51, 122, 183, 0.4); }

div#usermenu>div { display: inline-block; align-content: center; }

div#usermenu:hover { background-color: rgba(51, 122, 183, 0.4); cursor: pointer; }

div#menuinner { align-items: center; display: inline-block; }
div#menuinner>div{min-height: 50px; min-width: 100%;}
div#usermenu{ text-align: center; }

div#container { max-width: 850px; margin: 0 auto; 	padding-left: 10px; padding-right: 10px; text-align: center;}
div#outter-container { max-width: 900px; padding-left: 25px; padding-right: 25px; }
nav { float: inherit;}

section#content { float: none; width: 100%; min-height: 645px; margin-top: 40px; }

#content{ -webkit-box-align: stretch; box-align: stretch; align-items: stretch; display: flex; flex-wrap: wrap; margin-left: -12px;
		padding-top: 0; width: calc(100% + 24px); }
article{ flex-basis: 45%; text-align: center; display: table; border: 3px solid rgba(51, 122, 183, 0.4); border-radius: 10px; flex-grow: 1;
    flex-shrink: 1; margin-left: 12px; margin-right: 12px; margin-top: 24px; min-width: 0;}
div#col-1st {font-size: 2em; color: black; display: inline-flex; padding-top: 30px;}

</style>

	<div id="outter-container">

	<div id="container" class="col-md-">

		<section id="content">
			<nav id="topMenu">
				<ul class="navi">
					<li onclick="userEdit()">회원정보 조회 / 수정/ 회원탈퇴</li>
					<li onclick="userBoardList()">나의 글 보기</li>
					<li onclick="userCommnetList()">나의 댓글 보기</li>
					<li onclick="blackListInquery()">블랙리스트 조회</li>
					<li onclick="scrapInquery();">스크랩 조회</li>
					<li>쪽지함</li>
				</ul>
			</nav>
			<article>
			<div id="usermenu">

				<div id="menuinner" onclick="userEdit()">

					<div id='col-1st'>
						<div>회원정보 조회</div>
					</div>
					<hr />
					<div>
						<div>정보 조회, 수정  및 탈퇴</div>
					</div>
				</div>

			</div>
			</article>
			<article>
			<div id="usermenu" onclick="userBoardList();">
				<div id="menuinner">
					<div id='col-1st'>
						<div>나의 글 보기</div>
					</div>
					<hr />
					<div>
						<div>내가 작성한 모든 글 조회</div>
					</div>
				</div>
			</div>
			</article>
			<article>
			<div id="usermenu" onclick="userCommnetList()"> 
				<div id="menuinner">
					<div id='col-1st'>
						<div>나의 댓글 보기</div>
					</div>
					<hr />
					<div>
						<div>내가 작성한 모든 댓글 조회</div>
					</div>
				</div>
			</div>
			</article>
			<article>
			<div id="usermenu" class="blackListInquery"
				onclick="blackListInquery();">
				<div id="menuinner">
					<div id='col-1st'>
						<div >블랙 리스트 조회</div>
					</div>
					<hr />
					<div>
						<div>차단한 회원 조회, 추가 , 삭제</div>
					</div>
				</div>
			</div>
			</article>
			<article>
			<div id="usermenu" onclick="scrapInquery();">
				<div id="menuinner">
					<div id='col-1st'>
						<div>스크랩 조회</div>
					</div>
					<hr />
					<div>
						<div>스크랩으로 모아둔 글 조회, 추가, 삭제</div>
					</div>
				</div>
			</div>
			</article>
			<article>
			<div id="usermenu">
				<div id="menuinner">
					<div id='col-1st'>
						<div>쪽지함 조회</div>
					</div>
					<hr />
					<div>
						<div>받은 메세지/보낸 메세지 조회</div>
					</div>
				</div>
			</div>
			</article>
		</section>
	</div>	</div>
<script type="text/javascript">

$(function(){

});



function blackListInquery(){
	let url = "<%=request.getContextPath()%>/mypage/userBlackList?userId=<%=userLoggedIn.getUserId()%>";
	open(url, "blackList", "left= 100px, top=100px, width=820px, height=500px");
}
function scrapInquery(){
	let url = "<%=request.getContextPath()%>/mypage/scrap?userId=<%=userLoggedIn.getUserId()%>";
	open(url, "ScrapList", "left= 100px, top=100px, width=820px, height=400px");
}

function userCommnetList(){
	let url = "<%=request.getContextPath()%>/mypage/myCmtList?userId=<%=userLoggedIn.getUserId()%>";
	open(url, "ScrapList", "left= 100px, top=100px, width=820px, height=400px");
}
function userEdit(){
	location.href = "<%=request.getContextPath()%>/mypage/userEdit?userId=<%=userLoggedIn.getUserId()%>";
}

function userBoardList(){
	location.href = "<%=request.getContextPath()%>/mypage/userBoardList?userId=<%=userLoggedIn.getUserId()%>";
}


</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
