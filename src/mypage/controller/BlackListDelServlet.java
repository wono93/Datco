package mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.Init;

import mypage.model.service.MypageService;
import mypage.model.vo.BlackList;


/**
 * Servlet implementation class BlackListDelServlet
 */
@WebServlet("/mypage/BlackListDel")
public class BlackListDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlackListDelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blackId = request.getParameter("blackUser");
		String userId = request.getParameter("userId");
		
		
		
		BlackList bl = new BlackList();
		bl.setBlackId(blackId);
		bl.setUserId(userId);
		
		int result = new MypageService().deleteBlackUser(bl);
		 
		System.out.println(blackId+userId+"=================delservlet blackId, userId");
		
		if(result>0) {
			request.getRequestDispatcher("/WEB-INF/views/mypage/userBlackList.jsp").forward(request, response);
		}else {
			request.setAttribute("loc", "/WEB-INF/views/mypage/userBlackList.jsp");
			request.setAttribute("msg", "차단 유저 등록에 실패했습니다..");
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
