package ljy.vo;

public class OrderInfoVo {
	private int oi_idxNum;
	private int oi_num;
	private int oi_p_num;
	private int oi_price;
	private int oi_amount;
	
	public OrderInfoVo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public OrderInfoVo(int oi_idxNum, int oi_num, int oi_p_num, int oi_price, int oi_amount) {
		super();
		this.oi_idxNum = oi_idxNum;
		this.oi_num = oi_num;
		this.oi_p_num = oi_p_num;
		this.oi_price = oi_price;
		this.oi_amount = oi_amount;
	}



	public int getOi_idxNum() {
		return oi_idxNum;
	}

	public void setOi_idxNum(int oi_idxNum) {
		this.oi_idxNum = oi_idxNum;
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
		return "OrderInfoVo [oi_idxNum=" + oi_idxNum + ", oi_num=" + oi_num + ", oi_p_num=" + oi_p_num + ", oi_price="
				+ oi_price + ", oi_amount=" + oi_amount + "]";
	}
	
	
}
