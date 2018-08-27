package jyi.vo;

import java.sql.Date;

public class OrderVo {
	  private int o_num; //주문번호
	  private String o_id; //아이디
	  private Date o_date; //주문일자
	  private String o_addr; //배송지주소
	  private String o_paymethod; //결제방법
	  private int o_deliverynum; // 배송번호
	  private String o_phone; //수령인번호
	  private String o_msg; //기타사항
	  private int o_payment; //결제금액
	  private int o_state; //주문상태
	  private int o_amount; //총수량
	  
	public OrderVo() {
		super();
	}
	public OrderVo(int o_num, String o_id, Date o_date, String o_addr, String o_paymethod, int o_deliverynum,
			String o_phone, String o_msg, int o_payment, int o_state, int o_amount) {
		super();
		this.o_num = o_num;
		this.o_id = o_id;
		this.o_date = o_date;
		this.o_addr = o_addr;
		this.o_paymethod = o_paymethod;
		this.o_deliverynum = o_deliverynum;
		this.o_phone = o_phone;
		this.o_msg = o_msg;
		this.o_payment = o_payment;
		this.o_state = o_state;
		this.o_amount = o_amount;
	}
	public int getO_num() {
		return o_num;
	}
	public void setO_num(int o_num) {
		this.o_num = o_num;
	}
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public Date getO_date() {
		return o_date;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}
	public String getO_addr() {
		return o_addr;
	}
	public void setO_addr(String o_addr) {
		this.o_addr = o_addr;
	}
	public String getO_paymethod() {
		return o_paymethod;
	}
	public void setO_paymethod(String o_paymethod) {
		this.o_paymethod = o_paymethod;
	}
	public int getO_deliverynum() {
		return o_deliverynum;
	}
	public void setO_deliverynum(int o_deliverynum) {
		this.o_deliverynum = o_deliverynum;
	}
	public String getO_phone() {
		return o_phone;
	}
	public void setO_phone(String o_phone) {
		this.o_phone = o_phone;
	}
	public String getO_msg() {
		return o_msg;
	}
	public void setO_msg(String o_msg) {
		this.o_msg = o_msg;
	}
	public int getO_payment() {
		return o_payment;
	}
	public void setO_payment(int o_payment) {
		this.o_payment = o_payment;
	}
	public int getO_state() {
		return o_state;
	}
	public void setO_state(int o_state) {
		this.o_state = o_state;
	}
	public int getO_amount() {
		return o_amount;
	}
	public void setO_amount(int o_amount) {
		this.o_amount = o_amount;
	}
	@Override
	public String toString() {
		return "OrderVo [o_num=" + o_num + ", o_id=" + o_id + ", o_date=" + o_date + ", o_addr=" + o_addr
				+ ", o_paymethod=" + o_paymethod + ", o_deliverynum=" + o_deliverynum + ", o_phone=" + o_phone
				+ ", o_msg=" + o_msg + ", o_payment=" + o_payment + ", o_state=" + o_state + ", o_amount=" + o_amount
				+ "]";
	}
}
