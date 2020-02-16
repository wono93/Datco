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
 * Servlet implementation class HelpUserPwChaServlet
 */
@WebServlet("/user/helpUserPwCha")
public class HelpUserPwChaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelpUserPwChaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//변경요청하는 비밀번호 가져와서 update 해주기
		String userId = request.getParameter("userId");
		String password =request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		
		System.out.println("userId@servlet"+userId);
		System.out.println("password_new@servlet"+newPassword);
		System.out.println("password@servlet"+password);
		
		
		int result = new UserService().updatePassword(userId,newPassword);
		String loc = "";
		String msg = "";
		//성공시 main
		if(!password.equals(newPassword)) {
			if(result>0) {
				loc = "/";
				msg="비밀번호 변경이 완료되었습니다.";
			}
		}else {
			
			msg="기존비밀번호와 같습니다.";
			
		}
		request.setAttribute("loc", loc);
		request.setAttribute("msg", msg);
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
