package ordering_system;
import java.awt.*;
import java.util.*;
import javax.swing.*;
public class ShowVoucherPanel extends JPanel{
	JLabel lblShow=new JLabel("Vouchers Information");
	Object[][]twoD=new Object[20][6];
	String[]columns= {"No","Date","Voucher ID","Customer ID","Total"};
	JTable table=new JTable(twoD,columns);
	JScrollPane scroll=new JScrollPane(table);	
	Font myFont=new Font("Acid Bath",Font.BOLD,40);
	OrderItemPanel orderItemPanel=new OrderItemPanel();
	VoucherList vList=OrderItemPanel.vList;
	public ShowVoucherPanel()
	{
		setSize(800,600);
		setLayout(null);
		lblShow.setBounds(10,10,700,50);
		add(lblShow);
		lblShow.setFont(myFont);
		lblShow.setForeground(new Color(128,0,0));
		scroll.setBounds(10, 70, 650, 300);
		add(scroll);
		setDataOnTable();
	}
	public void setDataOnTable()
	{
		ArrayList<Voucher> voucherList=vList.getVoucherList();
		GregorianCalendar gc;
		String strDate;
		for(int i=0;i<voucherList.size();i++)
		{
			gc=voucherList.get(i).getDate();
			strDate=gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
			table.setValueAt(i+1, i, 0);
			table.setValueAt(strDate, i, 1);
			table.setValueAt(voucherList.get(i).getVoucherID(),i ,2);
			table.setValueAt(voucherList.get(i).getCust().getCustID(),i ,3);
			table.setValueAt(voucherList.get(i).getTotalAmount(),i ,4);
		}
	}
	

}
