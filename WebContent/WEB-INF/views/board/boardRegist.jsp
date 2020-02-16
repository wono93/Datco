<%@page import="java.util.List"%>
<%@page import="board.model.vo.Board"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	Board b = null;
	boolean CDRbool = false;
	if(request.getAttribute("board")!=null){
		b = (Board)request.getAttribute("board");
		CDRbool = "CDR".equals(b.getBoardCode())?true:false;
	}
	boolean bool = b!=null?true:false;
	
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
        $(function () {
			$("#category").on('change',function(){
		    	var value = $("#category option:selected").val();
		    	var container = ".code-container."+value;
		    	
	            $('textarea[class=code]').each(function (i) {
	                var lang = $(this).attr('class').split('.')[1];
	                var config = {
	                    value: $(this).text(),
	                    mode: lang,
	                    tabSize: 2,
	                    smartIndent: true,
	                    lineNumbers: true,
						viewportMargin: Infinity,
	                    readOnly: false,
	                    theme: 'xq-dark',
	                    lineWrapping: true
	                };
	                CodeMirror.fromTextArea(this, config).refresh();
			    
	            });
	            
			    $('#w').addClass(value)
		        $('#h').addClass(value);
		    	$(container).show();
                
			    $("#category").on('click',function(){
					$(container).siblings().hide();
				    $('#w').removeClass(value);
					$('#h').removeClass(value).siblings().remove();
			    });
		    });
            $('#summernote').summernote({
                height: 300
            });
		    $("#boardSelect").change(function(){
		    	var boardCode = $(this).val();
		    	/* console.log(boardCode); */
				if(boardCode == "CDR"){
					$("#select").show();
				}else{
					$("#select").hide();
				}
		    });
		    $("[name=upFile]").change(function(){
				let fname = $(this).val();
				//첨부파일이 있는 경우
				if(fname != ""){
					$("#fname").hide();
					//기존 파일을 감추기
				}
				//첨부파일이 없는 경우
				else{
					$("#fname").show();
					//기존 파일을 보여주기 
				}
			});
		}); 
			function boardValidate(){
			   	var content = $("[name=boardContent]").val();
			   	//console.log(content.length)
			   	/* if(content.trim().length==0){
			   		alert("내용을 입력하세요");
			   		return false;
			   	} */
			   	return true;
			} 

</script>
<section id="board-container" class="board-container">
	<form action="<%=request.getContextPath()%>/board/boardRegistEnd" method="post">
	<% if(b!=null){ %> 
	<h2>게시물 수정</h2>
	<% }else{ %> 
	<h2>새 글 작성</h2> 
		<!-- 게시글에 50포인트 부여 -->
		<input type="hidden" name="point" class="form-control" value="50"/>
	<% } %>
		<!-- 수정시 필요한 hidden boardNo -->
		<input type="hidden" name="boardNo" value='<%=bool?b.getBoardNo():""%>'/>
		<!-- 작성자 -->
		<div class="form-group  has-feedback">
			<input type="text" name="boardWriter" value="<%=bool?b.getBoardWriter():userLoggedIn.getUserId()%>" class="form-control" readonly/>
		</div>
		<!-- 작성자등급 -->
		<input type="hidden" name="boardWriterGrade" value="gold"/>
		<!-- 게시글종류 선택 -->
		<div class="form-group  has-feedback">
			<select name="boardCode" class="form-control" id="boardSelect" required>
				<option value="" selected="selected">작성하실 게시판 종류를 선택해 주세요.</option>
				<option value="FRE">자유게시판</option>
				<option value="CDR">코드리플</option>
				<option value="JOB">구인구직</option>
			</select>
		</div>		
		<!-- 코드선택 -->
		<div class="form-group  has-feedback" id="select">
			<select id="category" name="boardOption" class="form-control" required>
				<option value='<%=bool?b.getBoardOption():"none"%>' selected="selected">작성하실 코드를 선택해 주세요.</option>
				<option value="html">HTML</option>
				<option value="css">CSS</option>
				<option value="javascript">JavaScript</option>
				<option value="sql">SQL</option>
			</select>
		</div>
		<!-- 제목 -->
		<div class="form-group  has-feedback">
			<input type="text" name="boardTitle" value='<%=bool?b.getBoardTitle():""%>' placeholder="제목을 입력해 주세요." class="form-control" required/>
		</div>
		<!-- 내용 -->
	  	<!-- summernote API -->
	  	<div class="form-group  has-feedback">
			<textarea id="summernote" name="boardContent" cols="30" rows="10"><%=bool?(CDRbool?contentList.get(0):b.getBoardContent()):""%></textarea>
		</div>
	  	<!-- codemirror API -->
	  	<div class="form-group  has-feedback">
	  		<div class="code-container" id="w">
	       	<textarea class="code" id="h" name="codeContent"><%=bool?(CDRbool?contentList.get(1):""):""%></textarea>
	  		</div>
		</div>
		<!-- 버튼 -->
		<a href="<%=request.getContextPath()%>/board/boardList" id="cancel"	class="btn btn-outline-dark btn-default btn-wide" onclick="return confirm('작성을 정말로 취소하시겠습니까?')">취소</a> 
		<input type="submit" id="submit" class="btn btn-primary btn-wide pull-right" value="등록" onclick="return boardValidate();"/>
	</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>