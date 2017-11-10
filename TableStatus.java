import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class TableStatus extends JInternalFrame implements ActionListener
{
	JButton  table[];
	JLabel  header;
	JLabel n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19,n20;
	
	JButton b1;
	JPanel p1,p2,p3;
	ImageIcon i,i2;
	Connection con;
         JDesktopPane dp;

  	public TableStatus(JDesktopPane dp)
  	{
		super("Table Status");
	  	this.dp=dp;
		setSize(500,600);
		setLayout(null);

		Font f = new Font("One Stroke Script LET",Font.ITALIC,15);
		i=new ImageIcon("icon1.png");
		i2=new ImageIcon("icon2.png");


		p1 = new JPanel(null);
		p1.setBackground(new Color(159,235,235));
		p1.setBounds(20,20,450,40);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p1);

  		header=new JLabel("Table Status");
		header.setBounds(180,10,200,30);
		header.setFont(f);
		p1.add(header);
 

		p2 = new JPanel(null);
		p2.setBackground(new Color(244,244,90));
		p2.setBounds(20,70,450,400);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p2);

		table=new JButton[20];


		n1=new JLabel("2001");
		n1.setBounds(45,13,72,52);
		n1.setFont(f);
		p2.add(n1);

		table[0]=new JButton(i2);
		table[0].setBounds(30,40,50,50);
		table[0].setFont(f);
		table[0].addActionListener(this);
		p2.add(table[0]);


		n2=new JLabel("2002");
		n2.setBounds(45,86,72,52);
		n2.setFont(f);
		p2.add(n2);

         	table[1]=new JButton(i2);
		table[1].setBounds(30,112,50,50);
		table[1].setFont(f);
		table[1].addActionListener(this);
		p2.add(table[1]);

		n3=new JLabel("2003");
		n3.setBounds(45,159,72,52);
		n3.setFont(f);
		p2.add(n3);

         	table[2]=new JButton(i2);
		table[2].setBounds(30,185,50,50);
		table[2].setFont(f);
		table[2].addActionListener(this);
		p2.add(table[2]);

         	
		n4=new JLabel("2004");
		n4.setBounds(45,230,72,52);
		n4.setFont(f);
		p2.add(n4);

         	table[3]=new JButton(i2);
		table[3].setBounds(30,256,50,50);
		table[3].setFont(f);
		table[3].addActionListener(this);	
		p2.add(table[3]);

		n5=new JLabel("2005");
		n5.setBounds(45,300,72,52);
		n5.setFont(f);
		p2.add(n5);

		table[4]=new JButton(i2);
		table[4].setBounds(30,328,50,50);
		table[4].setFont(f);
		table[4].addActionListener(this);
		p2.add(table[4]);

		n6=new JLabel("2006");
		n6.setBounds(135,13,72,52);
		n6.setFont(f);
		p2.add(n6);

		table[5]=new JButton(i2);
		table[5].setBounds(120,40,50,50);
		table[5].setFont(f);
		table[5].addActionListener(this);
		p2.add(table[5]);

		n7=new JLabel("2007");
		n7.setBounds(135,86,72,52);
		n7.setFont(f);
		p2.add(n7);



		table[6]=new JButton(i2);
		table[6].setBounds(120,112,50,50);
		table[6].setFont(f);
		table[6].addActionListener(this);
		p2.add(table[6]);

		n8=new JLabel("2008");
		n8.setBounds(135,159,72,52);
		n8.setFont(f);
		p2.add(n8);

		table[7]=new JButton(i2);
		table[7].setBounds(120,184,50,50);
		table[7].setFont(f);
		table[7].addActionListener(this);
		p2.add(table[7]);

		n9=new JLabel("2009");
		n9.setBounds(135,230,72,52);
		n9.setFont(f);
		p2.add(n9);

		table[8]=new JButton(i2);
		table[8].setBounds(120,256,50,50);
		table[8].setFont(f);
		table[8].addActionListener(this);
		p2.add(table[8]);

		n10=new JLabel("2010");
		n10.setBounds(135,300,72,52);
		n10.setFont(f);
		p2.add(n10);

		table[9]=new JButton(i2);
		table[9].setBounds(120,328,50,50);
		table[9].setFont(f);
		table[9].addActionListener(this);
		p2.add(table[9]);

		n11=new JLabel("2011");
		n11.setBounds(230,13,72,52);
		n11.setFont(f);
		p2.add(n11);


		table[10]=new JButton(i2);
		table[10].setBounds(210,40,50,50);
		table[10].setFont(f);
		table[10].addActionListener(this);
		p2.add(table[10]);

		n12=new JLabel("2012");
		n12.setBounds(230,86,72,52);
		n12.setFont(f);
		p2.add(n12);

         	table[11]=new JButton(i2);
		table[11].setBounds(210,112,50,50);
		table[11].setFont(f);
		table[11].addActionListener(this);
		p2.add(table[11]);

		n13=new JLabel("2013");
		n13.setBounds(230,159,72,52);
		n13.setFont(f);
		p2.add(n13);

         	table[12]=new JButton(i2);
		table[12].setBounds(210,184,50,50);
		table[12].setFont(f);
		table[12].addActionListener(this);
		p2.add(table[12]);

		n14=new JLabel("2014");
		n14.setBounds(230,230,72,52);
		n14.setFont(f);
		p2.add(n14);

         	
         	table[13]=new JButton(i2);
		table[13].setBounds(210,256,50,50);
		table[13].setFont(f);
		table[13].addActionListener(this);
		p2.add(table[13]);

		n15=new JLabel("2015");
		n15.setBounds(230,300,72,52);
		n15.setFont(f);
		p2.add(n15);


		table[14]=new JButton(i2);
		table[14].setBounds(210,328,50,50);
		table[14].setFont(f);
		table[14].addActionListener(this);
		p2.add(table[14]);

		n16=new JLabel("2016");
		n16.setBounds(320,13,72,52);
		n16.setFont(f);
		p2.add(n16);

		table[15]=new JButton(i2);
		table[15].setBounds(300,40,50,50);
		table[15].setFont(f);
		table[15].addActionListener(this);
		p2.add(table[15]);

		n17=new JLabel("2017");
		n17.setBounds(320,86,72,52);
		n17.setFont(f);
		p2.add(n17);


		table[16]=new JButton(i2);
		table[16].setBounds(300,112,50,50);
		table[16].setFont(f);
		table[16].addActionListener(this);
		p2.add(table[16]);


		n18=new JLabel("2018");
		n18.setBounds(320,159,72,52);
		n18.setFont(f);
		p2.add(n18);

		table[17]=new JButton(i2);
		table[17].setBounds(300,184,50,50);
		table[17].setFont(f);
		table[17].addActionListener(this);
		p2.add(table[17]);
	
		n19=new JLabel("2019");
		n19.setBounds(320,232,72,52);
		n19.setFont(f);
		p2.add(n19);


		table[18]=new JButton(i2);
		table[18].setBounds(300,256,50,50);
		table[18].setFont(f);
		table[18].addActionListener(this);
		p2.add(table[18]);


		n20=new JLabel("2020");
		n20.setBounds(320,300,72,52);
		n20.setFont(f);
		p2.add(n20);

		table[19]=new JButton(i2);
		table[19].setBounds(300,328,50,50);
		table[19].setFont(f);
		table[19].addActionListener(this);
		p2.add(table[19]);		



		p3 = new JPanel(null);
		p3.setBackground(new Color(159,235,235));
		p3.setBounds(20,480,450,60);
		p3.setBorder(BorderFactory.createLineBorder(Color.black));
		add(p3);


		b1=new JButton("EXIT");
		b1.setBounds(180,20,100,25);
		b1.setFont(f);
		b1.addActionListener(this);
		p3.add(b1);

		
		


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setVisible(true);

         	try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myproject" ,"root","root");
			System.out.println("Connection Ok");
			Status();
		}
		catch(Exception e)
		{
			System.out.println("connection error : "+e);
		}
          
  	}

	void Status()
	{
	try
	{
		String q="Select tno from temp";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(q);
		while(rs.next())
		{
			table[rs.getInt("tno")-2001].setIcon(i);
		}		
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}

	public void actionPerformed(ActionEvent ae)
	{
	 	if(ae.getSource()==b1)
                  	 {
		  	
                           	dispose();	
						
		 }
		else if(ae.getSource()==table[0])
		{
			 Orders o1=new Orders("2001");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	

		}
		else if(ae.getSource()==table[1])
		{
			
			 Orders o1=new Orders("2002");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	



		}
		else if(ae.getSource()==table[2])
		{
			 Orders o1=new Orders("2003");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	



		}
		else if(ae.getSource()==table[3])
		{
			 Orders o1=new Orders("2004");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	



		}
		else if(ae.getSource()==table[4])
		{
			 Orders o1=new Orders("2005");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	



		}
		else if(ae.getSource()==table[5])
		{
			 Orders o1=new Orders("2006");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[6])
		{
			 Orders o1=new Orders("2007");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[7])
		{
			 Orders o1=new Orders("2008");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[8])
		{
			 Orders o1=new Orders("2009");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[9])
		{
			 Orders o1=new Orders("2010");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[10])
		{
			 Orders o1=new Orders("2011");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[11])
		{
			 Orders o1=new Orders("2012");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[12])
		{
			 Orders o1=new Orders("2013");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[13])
		{
			 Orders o1=new Orders("2014");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[14])
		{
			 Orders o1=new Orders("2015");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[15])
		{
			 Orders o1=new Orders("2016");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[16])
		{
			 Orders o1=new Orders("2017");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[17])
		{
			 Orders o1=new Orders("2018");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	



		}
		else if(ae.getSource()==table[18])
		{
			 Orders o1=new Orders("2019");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}
		else if(ae.getSource()==table[19])
		{
			 Orders o1=new Orders("2020");
			dp.add(o1);
			try
			{
				o1.setSelected(true);
			}
			catch(Exception e)
			{
			}	


		}

		
	}
   public static void main(String[] args)
  	{
            //new TableStatus();
  	}
  }











