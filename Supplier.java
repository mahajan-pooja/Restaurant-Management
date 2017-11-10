import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Supplier extends JInternalFrame implements ActionListener
{ 
 	JLabel  l1,l2,l3,l4,l5,l6,l7;
 	JTextField  t1,t2,t4,t5,t6;
 	JButton b1,b2,b3,b4,b5,b6;
	JPanel p1,p2,p3;
	JTextArea ta;
	Connection con;
	Statement stmt;

	public Supplier()
  	{
        		super("SUPPLIER");
  		setSize(500,500);
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,18);

		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,450,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);


  		l1=new JLabel("Supplier Details");
		l1.setBounds(150,10,200,30);
		l1.setFont(f);
		p1.add(l1);
 
        		p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,450,250);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);

        		l2=new JLabel("Supplier Code:");
		l2.setBounds(30,40,150,25);
		l2.setFont(f);
		p2.add(l2);

         	t1=new JTextField(20);
  		t1.setBounds(180,40,200,25);
		t1.setFont(f);
		t1.setEditable(false);
		p2.add(t1);

         	l3=new JLabel("Supplier Name:");
		l3.setBounds(30,70,150,25);
		l3.setFont(f);
		p2.add(l3);
  

         	t2=new JTextField(20);
  		t2.setBounds(180,70,200,25);
		t2.setFont(f);
		p2.add(t2);

        		l4=new JLabel("Address:");
		l4.setBounds(30,100,150,25);
		l4.setFont(f);
		p2.add(l4);

          	ta=new JTextArea(2,20);
  		ta.setBounds(180,100,200,50);
		ta.setFont(f);
         	ta.setLineWrap(true);
         	ta.setWrapStyleWord(true);
		p2.add(ta);


         	l5=new JLabel("Mobile no.:");
		l5.setBounds(30,155,150,25);
		l5.setFont(f);
		p2.add(l5);
  
          	t4=new JTextField(20);
  		t4.setBounds(180,155,200,25);
		t4.setFont(f);
		p2.add(t4);
         

        		l6=new JLabel("Email ID:");
		l6.setBounds(30,185,150,25);
		l6.setFont(f);
		p2.add(l6);
  

         	t5=new JTextField(20);
  		t5.setBounds(180,185,200,25);
		//t5.setFont(f);
		p2.add(t5);

         	p3= new JPanel(null);
		p3.setBackground(new Color(159,235,235));
		p3.setBounds(20,330,450,100);
		p3.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p3);


		b1=new JButton("SAVE");
           	b1.setBounds(40,20,100,25);
           	b1.setFont(f); 
           	b1.addActionListener(this);
          	p3.add(b1);

            	b2=new JButton("UPDATE");
           	b2.setBounds(160,20,100,25);
           	b2.setFont(f); 
           	b2.addActionListener(this);
          	p3.add(b2);
  
	   	b3=new JButton("DELETE");
           	b3.setBounds(270,20,100,25);
           	b3.setFont(f);
	  	b3.addActionListener(this); 
	 	p3.add(b3);
           
         	b4=new JButton("SEARCH");
         	b4.setBounds(40,60,100,25);
        	   	b4.setFont(f);
           	b4.addActionListener(this); 
          	p3.add(b4);

  
	   	b5=new JButton("CLEAR");
           	b5.setBounds(160,60,100,25);
           	b5.setFont(f);
		b5.addActionListener(this); 
          	p3.add(b5);

		b6=new JButton("CANCEL");
           	b6.setBounds(270,60,100,25);
           	b6.setFont(f);
		b6.addActionListener(this); 
          	p3.add(b6);

 
      		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setVisible(true);
  
       		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myproject" ,"root","root");
			System.out.println("Connection Ok");
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
 			System.out.println("hello");
			try
			{
//				String scode=t1.getText();
                           		String sname=t2.getText();
				if(sname.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Supplier name field is empty.");
					return;
				}
				for(int i=0;i<sname.length();i++)
				{
					char ch = sname.charAt(i);
					if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch==' ')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Supplier Name not valid.");
						return;	
					}
				}

				String saddr=ta.getText();
				if(saddr.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Supplier Address field is empty.");
					return;
				}
				for(int i=0;i<saddr.length();i++)
				{
					char ch = saddr.charAt(i);
					if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch>='0' && ch<='9' || ch==' ' || ch=='/' || ch==',' || ch=='.')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Suuplier Address not valid.");
						return;	
					}
				}

                           		String smob=t4.getText();
				if(smob.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Mobile number field is empty.");
					return;
				}
				for(int i=0;i<smob.length();i++)
				{
					char ch = smob.charAt(i);
					if(ch>='0' && ch<='9')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Moblie number not valid.");
						return;	
					}
				}

				String semail=t5.getText();
				if(semail.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Email address field is empty.");
					return;
				}
				for(int i=0;i<semail.length();i++)
				{
					char ch = semail.charAt(i);
					if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch>='0' && ch<='9' || ch=='@' || ch=='_' || ch=='.')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Email address not valid.");
						return;	
					}
				}


				String query = "insert into supplier(sname,saddr,smob,semail) values(?,?,?,?)";

				PreparedStatement pstat = con.prepareStatement(query);
                           		pstat.setString(1,sname);
				pstat.setString(2,saddr);
                           		pstat.setString(3,smob);
				pstat.setString(4,semail);
                           		 
                                     int row = pstat.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Details Saved");
				}
				else
    				System.out.println("Record not saved");
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
   			String scode=t1.getText();
			if(scode.length()==0)
                             {
				JOptionPane.showMessageDialog(this,"Search record first");
				return;
			}
				//String scode=t1.getText();
				String sname=t2.getText();
				String saddr=ta.getText();
				String smob=t4.getText();
				String semail=t5.getText();

	                      String query = "update  supplier set sname=?,saddr=?,smob=?,semail=? where scode="+scode;

				PreparedStatement pstat = con.prepareStatement(query);
				//pstat.setString(1,scode);
                           		pstat.setString(1,sname);
				pstat.setString(2,saddr);
                           		pstat.setString(3,smob);
				pstat.setString(4,semail);
                           		 
                                     int row = pstat.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Details updated");
				}
				else
    				System.out.println("Record not updated");
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
   			String scode=t1.getText();
			if(scode.length()==0)
                             {
				JOptionPane.showMessageDialog(this,"Search record first");
				return;
			}
				/*String scode=t1.getText();

				String sname=t2.getText();
				String saddr=ta.getText();
				String smob=t4.getText();
				String semail=t5.getText();*/

	                      String query = "delete from  supplier  where scode="+scode;
 

				PreparedStatement pstat = con.prepareStatement(query);
				
                                     int row = pstat.executeUpdate();
				if(row>0)
				{
                                         JOptionPane.showMessageDialog(this,"Record deleted");
                                         t1.setText("");
				    t2.setText("");
			         	    ta.setText("");
		  		    t4.setText("");
				    t5.setText("");
 				}
				else
    				System.out.println("Record not deleted.");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}


                    }
		else if(ae.getSource()==b4)
                   {
			try
			{
				b1.setEnabled(false);
				String scode =JOptionPane.showInputDialog(this,"Enter Supplier code to be searched:");
				if(scode.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Supplier code field is empty.");
					return;
				}
				for(int i=0;i<scode.length();i++)
				{
					char ch = scode.charAt(i);
					if(ch>='0' && ch<='9')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Supplier code not valid.");
						return;	
					}
				}

				if(scode==null)
				return;
				String Query = "select * from supplier where scode ="+scode;
				System.out.println(Query);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Query);
				if(rs.next())
				{
					String name = rs.getString("sname");
					String address = rs.getString("saddr");
					String mobile = rs.getString("smob");
					String email = rs.getString("semail");
					t1.setText(""+scode);
					t2.setText(name);
					ta.setText(address);
					t4.setText(mobile);
					t5.setText(email);
					b1.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Record not found");
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

                    }
		else if(ae.getSource()==b6)
                   {
			dispose();
                    }

		else if(ae.getSource()==b5)
			{
				t1.setText("");
				t2.setText("");
				ta.setText("");
				t4.setText("");
				t5.setText("");
				
				
				b1.setEnabled(true);
			}


	}
 	public static void main(String[] args)
  	{
            new Supplier();
  	}
}

























