import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Tables extends JInternalFrame implements ActionListener
{
 	JLabel  l1,l2,l3,l4,l5;
	JButton b1,b2,b3,b4,b5,b6;
	JPanel p1,p2,p3;
	JComboBox c1;
	JTextField t1;
	JRadioButton r[]=new JRadioButton[3];
	ButtonGroup grp;
	Connection con;
	ImageIcon i;

  	public Tables ()
  	{
		super("TABLES");
		setSize(450,400);
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,18);
		i=new ImageIcon("tb.png");

		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,400,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);


 	 	l1=new JLabel("Table Details");
		l1.setBounds(150,10,200,30);
		l1.setFont(f);
		p1.add(l1);
 
        		p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,400,170);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);

        		l2=new JLabel("Table No.");
		l2.setBounds(30,40,150,25);
		l2.setFont(f);
		p2.add(l2);

         	t1=new JTextField(20);
  		t1.setBounds(120,40,150,25);
		t1.setFont(f);
		t1.setEditable(false);
		p2.add(t1);


      
         	l3=new JLabel("Occupancy");
		l3.setBounds(30,90,150,25);
		l3.setFont(f);
		p2.add(l3);

	        	String[] no={"2","4","6","8","10"};
	        	c1=new JComboBox(no);
  		c1.setBounds(120,90,150,25);
		c1.setFont(f);
		p2.add(c1);


  

        		l4=new JLabel(i);
		l4.setBounds(275,30,123,123);
		l4.setFont(f);
		p2.add(l4);


        		p3= new JPanel(null);
		p3.setBackground(new Color(159,235,235));
		p3.setBounds(20,250,400,100);
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
				String tno = t1.getText();
				
				String occupancy=c1.getSelectedItem()+" ";
			
				/*String a="";
                                       if(r[0].isSelected())
                                        a="Empty";
				     if(r[1].isSelected())
                                        a="Reserved";
				     if(r[2].isSelected())
                                        a="Occupied";*/

				String query = "insert into tables(occupancy)  values(?)";

				PreparedStatement pstat = con.prepareStatement(query);
			
                           		pstat.setString(1,occupancy);

                           
				int row = pstat.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Table entry successful..");
				}
			}
			catch(Exception e)
			  {
				System.out.println();
			  } 
	          }
		else if(ae.getSource()==b2)
                  {
                  		try
                     	{
   				String tno=t1.getText();
				if(tno.length()==0)
                            	   {
					JOptionPane.showMessageDialog(this,"Search record first");
					return;
				   }
				
				
				String occupancy=c1.getSelectedItem()+"";
			
			
				/*String a="";
                                       if(r[0].isSelected())
                                        a="1";
				     if(r[1].isSelected())
                                        a="2";
				 if(r[2].isSelected())
                                      a="3";*/


	                      	String query = "update  tables set occupancy=? where tno="+tno;
				System.out.println("hello"+query);
				PreparedStatement pstat = con.prepareStatement(query);
				pstat.setString(1,occupancy);
                           		
				//pstat.setString(2,a);
                           		 
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
   				String tno=t1.getText();
			    	if(tno.length()==0)
                  		           {
					JOptionPane.showMessageDialog(this,"Search record first");
					return;
				}
				
				
	                      	String query = "delete from  tables  where tno="+tno;
 

				PreparedStatement pstat = con.prepareStatement(query);
				
                                     int row = pstat.executeUpdate();
				if(row>0)
				{
                                      	JOptionPane.showMessageDialog(this,"Record deleted");
                                          	t1.setText("");
				    
				    	//r[0].setSelected(true);
					//r[1].setSelected(false);
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
				String tno =JOptionPane.showInputDialog(this,"Enter Table no. to be searched:");
				if(tno.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Employee no. field is empty.");
					return;
				}
				for(int i=0;i<tno.length();i++)
				{
					char ch = tno.charAt(i);
					if(ch>='0' && ch<='9')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this," Table no. not valid.");
						return;	
					}
				}

				if(tno==null)
				return;
				t1.setText(tno);
				String Query = "select * from tables where tno="+tno;
				System.out.println(Query);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Query);
				if(rs.next())
				{

					String occupancy= rs.getString("occupancy");
					
					//String status = rs.getString("tstatus");
					c1.setSelectedItem(occupancy);
					
					/*if(status.equals("1"))
					r[0].setSelected(true);
					else if(status.equals("2"))
					r[1].setSelected(true);
					else
					r[2].setSelected(true);*/
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
				c1.setSelectedIndex(0);
				
				
				
				b1.setEnabled(true);
			}

}
   public static void main(String[] args)
  	{
           new Tables();
  	}
  }

























