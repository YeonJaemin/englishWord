package entity.board;

public class BoardEntity {

	private int boardNum;
	private String uid;
	private String title;
	private String content;
	private String date;
	private int enterNum;
	
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getEnterNum() {
		return enterNum;
	}
	public void setEnterNum(int enterNum) {
		this.enterNum = enterNum;
	}
	
	
}
