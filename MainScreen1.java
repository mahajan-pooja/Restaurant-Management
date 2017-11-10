import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.text.*;

public class MainScreen1 extends JFrame implements ActionListener,Runnable
 {
	int flag=0; 
 JButton bemp,bsupp,bpo,binvo,bri,bstock,bmenu,btable,bord,breport,bpwd,blogout,bcu,bgrn,btbstatus,bhelp,bas,repemp,repsupp,reppo,repinvo,repri,repstock,repmenu,reptable,repord,repgrn,repbill;

 JPanel p1,p2;
JDesktopPane dp;
 Connection con;
ImageIcon i,i1,i2;
JLabel l1,l2,l3,l4;
 
Thread t;
Menu m;
Employee emp;
Chgpwd cp;
Createuser cu;
Tables tb;
Orders od;
PO po;
Stock st;
GRN gn;
Rawitem ri;
Supplier sp;
TableStatus tbst;
ReportGRN rgrn;
ReportEmp re;
ReportSupp rs;
ReportPo rp;
ReportRaw rr;
ReportStock rst;
ReportMenu rm;
ReportTables rt;
ReportOrders ro;
ReportInvoice in;
ReportBill bill;

  public MainScreen1(String t)
  {
        super(t);
  	setSize(1363,725);
	setLayout(null);

	Font f = new Font("One Stroke Script LET",Font.ITALIC,18);
	i2=new ImageIcon("food1.png");


	dp = new JDesktopPane();
	dp.setBackground(new Color(255,41,255));
	dp.setBounds(218,10,1120,660);
	dp.setBorder(BorderFactory.createLineBorder(Color.black));
	add(dp);
	i=new ImageIcon("pic.jpg");
	l1=new JLabel(i);
	l1.setBounds(10,3,900,660);
	dp.add(l1);

	p1 = new JPanel(null);
	p1.setBackground(new Color(210,121,255));
	p1.setBounds(10,10,195,660);
	p1.setBorder(BorderFactory.createLineBorder(Color.black));
	add(p1);
	

	/*bemp=new JButton("EMPLOYEE");
	bemp.setBounds(10,20,165,35);
	bemp.setFont(f);
	bemp.addActionListener(this);
	p1.add(bemp);*/

	bsupp=new JButton("SUPPLIER");
	bsupp.setBounds(10,20,165,35);
	bsupp.setFont(f);
	bsupp.addActionListener(this);
	p1.add(bsupp);

	bri=new JButton("RAW ITEM");
	bri.setBounds(10,60,165,35);
	bri.setFont(f);
	bri.addActionListener(this);
     	p1.add(bri);

	bstock=new JButton("STOCK");
         bstock.setBounds(10,100,165,35);
	bstock.setFont(f);
	bstock.addActionListener(this);
	p1.add(bstock);

	bpo=new JButton("PURCHASE ORDER");
	bpo.setBounds(10,140,165,35);
	bpo.setFont(f);
	bpo.addActionListener(this);
	p1.add(bpo);

	bgrn=new JButton("GRN");
	bgrn.setBounds(10,180,165,35);
	bgrn.setFont(f);
	bgrn.addActionListener(this);
	p1.add(bgrn);

	bmenu=new JButton("MENU ITEM");
	bmenu.setBounds(10,220,165,35);
	bmenu.setFont(f);
	bmenu.addActionListener(this);
     	p1.add(bmenu);

	btable=new JButton("TABLES");
         btable.setBounds(10,260,165,35);
	btable.setFont(f);
	btable.addActionListener(this);
	p1.add(btable);

	bord=new JButton(" MENU ORDERS");
	bord.setBounds(10,300,165,35);
	bord.setFont(f);
	bord.addActionListener(this);
	p1.add(bord);

	/*bcu=new JButton("CREATE USER");
	bcu.setBounds(10,340,165,35);
	bcu.setFont(f);
	bcu.addActionListener(this);
	p1.add(bcu);*/

	bpwd=new JButton("CHANGE PASSWORD");
	bpwd.setBounds(10,420,165,35);
	bpwd.setFont(f);
	bpwd.addActionListener(this);
     	p1.add(bpwd);

	breport=new JButton("REPORTS");
         breport.setBounds(10,380,165,35);
	breport.setFont(f);
	breport.addActionListener(this);
	p1.add(breport);

	blogout=new JButton("LOGOUT");
         blogout.setBounds(10,460,165,35);
	blogout.setFont(f);
	blogout.addActionListener(this);
	p1.add(blogout);

	btbstatus=new JButton("TABLE STATUS");
         btbstatus.setBounds(10,340,165,35);
	btbstatus.setFont(f);
	btbstatus.addActionListener(this);
	p1.add(btbstatus);

	bhelp=new JButton("HELP?");
         bhelp.setBounds(10,500,165,35);
	bhelp.setFont(f);
	bhelp.addActionListener(this);
	p1.add(bhelp);

	bas=new JButton("ABOUT US.");
         bas.setBounds(10,540,165,35);
	bas.setFont(f);
	bas.addActionListener(this);
	p1.add(bas);


	
	p2 = new JPanel(null);
	p2.setBackground(new Color(210,121,255));
	p2.setBounds(1263,0,195,660);
	p2.setBorder(BorderFactory.createLineBorder(Color.black));
	dp.add(p2);


	l2=new JLabel("REPORTS");
	l2.setBounds(50,20,150,30);
	l2.setVisible(true);
	l2.setFont(f);
	p2.add(l2);

	l4=new JLabel(i2);
	l4.setBounds(20,500,150,150);
	p2.add(l4);

	repemp=new JButton("Employee");
	repemp.setBounds(10,80,165,35);
	repemp.setFont(f);
	repemp.addActionListener(this);
	p2.add(repemp);

	repsupp=new JButton("Supplier");
	repsupp.setBounds(10,120,165,35);
	repsupp.setFont(f);
	repsupp.addActionListener(this);
	p2.add(repsupp);

	repri=new JButton("Raw Item");
	repri.setBounds(10,160,165,35);
	repri.setFont(f);
	repri.addActionListener(this);
     	p2.add(repri);

	repstock=new JButton("Stock");
         repstock.setBounds(10,200,165,35);
	repstock.setFont(f);
	repstock.addActionListener(this);
	p2.add(repstock);

	reppo=new JButton("Purchase Order");
	reppo.setBounds(10,240,165,35);
	reppo.setFont(f);
	reppo.addActionListener(this);
	p2.add(reppo);

	repgrn=new JButton("GRN");
	repgrn.setBounds(10,280,165,35);
	repgrn.setFont(f);
	repgrn.addActionListener(this);
	p2.add(repgrn);

	repmenu=new JButton("Menu");
	repmenu.setBounds(10,320,165,35);
	repmenu.setFont(f);
	repmenu.addActionListener(this);
     	p2.add(repmenu);

	reptable=new JButton("Table");
         reptable.setBounds(10,360,165,35);
	reptable.setFont(f);
	reptable.addActionListener(this);
	p2.add(reptable);

	repord=new JButton("Orders");
	repord.setBounds(10,400,165,35);
	repord.setFont(f);
	repord.addActionListener(this);
	p2.add(repord);
	
	repinvo=new JButton("Invoice");
	repinvo.setBounds(10,440,165,35);
	repinvo.setFont(f);
	repinvo.addActionListener(this);
	p2.add(repinvo);

	repbill=new JButton("Customer Bill");
	repbill.setBounds(10,480,165,35);
	repbill.setFont(f);
	repbill.addActionListener(this);
	p2.add(repbill);


	setVisible(true);

}
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==bmenu)
	{
		if(m==null || m.isVisible()==false)
		{
			m=new Menu();
			dp.add(m);
		}
		try
		{
			m.setSelected(true);
		}
		catch(Exception ex)
		{
		}
	}

	/*else if(e.getSource()==bemp)
	{
		if(emp==null || emp.isVisible()==false)
		{
			emp=new Employee();
			dp.add(emp);
		}
		try
		{
			emp.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}*/

	else if(e.getSource()==bpwd)
	{
		if(cp==null || cp.isVisible()==false)
		{
			cp=new Chgpwd();
			dp.add(cp);
		}
		try
		{
			cp.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	/*else if(e.getSource()==bcu)
	{
		if(cu==null || cu.isVisible()==false)
		{
			cu=new Createuser();
			dp.add(cu);
		}
		try
		{
			cu.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}*/
	
	else if(e.getSource()==btable)
	{
		if(tb==null || tb.isVisible()==false)
		{
			tb=new Tables();
			dp.add(tb);
		}
		try
		{
			tb.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==bord)
	{
		if(od==null || od.isVisible()==false)
		{
			od=new Orders();
			dp.add(od);
		}
		try
		{
			od.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==bpo)
	{
		if(po==null || po.isVisible()==false)
		{
			po=new PO();
			dp.add(po);
		}
		try
		{
			po.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==bstock)
	{
		if(st==null || st.isVisible()==false)
		{
			st=new Stock();
			dp.add(st);
		}
		try
		{
			st.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==bgrn)
	{
		if(gn==null || gn.isVisible()==false)
		{
			gn=new GRN();
			dp.add(gn);
		}
		try
		{
			gn.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==bri)
	{
		if(ri==null || ri.isVisible()==false)
		{
			ri=new Rawitem();
			dp.add(ri);
		}
		try
		{
			ri.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==bsupp)
	{
		if(sp==null || sp.isVisible()==false)
		{
			sp=new Supplier();
			dp.add(sp);
		}
		try
		{
			sp.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==blogout)
	{
		dispose();
		Login log=new Login();
		dp.add(log);
	}

	else if(e.getSource()==btbstatus)
	{
		if(tbst==null || tbst.isVisible()==false)
		{
			tbst=new TableStatus(dp);
			dp.add(tbst);
		}
		try
		{
			tbst.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==bhelp)
	{
		System.out.println("hello");
		try
		{
			Runtime.getRuntime().exec("C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe C:\\Users\\User\\Documents\\JCreator LE\\MyProjects\\help1.html");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

	else if(e.getSource()==bas)
	{
		System.out.println("hello");
		try
		{
			Runtime.getRuntime().exec("C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe C:\\Users\\User\\Documents\\JCreator LE\\MyProjects\\Aboutus.html");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}


	else if(e.getSource()==breport)
	{
		t =new Thread(this);
		t.start();
	}

	else if(e.getSource()==repemp)
	{
		if(re==null || re.isVisible()==false)
		{
			re=new ReportEmp();
			dp.add(re);
		}
		try
		{
			re.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==repsupp)
	{
		if(rs==null || rs.isVisible()==false)
		{
			rs=new ReportSupp();
			dp.add(rs);
		}
		try
		{
			rs.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==reppo)
	{
		if(rp==null || rp.isVisible()==false)
		{
			rp=new ReportPo();
			dp.add(rp);
		}
		try
		{
			rp.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==repri)
	{
		if(rr==null || rr.isVisible()==false)
		{
			rr=new ReportRaw();
			dp.add(rr);
		}
		try
		{
			rr.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==repstock)
	{
		if(rst==null || rst.isVisible()==false)
		{
			rst=new ReportStock();
			dp.add(rst);
		}
		try
		{
			rst.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==repmenu)
	{
		if(rm==null || rm.isVisible()==false)
		{
			rm=new ReportMenu();
			dp.add(rm);
		}
		try
		{
			rm.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==reptable)
	{
		if(rt==null || rt.isVisible()==false)
		{
			rt=new ReportTables();
			dp.add(rt);
		}
		try
		{
			rt.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==repord)
	{
		if(ro==null || ro.isVisible()==false)
		{
			ro=new ReportOrders();
			dp.add(ro);
		}
		try
		{
			ro.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}


	else if(e.getSource()==repgrn)
	{
		if(rgrn==null || rgrn.isVisible()==false)
		{
			rgrn=new ReportGRN();
			dp.add(rgrn);
		}
		try
		{
			rgrn.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}
	
	else if(e.getSource()==repinvo)
	{
		if(in==null || in.isVisible()==false)
		{
			in=new ReportInvoice();
			dp.add(in);
		}
		try
		{
			in.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}

	else if(e.getSource()==repbill)
	{
		if(bill==null || in.isVisible()==false)
		{
			bill=new ReportBill();
			dp.add(bill);
		}
		try
		{
			bill.setSelected(true);
		}
		catch(Exception ex)
		{
		}

	}


}
	public void run()
	{
		System.out.println(flag+" flag");
		if(flag==0)
		{
			for(int i=1123;i>920;i-=5)
			{
				System.out.println(i+" i");
				p2.setLocation(i,0);
				try
				{
					Thread.sleep(5);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			flag=1;
		}
		else
		{
			for(int i=920;i<1123;i+=5)
			{
				p2.setLocation(i,0);
				try
				{
					Thread.sleep(5);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			flag=0;
		}
	}	
	public static void main(String[] args)
  	{
            MainScreen1 ms1=new MainScreen1("WELCOM SCREEN");
  	}
  }





























































