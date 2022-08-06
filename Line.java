package ordering_system;

import java.io.Serializable;

public class Line implements Serializable{
	private int lineNo;
	private Item item;
	private int qty;
	private double totalPrice;
	public Line() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Line(int lineNo, Item item, int qty, double totalPrice) {
		super();
		this.lineNo = lineNo;
		this.item = item;
		this.qty = qty;
		this.totalPrice = totalPrice;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	

}
