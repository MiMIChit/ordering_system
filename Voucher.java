package ordering_system;

import java.io.Serializable;
import java.util.*;

public class Voucher implements Serializable{
	
	private String voucherID;
	private GregorianCalendar date;
	private ArrayList<Line> lineList;
	private Customer cust;
	private double totalAmount;
	
	public Voucher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voucher(String voucherID, GregorianCalendar date, ArrayList<Line> lineList, Customer cust) {
		super();
		this.voucherID = voucherID;
		this.date = date;
		this.lineList = lineList;
		this.cust = cust;
		calTotalAmount();
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public String getVoucherID() {
		return voucherID;
	}


	public void setVoucherID(String voucherID) {
		this.voucherID = voucherID;
	}


	public GregorianCalendar getDate() {
		return date;
	}


	public void setDate(GregorianCalendar date) {
		this.date = date;
	}


	public ArrayList<Line> getLineList() {
		return lineList;
	}


	public void setLineList(ArrayList<Line> lineList) {
		this.lineList = lineList;
	}	
	
	public void calTotalAmount()
	{
		
		for(int i=0;i<lineList.size();i++)
		{
			Line line=lineList.get(i);
			this.totalAmount+=line.getTotalPrice();
		}
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
