package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserUpgradeServlet
 */
@WebServlet("/user/userUpgrade")
public class UserUpgradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpgradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userRole = request.getParameter("userRole");
		User user = new User();
		int result = 0;
		
		if("A".equals(userRole)) {
			result = new UserService().updateUserRole(userId, "U");
		}else {
			result = new UserService().updateUserRole(userId, "A");			
		}
		
		user = new UserService().selectOne(userId);
		
		String gsonblist = new Gson().toJson(user);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(gsonblist);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
