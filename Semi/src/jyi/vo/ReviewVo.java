package jyi.vo;

public class ReviewVo {
	  private int adminOk;
	  private int product_num;    
	  private String id;            
	  private String content;
	public ReviewVo() {
		super();
	}
	public ReviewVo(int adminOk, int product_num, String id, String content) {
		super();
		this.adminOk = adminOk;
		this.product_num = product_num;
		this.id = id;
		this.content = content;
	}
	public int getAdminOk() {
		return adminOk;
	}
	public void setAdminOk(int adminOk) {
		this.adminOk = adminOk;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
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
		return "ReviewVo [adminOk=" + adminOk + ", product_num=" + product_num + ", id=" + id + ", content=" + content
				+ "]";
	}   
	  
	  
}
