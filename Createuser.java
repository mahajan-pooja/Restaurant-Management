import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Createuser extends JInternalFrame implements ActionListener
{
 	JLabel  l1,l2,l3,l4,l5;
 	JTextField  t1;
	JButton b1,b2,b3;
	JPasswordField pwd1,pwd2;
	JPanel p1,p2;
	Connection con;
	ImageIcon i;
	
	public Createuser()
	  {
	        	super("CREATE USER");
		setSize(450,320);
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,15);
		i=new ImageIcon("e1.png");


		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,400,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);

	
	  	l1=new JLabel("Create User");
		l1.setBounds(150,10,200,30);
		l1.setFont(f);
		p1.add(l1);
 
	        	p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,400,200);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);
			
        		l2=new JLabel("User ID");
		l2.setBounds(30,40,150,25);
		l2.setFont(f);
		p2.add(l2);

         	t1=new JTextField(20);
  		t1.setBounds(180,40,150,25);
		t1.setFont(f);
		p2.add(t1);

         	l3=new JLabel("Password");
		l3.setBounds(30,70,150,25);
		l3.setFont(f);
		p2.add(l3);

         	pwd1=new JPasswordField(20);
         	pwd1.setEchoChar('*');
         	pwd1.setBounds(180,70,150,25);
         	p2.add(pwd1);
   
         	l4=new JLabel("Confirm Password");
		l4.setBounds(30,100,150,25);
		l4.setFont(f);
		p2.add(l4);

         	pwd2=new JPasswordField(20);
         	pwd2.setEchoChar('*');
         	pwd2.setBounds(180,100,150,25);
         	p2.add(pwd2);

		l5=new JLabel(i);
		l5.setBounds(30,110,90,90);
		l5.setFont(f);
		p2.add(l5);

	
         	b1=new JButton("SAVE");
         	b1.setBounds(150,150,100,25);
		b1.setFont(f);
		b1.addActionListener(this);
          	p2.add(b1);

          	b2=new JButton("CLOSE");
          	b2.setBounds(270,150,100,25);
		b2.setFont(f);
	       	b2.addActionListener(this);
		p2.add(b2);

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
					String username = t1.getText();
					if(username.length()==0)
					{
						JOptionPane.showMessageDialog(this,"User ID field is empty.");
						return;
					}
					for(int i=0;i<username.length();i++)
					{
						char ch = username.charAt(i);
						if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch>=0 && ch<=9)
						{
						}
					                      
						else
						{				
							JOptionPane.showMessageDialog(this,"User ID not valid.");
							return;	
						}
					}

					String password = pwd1.getText();
					String manager="manager";
					
					String q="Select username from login";
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(q);
					while(rs.next())
					{
						if(username.equals(rs.getString("username")))
						{
							JOptionPane.showMessageDialog(this,"Username already exists,try again..!!");
							t1.setText("");
							pwd1.setText("");
							pwd2.setText("");
							return;

						}				
					}
					String query = "insert into login(username,password,emptype) values(?,?,?)";
					
					PreparedStatement pstat = con.prepareStatement(query);
					pstat.setString(1,username);
                           			pstat.setString(2,password);
					pstat.setString(3,manager);


					int row = pstat.executeUpdate();
					if(row>0)
					{
						JOptionPane.showMessageDialog(this,"new user created");
							t1.setText("");
							pwd1.setText("");
							pwd2.setText("");

					}
				}
				catch(Exception e)
				{
					System.out.println();
				}
			}
		else if(ae.getSource()==b2)
                   {
                   	
			dispose();
                    }
	}
   public static void main(String[] args)
  	{
           new Createuser();
  	}
  }
	























