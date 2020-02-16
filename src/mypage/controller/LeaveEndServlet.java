package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;

/**
 * Servlet implementation class LeaveEndServlet
 */
@WebServlet("/mypage/leaveEnd")
public class LeaveEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원탈퇴처리하기
		HttpSession session = request.getSession(false);
		if(session != null)
			session.invalidate();
		
		String password = request.getParameter("password");
		System.out.println("password@servlet="+password);
		int result = new UserService().deleteUser(password);
		
		String msg= "";
		String loc = "";
		
		//성공시 main
		if(result>0) {
			msg = "회원탈퇴에 성공하셨습니다.";
			loc = "/";
		}else {
			//실패시 msg(alert)
			msg= "회원탈퇴에 실패하셨습니다";
			loc= "/mypage/leave";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
