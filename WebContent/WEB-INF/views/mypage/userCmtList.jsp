<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="user.model.vo.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="mypage.model.vo.BlackList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User userLoggedIn = (User) session.getAttribute("userLoggedIn");
	//쿠키확인
	Cookie[] cookies = request.getCookies();
	boolean saveIdChecked = false;
	String userId = "";
	if (cookies != null) {
		//System.out.println("cookies@header.jsp");		
		for (Cookie c : cookies) {
			String name = c.getName();
			String value = c.getValue();

			if ("saveId".equals(name)) {
				saveIdChecked = true;
				userId = value;
			}
		}
	}


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>블랙리스트 조회</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
h2 {
	text-align: center;
}

#content {
	text-align: center;
	align-items: center;
}
form {
	display: inline-block;
}
form#eachuser {
	display: inline;
}

 table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
    width: 800px;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }
  th{
  text-align: center;
  background-color: rgb(0, 108, 183);
  color: white;
  }

</style>
</head>
<body>
	<h2><%=userLoggedIn.getNickName()%>님의 작성된 댓글 목록</h2>
	<section id="content">
	
		<table id="blackListTable"></table>
<script>
	function btnfordel(){
		$(".btnfordel").css("cursor","pointer").on("click",function(e){
			  console.log(this);
			  let cmtList = {
					  cmtWriter:$(this).children().eq(1).val(),
					  cmtNo:$(this).children().eq(2).val()
			  };
					console.log('console');
		
			$.ajax({
				url: "<%=request.getContextPath()%>/mypage/cmtDel",
				data: cmtList,
				dataType : "json",
					success : function(data) {
						if(data == 'completed'){
							alert('선택하신 댓글이 삭제되었습니다.');
							location.reload();
						}else if(data == 'failed'){
							alert('삭제에 실패하였습니다 관리자에게 문의해주세요');
							location.reload();
						}
					},
					error : function(x, s, e) { console.log(x, s, e); }
			});
		});
		
			
	}

	function boardInquey(){
		$(".boardInquery").css("cursor","pointer").css("background-color", "rgba(0, 108, 183, 0.1)")
		  .on("click",function(e){
			  
				let boardNo = $(this).children(['[name=openNo]']).val();
			  let url = '<%=request.getContextPath() %>/board/boardView?boardNo='+boardNo;
			  if(opener.closed) {   //부모창이 닫혔는지 여부 확인
			      window.open(url, "openWin");
			   } else {
			      opener.location.href = url;
			      opener.focus();
			   }
			  
		  });
			
	}
	$().ready(function(){
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/myCmtListLoad",
			data: "userId=<%=userLoggedIn.getUserId()%>",
				dataType : "json",
				success : function(data) {
					let cl = '';
					let bt ='';
					let cmtList = "<thead><tr><th>(게시판)게시글 제목</th><th>댓글 내용</th><th>작성당시 등급</th><th>댓or대댓</th><th>작성일</th><th>삭제</th></tr></thead><tbody>";
					if (!(data.length == 0)) {
						 $.each(data, function(idx, cmt){
							 if(cmt.cmtLevel==1){ cl = '댓';  }else if(cmt.cmtLevel == 2){  cl = '대댓';}
							 if(cmt.boardCode == 'CDR'){ bt = '코드리플게시판'}else if(cmt.boardCode == 'CDR' ){bt = '자유게시판'
								 }else if(cmt.boardCode == 'CDR'){bt = '구인공고게시판'}
							 console.log(cmt.cmtWriterGrade);
							
							 let cmtcode = cmt.boardCode;
							  cmtList+= "<tr><td><span class='boardInquery'><input type='hidden' name='delNo' value='"+cmt.boardNo+"'>("+bt+")"+cmt.boardTitle+"</span></td>"+
							  "<td>"+cmt.content+" </td>"+	
							  "<td><img src='<%=request.getContextPath()%>/images/"+cmt.cmtWriterGrade+"' alt='' style='width:20px;' class='message_box'></td>"+	
							  "<td>"+cl+"</td>"+
							  "<td>"+cmt.cmtRegDate+"</td>"+
		                    "<td><span class='btnfordel'><img src='<%=request.getContextPath()%>/images/bin.png'/> <input type='hidden' name='cmtWriter' value="+cmt.cmtWriter+" /> <input type='hidden' name='cmtNo' value="+cmt.cmtNo+" /> </span></td></tr>";
							  });
						 cmtList+="</tbody>";
					} 
						if(data.length == 0) {
							cmtList += "<tr><td colspan='5'>조회된 댓글 목록이 없습니다.</td></tr></tbody>";
					}

					$("#blackListTable").html(cmtList);
					btnfordel();
					boardInquey();
				
				},
				error : function(x, s, e) { console.log(x, s, e); }
		});
	})


		
	$("#inquery").click(function(){
			location.reload();
	});

</script>
<br />
<table>
<tr>
	<td>
<button id="inquery" onclick="location.reload();">조회하기</button>
<input type="button" onclick="self.close();" value="닫기" />
	</td>
</tr>		
</table>
	</section>
</body>
</html>