import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class ReportSupp extends JInternalFrame implements ActionListener
{
 	JLabel  l1;
	JButton b1,b2;
	JPanel p1,p2,p3;
	JScrollPane sp1;
	JTable table;
	Connection con;

  	public ReportSupp()
  	{
		super("SUPPLIER REPORT");
		setSize(900,650);
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,20);

		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,850,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);


 	 	l1=new JLabel("Supplier Report");
		l1.setBounds(355,10,150,30);
		l1.setFont(f);
		p1.add(l1);

		p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,850,450);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);

		table = new JTable();
		sp1 = new JScrollPane(table);
		sp1.setBounds(20,20,800,400);
		p2.add(sp1);

		p3= new JPanel(null);
		p3.setBackground(new Color(159,235,235));
		p3.setBounds(20,530,850,60);
		p3.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p3);
		
		b1=new JButton("PRINT");
         	b1.setBounds(250,15,100,30);
           	b1.setFont(f); 
		b1.addActionListener(this);
          	p3.add(b1);
  
 	 	b2=new JButton("EXIT");
           	b2.setBounds(450,15,100,30);
           	b2.setFont(f); 
		b2.addActionListener(this);
		p3.add(b2);
	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setVisible(true);
 		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myproject" ,"root","root");
			System.out.println("Connection Ok");
			supp();
			
		}
		catch(Exception e)
		{
			System.out.println("connection error : "+e);
		}
          
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
			try
			{
				dispose();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	
	void supp()
	{
		try
		{
			String query = "select scode as'Supplier code',sname as 'Name',smob as 'Contact no.',semail as 'Email ID',saddr as 'Address' from supplier";
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			JResultModel brm=new JResultModel();
			brm.setResultSet(rs);
			table.setModel(brm);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void main(String[] args)
  	{
           new ReportSupp();
  	}
  }


	
	  	






