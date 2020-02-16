package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class HelpUserPwEndServlet
 */
@WebServlet("/user/helpUserPw")
public class HelpUserPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelpUserPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비밀번호 일치시 pw변경폼 돌려주기
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
	
//		System.out.println("userId@servlet"+userId);
//		System.out.println("email@servlet"+email);
		
		User passUser= new UserService().selectHelpUserPw(userId,email);
		//있을 경우 views/user/helpUserPw.jsp
		
		
		request.setAttribute("user", passUser);
		String msg="";
		String loc="";
		if(passUser!=null) {
			request.getRequestDispatcher("/WEB-INF/views/user/helpUserPw.jsp").forward(request, response);
			System.out.println("pass@servlet"+passUser.getPassword());
			return;
		}else {
			//없을경우  msg.jsp
			msg="정보와 일치하는 아이디가 존재하지않습니다.";
			loc="/user/helpUserIdPw";
			request.setAttribute("inputUserId", userId);
			request.setAttribute("inputUserEmail", email);
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
