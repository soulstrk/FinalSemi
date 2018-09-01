package Suhong;

public class Vojap {
	private int num;
	private String name;
	private String title;
	private String content;
	private int ref;
	private int lev;
	private int step;
	
	public Vojap() {}
	
	public Vojap(int num, String name, String title, String content, int ref, int lev, int step) {
		super();
		this.num = num;
		this.name = name;
		this.title = title;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "Vojap [num=" + num + ", name=" + name + ", title=" + title + ", content=" + content + ", ref=" + ref
				+ ", lev=" + lev + ", step=" + step + "]";
	}
	
}
