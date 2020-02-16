package message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import message.model.service.MessageService;
import message.model.vo.Message;

/**
 * Servlet implementation class DeleteMessageServlet
 */
@WebServlet("/message/delete")
public class DeleteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//메세지 삭제 
		int messageNo = Integer.parseInt(request.getParameter("messageNo"));

		int result = new MessageService().deleteMessage(messageNo);
		if(result>0) {
			System.out.println("메세지 삭제 완료");
		}else {
			System.out.println("\n______메세지 삭제 실패______\n");
		}
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
