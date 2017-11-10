import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Stock extends JInternalFrame implements ActionListener
{
 	JLabel  l1,l2,l3,l4,l5,l6;
 	JTextField  t1,t2,t3,t4;
 	JButton b1,b2,b3,b4,b5,b6;
	JPanel p1,p2,p3;
	JComboBox c1;
	ImageIcon i;
	Connection con;

 	public Stock()
 	{
        		super("STOCK");
		setBounds(50,50,550,450);
		
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,18);
		i=new ImageIcon("st.png");
		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,500,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);


  		l1=new JLabel("Stock Details");
		l1.setBounds(220,10,200,30);
		l1.setFont(f);
		p1.add(l1);
 
        		p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,500,200);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);

        		l2=new JLabel("Raw Item code.:");
		l2.setBounds(30,40,150,25);
		l2.setFont(f);
		p2.add(l2);

         	t1=new JTextField(20);
  		t1.setBounds(180,40,150,25);
		t1.setFont(f);
	//	t1.setEditable(false);
		p2.add(t1);

		l3=new JLabel("Raw Item Name:");
		l3.setBounds(30,70,150,25);
		l3.setFont(f);
		p2.add(l3);

          	t2=new JTextField(20);
  		t2.setBounds(180,70,150,25);
		t2.setFont(f);
		p2.add(t2);

		l4=new JLabel("Available Qty:");
		l4.setBounds(30,100,150,25);
		l4.setFont(f);
		p2.add(l4);
  

         	t3=new JTextField(20);
  		t3.setBounds(180,100,150,25);
		t3.setFont(f);
		p2.add(t3);

         	l5=new JLabel("Minimum Qty:");
		l5.setBounds(30,130,150,25);
		l5.setFont(f);
		p2.add(l5);
  

         	t4=new JTextField(20);
  		t4.setBounds(180,130,150,25);
		t4.setFont(f);
		p2.add(t4);

		l6=new JLabel(i);
		l6.setBounds(350,20,120,170);
		l6.setFont(f);
		p2.add(l6);


		p3= new JPanel(null);
		p3.setBackground(new Color(159,235,235));
		p3.setBounds(20,280,500,100);
		p3.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p3);

		b1=new JButton("SAVE");
		b1.setBounds(50,30,100,25);
          	b1.setFont(f); 
		b1.addActionListener(this);
          	p3.add(b1);

            	b2=new JButton("UPDATE");
           	b2.setBounds(160,30,100,25);
           	b2.setFont(f);
		b2.addActionListener(this);
          	p3.add(b2);
  
	   	b3=new JButton("SEARCH");
           	b3.setBounds(270,30,100,25);
           	b3.setFont(f);
		b3.addActionListener(this);
          	p3.add(b3);

             	b4=new JButton("DELETE");
           	b4.setBounds(160,60,100,25);
           	b4.setFont(f);
		b4.addActionListener(this);
          	p3.add(b4);

  
	  	b5=new JButton("CANCEL");
           	b5.setBounds(270,60,100,25);
           	b5.setFont(f);
		b5.addActionListener(this); 
          	p3.add(b5);

		b6=new JButton("CLEAR");
          	b6.setBounds(50,60,100,25);
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
				String rcode=t1.getText();
                           		String rname=t2.getText();
				if(rname.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Employee name field is empty.");
					return;
				}
				for(int i=0;i<rname.length();i++)
				{
					char ch = rname.charAt(i);
					if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch==' ')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Raw Item Name not valid.");
						return;	
					}
				}

				String availqty=t3.getText();
				if(availqty.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Available Quantity field is empty.");
					return;
				}
				for(int i=0;i<availqty.length();i++)
				{
					char ch = availqty.charAt(i);
					if(ch>='0' || ch<='9' || ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch==' ')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Available Quantity not valid.");
						return;	
					}
				}

				String minqty=t4.getText();
				if(minqty.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Minimum Quantity field is empty.");
					return;
				}
				for(int i=0;i<minqty.length();i++)
				{
					char ch = minqty.charAt(i);
					if(ch>='0' && ch<='9' || ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch==' ')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Minimum Quantity not valid.");
						return;	
					}
				}


				String query = "insert into stock(rcode,rname,availqty,minqty) values(?,?,?,?)";

				PreparedStatement pstat = con.prepareStatement(query);
				pstat.setString(1,rcode);
                           		pstat.setString(2,rname);
				pstat.setString(3,availqty);
				pstat.setString(4,minqty);
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
   				String rcode=t1.getText();
				if(rcode.length()==0)
                            	 {
					JOptionPane.showMessageDialog(this,"Search record first");
					return;
				}
				
				String rname=t2.getText();
				String availqty=t3.getText();
				String minqty=t4.getText();

				String query = "update  stock set rname=?,availqty=?,minqty=? where rcode="+rcode;

				PreparedStatement pstat = con.prepareStatement(query);
				
                           		pstat.setString(1,rname);
				pstat.setString(2,availqty);
                           		pstat.setString(3,minqty);
                           		 
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
				b1.setEnabled(false);
				String rcode =JOptionPane.showInputDialog(this,"Enter Raw Item code to be searched:");
				if(rcode==null)
				return;
				String Query = "select * from stock where rcode ="+rcode;
				System.out.println(Query);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Query);
				if(rs.next())
				{
					String name = rs.getString("rname");
					String minqty = rs.getString("availqty");
					String availqty = rs.getString("minqty");
					
					t1.setText(""+rcode);
					t2.setText(name);
					t3.setText(availqty);
					t4.setText(minqty);
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
		else if(ae.getSource()==b4)
                   {
			try
                     	{
   				String rcode=t1.getText();
				if(rcode.length()==0)
                            	 {
					JOptionPane.showMessageDialog(this,"Search record first");
					return;
				}
				
				/*String sname=t2.getText();
				String saddr=ta.getText();
				String smob=t4.getText();
				String semail=t5.getText();*/

	                   		String query = "delete from  stock where rcode="+rcode;
 

				PreparedStatement pstat = con.prepareStatement(query);
				
                                     int row = pstat.executeUpdate();
				if(row>0)
				{
                                     		JOptionPane.showMessageDialog(this,"Record deleted");
                                      	t1.setText("");
				 	t2.setText("");
			         	 	t3.setText("");
		  		 	t4.setText("");
				 }
				else
    				System.out.println("Record not deleted.");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==b5)
                   {
                   	
			dispose();
                    }

		else if(ae.getSource()==b6)
			{
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				
				
				
				b1.setEnabled(true);
			}

		
}
   public static void main(String[] args)
  	{
            	new Stock();
  	}
  }







































