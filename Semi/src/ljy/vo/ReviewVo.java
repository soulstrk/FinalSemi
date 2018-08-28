package ljy.vo;

public class ReviewVo {
	private int commentsNum;
	private int pNum;
	private String id;
	private String content;
	
	public ReviewVo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ReviewVo(int commentsNum, int pNum, String id, String content) {
		super();
		this.commentsNum = commentsNum;
		this.pNum = pNum;
		this.id = id;
		this.content = content;
	}



	public int getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ReviewVo [commentsNum=" + commentsNum + ", pNum=" + pNum + ", id=" + id + ", content=" + content + "]";
	}
	
	
}
