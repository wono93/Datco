package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class SelectedComment implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int boardNo;
	private int cmtNo;
	private Date selectedDate;
	private int point;
	
	public SelectedComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SelectedComment(int boardNo, int cmtNo, Date selectedDate, int point) {
		super();
		this.boardNo = boardNo;
		this.cmtNo = cmtNo;
		this.selectedDate = selectedDate;
		this.point = point;
	}

	@Override
	public String toString() {
		return "BoardSelected [boardNo=" + boardNo + ", cmtNo=" + cmtNo + ", selectedDate=" + selectedDate + ", point="
				+ point + "]";
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
	

}
