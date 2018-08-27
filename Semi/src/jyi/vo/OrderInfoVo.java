package jyi.vo;

public class OrderInfoVo {
	private int oi_idxnum; //인덱스번호     
	private int oi_num; //주문번호    
	private int oi_p_num; //상품번호    
	private int oi_price; //상품가격   
	private int oi_amount; //수량
	public OrderInfoVo() {
		super();
	}
	public OrderInfoVo(int oi_idxnum, int oi_num, int oi_p_num, int oi_price, int oi_amount) {
		super();
		this.oi_idxnum = oi_idxnum;
		this.oi_num = oi_num;
		this.oi_p_num = oi_p_num;
		this.oi_price = oi_price;
		this.oi_amount = oi_amount;
	}
	public int getOi_idxnum() {
		return oi_idxnum;
	}
	public void setOi_idxnum(int oi_idxnum) {
		this.oi_idxnum = oi_idxnum;
	}
	public int getOi_num() {
		return oi_num;
	}
	public void setOi_num(int oi_num) {
		this.oi_num = oi_num;
	}
	public int getOi_p_num() {
		return oi_p_num;
	}
	public void setOi_p_num(int oi_p_num) {
		this.oi_p_num = oi_p_num;
	}
	public int getOi_price() {
		return oi_price;
	}
	public void setOi_price(int oi_price) {
		this.oi_price = oi_price;
	}
	public int getOi_amount() {
		return oi_amount;
	}
	public void setOi_amount(int oi_amount) {
		this.oi_amount = oi_amount;
	}
	@Override
	public String toString() {
		return "OrderInfoVo [oi_idxnum=" + oi_idxnum + ", oi_num=" + oi_num + ", oi_p_num=" + oi_p_num + ", oi_price="
				+ oi_price + ", oi_amount=" + oi_amount + "]";
	}    
	
	
	
	
	
}
