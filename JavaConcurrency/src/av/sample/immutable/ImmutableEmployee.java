package av.sample.immutable;

import java.util.Date;

public final class ImmutableEmployee {

	private final int id;
	private final String name;
	private final Date joining; 
	private final Company company;
	
	
	public ImmutableEmployee(int id, String name, Date joining, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.joining = joining;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Date getJoining() {
		return new Date(joining.getTime());
	}

	public Company getCompany() {
		
		return new Company(company.getRegNo(), company.getName(), company.getType());
	}

	@Override
	public String toString() {
		return "ImmutableEmployee [id=" + id + ", name=" + name +", Joining="+joining+ ", company=" + company + "]";
	}
	
	
}
