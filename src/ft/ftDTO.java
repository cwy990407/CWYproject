package ft;

public class ftDTO { // 회원 정보를 저장하는 클래스

	private String number;
	private String name;
	private String age;
	private String tel;
	private String st;
	private String fi;
	private String fts;
	private String peo;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getFi() {
		return fi;
	}

	public void setFi(String fi) {
		this.fi = fi;
	}

	public String getFts() {
		return fts;
	}

	public void setFts(String fts) {
		this.fts = fts;
	}

	public String getPeo() {
		return peo;
	}

	public void setPeo(String peo) {
		this.peo = peo;
	}

	@Override
	public String toString() {	
		return "ftDTO -> number=" + number + ", name=" + name + ", age=" + age + ", tel=" + tel + ", st=" + st + ", fi="
				+ fi + ", fts=" + fts + ", peo=" + peo + "";
	}
}