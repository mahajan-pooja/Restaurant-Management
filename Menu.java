import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Menu extends JInternalFrame implements ActionListener
{
	JLabel  l1,l2,l3,l4,l5,l6,l7;
	JButton b1,b2,b3,b4,b5,b6;
	JPanel p1,p2,p3;
	JComboBox c1;
	JTextField t1,t2,t3,t4;
	JRadioButton r[]=new JRadioButton[2];
	ButtonGroup grp;
	ImageIcon i;
	Connection con;

  	public Menu()
  	{
		super("Menu");
		setSize(450,450);
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,18);
		i=new ImageIcon("m1.png");

		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,400,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);


  		l1=new JLabel("Menu Details");
		l1.setBounds(150,10,200,30);
		l1.setFont(f);
		p1.add(l1);
 
        		p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,400,200);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);

         	l2=new JLabel("Menu Code:");
		l2.setBounds(30,40,150,25);
		l2.setFont(f);
		p2.add(l2);

         	t1=new JTextField(20);
  		t1.setBounds(120,40,150,25);
		t1.setFont(f);
		t1.setEditable(false);
		p2.add(t1);
        
		l3=new JLabel("Menu Name:");
		l3.setBounds(30,70,150,25);
		l3.setFont(f);
		p2.add(l3);
	
		t2=new JTextField(20);
  		t2.setBounds(120,70,150,25);
		t2.setFont(f);
		p2.add(t2);


		l4=new JLabel("Menu Type:");
		l4.setBounds(30,100,150,25);
		l4.setFont(f);
		p2.add(l4);

		String[] type={"Starters","Main Course","Deserts"};
         	c1=new JComboBox(type);
  		c1.setBounds(120,100,150,25);
		c1.setFont(f);
		p2.add(c1);


		l5=new JLabel("Menu Rate:");
		l5.setBounds(30,130,150,25);
		l5.setFont(f);
		p2.add(l5);

		t3=new JTextField(20);
  		t3.setBounds(120,130,150,25);
		t3.setFont(f);
		p2.add(t3);

	  
		l6=new JLabel("Status:");
		l6.setBounds(30,160,150,25);
		l6.setFont(f);
		p2.add(l6);


         	r[0]=new JRadioButton("Available");
         	r[1]=new JRadioButton("Unavailable");
      
        
         	grp=new ButtonGroup();

         	r[0].setBounds(120,160,80,25);
         	r[1].setBounds(210,160,100,25);
         
		p2.add(r[0]);
         	p2.add(r[1]);
    

		grp.add(r[0]);
		grp.add(r[1]);

		l7=new JLabel(i);
		l7.setBounds(300,30,80,120);
		l7.setFont(f);
		p2.add(l7);

       
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
					String mcode = t1.getText();
					String mname = t2.getText();
					if(mname.length()==0)
					{
						JOptionPane.showMessageDialog(this,"Menu name field is empty.");
						return;
					}
					for(int i=0;i<mname.length();i++)
					{
						char ch = mname.charAt(i);
						if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch==' ')
						{
						}
					                      
						else
						{				
							JOptionPane.showMessageDialog(this,"Menu Name not valid.");
							return;	
						}
					}

					String mtype=c1.getSelectedItem()+" ";
					String mrate = t3.getText();
					if(mrate.length()==0)
					{
						JOptionPane.showMessageDialog(this,"Menu Rate field is empty.");
						return;
					}
					for(int i=0;i<mrate.length();i++)
					{
						char ch = mrate.charAt(i);
						if(ch>='0' && ch<='9')
						{
						}
					                      
						else
						{				
							JOptionPane.showMessageDialog(this,"Menu Rate not valid.");
							return;	
						}
					}


					String a="";
					if(r[0].isSelected())
					a="available";
					if(r[1].isSelected())
					a="unavailable";

					String query = "insert into menu(mname,mtype,mstatus,mrate) values(?,?,?,?)";
	
					PreparedStatement pstat = con.prepareStatement(query);
					//pstat.setString(1,mcode);
                           			pstat.setString(1,mname);
					pstat.setString(2,mtype);
                           			pstat.setString(3,a);
                           			pstat.setString(4,mrate);
					int row = pstat.executeUpdate();
					if(row>0)
					{
						JOptionPane.showMessageDialog(this,"menu entry successful..");
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
   					String mcode=t1.getText();
					if(mcode.length()==0)
                            		 {
						JOptionPane.showMessageDialog(this,"Search record first");
						return;
					}
				
					String mname=t2.getText();
					String mtype=c1.getSelectedItem()+"";
					Double mrate=Double.parseDouble(t3.getText());
			
					String a="";
					if(r[0].isSelected())
					a="1";
				     	if(r[1].isSelected())
                                        	a="2";


	                      	String query = "update  menu set mname=?,mtype=?,mrate=?,mstatus=? where mcode="+mcode;
				System.out.println("hello"+query);
				PreparedStatement pstat = con.prepareStatement(query);
                          		pstat.setString(1,mname);
				pstat.setString(2,mtype);
                           		pstat.setDouble(3,mrate);
				pstat.setString(4,a);
                           		 
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
   						String mcode=t1.getText();
						if(mcode.length()==0)
                  				           {
							JOptionPane.showMessageDialog(this,"Search record first");
							return;
						}
				
				
	                      			String query = "delete from  menu  where mcode="+mcode;
 

						PreparedStatement pstat = con.prepareStatement(query);
				
                  			                   int row = pstat.executeUpdate();
						if(row>0)
						{
                                         			JOptionPane.showMessageDialog(this,"Record deleted");
                                         			t1.setText("");
				     			t2.setText("");
			         	   			t3.setText("");
		  		    			t4.setText("");
				    			r[0].setSelected(true);
				 			r[1].setSelected(false);

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
					String mcode =JOptionPane.showInputDialog(this,"Enter Menu code to be searched:");
					if(mcode.length()==0)
					{
						JOptionPane.showMessageDialog(this,"Menu Item code. field is empty.");
						return;
					}
					for(int i=0;i<mcode.length();i++)
					{
						char ch = mcode.charAt(i);
						if(ch>='0' && ch<='9')
						{
						}
					                      
						else
						{				
							JOptionPane.showMessageDialog(this,"Menu Item code. not valid.");
							return;	
						}
					}
					if(mcode==null)
					return;
					String Query = "select * from menu where mcode ="+mcode;
					System.out.println(Query);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(Query);
					if(rs.next())
					{
						String name = rs.getString("mname");
						String type= rs.getString("mtype");
						String rate = rs.getString("mrate");
						String status = rs.getString("mstatus");
						t1.setText(""+mcode);
						t2.setText(name);
						c1.setSelectedItem(type);
						t3.setText(rate);
						if(status.equals("1"))
						r[0].setSelected(true);
						else
						r[1].setSelected(true);

 					
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
				t3.setText("");
				
				c1.setSelectedIndex(0);
				
				r[0].setSelected(true);
				r[1].setSelected(false);

				
				b1.setEnabled(true);
			}

}
	public static void main(String[] args)
  		{
           		new Menu();
  		}
 }





























