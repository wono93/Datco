package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardComment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cmtNo;
	private String cmtWriter;
	private String cmtWriterGrade;
	private int boardNo;
	private int cmtLevel;
	private int cmtRefNo;
	private String cmtContent;
	private Date cmtRegDate;
	private int Recommend;
	private int reported;
	private String boardCode;
	private String boardTitle;
	
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardComment(int cmtNo, String cmtWriter, String cmtWriterGrade, int boardNo, int cmtLevel, int cmtRefNo,
			String cmtContent, Date cmtRegDate, int recommend, int reported) {
		super();
		this.cmtNo = cmtNo;
		this.cmtWriter = cmtWriter;
		this.cmtWriterGrade = cmtWriterGrade;
		this.boardNo = boardNo;
		this.cmtLevel = cmtLevel;
		this.cmtRefNo = cmtRefNo;
		this.cmtContent = cmtContent;
		this.cmtRegDate = cmtRegDate;
		Recommend = recommend;
		this.reported = reported;
	}




	public BoardComment(int cmtNo, String cmtWriter, String cmtWriterGrade, int boardNo, int cmtLevel, int cmtRefNo,
			String cmtContent, Date cmtRegDate, int recommend, int reported, String boardCode, String boardTitle) {
		super();
		this.cmtNo = cmtNo;
		this.cmtWriter = cmtWriter;
		this.cmtWriterGrade = cmtWriterGrade;
		this.boardNo = boardNo;
		this.cmtLevel = cmtLevel;
		this.cmtRefNo = cmtRefNo;
		this.cmtContent = cmtContent;
		this.cmtRegDate = cmtRegDate;
		Recommend = recommend;
		this.reported = reported;
		this.boardCode = boardCode;
		this.boardTitle = boardTitle;
	}

	@Override
	public String toString() {
		return "BoardComment [cmtNo=" + cmtNo + ", cmtWriter=" + cmtWriter + ", cmtWriterGrade=" + cmtWriterGrade
				+ ", boardNo=" + boardNo + ", cmtLevel=" + cmtLevel + ", cmtRefNo=" + cmtRefNo + ", cmtContent="
				+ cmtContent + ", cmtRegDate=" + cmtRegDate + ", Recommend=" + Recommend + ", reported=" + reported
				+ ", boardCode=" + boardCode + ", boardTitle=" + boardTitle + "]";
	}

	public String getCmtWriterGrade() {
		return cmtWriterGrade;
	}

	public void setCmtWriterGrade(String cmtWriterGrade) {
		this.cmtWriterGrade = cmtWriterGrade;
	}

	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}
	public String getCmtWriter() {
		return cmtWriter;
	}
	public void setCmtWriter(String cmtWriter) {
		this.cmtWriter = cmtWriter;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCmtLevel() {
		return cmtLevel;
	}
	public void setCmtLevel(int cmtLevel) {
		this.cmtLevel = cmtLevel;
	}
	public int getCmtRefNo() {
		return cmtRefNo;
	}
	public void setCmtRefNo(int cmtRefNo) {
		this.cmtRefNo = cmtRefNo;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public Date getCmtRegDate() {
		return cmtRegDate;
	}
	public void setCmtRegDate(Date cmtRegDate) {
		this.cmtRegDate = cmtRegDate;
	}
	public int getRecommend() {
		return Recommend;
	}
	public void setRecommend(int recommend) {
		Recommend = recommend;
	}
	public int getReported() {
		return reported;
	}
	public void setReported(int reported) {
		this.reported = reported;
	}

	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	
}
