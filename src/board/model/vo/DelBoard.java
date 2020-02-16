package board.model.vo;

import java.sql.Date;

public class DelBoard extends Board {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date delRepDate;
	
	public DelBoard() {
		super();
	}
	
	public DelBoard(int boardNo, String boardWriter, String boardWriterGrade, String boardCode, String boardOption,
			String boardTitle, String boardContent, String originalFileName, String renamedFileName, int readCnt,
			int selectedCnt, int reportedCnt, Date boardRegDate, String cmtSelect, int point, Date delRepDate) {
		super(boardNo, boardWriter, boardWriterGrade, boardCode, boardOption, boardTitle, boardContent,
				originalFileName, renamedFileName, readCnt, selectedCnt, reportedCnt, boardRegDate, cmtSelect, point);
		this.delRepDate = delRepDate;
	}


	public DelBoard(Date delRepDate) {
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
