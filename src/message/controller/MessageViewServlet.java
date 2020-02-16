package message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import message.model.service.MessageService;
import message.model.vo.Message;

/**
 * Servlet implementation class MessageViewServlet
 */
@WebServlet("/message/view")
public class MessageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("messageNo") == null) {
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
		int messageNo = Integer.parseInt(request.getParameter("messageNo"));
		Message message = new MessageService().selectMessage(messageNo);
		request.getAttribute("awefawef");
		if(message.getReadDate() == null) { 
			int result = new MessageService().updateReadDate(message);
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/views/message/messageView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
