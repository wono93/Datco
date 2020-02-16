package user.model.vo;

import java.io.Serializable;

public class EmpUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String empName;
	private String empNo;
	private String empHomepage;
	private String empAddress;
	private String empInfo;
	
	public EmpUser() {
		super();
	}
	
	public EmpUser(String userId, String empName, String empNo, String empHomepage, String empAddress, String empInfo) {
		super();
		this.userId = userId;
		this.empName = empName;
		this.empNo = empNo;
		this.empHomepage = empHomepage;
		this.empAddress = empAddress;
		this.empInfo = empInfo;
	}
	
	@Override
	public String toString() {
		return "EmpUser [userId=" + userId + ", empName=" + empName + ", empNo=" + empNo + ", empHomepage="
				+ empHomepage + ", empAddress=" + empAddress + ", empInfo=" + empInfo + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpHomepage() {
		return empHomepage;
	}
	public void setEmpHomepage(String empHomepage) {
		this.empHomepage = empHomepage;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpInfo() {
		return empInfo;
	}
	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
	}
	
	
}
