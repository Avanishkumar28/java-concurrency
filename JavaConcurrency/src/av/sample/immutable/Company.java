package av.sample.immutable;

public class Company {

	private int regNo;
	private String name;
	private String type;
	
	public Company(int regNo, String name, String type) {
		super();
		this.regNo = regNo;
		this.name = name;
		this.type = type;
	}

	public int getRegNo() {
		return regNo;
	}


	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Company [Reg. No=" + regNo + ", Name=" + name + ", Type=" + type + "]";
	}
	
	
}
