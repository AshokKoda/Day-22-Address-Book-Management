package addressbookwithjson;

public class Contact {

	public String firstName;
	public String lastName;
	public String emailId;
	public String address;
	public long phoneNo;
	public int pinCode;
	
	public Contact(String firstName, String lastName, String emailId, String address, long phoneNo, int pinCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.address = address;
		this.phoneNo = phoneNo;
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", address="
				+ address + ", phoneNo=" + phoneNo + ", pinCode=" + pinCode + "]";
	}
	
	
	
}
