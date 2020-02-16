package mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MypageService;
import mypage.model.vo.Scrap;

/**
 * Servlet implementation class ScrapServlet
 */
@WebServlet("/mypage/scrap")
public class ScrapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유저의 스크랩 목록 jsp 돌려주기
		//파라미터 받아오기
		
		String userId = request.getParameter("userId");
		System.out.println(userId);

		List<Scrap> scrapList = new MypageService().selectScrap(userId);

	
		//값보내기
		//views/mypage/scrap.jsp
		request.setAttribute("scrapList", scrapList);
		request.getRequestDispatcher("/WEB-INF/views/mypage/scrap.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
