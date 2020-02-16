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
 * Servlet implementation class MessageSendServlet
 */
@WebServlet("/message/send")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int messageNo = Integer.parseInt(request.getParameter("messageNo"));
		String sendUser = request.getParameter("sendUser");
		String getUser = request.getParameter("getUser");
		String msgTitle = request.getParameter("msgTitle");
		String msgContent = request.getParameter("msgContent");
		Message message = new Message(messageNo, sendUser, getUser, msgTitle, msgContent, null, null);
		
		int result = new MessageService().insertMessage(message);
		String msg = "";
		
		if(result > 0) {
			//전송성공
			msg = "전송했습니다.";
		}else {
			//전송실패
			msg = "전송에 실패했습니다.";
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/message/mesageList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
