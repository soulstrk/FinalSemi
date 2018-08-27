package jyi.vo;

public class OrderPointVo {
	 private int num; //인덱스번호       
	 private int o_num; //주문번호        
	 private int plus_point; //쌓인적립금    
	 private int minus_point; //사용한적립금
	public OrderPointVo() {
		super();
	}
	public OrderPointVo(int num, int o_num, int plus_point, int minus_point) {
		super();
		this.num = num;
		this.o_num = o_num;
		this.plus_point = plus_point;
		this.minus_point = minus_point;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getO_num() {
		return o_num;
	}
	public void setO_num(int o_num) {
		this.o_num = o_num;
	}
	public int getPlus_point() {
		return plus_point;
	}
	public void setPlus_point(int plus_point) {
		this.plus_point = plus_point;
	}
	public int getMinus_point() {
		return minus_point;
	}
	public void setMinus_point(int minus_point) {
		this.minus_point = minus_point;
	}
	@Override
	public String toString() {
		return "OrderPointVo [num=" + num + ", o_num=" + o_num + ", plus_point=" + plus_point + ", minus_point="
				+ minus_point + "]";
	}  
	
	
}
