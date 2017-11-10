import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ReportBill extends JInternalFrame implements ActionListener,ItemListener
{
	 JLabel  l1,l2,l3,l4,l5,l6;
 	JTextField  t1,t2;
 	JButton b1,b2,b3,b4;
	JPanel p1,p2,p3,p4,p5;
	JComboBox c0,c1,c2,c3,c4,c5;
 
	Connection con;
	JScrollPane sp1;
	JTable table;



public ReportBill()
 {
        super("CUSTOMER BILL REPORT");
  	setSize(910,650);
	setLayout(null);

	Font f = new Font("One Stroke Script LET",Font.ITALIC,18);

	p1 = new JPanel(null);
	p1.setBackground(new Color(159,235,235));
	p1.setBounds(20,20,850,40);
	p1.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p1);


  	l1=new JLabel("Customer Bill Report");
	l1.setBounds(400,10,200,45);
	l1.setFont(f);
	p1.add(l1);
 
	 p2 = new JPanel(null);
	p2.setBackground(new Color(244,244,90));
	p2.setBounds(20,70,850,60);
	p2.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p2);
       

        l2=new JLabel("Select");
	l2.setBounds(20,20,80,25);
	l2.setFont(f);
	p2.add(l2);

	String[] type={"Date wise","Month wise","year wise"};
	c0=new JComboBox(type);
  	c0.setBounds(100,20,100,25);
	c0.setFont(f);
	c0.addItemListener(this);
	p2.add(c0);

	l6=new JLabel("Date:");
  	l6.setBounds(260,20,80,25);
	l6.setFont(f);
	p2.add(l6);

        
	String[] year={"2013","2014","2015","2016","2017"};
         c1=new JComboBox(year);
        	c1.setBounds(320,20,80,25);
        	p2.add(c1);

         	String[] month={"01","02","03","04","05","06","07","08","09","10","11","12"};
         	c2=new JComboBox(month);
		c2.setBounds(420,20,80,25);
         	p2.add(c2);

		String[] day=											{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
         	c3=new JComboBox(day);
         	c3.setBounds(520,20,80,25);
           	p2.add(c3);

		b3 = new JButton("Search");
		b3.setBounds(700,20,80,30);
		b3.addActionListener(this);
		p2.add(b3);

	 p3= new JPanel(null);
	p3.setBackground(new Color(159,235,235));
	p3.setBounds(20,140,850,60);
	p3.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p3);

	  l3=new JLabel("Order no:");
	l3.setBounds(20,20,80,25);
	l3.setFont(f);
	p3.add(l3);
  

	c4=new JComboBox();
  	c4.setBounds(100,20,80,25);
	c4.setFont(f);
	c4.addItemListener(this);
	p3.add(c4);



         l4=new JLabel("Date:");
	l4.setBounds(260,20,80,25);
	l4.setFont(f);
	p3.add(l4);
  
	t1=new JTextField(20);
  	t1.setBounds(330,20,100,25);
	t1.setFont(f);
	t1.setEditable(false);
	p3.add(t1);


	l5=new JLabel("Total amount:");
  	l5.setBounds(480,20,100,25);
	l5.setFont(f);
	p3.add(l5);
	
	t2=new JTextField(20);
  	t2.setBounds(570,20,100,25);
	t2.setFont(f);
	t2.setEditable(false);
	p3.add(t2);


	
        p4= new JPanel(null);
	p4.setBackground(new Color(159,235,235));
	p4.setBounds(20,210,850,300);
	p4.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p4);
 

	

	table = new JTable();
	sp1 = new JScrollPane(table);
	sp1.setBounds(20,10,800,270);
	p4.add(sp1);

	

  	p5= new JPanel(null);
	p5.setBackground(new Color(159,235,235));
	p5.setBounds(20,530,850,60);
	p5.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p5);
 


  	b1=new JButton("PRINT");
	b1.setBounds(200,20,200,25);
         b1.setFont(f); 
	b1.addActionListener(this);
         p5.add(b1);
  
        b2=new JButton("EXIT");
	b2.setBounds(420,20,200,25);
	b2.setFont(f); 
	b2.addActionListener(this);
	p5.add(b2);
 	 
 	


	 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	setVisible(true);
      
	 try
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/myproject" ,"root","root");
		System.out.println("Connection Ok");
		addOrderno();
		
	}
	catch(Exception e)
	{
		System.out.println("connection error : "+e);
	}
          
  }
	void addOrderDetail()
	{
		try
		{
			String q="Select ordate,amount from orders where ordno="+c4.getSelectedItem();
			System.out.println(q);
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(q);

			if(rs.next())
			{
				String date = rs.getString("ordate");
				String total= rs.getString("amount");
				Double amt=Double.parseDouble(total);

				t1.setText(""+date);
				t2.setText(""+amt);
			}
			String q2 = "select srno as'Sr no.',description as 'Description',qty as'Quantity',rate as 'Menu Item rate',total as 'Total' from mi_ord where ordno="+c4.getSelectedItem();
			 st = con.createStatement();
			rs=st.executeQuery(q2);
			JResultModel brm = new JResultModel();
			brm.setResultSet(rs);
			table.setModel(brm);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	public void itemStateChanged(ItemEvent ie)
	{
		addOrderDetail();
	}


	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try
			{
				table.print();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
                  else if(ae.getSource()==b2)
                   {
		  	
                           	dispose();	
						
		 }
                    

		else if(ae.getSource()==b3)
		{
			addOrderno();
				
		}
	}	
	

		void addOrderno()
		{
			try
			{
				String wise = c0.getSelectedItem().toString();
				System.out.println(wise);
				String query = "";
				if(wise.equals("Date wise"))
				{
					query = "select ordno from orders where ordate like '"+c1.getSelectedItem()+"-"+c2.getSelectedItem()+"-"+c3.getSelectedItem()+"%' ";
				}
				else if(wise.equals("Month wise"))
				{
					query = "select ordno from orders where ordate like '"+c1.getSelectedItem()+"-"+c2.getSelectedItem()+"-%' ";
				}
				else if(wise.equals("year wise"))
				{
					query = "select ordno from orders where ordate like '"+c1.getSelectedItem()+"-%' ";
				}
				System.out.println(query);
				c4.removeItemListener(this);
				c4.removeAllItems();
				Statement stmt=con.createStatement();


				ResultSet rs=stmt.executeQuery(query);
				while(rs.next())
				{
					c4.addItem(rs.getString("ordno"));
				}
				c4.addItemListener(this);
				addOrderDetail();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	

	
    
  
   public static void main(String[] args)
  	{
            new ReportBill();
  	}
}














