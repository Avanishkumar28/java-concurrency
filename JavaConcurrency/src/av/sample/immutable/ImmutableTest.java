package av.sample.immutable;

import java.util.Date;

public class ImmutableTest {

	public static void main(String[] args) {
		Company company = new Company(101, "Fujitsu", "IT");
		Date joining = new Date();
		ImmutableEmployee emp = new ImmutableEmployee(1, "AV", joining, company);
		System.out.println(emp);
		Company empCompany = emp.getCompany();
		empCompany.setRegNo(1002);
		empCompany.setName("ST");
		empCompany.setType("Electronics");
		
		Date empJoining = emp.getJoining();
		empJoining.setTime(124500);
		
		System.out.println("After Change: "+emp);
		System.out.println("Chenged Company Obj: "+empCompany);
		System.out.println("Chenged Joining: "+empJoining);

	}

}
