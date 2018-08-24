package jyi.vo;

public class ProductsVo {
	private int p_num;
	private String p_name;
	private int p_price;
	private int p_stock;
	private String p_kind;
	private int p_discountOk;
	private String p_artist;
	private String p_explain;
	private int p_best;
	private String p_image;
	private int p_discount_rate;
	
	public ProductsVo() {
		super();
	}
	public ProductsVo(int p_num, String p_name, int p_price, int p_stock, String p_kind, int p_discountOk,
			String p_artist, String p_explain, int p_best, String p_image, int p_discount_rate) {
		super();
		this.p_num = p_num;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_stock = p_stock;
		this.p_kind = p_kind;
		this.p_discountOk = p_discountOk;
		this.p_artist = p_artist;
		this.p_explain = p_explain;
		this.p_best = p_best;
		this.p_image = p_image;
		this.p_discount_rate=p_discount_rate;
	}
	public int getP_discount_rate() {
		return p_discount_rate;
	}
	public void setP_discount_rate(int p_discount_rate) {
		this.p_discount_rate = p_discount_rate;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_stock() {
		return p_stock;
	}
	public void setP_stock(int p_stock) {
		this.p_stock = p_stock;
	}
	public String getP_kind() {
		return p_kind;
	}
	public void setP_kind(String p_kind) {
		this.p_kind = p_kind;
	}
	public int getP_discountOk() {
		return p_discountOk;
	}
	public void setP_discountOk(int p_discountOk) {
		this.p_discountOk = p_discountOk;
	}
	public String getP_artist() {
		return p_artist;
	}
	public void setP_artist(String p_artist) {
		this.p_artist = p_artist;
	}
	public String getP_explain() {
		return p_explain;
	}
	public void setP_explain(String p_explain) {
		this.p_explain = p_explain;
	}
	public int getP_best() {
		return p_best;
	}
	public void setP_best(int p_best) {
		this.p_best = p_best;
	}
	public String getP_image() {
		return p_image;
	}
	public void setP_image(String p_image) {
		this.p_image = p_image;
	}
	@Override
	public String toString() {
		return "ProductsVo [p_num=" + p_num + ", p_name=" + p_name + ", p_price=" + p_price + ", p_stock=" + p_stock
				+ ", p_kind=" + p_kind + ", p_discountOk=" + p_discountOk + ", p_artist=" + p_artist + ", p_explain="
				+ p_explain + ", p_best=" + p_best + ", p_image=" + p_image + ", p_discount_rate=" + p_discount_rate
				+ "]";
	}
	
    
    
}
