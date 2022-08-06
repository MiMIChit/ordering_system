package ordering_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class OrderCancelPanel extends JPanel{
	
	//JPanel p1=new JPanel();
	//JPanel p2=new JPanel();
	JLabel lblVoucherID=new JLabel("Voucher ID");
	JTextField txtVoucherID=new JTextField(20);
	JButton btnConfirm=new JButton("Confirm");
	JButton btnClear=new JButton("Clear");
	JLabel lblVoucherDate=new JLabel("Voucher Date");
	JLabel lblCustName=new JLabel("Cust Name");
	JPanel p1=new JPanel();
	Font myFont=new Font("Acid Bath",Font.PLAIN,20);
	String[]columnNames= {"Item ID","Item Name","Price","Qty","Sub Total"};
	Object[][]tableValue=new Object[100][5];
	JTable table=new JTable(tableValue,columnNames);
	JScrollPane scrollPane=new JScrollPane(table);
	JLabel lblTotalAmt=new JLabel("Total Amount");
	JTextField txtTotalAmt=new JTextField(20);
	
	public OrderCancelPanel()
	{
		setSize(800,600);
		setLayout(null);
		lblVoucherID.setBounds(10, 10, 150, 30);
		txtVoucherID.setBounds(120, 10, 150, 30);
		lblVoucherID.setFont(myFont);
		txtVoucherID.setFont(myFont);
		txtVoucherID.setForeground(Color.gray);
		
		btnConfirm.setBounds(280,10,150,30);
		btnClear.setBounds(440,10,100,30);
		btnConfirm.setFont(myFont);
		btnClear.setFont(myFont);
		
		p1.setBounds(10,50,650,450);
		p1.setBorder(new LineBorder(Color.gray,5,true));
		p1.setLayout(null);
		lblVoucherDate.setBounds(480,10,150,30);
		lblVoucherDate.setForeground(Color.LIGHT_GRAY);
		lblVoucherDate.setFont(myFont);
		p1.add(lblVoucherDate);
		lblCustName.setBounds(480,50,150,30);
		lblCustName.setForeground(Color.LIGHT_GRAY);
		lblCustName.setFont(myFont);
		p1.add(lblCustName);
		scrollPane.setBounds(30,90,600,250);
		p1.add(scrollPane);
		
		lblTotalAmt.setBounds(300,380,100,30);
		txtTotalAmt.setBounds(410,380,150,30);
		txtTotalAmt.setEditable(false);
		p1.add(lblTotalAmt);
		p1.add(txtTotalAmt);
		
		add(lblVoucherID);
		add(txtVoucherID);
		add(btnConfirm);
		add(btnClear);
		add(p1);
		
		txtVoucherID.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VoucherList vList=OrderItemPanel.vList;
				Voucher v,custVoucher=null;
				int day,month,year;
				GregorianCalendar gc;
				ArrayList<Line>lineList;
				for(int i=0;i<vList.getNumOfVouchers();i++)
				{
					v=vList.getVoucher(i);
					if(txtVoucherID.getText().equalsIgnoreCase(v.getVoucherID()))
					{
						custVoucher=v;
					}
				}
				if(custVoucher==null)
					JOptionPane.showMessageDialog(null, "Please check your voucher id.");
				else
				{
					gc=custVoucher.getDate();
					day=gc.get(GregorianCalendar.DAY_OF_MONTH);
					month=gc.get(GregorianCalendar.MONTH);
					year=gc.get(GregorianCalendar.YEAR);
					lblVoucherDate.setText("Date:"+day+"/"+(month+1)+"/"+year);
					lblCustName.setText("Name:"+custVoucher.getCust().getCustName());
					lineList=custVoucher.getLineList();
					for(int i=0;i<lineList.size();i++)
					{
						Item item=lineList.get(i).getItem();
						table.setValueAt(item.getItemID(),i , 0);
						table.setValueAt(item.getItemName(), i, 1);
						table.setValueAt(item.getPrice(), i, 2);
						table.setValueAt(lineList.get(i).getQty(),i , 3);
						table.setValueAt(lineList.get(i).getTotalPrice(), i, 4);
					}
					txtTotalAmt.setText(""+custVoucher.getTotalAmount());
				}
			}
			
		});
		
	}
}