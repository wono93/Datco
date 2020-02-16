package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypage.model.service.MypageService;
import mypage.model.vo.Scrap;

/**
 * Servlet implementation class ScrapDelServlet
 */
@WebServlet("/mypage/scrapDel")
public class ScrapDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapDelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//스크랩 삭제 처리
	//스크랩 삭제 처리
		String userId = request.getParameter("userId");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		Scrap scrap = new Scrap();
		scrap.setBoardNo(boardNo);
		scrap.setUserId(userId);
		
		int result = new MypageService().deleteScrap(scrap);

		if (result > 0) {
			String sendresult = new Gson().toJson(result);
			PrintWriter out = response.getWriter();			
			out.write(sendresult);
	
		} else {
			
			String sendresult = new Gson().toJson(result);
			PrintWriter out = response.getWriter();
			out.write(sendresult);
		}
		
		//삭제 후 redirect
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
