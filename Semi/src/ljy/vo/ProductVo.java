package ljy.vo;

public class ProductVo {
	// ��ǰ��ȣ 
    private Integer pNum;

    // ��ǰ�̸� 
    private String pName;

    // ��ǰ���� 
    private Integer pPrice;

    // ��ǰ��� 
    private Integer pStock;

    // ��ǰ���� 
    private String pKind;

    // ���ο��� 
    private Integer pDiscountok;

    // �۰� 
    private String pArtist;

    // ��ǰ���� 
    private String pExplain;

    // best��ǰ���� 
    private Integer pBest;

    // ��ǰ�̹��� 
    private String pImage;

    // ������ 
    private Integer pDiscountRate;
    
    public ProductVo() {
		// TODO Auto-generated constructor stub
	}
    
    

	public ProductVo(Integer pNum, String pName, Integer pPrice, Integer pStock, String pKind, Integer pDiscountok,
			String pArtist, String pExplain, Integer pBest, String pImage, Integer pDiscountRate) {
		super();
		this.pNum = pNum;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pStock = pStock;
		this.pKind = pKind;
		this.pDiscountok = pDiscountok;
		this.pArtist = pArtist;
		this.pExplain = pExplain;
		this.pBest = pBest;
		this.pImage = pImage;
		this.pDiscountRate = pDiscountRate;
	}


	public Integer getpNum() {
		return pNum;
	}

	public void setpNum(Integer pNum) {
		this.pNum = pNum;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getpPrice() {
		return pPrice;
	}

	public void setpPrice(Integer pPrice) {
		this.pPrice = pPrice;
	}

	public Integer getpStock() {
		return pStock;
	}

	public void setpStock(Integer pStock) {
		this.pStock = pStock;
	}

	public String getpKind() {
		return pKind;
	}

	public void setpKind(String pKind) {
		this.pKind = pKind;
	}

	public Integer getpDiscountok() {
		return pDiscountok;
	}

	public void setpDiscountok(Integer pDiscountok) {
		this.pDiscountok = pDiscountok;
	}

	public String getpArtist() {
		return pArtist;
	}

	public void setpArtist(String pArtist) {
		this.pArtist = pArtist;
	}

	public String getpExplain() {
		return pExplain;
	}

	public void setpExplain(String pExplain) {
		this.pExplain = pExplain;
	}

	public Integer getpBest() {
		return pBest;
	}

	public void setpBest(Integer pBest) {
		this.pBest = pBest;
	}

	public String getpImage() {
		return pImage;
	}

	public void setpImage(String pImage) {
		this.pImage = pImage;
	}

	public Integer getpDiscountRate() {
		return pDiscountRate;
	}

	public void setpDiscountRate(Integer pDiscountRate) {
		this.pDiscountRate = pDiscountRate;
	}



	@Override
	public String toString() {
		return "ProductVo [pNum=" + pNum + ", pName=" + pName + ", pPrice=" + pPrice + ", pStock=" + pStock + ", pKind="
				+ pKind + ", pDiscountok=" + pDiscountok + ", pArtist=" + pArtist + ", pExplain=" + pExplain
				+ ", pBest=" + pBest + ", pImage=" + pImage + ", pDiscountRate=" + pDiscountRate + "]";
	}
    
    
}
