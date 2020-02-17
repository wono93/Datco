package common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import common.util.Utils;

public class EncryptWrapper extends HttpServletRequestWrapper {

	//부모타입의 기본생성자가 없으므로 파라미터생성자를 통해
	//부모에 HttpServletRequest객체를 전달한다.
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 사용자입력값 name이 password인 경우만, 
	 * 암호화처리한 value값을 리턴한다.
	 */
	@Override
	public String getParameter(String name) {

		if("password".equals(name) || "newPassword".equals(name)) {
			return Utils.getSha512(super.getParameter(name));
		}
		else {
			return super.getParameter(name);			
		}
		
	}
	
	
	
	
	
	
}
