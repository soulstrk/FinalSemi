package jyi.vo;

public class ViewsVo {
	private int v_num; //인덱스
	private String id; //아이디
	private int p_num; //상품번호
	private int v_snum; //스텝번호
	public ViewsVo() {
		super();
	}
	public ViewsVo(int v_num, String id, int p_num, int v_snum) {
		super();
		this.v_num = v_num;
		this.id = id;
		this.p_num = p_num;
		this.v_snum = v_snum;
	}
	public int getV_num() {
		return v_num;
	}
	public void setV_num(int v_num) {
		this.v_num = v_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getV_snum() {
		return v_snum;
	}
	public void setV_snum(int v_snum) {
		this.v_snum = v_snum;
	}
	@Override
	public String toString() {
		return "ViewVo [v_num=" + v_num + ", id=" + id + ", p_num=" + p_num + ", v_snum=" + v_snum + "]";
	}
	
	
	
}
