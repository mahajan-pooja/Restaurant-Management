import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.String.*;
import java.sql.*;


public class Login extends JFrame implements ActionListener

{
	 JLabel  l1,l2,l3,l4;
 	JTextField  t1;
 	JButton b1;
 	JPasswordField pwd;
	JPanel p1,p2,p3;
	Connection con;
	PreparedStatement stat;
	ResultSet rs;
	ImageIcon i;

  public Login()
  {
        super("LOGIN");
  	setSize(450,320);
	setLayout(null);

	Font f = new Font("One Stroke Script LET",Font.ITALIC,18);
	i=new ImageIcon("loginkey.png");


	p1 = new JPanel(null);
	p1.setBackground(new Color(153,51,255));
	p1.setBounds(20,20,400,40);
	p1.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p1);


  	l1=new JLabel("Login Details");
	l1.setBounds(150,10,200,30);
	l1.setFont(f);
	p1.add(l1);
 
         p2 = new JPanel(null);
	p2.setBackground(new Color(204,102,255));
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

         pwd=new JPasswordField(20);
         pwd.setEchoChar('*');
         pwd.setBounds(180,70,150,25);
         p2.add(pwd);

	 l4=new JLabel(i);
	l4.setBounds(30,100,100,100);
	l4.setFont(f);
	p2.add(l4);
   
         b1=new JButton("LOGIN");
         b1.setBounds(180,130,100,25);
	b1.addActionListener(this);
         p2.add(b1);

	

          
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setVisible(true);
         	 try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myproject" ,"root","root");	
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
				String q = "select emptype from login where username=? and password=?";
				stat=con.prepareStatement(q);
				stat.setString(1,t1.getText());
				stat.setString(2,pwd.getText());
				rs=stat.executeQuery();
				if(rs.next())
				{
					String ty = rs.getString("emptype");
					if(ty.equals("owner"))
					{
					MainScreen ms =new MainScreen("MAIN SCREEN");
					JOptionPane.showMessageDialog(null, "Welcome!! Login Successful", "Password", 1);	
					dispose();
					}
					else
					{
						MainScreen1 ms1 =new MainScreen1("MAIN SCREEN");
					JOptionPane.showMessageDialog(null, "Welcome!! Login Successful", "Password", 1);
					dispose();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect Login or Password", "Password", 1);
				}
			}
			catch(Exception ex)
			{
				System.out.println("Unsuccessful Login\n"+ex);
			}
	}
		
}
   public static void main(String[] args)
  	{
            new Login();
  	}
  }




























