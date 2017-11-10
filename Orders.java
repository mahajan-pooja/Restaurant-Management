import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.*;

public class Orders extends JInternalFrame implements ActionListener,MouseListener,ItemListener
 {
 JLabel  l1,l2,l3,l4,l5,l6,l7;
 JTextField  t1,t2,t3;
 JButton b1,b2,b3,b4,b5;
 JList list;
 JTable table;
 JPanel p1,p2,p3;
 Connection con;
 JScrollPane sp1,sp2;
 JComboBox c1,c2;
 DefaultListModel dlm;

  public Orders(String tno)
  {
	this();
	c1.setSelectedItem(tno);	
 }
  public Orders()
  {
        super("ORDERS");
  	setSize(910,650);
	setLayout(null);

	Font f = new Font("One Stroke Script LET",Font.ITALIC,18);

	p1 = new JPanel(null);
	p1.setBackground(new Color(159,235,235));
	p1.setBounds(20,20,860,100);
	p1.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p1);


  	l1=new JLabel("Order no.");
	l1.setBounds(50,10,80,25);
	l1.setFont(f);
	p1.add(l1);

	 t1=new JTextField(20);
  	t1.setBounds(140,10,80,25);
	t1.setFont(f);
	t1.setEditable(false);
	p1.add(t1);

	l2=new JLabel("Date");
	l2.setBounds(530,10,80,25);
	l2.setFont(f);
	p1.add(l2);
	
	java.util.Date dt = new java.util.Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	String d = sf.format(dt);

	l3=new JLabel(d);
  	l3.setBounds(620,10,85,25);
	l3.setFont(f);
	p1.add(l3);

       	l4=new JLabel("Table no.");
	l4.setBounds(50,40,80,25);
	l4.setFont(f);
	p1.add(l4);

	String[] tno=	{"select","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020	"};
	c1=new JComboBox(tno);
  	c1.setBounds(140,40,80,25);
	c1.addItemListener(this);
	
	c1.setFont(f);
	p1.add(c1);

	l5=new JLabel("Employee no.");
	l5.setBounds(530,40,100,25);
	l5.setFont(f);
	p1.add(l5);

         c2=new JComboBox();
  	c2.setBounds(640,40,80,25);
	c2.setFont(f);
	p1.add(c2);


	p2 = new JPanel(null);
	p2.setBackground(new Color(244,244,90));
	p2.setBounds(20,130,860,400);
	p2.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p2);

        dlm = new DefaultListModel();
 	list = new JList(dlm);
	list.addMouseListener(this);
	sp1 = new JScrollPane(list);
	sp1.setBounds(20,20,200,330);
	p2.add(sp1);

	table = new JTable();
	sp2 = new JScrollPane(table);
	sp2.setBounds(230,20,600,330);
	p2.add(sp2);

	l6=new JLabel("Amount");
	l6.setBounds(530,360,80,25);
	l6.setFont(f);
	p2.add(l6);

	l7=new JLabel("");
	l7.setBounds(620,360,80,25);
	l7.setFont(f);
	p2.add(l7);

	p3 = new JPanel(null);
	p3.setBackground(new Color(159,235,235));
	p3.setBounds(20,550,860,60);
	p3.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p3);

	b1=new JButton("SAVE");
	b1.setBounds(130,20,100,25);
	b1.setFont(f);
	b1.addActionListener(this);
	p3.add(b1);

	b2=new JButton("PRINT");
	b2.setFont(f);
	b2.setBounds(280,20,100,25);
	b2.setEnabled(false);
	b2.addActionListener(this);
	p3.add(b2);

	b3=new JButton("REMOVE ITEM");
	b3.setBounds(420,20,150,25);
	b3.setFont(f);
	b3.addActionListener(this);
     	p3.add(b3);

	b4=new JButton("CANCEL");
	b4.setFont(f);
        	b4.setBounds(610,20,100,25);
	b4.addActionListener(this);
        	p3.add(b4);

	    
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
         try
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/myproject" ,"root","root");
		System.out.println("Connection Ok");
		addMenuItem();
		addEmpno();	
	}
	catch(Exception e)
	{
		System.out.println("connection error : "+e);
	}
  	setVisible(true);
         
  }

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
  		  {
	   		try
         		{
				String ordno=t1.getText();
				String ordate = l3.getText();
				String empno = c2.getSelectedItem()+" ";
				String tno=c1.getSelectedItem()+" ";
				                        

				String q = "select sum(total) as tot from temp where tno="+c1.getSelectedItem();
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				rs.next();
	                             Double amount=rs.getDouble("tot");
				System.out.println(amount);			
				
                                      String query = "insert into orders(ordate,tno,empno,amount) values(?,?,?,?)";
				System.out.println(ordate);
				System.out.println(tno);
				System.out.println(empno);
				System.out.println(amount);


				PreparedStatement pstat = con.prepareStatement(query);
				pstat.setString(1,ordate);
                           		pstat.setString(2,tno);
				pstat.setString(3,empno);
				pstat.setDouble(4,amount);
                           		int row = pstat.executeUpdate();
				if(row>0)
				{
					System.out.println("record saved in orders");
					String q2 = "select max(ordno) from orders";
					Statement stmt1=con.createStatement();
					rs=stmt1.executeQuery(q2);
					rs.next();
					int max=rs.getInt(1);
					System.out.println(max);
					
					String q3 = "select * from temp where tno="+c1.getSelectedItem();
					Statement stmt2=con.createStatement();
					rs=stmt2.executeQuery(q3);
					System.out.println("selected from temp");
					
					String q4 = "insert into mi_ord(ordno,srno,description,qty,rate,total) values(?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(q4);
					while(rs.next())
					{
						ps1.setInt(1,max);
						ps1.setInt(2,rs.getInt("srno"));
						ps1.setString(3,rs.getString("description"));
						ps1.setString(4,rs.getString("qty"));
						ps1.setString(5,rs.getString("rate"));
						ps1.setString(6,rs.getString("total"));
						ps1.executeUpdate();
						System.out.println("inserted into mi-ord");
					}
					String q5 = "delete from temp where tno="+c1.getSelectedItem();
					PreparedStatement ps2=con.prepareStatement(q5);
					ps2.executeUpdate();
			
					JOptionPane.showMessageDialog(this,"details saved successful..");
					b2.setEnabled(true);
				}
			}
			catch(Exception e)
			  {
				System.out.println(e);
			  } 

	          }
		
		else if(ae.getSource()==b2)
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


		else if(ae.getSource()==b3)
                   {
                  		try
                     	{
				int row=table.getSelectedRow();
				String value=table.getValueAt(row,0).toString();
				String que="delete from temp where srno="+value;
				PreparedStatement pst = con.prepareStatement(que);
				int row2 = pst.executeUpdate();
				resetTable();
				if(row2>0)
				{
					JOptionPane.showMessageDialog(this,"Ordered item removed successfully..");
				}

	
	         		}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

		else if(ae.getSource()==b4)
                   {
                  		dispose();
		}

	}
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==c1)
		{
			resetTable();
		}
	}
	public void mouseExited(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}

	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource()==list)
		{
			if(me.getClickCount()==2)
			{
				try
				{
				 	String Quantity =JOptionPane.showInputDialog(this,"Enter Quantity");
					int index = list.getSelectedIndex();
					String MenuItem = dlm.get(index).toString();
					String TableNo= c1.getSelectedItem().toString();
			
					String qu2 = "select max(srno) from temp";
					Statement st = con.createStatement();
					ResultSet rs=st.executeQuery(qu2);

					rs.next();
					int max = rs.getInt(1)+1;

					String qur="select mrate from menu where mname=?";
					PreparedStatement ps = con.prepareStatement(qur);
					ps.setString(1,MenuItem);
					rs= ps.executeQuery();
					rs.next();
					Double rate=rs.getDouble("mrate");
					
				          String query1 ="insert into temp(srno,tno,description,qty,rate,total) values(?,?,?,?,?,?)";
					PreparedStatement pstat = con.prepareStatement(query1);
					int qty=Integer.parseInt(Quantity);
					double total=rate*qty;

					pstat.setInt(1,max);
					pstat.setString(2,TableNo);
					pstat.setString(3,MenuItem);
					pstat.setInt(4,qty);
					pstat.setDouble(5,rate);
					pstat.setDouble(6,total);


					int row = pstat.executeUpdate();
					resetTable();			
				}
				catch(Exception e)
				{
					System.out.println(e);
				}

			}
		}
	}
	void resetTable()
	{
		try
		{
			
			String query = "select srno,description,qty,rate,total from temp where tno="+c1.getSelectedItem();
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(query);
			JResultModel brm = new JResultModel();
			brm.setResultSet(rs);
			table.setModel(brm);

			String q = "select sum(total) as tot from temp where tno="+c1.getSelectedItem();
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery(q);
			rs.next();
                            Double amount=rs.getDouble("tot");
			l7.setText(amount+"");			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	void addMenuItem()
	{
		try
		{
			System.out.println("menu item");
			String query = "select mname from menu";
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next())
			{
				dlm.addElement(rs.getString("mname"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void addEmpno()
	{
		try
		{
			System.out.println("Employee no.");
			String query = "select empno from employee";
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next())
			{
				c2.addItem(rs.getString("empno"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	public static void main(String[] args)
  	{
         	new Orders();
  	}
  }















































