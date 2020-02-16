package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int boardNo;
	private String boardWriter;
	private String boardWriterGrade;
	private String boardCode;
	private String boardOption;
	private String boardTitle;
	private String boardContent;
	private String originalFileName;
	private String renamedFileName;
	private int readCnt;
	private int selectedCnt;
	private int reportedCnt;
	private Date boardRegDate;
	private String cmtSelect;
	private int point;
	
	public Board() {
		super();
	}
	
	//board for userInfo
	public Board(int boardNo, String boardCode, String boardOption, String boardTitle,Date boardRegDate) {
		super();
		this.boardNo = boardNo;
		this.boardCode = boardCode;
		this.boardOption = boardOption;
		this.boardTitle = boardTitle;
		this.boardRegDate = boardRegDate;
	}
	//board in point
	public Board(int boardNo, String boardWriter, String boardWriterGrade, String boardCode, String boardOption,
			String boardTitle, String boardContent, String originalFileName, String renamedFileName, int readCnt,
			int selectedCnt, int reportedCnt, Date boardRegDate, String cmtSelect, int point) {
		super();
		this.boardNo = boardNo;
		this.boardWriter = boardWriter;
		this.boardWriterGrade = boardWriterGrade;
		this.boardCode = boardCode;
		this.boardOption = boardOption;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
		this.readCnt = readCnt;
		this.selectedCnt = selectedCnt;
		this.reportedCnt = reportedCnt;
		this.boardRegDate = boardRegDate;
		this.cmtSelect = cmtSelect;
		this.point = point;
	}
	//board not point
	public Board(int boardNo, String boardWriter, String boardWriterGrade, String boardCode, String boardOption,
			String boardTitle, String boardContent, String originalFileName, String renamedFileName, int readCnt,
			int selectedCnt, int reportedCnt, Date boardRegDate, String cmtSelect) {
		super();
		this.boardNo = boardNo;
		this.boardWriter = boardWriter;
		this.boardWriterGrade = boardWriterGrade;
		this.boardCode = boardCode;
		this.boardOption = boardOption;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
		this.readCnt = readCnt;
		this.selectedCnt = selectedCnt;
		this.reportedCnt = reportedCnt;
		this.boardRegDate = boardRegDate;
		this.cmtSelect = cmtSelect;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardWriter=" + boardWriter + ", boardWriterGrade=" + boardWriterGrade
				+ ", boardCode=" + boardCode + ", boardOption=" + boardOption + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", originalFileName=" + originalFileName + ", renamedFileName="
				+ renamedFileName + ", readCnt=" + readCnt + ", selectedCnt=" + selectedCnt + ", reportedCnt="
				+ reportedCnt + ", boardRegDate=" + boardRegDate + ", cmtSelect=" + cmtSelect + ", point=" + point
				+ "]";
	}




	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardWriterGrade() {
		return boardWriterGrade;
	}

	public void setBoardWriterGrade(String boardWriterGrade) {
		this.boardWriterGrade = boardWriterGrade;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getBoardOption() {
		return boardOption;
	}

	public void setBoardOption(String boardOption) {
		this.boardOption = boardOption;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenamedFileName() {
		return renamedFileName;
	}

	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getSelectedCnt() {
		return selectedCnt;
	}

	public void setSelectedCnt(int selectedCnt) {
		this.selectedCnt = selectedCnt;
	}

	public int getReportedCnt() {
		return reportedCnt;
	}

	public void setReportedCnt(int reportedCnt) {
		this.reportedCnt = reportedCnt;
	}

	public Date getBoardRegDate() {
		return boardRegDate;
	}

	public void setBoardRegDate(Date boardRegDate) {
		this.boardRegDate = boardRegDate;
	}

	public String getCmtSelect() {
		return cmtSelect;
	}

	public void setCmtSelect(String cmtSelect) {
		this.cmtSelect = cmtSelect;
	}

	
}
