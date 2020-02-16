package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MypageService;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserLoginEndServlet
 */
@WebServlet("/user/loginEnd")
public class UserLoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 시켜주기
		
		final int TODAYPOINT = 100;
		
		//값 받고 유저객체 생성
		User user = new User(); 
		
	
		user = new UserService().selectUser(user);

		//로그인처리해주기
		if(user != null) {
			//로그인 마지막 날짜가 오늘이 아닌경우 포인트 증가시켜주기
			if("user.getdate"!="sysdate"){
				int result = new MypageService().insertPointLog(user.getUserId(),TODAYPOINT, "출석");
				if(result>0) {
					new UserService().updateLoginDate(user.getUserId());
				}
			}

			//세션에 user객체 저장하기
			
			
			
			
			//아이디 저장옵션 체크시 쿠키저장
			//돌려줄 jsp는 협의.
			return;
		}
		
		//로그인 실패시
		//msg.jsp
			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
