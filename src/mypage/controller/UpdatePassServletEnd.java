package mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UpdatePassServlet
 */
@WebServlet("/mypage/updatePassEnd")
public class UpdatePassServletEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private UserService userService = new UserService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassServletEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		
		System.out.println(userId);
		System.out.println(password);
		System.out.println(newPassword);
		
		
		//2.서비스로직호출
		User u = userService.selectOne(userId);
		
		//3. 현재패스워드를 맞게 입력했으면, 비밀번호를 업데이트함. 
		//그렇지 않으면, 다시 팝업창 url을 호출함.
		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";
		int result = 0;
		if(u != null){
			
			if(password.equals(u.getPassword())) {
				//현재 member객체에 갱신할 비밀번호를 업데이트
				u.setPassword(newPassword);
				result = userService.updatePassword(u);
				if(result>0){
					msg = "패스워드 변경 성공";
					
				}
			} else {
				msg = "패스워드를 잘못 입력하셨습니다.";
				loc = "/mypage/updatePass?userId="+userId;
			}
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
