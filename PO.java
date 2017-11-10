import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.*;

public class PO extends JInternalFrame implements  ActionListener,MouseListener,ItemListener
 {
 JLabel  l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
 JTextField  t1;
 JButton b1,b2,b3,b4;
 JList list;
 JTable table;
 JPanel p1,p2,p3;
 Connection con;
 JScrollPane sp1,sp2;
 JComboBox c1;
 DefaultListModel dlm;

  public PO()
  {
        super("PURCHASE ORDER");
  	setSize(900,650);
	setLayout(null);

	Font f = new Font("One Stroke Script LET",Font.ITALIC,18);

	p1 = new JPanel(null);
	p1.setBackground(new Color(159,235,235));
	p1.setBounds(20,20,860,100);
	p1.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p1);


  	l1=new JLabel("PO no.:");
	l1.setBounds(50,10,80,25);
	l1.setFont(f);
	p1.add(l1);

	 t1=new JTextField(20);
  	t1.setBounds(185,10,80,25);
	t1.setFont(f);
	t1.setEditable(false);
	p1.add(t1);

	 l2=new JLabel("Date:");
	l2.setBounds(530,10,80,25);
	l2.setFont(f);
	p1.add(l2);
	
	java.util.Date dt = new java.util.Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	String d = sf.format(dt);

	l7=new JLabel(d);
  	l7.setBounds(620,10,80,25);
	l7.setFont(f);
	p1.add(l7);

        
   
         l3=new JLabel("Supplier name:");
	l3.setBounds(50,40,125,25);
	l3.setFont(f);
	p1.add(l3);

	c1=new JComboBox();
  	c1.setBounds(185,40,180,25);
	c1.addItemListener(this);
	c1.setFont(f);
	p1.add(c1);

	



	p2 = new JPanel(null);
	p2.setBackground(new Color(244,244,90));
	p2.setBounds(20,130,860,400);
	p2.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p2);

	l4=new JLabel("Amount:");
	l4.setBounds(530,360,80,25);
	l4.setFont(f);
	p2.add(l4);

	l5=new JLabel("");
	l5.setBounds(620,360,80,25);
	l5.setFont(f);
	p2.add(l5);

	l8=new JLabel("Minimum Qty:");
	l8.setBounds(40,360,100,25);
	l8.setFont(f);
	p2.add(l8);

	l9=new JLabel("");
	l9.setBounds(140,360,80,25);
	l9.setFont(f);
	p2.add(l9);

	l10=new JLabel("Available Qty:");
	l10.setBounds(220,360,100,25);
	l10.setFont(f);
	p2.add(l10);

	l11=new JLabel("");
	l11.setBounds(320,360,80,25);
	l11.setFont(f);
	p2.add(l11);



	
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

	p3 = new JPanel(null);
	p3.setBackground(new Color(159,235,235));
	p3.setBounds(20,550,860,60);
	p3.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p3);

	b1=new JButton("GENERATE ORDER");
	b1.setBounds(90,20,160,25);
	b1.setFont(f);
	b1.addActionListener(this);
	p3.add(b1);

	b2=new JButton("PRINT");
	b2.setBounds(270,20,100,25);
	b2.setFont(f);
	p3.add(b2);

	b3=new JButton("REMOVE ITEM");
	b3.setBounds(410,20,150,25);
	b3.setFont(f);
	b3.addActionListener(this);
     	p3.add(b3);

	b4=new JButton("CANCEL");
         b4.setBounds(600,20,100,25);
	b4.setFont(f);
	b4.addActionListener(this);
         p3.add(b4);

	    
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
         try
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/myproject" ,"root","root");
		System.out.println("Connection Ok");
		addRawItem();	
		addSupplier();
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
				String pno=t1.getText();
				

				String date = l7.getText();
				String sname = c1.getSelectedItem()+" ";
				
				                        

				String q = "select sum(total) as tot from temp1";
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				rs.next();
	                             Double amount=rs.getDouble("tot");
				System.out.println(amount);			
				
                                      String query = "insert into porder(podate,sname,amount) values(?,?,?)";
				System.out.println(pno);
				//System.out.println(podate);
				System.out.println(sname);
				System.out.println(amount);


				PreparedStatement pstat = con.prepareStatement(query);
				pstat.setString(1,date);
				pstat.setString(2,sname);
				pstat.setDouble(3,amount);
                           		int row = pstat.executeUpdate();
				if(row>0)
				{
					System.out.println("record saved in porder");
					String q2 = "select max(pno) from porder";
					Statement stmt1=con.createStatement();
					rs=stmt1.executeQuery(q2);
					rs.next();
					int max=rs.getInt(1);
					System.out.println(max);
					
					String q3 = "select * from temp1";
					Statement stmt2=con.createStatement();
					rs=stmt2.executeQuery(q3);
					System.out.println("selected from temp1");
					
					String q4 = "insert into po_ri(pno,srno,description,qty,prate,total) values(?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(q4);
					while(rs.next())
					{
						ps1.setInt(1,max);
						ps1.setInt(2,rs.getInt("srno"));
						ps1.setString(3,rs.getString("description"));
						ps1.setString(4,rs.getString("qty"));
						ps1.setString(5,rs.getString("prate"));
						ps1.setString(6,rs.getString("total"));
						ps1.executeUpdate();
						System.out.println("inserted into po_ri");
					}
					String q5 = "delete from temp1";
					PreparedStatement ps2=con.prepareStatement(q5);
					ps2.executeUpdate();
			
					JOptionPane.showMessageDialog(this,"Order saved successful..");
					resetTable();

				}
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
				String que="delete from temp1 where srno="+value;
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
	public void itemStateChanged(ItemEvent ie){}
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
					int index = list.getSelectedIndex();
					String RawItem = dlm.get(index).toString();
					
					String qu="Select minqty,availqty from stock where rname='"+RawItem+"'" ;
					Statement stmt3=con.createStatement();
					ResultSet rs=stmt3.executeQuery(qu);
					rs.next();
					String minqty=rs.getString("minqty");
					String availqty=rs.getString("availqty");

					l9.setText(""+minqty);
					l11.setText(""+availqty);

					String Quantity =JOptionPane.showInputDialog(this,"Enter Quantity");
					
					String porate =JOptionPane.showInputDialog(this,"Enter Purchase Order rate");
					
					String q = "select sum(total) as tot from temp1";
					Statement stmt=con.createStatement();
					rs=stmt.executeQuery(q);
					rs.next();
	                             	Double amount=rs.getDouble("tot");
					System.out.println(amount);	
			
					String qu2 = "select max(srno) from temp1";
					Statement st = con.createStatement();
					 rs=st.executeQuery(qu2);

					rs.next();
					int max = rs.getInt(1)+1;

					
					
				          String query1 ="insert into temp1(srno,description,qty,prate,total) values(?,?,?,?,?)";
					PreparedStatement pstat = con.prepareStatement(query1);
					int qty=Integer.parseInt(Quantity);
					double rate=Double.parseDouble(porate);

					double total=rate*qty;

					pstat.setInt(1,max);
					pstat.setString(2,RawItem);
					pstat.setInt(3,qty);
					pstat.setDouble(4,rate);
					pstat.setDouble(5,total);
					



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
			String query = "select srno as 'Sr No',description,qty as 'quantity',prate as 'purchase rate',total from temp1";
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(query);
			JResultModel brm = new JResultModel();
			brm.setResultSet(rs);
			table.setModel(brm);

			String q = "select sum(total) as tot from temp1";
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery(q);
			rs.next();
                            Double amount=rs.getDouble("tot");
			l5.setText(amount+"");			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	void addRawItem()
	{
		try
		{
			System.out.println("Raw Item");
			String query = "select rname from RI";
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next())
			{
				dlm.addElement(rs.getString("rname"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	void addSupplier()
	{
		try
		{
			System.out.println("Suppler name");
			String query = "select sname from supplier";
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next())
			{
				c1.addItem(rs.getString("sname"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
    
 

 public static void main(String[] args)
  	{
            new PO();
  	}
}





























