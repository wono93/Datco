package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.model.service.BoardService;

/**
 * Servlet implementation class userCmtDel
 */
@WebServlet("/mypage/cmtDel")
public class userCmtDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userCmtDel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cmtNo = Integer.parseInt(request.getParameter("cmtNo"));

		int result = new BoardService().boardCmtDel(cmtNo);
		
		String suc = null;
		String gsonblist = null;
		
		if (result > 0) {
			suc = "completed";
			gsonblist = new Gson().toJson(suc);

			PrintWriter out = response.getWriter();
			out.write(gsonblist);

		} else {
			suc = "failed";
			gsonblist = new Gson().toJson(suc);
			PrintWriter out = response.getWriter();
			out.write(gsonblist);
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
