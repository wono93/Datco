<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<style>
#topMenu {
	height: 55px;
	width: 100%;
	display: inline-block;
}

#topMenu ul {
	text-align: center;
	display: inline-block;
}

#topMenu ul li {
	list-style: none;
	border: 1px solid;
	border-radius: 50px;
	float: left;
	line-height: 30px;
	vertical-align: middle;
	text-align: center;
}

/* div#usermenu {
	width: 45%;
	height: 200px;
	text-align: center;
	display: table;
	border: 1px solid black;
	border-radius: 10px;
	margin: 10px;
	float: left;
} */

div#usermenu>table {
	display: inline-block;
	align-content: center;
}

div#usermenu:hover {
	background-color: gray;
}

div#menuinner {
	align-items: center;
	display: inline-block;
}

div#usermenu td, tr {
	text-align: center;
}

div#container {
	max-width: 850px;
	margin: 0 auto;
 	padding-left: 10px;
    padding-right: 10px;
}
div#outter-container {
	max-width: 900px;
    padding-left: 25px;
    padding-right: 25px;
}
nav {
	float: inherit;
}

section#content {
	float: none;
	width: 100%;
	min-height: 645px;
	margin-top: 40px;
}

#content{
-webkit-box-align: stretch;
    box-align: stretch;
    align-items: stretch;
    display: flex;
    flex-wrap: wrap;
    margin-left: -12px;
    padding-top: 0;
    width: calc(100% + 24px);
}
article{
	flex-basis: 45%;
	text-align: center;
	display: table;
	border: 1px solid black;
	border-radius: 10px;
	
	flex-grow: 1;
    flex-shrink: 1;
    margin-left: 12px;
    margin-right: 12px;
    margin-top: 24px;
    min-width: 0;
}
}
p {
	display: inline-block;
}
</style>


<body>
	<div id="outter-container">

	<div id="container" class="col-md-">

		<section id="content">
			<nav id="topMenu">
				<ul class="navi">
					<li>회원정보 조회 / 수정/ 회원탈퇴</li>
					<li>나의 글 보기</li>
					<li onclick="blackListInquery()">나의 댓글 보기</li>
					<li>블랙리스트 조회</li>
					<li>스크랩 조회</li>
					<li>쪽지함</li>
				</ul>
			</nav>
			<article>
			<div id="usermenu">
				<table id="menuinner">
					<tr>
						<th>회원정보 조회 / 수정/ 회원탈퇴</th>
					</tr>
					<tr>
						<td>정보 조회 및 수정, 탈퇴</td>
					</tr>
				</table>

			</div>
			</article>
			<article>
			<div id="usermenu">
				<table id="menuinner">
					<tr>
						<th>나의 글 보기</th>
					</tr>
					<tr>
						<td>작성한 모든 글 조회</td>
					</tr>
				</table>
			</div>
			</article>
			<article>
			<div id="usermenu">
				<table id="menuinner">
					<tr>
						<th>나의 댓글 보기</th>
					</tr>
					<tr>
						<td>손님으로 방문하기</td>
					</tr>
				</table>
			</div>
			</article>
			<article>
			<div id="usermenu" class="blackListInquery"
				onclick="blackListInquery();">
				<table id="menuinner">
					<tr>
						<th>블랙 리스트 조회</th>
					</tr>
					<tr>
						<td>차단한 회원 조회하기</td>
					</tr>
				</table>
			</div>
			</article>
			<article>
			<div id="usermenu">
				<table id="menuinner">
					<tr>
						<th>스크랩 조회</th>
					</tr>
					<tr>
						<td>스크랩 한 게시글 목록 보기</td>
					</tr>
				</table>
			</div>
			</article>
			<article>
			<div id="usermenu">
				<table id="menuinner">
					<tr>
						<th>쪽지함 조회</th>
					</tr>
					<tr>
						<td>받은 메세지/보낸 메세지 조회</td>
					</tr>
				</table>
			</div>
			</article>
		</section>
	</div>
	</div>
<script type="text/javascript">

$(function(){

});

function blackListInquery(){
	let url = "<%=request.getContextPath()%>/mypage/userBlackList?userId=<%=userLoggedIn.getUserId()%>";
	open(url, "blackList", "left= 100px, top=100px, width=820px, height=400px");
	console.log('dkdkdkdkd');
}

</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>