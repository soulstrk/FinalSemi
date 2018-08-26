package jyi.vo;

import java.sql.Date;

public class CartVo {
    private int c_num; //장바구니번호      
    private String c_id; //아이디       
    private int c_p_num; //상품번호    
    private int c_amount; //수량  
    private Date c_date; //등록날짜
	public CartVo() {
		super();
	}
	public CartVo(int c_num, String c_id, int c_p_num, int c_amount, Date c_date) {
		super();
		this.c_num = c_num;
		this.c_id = c_id;
		this.c_p_num = c_p_num;
		this.c_amount = c_amount;
		this.c_date = c_date;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public int getC_p_num() {
		return c_p_num;
	}
	public void setC_p_num(int c_p_num) {
		this.c_p_num = c_p_num;
	}
	public int getC_amount() {
		return c_amount;
	}
	public void setC_amount(int c_amount) {
		this.c_amount = c_amount;
	}
	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}
	@Override
	public String toString() {
		return "CartVo [c_num=" + c_num + ", c_id=" + c_id + ", c_p_num=" + c_p_num + ", c_amount=" + c_amount
				+ ", c_date=" + c_date + "]";
	}   
    
    
    
}
