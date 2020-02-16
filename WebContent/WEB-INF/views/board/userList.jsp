<%@page import="mypage.model.vo.Point"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" /> 
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
​
<style>
article{width:850px;}
@font-face { font-family: 'Recipekorea'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'BBTreeGR'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeGR.woff') format('woff'); font-weight: normal; font-style: normal; }
.Recipekorea{font-family: Recipekorea;}
.BBTreeGR{font-family: BBTreeGR}
.content_sort{text-align: center;}
th{font-size: 15px;}
</style>
<article>
	<div class="btn-group btn-group-toggle" data-toggle="buttons">
	  <label class="btn btn-secondary active">
	    <input type="radio" name="options" id="point-sort" autocomplete="off" checked> 포인트
	  </label>
	  <label class="btn btn-secondary">
	    <input type="radio" name="options" id="selected-sort" autocomplete="off"> 채택수
	  </label>
	   <form class="form-inline">
	   <div></div>
	   <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="searchNick" id="searchNick" style="float:rigth;" >
	   <button class="btn btn-outline-success my-2 my-sm-0" type="button" id="btn-search">Search</button>
	 	</form>
	</div>	
   <table class="table"> 
     <thead>
       <tr> 
         <th scope="col" class='BBTreeGR'>닉네임</th> 
         <th scope="col" class='content_sort BBTreeGR'>포인트</th> 
         <th scope="col" class='content_sort BBTreeGR'>채택수</th> 
       </tr> 
     </thead> 
     <tbody>
     <tr></tr>	
     </tbody> 
   </table>
</article> 
<style>
.content_id{
	position: relative;
}
</style>
<script>
$(function(){
	$("#point-sort").on("click",function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/board/userListSort?sort=point",
			dataType:"json",
			success:function(data){
				let pointList = "";
				$.each(data,function(idx,point){
					console.log(point.userGrade);
					pointList += ""
					+ "<tr><td ><img src='<%=request.getContextPath()%>/images/"+point.userGrade+"' alt='' style='width:20px;' class='message_box'><span class='content_user'>"+point.nickName+"</span></td>"
					+ "<td class='content_sort point'>"+point.point+"</td>"
					+ "<td class='content_sort selected'>"+point.selectCnt+"</td>"
					+ "</tr>";
					console.log("<%=request.getContextPath()%>/images/"+point.userGrade);
				});
				
				$("tbody").html(pointList);
				
				let lev1 = 100;
				$point = $(".point");
				$point.each(function(idx, td){
					if($(td).text()>lev1){
						$(td).addClass("Recipekorea");
					};
				});
				content();
			},
			error(x,s,e){
				console.log("ajax error");
			}
		});
	});
	$("#selected-sort").on("click",function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/board/userListSort?sort=selected",
			dataType:"json",
			success:function(data){
				let pointList = "";
				$.each(data,function(idx,point){
					pointList += ""
					+ "<tr><td ><img src='<%=request.getContextPath()%>/images/"+point.userGrade+"' alt='' style='width:20px;' class='message_box'><span class='content_user'>"+point.nickName+"</span></td>"
					+ "<td class='content_sort point'>"+point.point+"</td>"
					+ "<td class='content_sort selected'>"+point.selectCnt+"</td>"
					+ "</tr>";
				});
				$("tbody").html(pointList);
				
				let lev1 = 100;
				$selected = $(".selected");
				$selected.each(function(idx, td){
					
					if($(td).text()>lev1){
						$(td).addClass("Recipekorea");
					}
				});
				content();
			},
			error(x,s,e){
				console.log("ajax error");
			}
		});
	});
	$("#btn-search").on("click",function(){
		var searchNick = $("#searchNick").val();
		
		$.ajax({
			url:"<%=request.getContextPath()%>/board/userListSearch",
			dataType:"json",
			data:{searchNick:searchNick},
			success:function(data){
				let searchList = "";
				$.each(data,function(idx,list){
					searchList+= ""
					+ "<tr><td ><img src='<%=request.getContextPath()%>/images/"+list.userGrade+"' alt='' style='width:20px;' class='message_box'><span class='content_user'>"+list.nickName+"</span></td>"
					+ "<td class='content_sort point'>"+list.point+"</td>"
					+ "<td class='content_sort selected'>"+list.selectCnt+"</td>"
					+ "</tr>";
					
				});
				$("tbody").html(searchList);
				
				let lev1 = 100;
				$point = $(".point");
				$point.each(function(idx, td){
					if($(td).text()>lev1){
						$(td).addClass("Recipekorea");
					};
				});
				content();
			},error(x,s,e){
				console.log("ajax error");
			}
		});
	});
	
	$("#point-sort").trigger("click");
	
	
	
});
function content(){
	$(".content_user").css("cursor","pointer")
					  .on("click",function(e){
						  let nickName = $(this).text();
							console.log(nickName);
							 let $infoLoc = '<%=request.getContextPath()%>\/user\/userInfo?nickName='+nickName;
							open($infoLoc,"waefw","width=500, height=300, top=300, left=200");
					  });
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>