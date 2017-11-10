import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Chgpwd extends JInternalFrame implements ActionListener
{
	JLabel  l1,l2,l3,l4,l5;
	JTextField  t1;
	JButton b1,b2,b3;
	JPasswordField pwd1,pwd2,pwd3;
	JPanel p1,p2,p3;
	Connection con;

  	public Chgpwd()
  	{
		super("CHANGE PASSWORD");
	  	setSize(450,370);
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,15);

		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,400,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);


  		l1=new JLabel("Change Password");
		l1.setBounds(150,10,200,30);
		l1.setFont(f);
		p1.add(l1);
 
		p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,400,170);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);

		l2=new JLabel("User Name");
		l2.setBounds(30,40,150,25);
		l2.setFont(f);
		p2.add(l2);

         	t1=new JTextField(20);
  		t1.setBounds(180,40,150,25);
		t1.setFont(f);
		p2.add(t1);

         	l3=new JLabel("Current Password");
		l3.setBounds(30,70,150,25);
		l3.setFont(f);
		p2.add(l3);

	         	pwd1=new JPasswordField(20);
		pwd1.setEchoChar('*');
         	pwd1.setBounds(180,70,150,25);
         	p2.add(pwd1);
   
         	l4=new JLabel("New Password");
		l4.setBounds(30,100,150,25);
		l4.setFont(f);
		p2.add(l4);

         	pwd2=new JPasswordField(20);
         	pwd2.setEchoChar('*');
         	pwd2.setBounds(180,100,150,25);
         	p2.add(pwd2);

         	l5=new JLabel("Confirm Password");
		l5.setBounds(30,130,150,25);
		l5.setFont(f);
		p2.add(l5);

         	pwd3=new JPasswordField(20);
         	pwd3.setEchoChar('*');
         	pwd3.setBounds(180,130,150,25);
         	p2.add(pwd3);

		p3 = new JPanel(null);
		p3.setBackground(new Color(159,235,235));
		p3.setBounds(20,250,400,60);
		p3.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p3);


		/*b1=new JButton("SEARCH");
		b1.setBounds(40,20,100,25);
		b1.setFont(f);
		b1.addActionListener(this);
          	p3.add(b1);*/

		b2=new JButton("OK");
		b2.setBounds(100,20,100,25);
		b2.setFont(f);
		b2.addActionListener(this);
		p3.add(b2);

		b3=new JButton("CANCEL");
		b3.setBounds(210,20,100,25);
		b3.setFont(f);
		b3.addActionListener(this);
		p3.add(b3);




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
		/*if(ae.getSource()==b1)
		{
			try
			{

				String id=JOptionPane.showInputDialog(this,"Enter User name.");
				if(id.length()==0)
				return;
				String query="select * from login where username= '"+id+"'";
				System.out.println(query);
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				while(rs.next())
				{
					String pwd=rs.getString("password");
					t1.setText(id);
					pwd1.setText(pwd);
					System.out.println(pwd);
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}*/

		if(ae.getSource()==b2)
		{
			try
			{
				String name=t1.getText();
				if(name.length()==0)
				{
					JOptionPane.showMessageDialog(this,"User Name field is empty.");
					return;
				}
				for(int i=0;i<name.length();i++)
				{
					char ch = name.charAt(i);
					if(ch>='a' && ch<='z' || ch>='A' && ch<='Z' || ch>='0' && ch<='9' || ch=='@' || ch=='#' || ch=='*')
					{
					}
					                      
					else
					{				
						JOptionPane.showMessageDialog(this,"User Name not valid.");
						return;	
					}
				}

				String password=pwd2.getText();
				System.out.println(password);
				String q="update login set password=? where username='"+name+"'";
				System.out.println(q);
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1,password);
				int row=ps.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Password Changed.");
					t1.setText("");
					pwd1.setText("");
					pwd2.setText("");
					pwd3.setText("");

				}
				else
				{
					JOptionPane.showMessageDialog(this,"Password  Not Changed.");

				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==b3)
                   {
                   	
			dispose();
                    }
            
          
  }
   public static void main(String[] args)
  	{
            new Chgpwd();
  	}
  }





















