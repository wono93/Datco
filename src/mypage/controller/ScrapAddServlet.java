package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MypageService;
import mypage.model.vo.Scrap;

/**
 * Servlet implementation class ScrapAddServlet
 */
@WebServlet("/mypage/scrapAdd")
public class ScrapAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터핸들링.
//		System.out.println("scrapAddServlet@=====================================");
		String userId = request.getParameter("userId");
		String memo = request.getParameter("memo");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		//파라미터 전달 시 간결하게 하기 위해 스크랩으로 변환하여 전달.
		Scrap s = new Scrap();
		s.setUserId(userId);
		s.setBoardNo(boardNo);
		s.setMemo(memo);

		
		//업무로직 // Scraptable에 insert
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		List<Scrap> sList = new MypageService().selectScrapExist(userId, boardNo);
		String msg = "";
		if(sList.isEmpty() && sList.size() == 0 ) {
			int result = new MypageService().scrapAdd(s);
			if(result>0) {	
				msg = "스크랩 등록 성공~!";
			}else {
				msg = "스크랩 등록에 실패하였습니다. 관리자에게 문의해주세요";
			}
		}else{
			msg = "이미 스크랩 된 글입니다. 스크랩목록을 조회해주세요~";
		}
		out.println("<script>alert('"+msg+"'); location.href='"+referer+"';</script>");
		out.flush();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
