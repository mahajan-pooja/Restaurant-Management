import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.io.*;
public class Employee extends JInternalFrame implements ActionListener
{
	JLabel  l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	JTextField  t1,t2,t3,t4,t5;
	JButton b1,b2,b3,b4,b5,b6;
	JComboBox c1,c2,c3,c4,c5,c6,c7;
	JPanel p1,p2,p3;
	Connection con;
	ImageIcon i;
	JTextArea ta;

	public Employee()
	{
		super("EMPLOYEE");
		setSize(650,590);
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,18);
		i=new ImageIcon("c1.png");

		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,600,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);


  		l1=new JLabel("Employee Details");
		l1.setBounds(250,10,150,30);
		l1.setFont(f);
		p1.add(l1);
 
		p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,600,350);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);

        		l2=new JLabel("Employee no.:");
		l2.setBounds(30,40,150,25);
		l2.setFont(f);
		p2.add(l2);

         	t1=new JTextField(20);
  		t1.setBounds(200,40,150,25);
		t1.setFont(f);
		t1.setEditable(false);
		p2.add(t1);

         	l3=new JLabel("Employee Name:");
		l3.setBounds(30,70,150,25);
		l3.setFont(f);
		p2.add(l3);
  

           	t2=new JTextField(20);
  		t2.setBounds(200,70,150,25);
		t2.setFont(f);
		p2.add(t2);

        		l4=new JLabel("Contact no.:");
		l4.setBounds(30,100,150,25);
		l4.setFont(f);
		p2.add(l4);
  
  		t3=new JTextField(20);
  		t3.setBounds(200,100,150,25);
		t3.setFont(f);
		p2.add(t3);

         	l5=new JLabel("Address:");
		l5.setBounds(30,130,150,25);
		l5.setFont(f);
		p2.add(l5);

          	ta=new JTextArea(2,20);
  		ta.setBounds(200,130,150,50);
		ta.setFont(f);
         	ta.setLineWrap(true);
         	ta.setWrapStyleWord(true);
		p2.add(ta);

         	l5=new JLabel("Salary(Rs.):");
		l5.setBounds(30,185,150,25);
		l5.setFont(f);
		p2.add(l5);
  
         	t4=new JTextField(20);
  		t4.setBounds(200,185,150,25);
		t4.setFont(f);
		p2.add(t4);

        		l6=new JLabel("Date of Birth(yy/mm/dd):");
		l6.setBounds(30,230,170,25);
		l6.setFont(f);
		p2.add(l6);

        		String[] year={"1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999"};
         	c1=new JComboBox(year);
         	c1.setBounds(200,230,80,25);
         	p2.add(c1);

         	String[] month={"01","02","03","04","05","06","07","08","09","10","11","12"};
         	c2=new JComboBox(month);
		c2.setBounds(280,230,80,25);
         	p2.add(c2);

		String[] day=											{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
         	c3=new JComboBox(day);
         	c3.setBounds(360,230,90,25);
           	p2.add(c3);

 		l7=new JLabel("Date of Join(yy/mm/dd):");
		l7.setBounds(30,260,185,25);
		l7.setFont(f);
		p2.add(l7);

        		String[] jyear={"2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017"};
         	c4=new JComboBox(jyear);
         	c4.setBounds(200,260,80,25);
           	p2.add(c4);

         	String[] jmonth={"01","02","03","04","05","06","07","08","09","10","11","12"};
         	c5=new JComboBox(jmonth);
           	c5.setBounds(280,260,80,25);
           	p2.add(c5);

 		String[] jday=			{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
         	c6=new JComboBox(jday);
           	c6.setBounds(360,260,90,25);
           	p2.add(c6);

	   	l8=new JLabel("Designation:");
		l8.setBounds(30,290,100,25);
		l8.setFont(f);
		p2.add(l8);

        		String[] type={"Manager","Waiter","Cook","Captain"};
         	c7=new JComboBox(type);
  		c7.setBounds(200,290,100,25);
		c7.setFont(f);
		p2.add(c7);

		l10=new JLabel(i);
		l10.setBounds(380,30,190,190);
		l10.setFont(f);
		p2.add(l10);




         	p3 = new JPanel(null);
		p3.setBackground(new Color(159,235,235));
		p3.setBounds(20,430,600,100);
		p3.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p3);

         	b1=new JButton("SAVE");
          	b1.setBounds(40,20,100,35);
		b1.setFont(f);
		b1.addActionListener(this);
          	p3.add(b1);

          	b2=new JButton("UPDATE");
          	b2.setBounds(180,20,100,35);
		b2.setFont(f);
		b2.addActionListener(this);
         	p3.add(b2);

        	 	b3=new JButton("CLEAR");
          	b3.setBounds(180,60,100,35);
		b3.setFont(f);
		b3.addActionListener(this);
         	p3.add(b3);

         	b4=new JButton("SEARCH");
         	b4.setBounds(300,20,100,35);
		b4.setFont(f);
		b4.addActionListener(this);
         	p3.add(b4);

         	b5=new JButton("DELETE");
         	b5.setBounds(420,20,100,35);
		b5.setFont(f);
		b5.addActionListener(this);
          	p3.add(b5);

		b6=new JButton("CANCEL");
         	b6.setBounds(300,60,100,35);
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
				String eno = t1.getText();
				
				String enm = t2.getText();
				if(enm.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Employee name field is empty.");
					return;
				}
				for(int i=0;i<enm.length();i++)
				{
					char ch = enm.charAt(i);
					if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch==' ')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Employee Name not valid.");
						return;	
					}
				}
				String ect = t3.getText();
				if(ect.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Contact no. field is empty.");
					return;
				}
				for(int i=0;i<ect.length();i++)
				{
					char ch = ect.charAt(i);
					if(ch>='0' && ch<='9')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Contact no. not valid.");
						return;	
					}
				}

				String eadd = ta.getText();
				if(eadd.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Address field is empty.");
					return;
				}
				for(int i=0;i<eadd.length();i++)
				{
					char ch = eadd.charAt(i);
					if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch>='0' && ch<='9' || ch=='/' || ch==' ' || ch==',' || ch=='.')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Address not valid.");
						return;	
					}
				}


				String esalary =t4.getText();
				if(esalary.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Salary field is empty.");
					return;
				}
				double esal=0.0;
				for(int i=0;i<esalary.length();i++)
				{
					try
					{
						esal=Double.parseDouble(esalary);
					}
					catch(Exception e)   
					{				
						JOptionPane.showMessageDialog(this,"Salary not valid.");
						return;	
					}
				}
				esal=Double.parseDouble(esalary);

				String edy=c1.getSelectedItem().toString();
				String edm=c2.getSelectedItem().toString();
				String edd=c3.getSelectedItem().toString();
				String ejy=c4.getSelectedItem().toString();
				String ejm=c5.getSelectedItem().toString();
				String ejd=c6.getSelectedItem().toString();
 				String dtb=edy+"-"+edm+"-"+edd;
                            	String dtj=ejy+"-"+ejm+"-"+ejd;
				String etype=c7.getSelectedItem().toString();



				String query = "insert into employee(empname,contact,address,emptype,salary,dob,doj) values(?,?,?,?,?,?,?)";

				PreparedStatement pstat = con.prepareStatement(query);
		
				pstat.setString(1,enm);
				pstat.setString(2,ect);
				pstat.setString(3,eadd);
				pstat.setString(4,etype);
				pstat.setDouble(5,esal);
				pstat.setString(6,dtb);
				pstat.setString(7,dtj);


				int row = pstat.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Record inserted");
				}
			}
                  		catch(Exception e)
			  {
					System.out.println(e);
			  }
		}

		if(ae.getSource()==b2)
		 {
                  		  try
			  {
   				String empno=t1.getText();
				if(empno.length()==0)
                             	{
					JOptionPane.showMessageDialog(this,"Search record first");
					return;
				}
				
				String name=t2.getText();
				String contact=t3.getText();
			 	String address=ta.getText();
                                     Double salary=Double.parseDouble(t4.getText());
				String edy=c1.getSelectedItem().toString();
				String edm=c2.getSelectedItem().toString();
				String edd=c3.getSelectedItem().toString();
				String ejy=c4.getSelectedItem().toString();
				String ejm=c5.getSelectedItem().toString();
				String ejd=c6.getSelectedItem().toString();
				String dtb=edy+"-"+edm+"-"+edd;
	                             String dtj=ejy+"-"+ejm+"-"+ejd;
				String type=c7.getSelectedItem().toString();

			


	                   		String query = "update  employee set empname=?,contact=?,address=?,salary=?,dob=?,doj=?,emptype=? where empno="+empno;
				System.out.println("hello"+query);
				PreparedStatement pstat = con.prepareStatement(query);
                          		pstat.setString(1,name);
				pstat.setString(2,contact);
                           		pstat.setString(3,address);
				pstat.setDouble(4,salary);
				pstat.setString(5,dtb);
				pstat.setString(6,dtj);
				pstat.setString(7,type);
                           		 
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

		else if(ae.getSource()==b6)
			{
				dispose();	
			}
		
		else if(ae.getSource()==b3)
			{
				t1.setText("");
				t2.setText("");
				t3.setText("");
				ta.setText("");
				t4.setText("");
				c1.setSelectedIndex(0);
				c2.setSelectedIndex(0);
				c3.setSelectedIndex(0);
				c4.setSelectedIndex(0);
				c5.setSelectedIndex(0);
				c6.setSelectedIndex(0);
				c7.setSelectedIndex(0);

				
				b1.setEnabled(true);
			}

					
		else if(ae.getSource()==b4)
                  		 {

				b1.setEnabled(false);
				try
				{
					String empno =JOptionPane.showInputDialog(this,"Enter Employee code to be searched:");
					if(empno.length()==0)
				{
					JOptionPane.showMessageDialog(this,"Employee no. field is empty.");
					return;
				}
				for(int i=0;i<empno.length();i++)
				{
					char ch = empno.charAt(i);
					if(ch>='0' && ch<='9')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"Employee no. not valid.");
						return;	
					}
				}

					if(empno==null)
						return;
					t1.setText(empno);
					String Query = "select * from employee where empno ="+empno;
					System.out.println(Query);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(Query);
					if(rs.next())
					{
						String name = rs.getString("empname");
						String contact= rs.getString("contact");
						String address = rs.getString("address");
 						String salary=rs.getString("salary");
						String dob=rs.getString("dob");
 						System.out.println("dob "+dob);
						String yy = dob.substring(0,dob.indexOf('-'));
						System.out.println("yy "+yy);
						String mm = dob.substring(dob.indexOf('-')+1,dob.lastIndexOf('-'));
						System.out.println("mm "+mm);
						String dd = dob.substring(dob.lastIndexOf('-')+1);
						System.out.println("dd "+dd);
						c1.setSelectedItem(yy);			
						c2.setSelectedItem(mm);		
						c3.setSelectedItem(dd);		

						String doj=rs.getString("doj");
 						System.out.println("doj "+doj);
						String yyj = dob.substring(0,dob.indexOf('-'));
						System.out.println("yy "+yyj);
						String mmj = dob.substring(dob.indexOf('-')+1,dob.lastIndexOf('-'));
						System.out.println("mm "+mmj);
						String ddj = dob.substring(dob.lastIndexOf('-')+1);
						System.out.println("dd "+ddj);
						c4.setSelectedItem(yyj);			
						c5.setSelectedItem(mmj);		
						c6.setSelectedItem(ddj);		
						String emptype= rs.getString("emptype");
						c7.setSelectedItem(emptype);
					
						t2.setText(""+name);
						t3.setText(""+contact);
						ta.setText(""+address);
						t4.setText(""+salary);
					
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
                   try
                     {
   			String empno=t1.getText();
			if(empno.length()==0)
                             {
				JOptionPane.showMessageDialog(this,"Search record first");
				return;
			}
				
	                      String query = "delete from  employee where empno="+empno;
 

				PreparedStatement pstat = con.prepareStatement(query);
				
                                     int row = pstat.executeUpdate();
				if(row>0)
				{
                                         	JOptionPane.showMessageDialog(this,"Record deleted");
                                         	t1.setText("");
				    	t2.setText("");
			         	    	t3.setText("");
		  		    	ta.setText("");
				    	c1.setSelectedIndex(0);
					c2.setSelectedIndex(0);
					c3.setSelectedIndex(0);
					c4.setSelectedIndex(0);
					c5.setSelectedIndex(0);
					c6.setSelectedIndex(0);
					c7.setSelectedIndex(0);
				}
				else
    				System.out.println("Record not deleted.");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}


                    }

	}
		
	
   public static void main(String[] args)
  	{
		new Employee();
  	}
  }





























