package ljy.vo;

import java.sql.Date;

public class OrderVo1 {
	 // �ֹ���ȣ 
    private Integer oNum;

    // ���̵� 
    private String oId;

    // �ֹ����� 
    private Date oDate;

    // ������ּ� 
    private String oAddr;

    // ������� 
    private String oPaymethod;

    // ��۹�ȣ 
    private Integer oDeliverynum;

    // ����������ȣ 
    private String oPhone;

    // ��Ÿ���� 
    private String oMsg;

    // ���� 
    private int oAmount;

    // �����ݾ� 
    private Integer oPayment;

    // �ֹ����� 
    private Integer oState;
    
    public OrderVo1() {}

	public Integer getoNum() {
		return oNum;
	}

	public void setoNum(Integer oNum) {
		this.oNum = oNum;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	public String getoAddr() {
		return oAddr;
	}

	public void setoAddr(String oAddr) {
		this.oAddr = oAddr;
	}

	public String getoPaymethod() {
		return oPaymethod;
	}

	public void setoPaymethod(String oPaymethod) {
		this.oPaymethod = oPaymethod;
	}

	public Integer getoDeliverynum() {
		return oDeliverynum;
	}

	public void setoDeliverynum(Integer oDeliverynum) {
		this.oDeliverynum = oDeliverynum;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public String getoMsg() {
		return oMsg;
	}

	public void setoMsg(String oMsg) {
		this.oMsg = oMsg;
	}

	public int getoAmount() {
		return oAmount;
	}

	public void setoAmount(int oAmount) {
		this.oAmount = oAmount;
	}

	public Integer getoPayment() {
		return oPayment;
	}

	public void setoPayment(Integer oPayment) {
		this.oPayment = oPayment;
	}

	public Integer getoState() {
		return oState;
	}

	public void setoState(Integer oState) {
		this.oState = oState;
	}

	public OrderVo1(Integer oNum, String oId, Date oDate, String oAddr, String oPaymethod, Integer oDeliverynum,
			String oPhone, String oMsg, int oAmount, Integer oPayment, Integer oState) {
		super();
		this.oNum = oNum;
		this.oId = oId;
		this.oDate = oDate;
		this.oAddr = oAddr;
		this.oPaymethod = oPaymethod;
		this.oDeliverynum = oDeliverynum;
		this.oPhone = oPhone;
		this.oMsg = oMsg;
		this.oAmount = oAmount;
		this.oPayment = oPayment;
		this.oState = oState;
	}

	@Override
	public String toString() {
		return "OrderVo [oNum=" + oNum + ", oId=" + oId + ", oDate=" + oDate + ", oAddr=" + oAddr + ", oPaymethod="
				+ oPaymethod + ", oDeliverynum=" + oDeliverynum + ", oPhone=" + oPhone + ", oMsg=" + oMsg + ", oAmount="
				+ oAmount + ", oPayment=" + oPayment + ", oState=" + oState + "]";
	}
    
    
}
