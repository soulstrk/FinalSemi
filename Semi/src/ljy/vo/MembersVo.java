package ljy.vo;

import java.util.Date;

///ȸ�� 
public class MembersVo {

 // ���̵� 
 private String id;

 // ��й�ȣ 
 private String pwd;

 // �̸� 
 private String name;

 // �ּ� 
 private String addr;

 // �ڵ�����ȣ 
 private String phone;

 // �̸��� 
 private String email;

 // ������ȣ 
 private Integer qNum;

 // �����亯 
 private String answer;

 // �����ڿ��� 
 private Integer adminok;

 // �����ȣ 
 private Integer postnum;

 // ������ 
 private Integer point;
 
 private String gender;
 
 private Date regdate;

 
 public MembersVo() {
	// TODO Auto-generated constructor stub
}
 
 
 public MembersVo(String id, String pwd, String name, String addr, String phone, String email, Integer qNum,
		 String answer, Integer adminok, Integer postnum, Integer point,String gender,Date regdate) {
	super();
	this.id = id;
	this.pwd = pwd;
	this.name = name;
	this.addr = addr;
	this.phone = phone;
	this.email = email;
	this.qNum = qNum;
	this.answer = answer;
	this.adminok = adminok;
	this.postnum = postnum;
	this.point = point;
	this.gender = gender;
	this.regdate = regdate;
}


public Date getRegdate() {
	return regdate;
}

public void setRegdate(Date regdate) {
	this.regdate = regdate;
}

public String getId() {
     return id;
 }

 public void setId(String id) {
     this.id = id;
 }

 public String getPwd() {
     return pwd;
 }

 public void setPwd(String pwd) {
     this.pwd = pwd;
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getAddr() {
     return addr;
 }

 public void setAddr(String addr) {
     this.addr = addr;
 }

 public String getPhone() {
     return phone;
 }

public String getGender() {
	return gender;
}


public void setGender(String gender) {
	this.gender = gender;
}


public void setPhone(String phone) {
     this.phone = phone;
 }

 public String getEmail() {
     return email;
 }

 public void setEmail(String email) {
     this.email = email;
 }

 public Integer getQNum() {
     return qNum;
 }

 public void setQNum(Integer qNum) {
     this.qNum = qNum;
 }

 public String getAnswer() {
     return answer;
 }

 public void setAnswer(String answer) {
     this.answer = answer;
 }

 public Integer getAdminok() {
     return adminok;
 }

 public void setAdminok(Integer adminok) {
     this.adminok = adminok;
 }

 public Integer getPostnum() {
     return postnum;
 }

 public void setPostnum(Integer postnum) {
     this.postnum = postnum;
 }

 public Integer getPoint() {
     return point;
 }

 public void setPoint(Integer point) {
     this.point = point;
 }

 // Members �� ����
 public void CopyData(MembersVo param)
 {
     this.id = param.getId();
     this.pwd = param.getPwd();
     this.name = param.getName();
     this.addr = param.getAddr();
     this.phone = param.getPhone();
     this.email = param.getEmail();
     this.qNum = param.getQNum();
     this.answer = param.getAnswer();
     this.adminok = param.getAdminok();
     this.postnum = param.getPostnum();
     this.point = param.getPoint();
 }


@Override
public String toString() {
	return "MembersVo [id=" + id + ", pwd=" + pwd + ", name=" + name + ", addr=" + addr + ", phone=" + phone
			+ ", email=" + email + ", qNum=" + qNum + ", answer=" + answer + ", adminok=" + adminok + ", postnum="
			+ postnum + ", point=" + point + ", gender=" + gender + ", regdate=" + regdate + "]";
}
 
 
}