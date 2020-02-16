package message.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import message.model.service.MessageService;
import message.model.vo.Message;

/**
 * Servlet implementation class MessageListServlet
 */
@WebServlet("/message/list")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int numPerPage = 15;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			
		}
		
		List<Message> msgList = null;
		
		if(request.getParameter("getUser")!=null) {
			String getUser = request.getParameter("getUser");
			msgList = new MessageService().selectMessageGeter(getUser);
		}else {
			String sendUser = request.getParameter("sendUser");
			msgList = new MessageService().selectMessageSender(sendUser);
		}
		
		request.setAttribute("msgList", msgList);
		request.getRequestDispatcher("/WEB-INF/views/message/message.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
