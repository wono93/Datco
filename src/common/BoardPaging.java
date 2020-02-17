package common;

public class BoardPaging {

	public String pagingBar(String requestPath, String boardCode, int cPage, int pageNo, int pageEnd, int totalPage) {
		StringBuffer pageBar = new StringBuffer("<ul class='pagination' id='pagingNumber'>");	

		if(pageNo != 1 ){
			pageBar.append("<li class='page-item'>"
					+  "	<a class='page-link' href='"+requestPath+"/board/boardList?boardCode="+boardCode+"&cPage="+(pageNo-1)+"'>이전</a> "
					+  "</li>");
		}
		
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar.append("<li class='page-item active' aria-current='page'>" 
						+	"	<span class='page-link'>" + cPage 
						+   "	<span class='sr-only'>(current)</span>" 
						+	"   </span>"  
						+ 	"</li>");
			} 
			else {
				pageBar.append("<li class='page-item'>"
						+ 	"	<a class='page-link' href='" + requestPath+"/board/boardList?boardCode="+boardCode+"&cPage="+pageNo+"'>"
						+ 	pageNo+ "</a>"
						+ 	"</li>");
			}
			pageNo++;
		}
		
		if(pageNo <= totalPage){
			pageBar.append("<li class='page-item'>" 
					+	"      <a class='page-link' href='"+requestPath+"/board/boardList?boardCode="+boardCode+"&cPage="+pageNo+"'>"
					+ 	"다음</a>" 
					+	"</li>");
		}
		pageBar.append("</ul>");
		return pageBar.toString();
	}
}
