package ordering_system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.*;
public class OrderItemPanel extends JPanel{
	
	JLabel lblDate=new JLabel("Date");
	JLabel lblVoucherID=new JLabel("Voucher ID");
	JLabel lblCustID=new JLabel("Customer ID");
	JLabel lblCustName=new JLabel("Customer Name");
	JLabel lblPhone=new JLabel("Phone");
	JLabel lblAddress=new JLabel("Address");
	
	JTextField txtDate=new JTextField(20);
	JTextField txtVoucherID=new JTextField(20);
	JTextField txtCustID=new JTextField(10);
	JTextField txtCustName=new JTextField(10);
	JTextField txtPhone=new JTextField(10);
	JTextField txtAddress=new JTextField(10);
	Font myFont=new Font("Acid Bath",Font.PLAIN,20);
	JPanel custPanel=new JPanel();
	
	Object[][]twoD=new Object[20][6];
	String[]columns= {"No","Item ID","Item Name","Item Price","Qty","Sub Total"};
	JTable table=new JTable(twoD,columns);
	JScrollPane scroll=new JScrollPane(table);	
	
	JButton btnOrder=new JButton("Order Now");
	JButton btnClear=new JButton("Clear");
	JButton btnShow=new JButton("Show Today Vouchers");
	CreateItemList cList=new CreateItemList();
	static VoucherList vList=new VoucherList();
	
	public OrderItemPanel()
	{
		setSize(800,600);
		setLayout(null);
		lblDate.setBounds(350,20,50,30);
		add(lblDate);
		lblDate.setFont(myFont);
		
		txtDate.setForeground(Color.gray);
		txtDate.setBounds(410,20,150,30);
		add(txtDate);
		txtDate.setFont(myFont);
		
		lblVoucherID.setBounds(20,70,150,30);
		add(lblVoucherID);
		lblVoucherID.setFont(myFont);
		
		txtVoucherID.setForeground(Color.gray);
		txtVoucherID.setBounds(180,70,150,30);
		add(txtVoucherID);
		txtVoucherID.setFont(myFont);
		
		
		lblCustID.setFont(myFont);
		txtCustID.setFont(myFont);
		lblCustName.setFont(myFont);
		txtCustName.setFont(myFont);
		lblPhone.setFont(myFont);
		txtPhone.setFont(myFont);
		lblAddress.setFont(myFont);
		txtAddress.setFont(myFont);
		
		txtCustID.setForeground(Color.gray);
		txtCustName.setForeground(Color.gray);
		txtPhone.setForeground(Color.gray);
		txtAddress.setForeground(Color.gray);
		custPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		custPanel.add(lblCustID);
		custPanel.add(txtCustID);
		custPanel.add(lblCustName);
		custPanel.add(txtCustName);
		custPanel.add(lblPhone);
		custPanel.add(txtPhone);
		custPanel.add(lblAddress);
		custPanel.add(txtAddress);
		
		custPanel.setBorder(new TitledBorder("Cust Info"));
		custPanel.setBounds(20,110,650,100);
		add(custPanel);
		
		scroll.setBounds(20,220,650,200);
		add(scroll);
		
		btnOrder.setBounds(20,450,150,30);
		add(btnOrder);
		btnOrder.setFont(myFont);
		btnOrder.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Line>lineList=new ArrayList<Line>();
				Customer cust=new Customer(txtCustID.getText(),txtCustName.getText(),txtPhone.getText(),txtAddress.getText());
				String dateString=txtDate.getText();
				StringTokenizer tokenizer=new StringTokenizer(dateString,"/");
				int day=Integer.parseInt(tokenizer.nextToken()),month=Integer.parseInt(tokenizer.nextToken()),year=Integer.parseInt(tokenizer.nextToken());
				GregorianCalendar vDate=new GregorianCalendar(year,month-1,day);
				for(int i=0;i<twoD.length;i++)
				{
					if(table.getValueAt(i, 0)!=null)
					{
						Item item=new Item(table.getValueAt(i, 1).toString(),table.getValueAt(i, 2).toString(),Double.parseDouble(table.getValueAt(i, 3).toString()));
						int lineNo=Integer.parseInt(table.getValueAt(i, 0).toString());
						int qty=Integer.parseInt(table.getValueAt(i, 4).toString());
						double total=Double.parseDouble(table.getValueAt(i, 5).toString());
						Line line=new Line(lineNo,item,qty,total);
						lineList.add(line);
					}
				}
				Voucher todayVoucher=new Voucher(txtVoucherID.getText(),vDate,lineList,cust);
				vList.addVoucher(todayVoucher);
			}
			
		});
		
		btnShow.setBounds(180,450,250,30);
		btnShow.setFont(myFont);
		add(btnShow);
		btnShow.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str="Vochers Information\n";				
				for(int i=0;i<vList.getNumOfVouchers();i++)
				{
					Voucher v=vList.getVoucher(i);
					str+=String.format("%10s%20.2f\n", v.getVoucherID(),v.getTotalAmount());
				}
				JOptionPane.showMessageDialog(null,str );
			}
			
		});
		
		btnClear.setBounds(440,450,150,30);
		add(btnClear);
		btnClear.setFont(myFont);
		btnClear.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtDate.setText("");
				txtVoucherID.setText("");
				txtCustID.setText("");
				txtCustName.setText("");
				txtPhone.setText("");
				txtAddress.setText("");
				for(int i=0;i<twoD.length;i++)
				{
					for(int j=0;j<twoD[i].length;j++)
					{
						table.setValueAt(null, i, j);
					}
				}
				
			}
		
		});
		
		table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				//JOptionPane.showMessageDialog(null,"Key Event in Table occurred");
				//JOptionPane.showMessageDialog(null, "Hi");
				// TODO Auto-generated method stub
				Item item=null;
					//if(table.getValueAt(table.getSelectedRow(), 1).toString().length()!=0)
				if(table.getValueAt(table.getSelectedRow(),1)!=null)	
				{
					item=cList.getItem(table.getValueAt(table.getSelectedRow(), 1).toString());
					if(item!=null)
					{
						//System.out.println("AAA");
						//JOptionPane.showMessageDialog(null, "Item Name:"+item.getItemName());
						table.setValueAt(item.getItemName(), table.getSelectedRow(), 2);
						table.setValueAt(item.getPrice(), table.getSelectedRow(), 3);
					}
					else
					{
						System.out.println(item);
						System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());
					}
				}
				if(table.getValueAt(table.getSelectedRow(),4)!=null)	
				{
					if(item!=null)
					{
						double subTotal=item.getPrice()*Integer.parseInt(table.getValueAt(table.getSelectedRow(),4).toString());
						table.setValueAt(subTotal,table.getSelectedRow(), 5);
					}
				}
					
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	public VoucherList getVoucherList()
	{
		return vList;
	}
}
