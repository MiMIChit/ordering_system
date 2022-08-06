package ordering_system;

import java.io.Serializable;

public class Item implements Serializable{
	private String itemID;
	private String itemName;
	private double price;
	
	public Item() {
		super();//call parent class constructor
		itemID="#####";
		itemName="#####";
		price=0.0;
	}

	public Item(String itemID, String itemName, double price) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public String toString()
	{
		return this.itemID+"\n"+this.itemName+"\n"+this.price;
	}
}
