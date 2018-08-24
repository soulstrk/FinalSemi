package jyi.vo;

import java.sql.Date;

public class CustomerBoardVo {
	private int b_num;
	private String b_id;
	private Date b_date;
	private String b_content;
	private int b_ref;
	private String b_title;
	private int b_is;
	private int b_public_private;
	private int b_result;
	
	public CustomerBoardVo() {
		super();
	}
	public CustomerBoardVo(int b_num, String b_id, Date b_date, String b_content, int b_ref, String b_title, int b_is, int b_public_private,int b_result) {
		super();
		this.b_num = b_num;
		this.b_id = b_id;
		this.b_date = b_date;
		this.b_content = b_content;
		this.b_ref = b_ref;
		this.b_title = b_title;
		this.b_is = b_is;
		this.b_public_private=b_public_private;
		this.b_result=b_result;
	}
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getB_ref() {
		return b_ref;
	}
	public void setB_ref(int b_ref) {
		this.b_ref = b_ref;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public int getB_is() {
		return b_is;
	}
	public void setB_is(int b_is) {
		this.b_is = b_is;
	}
	public int getB_public_private() {
		return b_public_private;
	}
	public void setB_public_private(int b_public_private) {
		this.b_public_private = b_public_private;
	}
	public int getB_result() {
		return b_result;
	}
	public void setB_result(int b_result) {
		this.b_result = b_result;
	}
	@Override
	public String toString() {
		return "CustomerBoardVo [b_num=" + b_num + ", b_id=" + b_id + ", b_date=" + b_date + ", b_content=" + b_content
				+ ", b_ref=" + b_ref + ", b_title=" + b_title + ", b_is=" + b_is + ", b_public_private="
				+ b_public_private + ", b_result=" + b_result + "]";
	}
	
	
	
	
}
