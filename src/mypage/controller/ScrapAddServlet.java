package mypage.controller;

import java.io.IOException;

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
@WebServlet("/scrap/scrapAdd")
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
		String userId = request.getParameter("userId");
		String memo = request.getParameter("memo");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String referer = request.getHeader("Referer");
		//파라미터 전달 시 간결하게 하기 위해 스크랩으로 변환하여 전달.
		Scrap s = new Scrap();
		s.setUserId(userId);
		s.setBoardNo(boardNo);
		s.setMemo(memo);
		
		
				//업무로직 // Scraptable에 insert
		int result = new MypageService().scrapAdd(s);
		
		if(result>0) {
			response.sendRedirect(request.getHeader(referer));
		}else {
			request.setAttribute("loc", referer);
			request.setAttribute("msg", "스크랩 등록에 실패했습니다..");
			request.getRequestDispatcher("/WEB-INF/views/common.msg").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
