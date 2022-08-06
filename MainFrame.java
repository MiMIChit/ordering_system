package ordering_system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainFrame extends JFrame implements ActionListener{
	JMenuBar menuBar=new JMenuBar();
	JMenu fileMenu=new JMenu("File");
	JMenuItem orderMenuItem=new JMenuItem("Order Item");
	JMenuItem voucherMenuItem=new JMenuItem("Show Voucher");
	JMenuItem cancelOrderMenuItem=new JMenuItem("Cancel Order");
	JMenuItem exitMenuItem=new JMenuItem("Exit");
	Toolkit tk=Toolkit.getDefaultToolkit();
	int scrWidth,scrHeight,frmWidth=700,frmHeight=600;
	int x,y;
	OrderItemPanel orderPanel=new OrderItemPanel();
	
	public MainFrame()
	{
		scrWidth=(int)tk.getScreenSize().getWidth();
		scrHeight=(int)tk.getScreenSize().getHeight();
		x=scrWidth/2-frmWidth/2;
		y=scrHeight/2-frmHeight/2;
		setVisible(true);
		setLocation(x,y);
		setTitle("Ordering System");
		setSize(frmWidth,frmHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(orderMenuItem);
		fileMenu.add(voucherMenuItem);
		fileMenu.add(cancelOrderMenuItem);
		fileMenu.add(exitMenuItem);
		orderMenuItem.addActionListener(this);
		voucherMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this);
		cancelOrderMenuItem.addActionListener(this);
		//add(orderPanel);
	}
	public static void main(String[]args)
	{
		MainFrame mFrame=new MainFrame();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if(source==orderMenuItem)
		{
			this.getContentPane().removeAll();
			add(orderPanel);
			validate();
		}
		else if(source==voucherMenuItem)
		{
			ShowVoucherPanel showVoucherPanel=new ShowVoucherPanel();
			this.getContentPane().removeAll();
			add(showVoucherPanel);
			validate();
		}
		else if(source==cancelOrderMenuItem)
		{
			OrderCancelPanel orderCancelPanel=new OrderCancelPanel();
			this.getContentPane().removeAll();
			add(orderCancelPanel);
			validate();
		}
		else if(source==exitMenuItem)
		{
			System.exit(0);
		}
	}
	

}
