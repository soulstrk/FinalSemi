package jyi.vo;

public class ReviewVo {
	  private int comments_num; //글번호
	  private int p_num; //상품번호    
	  private String id; //아이디           
	  private String content; //상품후기내용
	  
	public ReviewVo() {
		super();
	}

	public ReviewVo(int comments_num, int p_num, String id, String content) {
		super();
		this.comments_num = comments_num;
		this.p_num = p_num;
		this.id = id;
		this.content = content;
	}

	public int getComments_num() {
		return comments_num;
	}

	public void setComments_num(int comments_num) {
		this.comments_num = comments_num;
	}

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
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
		return "ReviewVo [comments_num=" + comments_num + ", p_num=" + p_num + ", id=" + id + ", content=" + content
				+ "]";
	}

	
	
	
}
