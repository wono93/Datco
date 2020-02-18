package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.model.service.BoardService;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class UserCmtListLoadingServlet
 */
@WebServlet("/mypage/myCmtListLoad")
public class UserCmtListLoadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCmtListLoadingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		List<BoardComment> cmtList = new BoardService().selectBoardCommentWriter(userId);
		
		for(BoardComment c : cmtList) {
			c.setCmtWriterGrade(c.getCmtWriterGrade()+".png");
		}
		
		//views/mypage/userBoardComment.jsp
		String gsonblist = new Gson().toJson(cmtList);
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(gsonblist);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
