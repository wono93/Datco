package user.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class DelUser extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date delDate;
	
	public DelUser() {
		super();
	}

	public DelUser(String userId, String userName, String nickName, String password, String email, String phone,
			Date enrollDate, String userRole, String address, Date preLogDate, Date delDate) {
		super(userId, userName, nickName, password, email, phone, enrollDate, userRole, address, preLogDate);
		this.delDate = delDate;
	}


	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"delDate="+delDate;
	}
	
}
