package board.model.vo;

import java.sql.Date;

public class DelBoardComment extends BoardComment {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date delRepDate;
	
	public DelBoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DelBoardComment(int cmtNo, String cmtWriter, String cmtWriterGrade, int boardNo, int cmtLevel, int cmtRefNo,
			String cmtContent, Date cmtRegDate, int recommend, int reported, Date delRepDate) {
		super(cmtNo, cmtWriter, cmtWriterGrade, boardNo, cmtLevel, cmtRefNo, cmtContent, cmtRegDate, recommend,
				reported);
		this.delRepDate = delRepDate;
	}




	public DelBoardComment(Date delRepDate) {
		super();
		this.delRepDate = delRepDate;
	}

	public Date getDelRepDate() {
		return delRepDate;
	}

	public void setDelRepDate(Date delRepDate) {
		this.delRepDate = delRepDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"delRepDate="+delRepDate;
	}
	
	
}
