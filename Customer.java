package ordering_system;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private String custID;
	private String custName;
	private String phoneNumber;
	private String address;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String custID, String custName, String phoneNumber, String address) {
		super();
		this.custID = custID;
		this.custName = custName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
