package ordering_system;
import java.io.Serializable;
import java.util.*;
public class VoucherList implements Serializable{
	private ArrayList<Voucher> voucherList;

	public VoucherList() {
		super();
		voucherList=new ArrayList<Voucher>();
	}

	public ArrayList<Voucher> getVoucherList() {
		return voucherList;
	}

	public void setVoucherList(ArrayList<Voucher> voucherList) {
		this.voucherList = voucherList;
	}
	
	public Voucher searchVoucherByVoucherID(String voucherID)
	{
		Voucher voucherObj=null,temp;
		for(int i=0;i<voucherList.size();i++)
		{
			temp=voucherList.get(i);
			if(voucherID.equalsIgnoreCase(temp.getVoucherID()))
				voucherObj=temp;
		}
		return voucherObj;
	}
	public Voucher searchVoucherByCustomerID(String custID)
	{
		Voucher voucherObj=null,temp;
		Customer cust;
		for(int i=0;i<voucherList.size();i++)
		{
			temp=voucherList.get(i);
			cust=temp.getCust();
			if(custID.equalsIgnoreCase(cust.getCustID()))
				voucherObj=temp;
		}
		return voucherObj;
	}
	public void addVoucher(Voucher v)
	{
		voucherList.add(v);
	}
	public void removeVoucherByVoucherID(String voucherID)
	{
		Voucher voucherObj;
		voucherObj=searchVoucherByVoucherID(voucherID);
		voucherList.remove(voucherObj);
	}
	public void removeVoucherByCustomerID(String custID)
	{
		Voucher voucherObj;
		voucherObj=searchVoucherByCustomerID(custID);
		voucherList.remove(voucherObj);
	}
	public Voucher getVoucher(int i)
	{
		return voucherList.get(i);
	}
	public int getNumOfVouchers()
	{
		return voucherList.size();
	}
}
