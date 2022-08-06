package ordering_system;

import java.util.*;
import java.io.*;
public class MainProgram {
	static Scanner input=new Scanner(System.in);
	static VoucherList voucherList;
	static ObjectOutputStream ooStream;//write 
	static ObjectInputStream inputStream;//read
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int userOption,showOption;
		String voucherID,cusID;
		userOption=getOption();
		loadVouchers();
		
		while(userOption!=0)
		{
			if(userOption==1)
			{
				Voucher voucherObj=getVoucherInfo();
				voucherList.addVoucher(voucherObj);
			}
			else if(userOption==2)
			{
				Voucher voucherObj;
				System.out.println("Show option:");
				System.out.println("Please enter an option\n"
						+ "1:search by voucherID\n"
						+ "2:search by customerID\n");
				showOption=input.nextInt();
				if(showOption==1)
				{
					System.out.println("Please enter voucherID:");
					voucherID=input.next();
					voucherObj=voucherList.searchVoucherByVoucherID(voucherID);
					if(voucherObj!=null)
						showVoucher(voucherObj);
				}
				else if(showOption==2)
				{
					System.out.println("Please enter customerID:");
					cusID=input.next();
					voucherObj=voucherList.searchVoucherByCustomerID(cusID);
					if(voucherObj!=null)
						showVoucher(voucherObj);
				}
				else
				{
					System.out.println("Your choice is invalid.");
				}
			}
			else if(userOption==3)
			{
				System.out.println("Cancel Order:");
				System.out.println("Please enter an option\n"
						+ "1:search by voucherID\n"
						+ "2:search by customerID\n");
				showOption=input.nextInt();
				if(showOption==1)
				{
					System.out.println("Please enter voucherID:");
					voucherID=input.next();
					voucherList.removeVoucherByVoucherID(voucherID);
				}
				else if(showOption==2)
				{
					System.out.println("Please enter customerID:");
					cusID=input.next();
					voucherList.removeVoucherByCustomerID(cusID);
				}
				else
				{
					System.out.println("Your choice is invalid.");
				}
			}
			userOption=getOption();
		}
		saveVouchers();
		System.out.println("Thanks for ordering");
	}
	public static void saveVouchers()
	{
		try 
		{
			ooStream=new ObjectOutputStream(new FileOutputStream("Vouchers.obj"));
			ooStream.writeObject(voucherList);
			ooStream.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File Not Found Exception!!!");
		} 
		catch (IOException e)
		{
			System.out.println("IO Exception!!!");
		}
		
	}
	public static void loadVouchers()
	{
		try 
		{
			inputStream=new ObjectInputStream(new FileInputStream("Vouchers.obj"));
			voucherList=(VoucherList)inputStream.readObject();
			inputStream.close();
		} 
		catch (FileNotFoundException e) 
		{
			voucherList=new VoucherList();
		} 
		catch (IOException e) 
		{
			System.out.println("IO Exception!!!");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class Not Found Exception!!!");
		}
		
	}
	public static void showVoucher(Voucher v)
	{
		GregorianCalendar gc;
		ArrayList<Line> lineList;
		System.out.println("Voucher Info:");
		System.out.println("ID:"+v.getVoucherID());
		gc=v.getDate();
		int day=gc.get(GregorianCalendar.DAY_OF_MONTH);
		int month=gc.get(GregorianCalendar.MONTH);
		int year=gc.get(GregorianCalendar.YEAR);
		System.out.printf("Date:"+day+"/"+month+"/"+year);
		System.out.println("Customer Name:"+v.getCust().getCustName());
		lineList=v.getLineList();
		System.out.printf("%15s%15s%15s%15s%15s%15s\n","No","Item ID","Item Name","Item Price","Quantity","Total Amount");
		for(int i=0;i<lineList.size();i++)
		{
			System.out.printf("%15d",lineList.get(i).getLineNo());
			System.out.printf("%15s",lineList.get(i).getItem().getItemID());
			System.out.printf("%15s",lineList.get(i).getItem().getItemName());
			System.out.printf("%15.2f",lineList.get(i).getItem().getPrice());
			System.out.printf("%15d",lineList.get(i).getQty());
			System.out.printf("%15.2f\n",lineList.get(i).getTotalPrice());
		}
		System.out.printf("Total Amount:%20.2f\n",v.getTotalAmount());
		
		
	}
	public static Customer getCustomerInfo()
	{
		String custID,name,phone,address;
		System.out.println("Please enter customer ID:");
		custID=input.next();
		System.out.println("Please enter customer name:");
		name=input.next();
		System.out.println("Please enter customer phone:");
		phone=input.next();
		System.out.println("Please enter customer address:");
		address=input.next();
		Customer cust=new Customer(custID,name,phone,address);
		return cust;
	}
	public static Item getItemInfo()
	{
		String itemID,itemName;
		double itemPrice;
		System.out.println("Please enter item ID:");
		itemID=input.next();
		System.out.println("Please enter item name:");
		itemName=input.next();
		System.out.println("Please enter item price:");
		itemPrice=input.nextDouble();
		Item item=new Item(itemID,itemName,itemPrice);
		return item;
	}
	public static Line getLineInfo()
	{
		int lineNo,qty;
		double totalPrice;
		Item item;
		System.out.println("Please enter line number:");
		lineNo=input.nextInt();
		item=getItemInfo();
		System.out.println("Please enter item quantity:");
		qty=input.nextInt();
		totalPrice=item.getPrice()*qty;
		Line line=new Line(lineNo,item,qty,totalPrice);
		return line;
	}
	public static Voucher getVoucherInfo()
	{
		String voucherID,date,option;
		String[]ary;
		ArrayList<Line>lineList=new ArrayList<Line>();
		Customer cus;
		int day,month,year;
		System.out.println("Please enter voucher ID:");
		voucherID=input.next();
		System.out.println("Please enter date dd/mm/yyyy");
		date=input.next();
		ary=date.split("/");
		day=Integer.parseInt(ary[0]);
		month=Integer.parseInt(ary[1]);
		year=Integer.parseInt(ary[2]);
		GregorianCalendar voucherDate=new GregorianCalendar(year,month,day);
		cus=getCustomerInfo();
		do {
			lineList.add(getLineInfo());
			System.out.println("Do you want to order next item?\nPlease enter yes or no.");
			option=input.next();
		}while(option.equalsIgnoreCase("yes"));
		Voucher v=new Voucher(voucherID,voucherDate,lineList,cus);
		return v;
	}
	public static int getOption()
	{
		System.out.println("Please enter your option\n"
				+ "1:order item\n"
				+ "2:show voucher\n"
				+ "3:cancel order\n"
				+ "0:exit");
		return input.nextInt();
	}
}
