<%@page import="com.google.gson.Gson"%>
<%@page import="board.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<% 
	Board b = (Board)request.getAttribute("board"); 
	if(b.getCmtSelect().equals("Y")){
		int cmtNo = (int)request.getAttribute("select"); 
	}
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	
	boolean CDRbool = "CDR".equals(b.getBoardCode())?true:false;
	List<String> contentList  = null;
	if(CDRbool)
		contentList = new Gson().fromJson(b.getBoardContent(), List.class);
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<script src="<%=request.getContextPath()%>/codemirror/lib/codemirror.js"></script>
<script src="<%=request.getContextPath()%>/codemirror/mode/javascript/javascript.js"></script>
<script src="<%=request.getContextPath()%>/codemirror/mode/html/html.js"></script>
<script src="<%=request.getContextPath()%>/codemirror/mode/sql/sql.js"></script>
<script src="<%=request.getContextPath()%>/codemirror/mode/css/css.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/codemirror/lib/codemirror.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/codemirror/theme/xq-dark.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote-bs4.min.js"></script>
<script src="https://kit.fontawesome.com/b73d2de8cf.js" crossorigin="anonymous"></script>
<script>
	$(function(){
		/* 코드미러API */
		var codeFrag = $('pre[class!="code"]');
		codeFrag.each(function(i){
			var codeText = $(this).text();
			$(this).text('');
			var lang = $(this).attr('class').split('-')[1];
			var config={
					value:codeText,
					mode:lang.trim(),
					tabSize:2,
					smartIndent:true,
					lineNumbers:true,
					viewportMargin: Infinity,
					readOnly:false,
					theme:'xq-dark',
					lineWrapping:true
			}; 
            CodeMirror(this, config).refresh();
			$("#code").trigger("click");
		});
		/* 작성한 코드옵션에 따른 코드미러API생성 */
	   	var boardOption = "<%=b.getBoardOption()%>";
	   	var codeValue = ".code-"+boardOption;
		$(codeValue).show();
		/* 서머노트API */			
	 	$('#summernote').summernote({
	       height: 100
	    });
	 	$("[name=scrap]").click(function(){
	 		if(<%=userLoggedIn == null%>)
	 			loginAlert();
	 	});
	 	$("[name=boardCommentContent]").click(function(){
	 		if(<%=userLoggedIn == null%>)
	 			loginAlert();
	 	});
	 	$("[name=boardCommentFrm]").submit(function(e){
	 		//로그인 여부 검사
	 		if(<%=userLoggedIn == null%>){
	 			loginAlert();
	 			return false;				
	 		}
	 		//내용검사
	 		var content = $("[name=boardCommentContent]").val().trim();
	 		if(content.length == 0){
	 			return false;
	 		}
	 		return true;
	 	});
  		$("#textarea").click(function(){
			<% if(userLoggedIn != null) {%>
	       	$("#comment-container").hide();
			$("#comment-summernote").show();
			<%} else {%>
				loginAlert();
			<%}%>
		});
		$(".btn-reply").find($(".btn-outline-success")).click(function(){
			if(!confirm('정말 채택하시겠습니까?'))
				return;
			location.href = "<%=request.getContextPath()%>/board/boardCmtSelect?cmtNo="+$(this).val()+"&boardNo=<%=b.getBoardNo()%>";
		});
		$(".btn-reply").find($(".btn-outline-danger")).click(function(){
			if(!confirm('정말 삭제하시겠습니까?'))
				return;
			location.href = "<%=request.getContextPath()%>/board/boardCmtDel?cmtNo="+$(this).val()+"&boardNo=<%=b.getBoardNo()%>";
		});
	 	$(".btn-reply").find($(".btn-primary")).on("click",function(){
	 	<% if(userLoggedIn != null) {%>
	 		//현재댓글 번호
	 		var boardCommentNo = $(this).val();
	
	 		//답글작성폼 생성
	 		var tr = $("<tr></tr>");
	 		var td = $("<td style='display:none;text-align:left;' colspan='2'></td>");
	 		var form = $("<form action = '<%=request.getContextPath()%>/board/boardCmtRegist' method='POST'></form>");
	 		form.append("<input type='hidden' name='boardNo' value='<%=b.getBoardNo()%>'/>");
	 		form.append("<input type='hidden' name='cmtWriter' value='<%=userLoggedIn!=null?userLoggedIn.getUserId():""%>'/>");
	 		form.append("<input type='hidden' name='cmtLevel' value='2'/>");
	 		form.append("<input type='hidden' name='cmtRefNo' value='"+boardCommentNo+"'/>");
	 		form.append("<input type='hidden' name='cmtWriterGrade' value='gold>'/>");
	 		form.append("<textarea name='cmtContent' cols='60' rows='1'></textarea>");
	 		form.append("<button type='submit' class='btn-insert2'>등록</button>");
	 		
	 		td.html(form);
	 		tr.html(td);
	 		tr.insertAfter($(this).parent().parent()) //tr>td>.btn-reply	
	 		  .children("td")
	 		  .slideDown(0)
	 		  .children("form")
	 		  .submit(function(e){
	 			  let content = $(this).children("textarea")
	 			  		 			   .val()
	 			  					   .trim();
	 			  if(content.length == 0)
	 				  e.preventDefault();
	 			  
	 		  })
	 		  .find("textarea")
	 		  .focus();
	 		
	 		//클릭이벤트 한번 실행후에는 핸들러 제거
	 		$(this).off('click');
	 		
 			<% } else { %>
 				loginAlert();
 			<% } %>
 		});
		$("#code-click").trigger("click");
	});
		function updateBoard(){
			location.href = "<%=request.getContextPath()%>/board/boardRegist?boardNo=<%=b.getBoardNo()%>";
		}
		/* 게시글신고 */
		function reportBoard(){
				if(!confirm("정말 신고하시겠습니까?\n(허위신고시 처벌받을 수 있습니다.)"))
					return;
				$("[name=report]").submit();
		}
		/* 답변신고 */
		function reportComment(){
				if(!confirm("정말 신고하시겠습니까?\n(허위신고시 처벌받을 수 있습니다.)"))
					return;
				$("[name=rpt]").submit();
		}
		function loginAlert(){
		 	alert("로그인 후 이용하실 수 있습니다.");
		 	$("#login-memberId").focus();
		}
		function deleteBoard(){
			if(!confirm("정말로 삭제하시겠습니까?"))
				return;
			$("[name=boardDelFrm]").submit();
		}
</script>
<section id="board-container">
	<div id="boardtop" class="boardview">
		<!-- 게시판명(클릭한 게시판을 따라 게시판명을 표시) -->
		<%if(b.getBoardCode().equals("FRE")) {%>
		<a href="<%=request.getContextPath() %>/board/boardList?boardCode=FRE">
			<h2>Free Board</h2>
		</a>
		<%}else if(b.getBoardCode().equals("CDR")){%>
		<a href="<%=request.getContextPath() %>/board/boardList?boardCode=CDR">
			<h2>Code Ripple</h2>
		</a>
		<%}else if(b.getBoardCode().equals("JOB")){%>
		<a href="<%=request.getContextPath() %>/board/boardList?boardCode=JOB">
			<h2>JOB</h2>
		</a>
		<% } %>
		<input type="button" value="새 글 작성" onclick="location.href='<%=request.getContextPath() %>/board/boardRegist'" id="addboard" class="btn btn-primary btn-wide pull-right" />
	</div>
	<!-- 게시글 작성자(작성자의 등급, 등급에 맞는 이미지, 작성자 이름표시, 클릭시 마이페이지 이동 / 미 로그인시 등급이미지,태그삭제) -->
	<div id="boardwriter" class="boardview">
		<% if(userLoggedIn != null &&
				b.getBoardWriter().equals(userLoggedIn.getUserId())){ %>
			<a href="<%=request.getContextPath()%>/mypage/mypageList?userId=<%=userLoggedIn.getUserId()%>">
				<img src="<%=request.getContextPath()%>/images/<%=b.getBoardWriterGrade() %>.png" alt="" width="50px"/>
				<h3><%=b.getBoardWriter()%></h3>
			</a>
		<% } else { %>
			<img src="<%=request.getContextPath()%>/images/none.png" alt="" width="50px"/>
				<h3><%=b.getBoardWriter()%></h3>
		<% } %>
	</div>
	<div id="boardcontent" class="boardview">
		<div id="boardinfo" class="boardview">
			<!-- 게시글제목(일정 길이 넘어갈 시 폰트사이즈 조절) -->
			<%if(b.getBoardTitle().length() > 10 && b.getBoardTitle().length() < 20) {%>
			<div name="boardTitle" id="boardTitleM"><%=b.getBoardTitle() %>
			<% } else if(b.getBoardTitle().length() >= 20) {%>
			<div name="boardTitle" id="boardTitleL"><%=b.getBoardTitle() %>
			<% } else { %>
			<div name="boardTitle" id="boardTitleS"><%=b.getBoardTitle() %>
			<% } %>
				<!-- 스크랩 -->
				<div id="scrap">
					<button type="button" class="btn btn-primary" name="scrap">
					<%-- <% if(userLoggedIn.getBoardScrap() != this.getBoardScrap()) {%>
					<!-- 스크랩 중 -->
						<i class="fas fa-bookmark"></i>
					<% } else {%>--%>
					<!-- 스크랩 안함 -->
						<i class="far fa-bookmark"></i>
					<%-- <% } %> --%>
			   			<span class="badge badge-light">9</span>
			  			<span class="sr-only">스크랩 수</span>
					</button>
				</div>
			</div>
			<!--게시글 신고하기 -->
			<% if(userLoggedIn!=null){%>
			<form action="<%=request.getContextPath()%>/board/boardReport" method="POST" name="report">
				<input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>" />
				<input type="hidden" name="userId" value="<%=userLoggedIn.getUserId()%>" />
				<input type="hidden" name="boardCode" value="<%=b.getBoardCode() %>" />
				<a onclick="reportBoard();"><i class="far fa-angry"></i></a>
			</form>
			<%} %>
			<!-- 조회수 -->
			<div name="boardCount" id="boardCount">
				<img src="<%=request.getContextPath()%>/images/eye.png" alt="" width="26px"/>
				<%=b.getReadCnt() %>
			</div>
			<!-- 작성날짜 -->
			<span class="badge badge-pill badge-secondary"><%=b.getBoardRegDate() %></span>
		</div>
		<!-- 내용 -->
		<div id="content" class="boardview">
			<div class="form-group  has-feedback"><hr />
	        	<div><%=CDRbool?contentList.get(0):b.getBoardContent()%></div>
		        	<div class="form-group  has-feedback" id="code-">
		        	<!-- codemirror API -->
			        <pre class="code-javascript"  name="codeContent" style="display:none;"><%=CDRbool?contentList.get(1):""%></pre>
			        <pre class="code-html"  name="codeContent" style="display:none;"><%=CDRbool?contentList.get(1):""%></pre>
			        <pre class="code-css"  name="codeContent" style="display:none;"><%=CDRbool?contentList.get(1):""%></pre>
			        <pre class="code-sql"  name="codeContent" style="display:none;"><%=CDRbool?contentList.get(1):""%></pre>
		        </div>
			</div>
		<%-- <div class="boardview">
			<!-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --> 
			<%if(b.getRenamedFileName()!=null){ %>
			<a href="javascript:fileDownload('<%=b.getOriginalFileName()%>','<%=b.getRenamedFileName()%>');">
			<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>:<%=b.getOriginalFileName() %></a>
			<% } %>
		</div> --%>
		<% if(userLoggedIn!=null && (b.getBoardWriter().equals(userLoggedIn.getUserId())||"A".equals(userLoggedIn.getUserRole()))){%>
		<div class="boardview">
			<!-- 작성자와 관리자에게만 수정/삭제버튼 표시 -->
			<input type="button" value="수정" onclick="updateBoard();" id="cancel" class="btn btn-outline-dark btn-default btn-wide" /> 
			<input type="button" value="삭제"	 onclick="deleteBoard();" id="submit" class="btn btn-primary btn-wide pull-right"/>
				<input type="hidden" name="boardCode" value='<%=b.getBoardCode()!=null?b.getBoardCode():""%>' />
			<!-- 게시물삭제폼 -->
			<form action="<%=request.getContextPath()%>/board/boardDel" method="POST" name="boardDelFrm">
				<input type="hidden" name="boardNo" value="<%=b.getBoardNo() %>" /> 
				<input type="hidden" name="boardCode" value='<%=b.getBoardCode()!=null?b.getBoardCode():""%>' />
			</form>
		</div>
		<% } %>
	</div>	
	<!-- 댓글목록테이블 -->
	<div id="comment">
		<table id="tbl-comment">
			<%
			if(commentList.size() != 0){
			%>
			<tr><td colspan="2" style="text-align:left;background:lightgray;">답변</td></tr>
			<%
				for(BoardComment bc : commentList){
					if(bc.getCmtLevel()==1){
			%>
			<tr class="level1">
				<td>
					<sub class="comment-writer"><%=bc.getCmtWriter() %></sub>
					<sub class="comment-date"><%=bc.getCmtRegDate() %></sub><br />
					<%=bc.getCmtContent() %>
				</td>
				<td class="btn-reply">
					<!-- 답글버튼 -->
					<button  class="btn btn-primary" style="margin:3px;" value="<%=bc.getCmtNo()%>">답글</button>
					<!-- 신고하기 -->
					<% if(userLoggedIn!=null){%>
					<form action="<%=request.getContextPath()%>/board/boardReport" method="POST" name="rpt">
						<input type="hidden" name="boardNo" value="<%=bc.getBoardNo()%>" />
						<input type="hidden" name="cmtNo" value="<%=bc.getCmtNo()%>" />
						<input type="hidden" name="userId" value="<%=userLoggedIn.getUserId()%>" />
						<a onclick="reportComment();"><i class="far fa-angry"></i></a>
					</form>
					<%} %>
					<!-- 채택버튼(코드리플게시판의 작성자에게만 노출) -->
					<% if(userLoggedIn!=null && 
						(b.getBoardWriter().equals(userLoggedIn.getUserId())) && 
							b.getBoardCode().equals("CDR")){%>
					<button class="btn btn-outline-success" style="margin:3px;" value="<%=bc.getCmtNo()%>">채택</button>
					<% 	} %>
					<!-- 삭제버튼(작성자와 관리자에게만 노출) -->
					<% if(userLoggedIn!=null && 
							 (bc.getCmtWriter().equals(userLoggedIn.getUserId()) ||
							 "A".equals(userLoggedIn.getUserRole()))){ %>
					<button class="btn btn-outline-danger" style="margin:3px;" value="<%=bc.getCmtNo()%>">삭제</button>
					<% } %>
				</td>
			</tr>
			<%
					} else {
			%>
			<!-- 대댓글 -->
			<tr class="level2">
				<td><sub class="comment-writer"><%=bc.getCmtWriter() %></sub>
					<sub class="comment-date"><%=bc.getCmtRegDate() %></sub> <br />
					<%=bc.getCmtContent() %></td>
				<td></td>
			</tr>
			<% 	
					}//end of if(level)
				}//end of for
			}//end of if(commentList)
			%>
		</table>
	</div> 
	
	<!-- 게시물 답변작성 기본 폼 -->
	<div id="comment-container" class="boardview">
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/board/boardCmtRegist" method="post" name="boardCommentFrm">
				<input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>" />
				<input type="hidden" name="cmtWriter" value="<%=userLoggedIn!=null?userLoggedIn.getUserId():""%>" />
				<input type="hidden" name="cmtLevel" value="1" /> 
				<input type="hidden" name="cmtRefNo" value='0' />
				<input type="hidden" name="cmtWriterGrade" value='gold' />
				<textarea name="cmtContent" id="textarea" cols="60" rows="3" class="textarea" placeholder="답변을 작성해 주세요."></textarea>
				<button type="submit" class="btn btn-primary btn-wide pull-right" style="float:right">등록</button>
			</form>
		</div>
	</div>
	<!-- textarea클릭 시 나타나는 summernote textarea -->
	<div id="comment-summernote" style="display:none">
		<div>
			<form action = '<%=request.getContextPath()%>/board/boardCmtRegist' method='POST'name="boardCommentFrm">
				<input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>" />
				<input type="hidden" name="cmtWriter" value="<%=userLoggedIn!=null?userLoggedIn.getUserId():""%>" />
				<input type="hidden" name="cmtLevel" value="1" /> 
				<input type="hidden" name="cmtRefNo" value='0' />
				<input type="hidden" name="cmtWriterGrade" value='gold' />
				<div class="textarea">
					<textarea id='summernote' name='cmtContent' ></textarea>
				</div>
				<button type="submit" class="btn btn-primary btn-wide pull-right" style="float:right">등록</button>
			</form>
		</div> 
	</div> 
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

