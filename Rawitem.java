import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Rawitem extends JInternalFrame implements ActionListener
{
 	JLabel  l1,l2,l3,l4,l5;
 	JTextField  t1,t2;
 	JButton b1,b2,b3,b4,b5,b6;
	JPanel p1,p2,p3;
	JComboBox c1,c2;
	Connection con;
	

  public Rawitem()
  {
        super("RAW ITEM");
  	setSize(450,450);
	setLayout(null);

	Font f = new Font("One Stroke Script LET",Font.ITALIC,18);

	p1 = new JPanel(null);
	p1.setBackground(new Color(159,235,235));
	p1.setBounds(20,20,400,40);
	p1.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p1);


  	l1=new JLabel("Raw Item Details");
	l1.setBounds(150,10,200,30);
	l1.setFont(f);
	p1.add(l1);
 
        	p2 = new JPanel(null);
	p2.setBackground(new Color(244,244,90));
	p2.setBounds(20,70,400,200);
	p2.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p2);

        	l2=new JLabel("Raw Item code.:");
	l2.setBounds(30,40,150,25);
	l2.setFont(f);
	p2.add(l2);

         t1=new JTextField(20);
  	t1.setBounds(180,40,150,25);
	t1.setFont(f);
	t1.setEditable(false);
	p2.add(t1);

         l3=new JLabel("Raw Item Type:");
	l3.setBounds(30,70,150,25);
	l3.setFont(f);
	p2.add(l3);

        	String[] raw={"Vegetables","Grains","Liquids"};
         c1=new JComboBox(raw);
  	c1.setBounds(180,70,150,25);
	c1.setFont(f);
	p2.add(c1);

	l4=new JLabel("Raw Item Name:");
	l4.setBounds(30,100,150,25);
	l4.setFont(f);
	p2.add(l4);

         t2=new JTextField(20);
  	t2.setBounds(180,100,150,25);
	t2.setFont(f);
	p2.add(t2);


         l5=new JLabel("Unit Of Measure:");
	l5.setBounds(30,130,150,25);
	l5.setFont(f);
	p2.add(l5);

         String[] wt={"grams","kilo","litre"};
         c2=new JComboBox(wt);
  	c2.setBounds(180,130,150,25);
	c2.setFont(f);
	p2.add(c2);


 

         p3= new JPanel(null);
	p3.setBackground(new Color(159,235,235));
	p3.setBounds(20,280,400,100);
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
	
	b3=new JButton("DELETE");
         b3.setBounds(270,30,100,25);
         b3.setFont(f);
	b3.addActionListener(this); 
         p3.add(b3);

	b4=new JButton("SEARCH");
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
			try
         		{
				String rcode = t1.getText();
				
				String rtype=c1.getSelectedItem()+" ";
				String rname = t2.getText();
				if(rname.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Raw Item name field is empty.");
					return;
				}
				for(int i=0;i<rname.length();i++)
				{
					char ch = rname.charAt(i);
					if(ch>='a' && ch<='z' || ch>='A' && ch<='Z')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Raw Item Name not valid.");
						return;	
					}
				}

				
				String unitofmeasure=c2.getSelectedItem()+"";

                                     String query = "insert into RI(rtype,rname,unitofmeasurement)  values(?,?,?)";

				PreparedStatement pstat = con.prepareStatement(query);
			
                           		pstat.setString(1,rtype);
                           		pstat.setString(2,rname);
                                     pstat.setString(3,unitofmeasure);

				int row = pstat.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Raw Item entry successful..");
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
   				String rcode=t1.getText();
				if(rcode.length()==0)
                            	   {
					JOptionPane.showMessageDialog(this,"Search record first");
					return;
				   }
				
				String type=c1.getSelectedItem()+"";
				String name=t2.getText();
				String unitofmeasure=c2.getSelectedItem()+"";
	                      	String query = "update  RI set rtype=?,rname=?,unitofmeasurement=? where rcode="+rcode;
				System.out.println("hello"+query);
				PreparedStatement pstat = con.prepareStatement(query);
				pstat.setString(1,type);
                           		pstat.setString(2,name);
                           		pstat.setString(3,unitofmeasure);
                                     
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
   				String rcode=t1.getText();
			    	if(rcode.length()==0)
                  		           {
					JOptionPane.showMessageDialog(this,"Search record first");
					return;
				  }
				
				
		                   String query = "delete from  RI  where rcode="+rcode;
 	
				PreparedStatement pstat = con.prepareStatement(query);
				
				int row = pstat.executeUpdate();
				if(row>0)
				{
                                      	JOptionPane.showMessageDialog(this,"Record deleted");
                                          	t1.setText("");
				    	c1.setSelectedIndex(0);
					t2.setText("");
					c2.setSelectedIndex(0);

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
				String rcode=JOptionPane.showInputDialog(this,"Enter Raw Item to be searched:");
				if(rcode==null)
				return;
				t1.setText(rcode);
				String Query = "select * from RI where rcode="+rcode;
				System.out.println(Query);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Query);
				if(rs.next())
				{

					String type= rs.getString("rtype");
					System.out.println(type.length());
					String rname = rs.getString("rname");
					System.out.println(rname);
					String unitofmeasure= rs.getString("unitofmeasurement");
					System.out.println(unitofmeasure);
					t1.setText(""+rcode);
					type=type.trim();
					c1.setSelectedItem(type);
					t2.setText(rname);
					c2.setSelectedItem(unitofmeasure);
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
		else if(ae.getSource()==b5)
                   {
			dispose();
                    }

		else if(ae.getSource()==b6)
			{
				t1.setText("");
				t2.setText("");
				
				c1.setSelectedIndex(0);
				c2.setSelectedIndex(0);
				
				
				b1.setEnabled(true);
			}

}
   public static void main(String[] args)
  	{
          	new Rawitem();
  	}
  }

































