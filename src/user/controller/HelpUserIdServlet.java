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
 * Servlet implementation class HelpUserIdEndServlet
 */
@WebServlet("/user/helpUserId")
public class HelpUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelpUserIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//정보가 일치할 경우 id 돌려주기
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		//유저정보 받아오기
		
		
//		System.out.println("name@servlet"+name);
//		System.out.println("email@servlet"+email);
		//있을경우 id 돌려주기
		User user = new UserService().selectHelpUserId(name,email);
		String msg = "";
		String loc="/user/helpUserIdPw";
		if(user!=null) {
			msg="일치하는 아이디는["+user.getUserId()+"]님 입니다.";
			
		}else {
			//없을경우  msg.jsp
			msg="정보와 일치하는 아이디가 존재하지않습니다.";
			
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
