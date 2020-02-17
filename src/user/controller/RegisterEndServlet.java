package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.vo.Point;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class RegisterEndServlet
 */
@WebServlet("/user/registerEnd")
public class RegisterEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//파라미터 
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address= request.getParameter("address");
		
		
		
		User user = new User(userId,userName,nickName,password,email,phone,null,null,address,null);
		
		Point p = new Point();
		p.setUserId(userId);
		p.setPoint(1000);
		System.out.println("p@servlet"+p);
		
		int result = new UserService().insertUser(user);

		String msg = "";
		String loc = "";
		
		//회원가입 성공시
		if(result>0) {
			//메인페이지로
			loc= "/";
			msg="회원가입 성공!";
			System.out.println("loc@servlet"+loc);
			
		
		}else {
			//실패시 alert만 띄우기
			msg="회원가입에 실패하셨습니다.";
				
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
