import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class GRN extends JInternalFrame implements ActionListener,ItemListener
{
 JLabel  l1,l2,l3,l4,l5,l6;
 JTextField  t1,t2;
 JButton b1,b2,b3,b4;
JPanel p1,p2,p3;
JComboBox c1;
 
Connection con;
JScrollPane sp1,sp2;
JTable table;
DefaultListModel dlm;



  public GRN()
  {
        super("GRN");
  	setSize(910,650);
	setLayout(null);

	Font f = new Font("One Stroke Script LET",Font.ITALIC,15);

	p1 = new JPanel(null);
	p1.setBackground(new Color(159,235,235));
	p1.setBounds(20,20,850,100);
	p1.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p1);


  	l1=new JLabel("GRN no.:");
	l1.setBounds(20,20,80,30);
	l1.setFont(f);
	p1.add(l1);
 
	t1=new JTextField(20);
  	t1.setBounds(110,20,150,30);
	t1.setFont(f);
	t1.setEditable(false);
	p1.add(t1);

       

        l2=new JLabel("PO No.:");
	l2.setBounds(20,60,80,25);
	l2.setFont(f);
	p1.add(l2);


	c1=new JComboBox();
  	c1.setBounds(110,60,80,25);
	c1.setFont(f);
	c1.addItemListener(this);
	p1.add(c1);
        

         l3=new JLabel("Date:");
	l3.setBounds(700,20,80,25);
	l3.setFont(f);
	p1.add(l3);
  
	java.util.Date dt = new java.util.Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	String d = sf.format(dt);

	l4=new JLabel(d);
  	l4.setBounds(740,20,80,25);
	l4.setFont(f);
	p1.add(l4);

	 p2 = new JPanel(null);
	p2.setBackground(new Color(244,244,90));
	p2.setBounds(20,140,850,380);
	p2.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p2);
        

	

	table = new JTable();
	sp2 = new JScrollPane(table);
	sp2.setBounds(20,20,800,300);
	p2.add(sp2);

	l5=new JLabel("Amount:");
	l5.setBounds(700,340,80,25);
	l5.setFont(f);
	p2.add(l5);

	l6=new JLabel("");
	l6.setBounds(760,340,80,25);
	l6.setFont(f);
	p2.add(l6);

  

  
         p3= new JPanel(null);
	p3.setBackground(new Color(159,235,235));
	p3.setBounds(20,530,850,60);
	p3.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p3);


	b1=new JButton("REJECTED ITEM");
	b1.setBounds(50,20,200,25);
         b1.setFont(f); 
	b1.addActionListener(this);
         p3.add(b1);
  
        b2=new JButton("SAVE & CREATE INVOICE");
	b2.setBounds(270,20,200,25);
	b2.setFont(f); 
	b2.addActionListener(this);
	p3.add(b2);
 	 
 	b3=new JButton("PRINT");
         b3.setBounds(500,20,100,25);
         b3.setFont(f);
	b3.addActionListener(this);
 	p3.add(b3);

	b4=new JButton("CANCEL");
         b4.setBounds(650,20,100,25);
         b4.setFont(f);
	b4.addActionListener(this);
 	p3.add(b4);


	 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	setVisible(true);
      
	 try
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/myproject" ,"root","root");
		System.out.println("Connection Ok");
		addPno();
		ResetTable();

	
	}
	catch(Exception e)
	{
		System.out.println("connection error : "+e);
	}
          
  }

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==c1)
		{	
			ResetTable();

		}

	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try
			{
				int row=table.getSelectedRow();
				
				String v2 = table.getValueAt(row,0).toString();
				String que="delete from po_ri where srno="+v2;
				PreparedStatement pst = con.prepareStatement(que);
				int row2 = pst.executeUpdate();
				ResetTable();
				if(row2>0)
				{
					JOptionPane.showMessageDialog(this,"Ordered item rejected successfully..");
					ResetTable();
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
				String gno=t1.getText();
				String gdate = l4.getText();
				String pno = c1.getSelectedItem()+" ";
				String amount=l6.getText();
				double amt=Double.parseDouble(amount);

				String query = "insert into grn1(pno,date,amount) values(?,?,?)";

				PreparedStatement pstat = con.prepareStatement(query);
				pstat.setString(1,pno);
                           		pstat.setString(2,gdate);
				pstat.setDouble(3,amt);
				
                           		int row = pstat.executeUpdate();
				if(row>0)
				{
					System.out.println("record saved in orders");
					String q3 = "select max(grno) from grn1";
					Statement stmt1=con.createStatement();
					ResultSet rs=stmt1.executeQuery(q3);
					rs.next();
					int max=rs.getInt(1);
					System.out.println(max);
				
					String ino=JOptionPane.showInputDialog(this,"Enter invoice number.");
					String am=JOptionPane.showInputDialog(this,"Enter total amount to be paid.");
					double amountp=Double.parseDouble(am);
				
					String q="insert into invoice1(ino,grno,amount) values(?,?,?)";
					
					PreparedStatement pst = con.prepareStatement(q);				
						pst.setString(1,ino);
                           				pst.setInt(2,max);
						pst.setDouble(3,amountp);
						pst.executeUpdate();
					

						JOptionPane.showMessageDialog(this,"Invoice details saved successfully..");

						
					

					String q4 = "select * from po_ri where pno="+c1.getSelectedItem();
					Statement stmt2=con.createStatement();
					rs=stmt2.executeQuery(q4);
					System.out.println("selected from po_ri");

					String q5 = "insert into grndesc(grno,srno,description,qty,prate,total) values(?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(q5);
					while(rs.next())
					{
						ps1.setInt(1,max);
						ps1.setInt(2,rs.getInt("srno"));
						ps1.setString(3,rs.getString("description"));
						ps1.setString(4,rs.getString("qty"));
						ps1.setString(5,rs.getString("prate"));
						ps1.setString(6,rs.getString("total"));
						ps1.executeUpdate();
						System.out.println("inserted into grndesc");
					}
					
			
					JOptionPane.showMessageDialog(this,"details saved successful..");
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
	void addPno()
	{
		try
		{
			System.out.println("PO no.");
			String query = "select pno from porder";
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next())
			{
				c1.addItem(rs.getString("pno"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void ResetTable()
	{
		try
		{
			String q="Select srno as 'Sr No',description,qty as 'quantity',prate as 'purchase rate',total from po_ri where pno="+c1.getSelectedItem();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(q);
			JResultModel brm = new JResultModel();
			brm.setResultSet(rs);
			table.setModel(brm);

			String q1 = "select sum(total) as tot from po_ri where pno="+c1.getSelectedItem();
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery(q1);
			rs.next();
                            Double amount=rs.getDouble("tot");
			l6.setText(amount+"");	

		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		


    
  }
   public static void main(String[] args)
  	{
            new GRN();
  	}
  }







































