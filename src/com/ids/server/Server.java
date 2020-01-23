package com.ids.server;


import java.awt.Color;

import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


import com.ids.constants.Constants;
import com.ids.dao.LoginDao;
import com.ids.login.Login;
import com.ids.mail.service.CL_SendMail;
import com.ids.support.Layers;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;



public class Server extends JFrame
{
	static String userid="";
	private JPanel outerjpanel,block_panel;
	private JPanel innerjpanel;
	private JPanel userregjpanel;
	private JPanel cpjpanel;

	
	private JButton innerjp_register_btn,unblock_btn;
	private JButton innerjp_user_btn;
	private JButton innerjp_f_file_btn;
	private JButton innerjp_f_log_btn,block_panel_close_btn;
	private JButton innerjp_f_blockip_btn;
	private JButton innerjp_cp_btn;
	private JButton innerjp_logout_btn;
	
	private JLabel userreg_p_name_lbl;
	private JLabel userreg_p_id_lbl;
	private JLabel userreg_p_pwd_lbl;

	private JLabel userreg_p_email_lbl;
	private JLabel userreg_p_ip_lbl;
	private JLabel userreg_p_mac_lbl;

	
	private JTextField userreg_p_name_tfield;
	private JTextField userreg_p_id_tfield;
	private JPasswordField userreg_p_pwd_pfield;

	private JTextField userreg_p_email_tfield;
	private JTextField userreg_p_ip_tfield;
	private JTextField userreg_p_mac_tfield;

	private JButton userregjpanel_insert_btn;
	private JButton userregjpanel_close_btn;
	
	private JButton cpjp_change_btn,un_block_btn;
	private JButton cpjp_close_btn;
	
	Socket  connection1,connection2;
	
	public JTextField uplaodjp_browse_tfield; 

	private JLabel cpjp_uid_lbl;
	private JLabel cpjp_old_pwd_lbl;
	private JLabel cpjp_new_pwd_lbl;
	private JLabel cpjp_conform_pwd_lbl;
	
	private JTextField cpjp_uid_tfield;
	private JPasswordField cpjp_old_pwd_pfield;
	private JPasswordField cpjp_new_pwd_pfield;
	private JPasswordField cpjp_conform_pwd_pfield;
	
	
	JComboBox file_type_combo ;

	private JLabel background_img_lbl;
	private JLabel title_img_lbl;
	private JLabel menu_img_lbl;
	private JLabel panel_img_lbl;
	private JLabel label_Userid=null;
	
	public JFileChooser chooser ;
	public File curFile;
	
	public static String server_IP;
	public static int server_port;
	
	public static String server_IP1;
	public static int server_port1;
	
	public Socket user;
	JComboBox Jc_box;
	BufferedOutputStream server_bos = null;
	BufferedOutputStream file_bos = null;
	
	byte server_byteArray[] = new byte[1024];
	byte file_byteArray[] = new byte[1024];
	
	String fl_transfer_time;
	String fl_transfer_date;
	String fl_transfer_group;
	String received_data1;
	String file_type;

	  
	URL background_img_url,title_img_url,menu_img_url,panel_img_url,upload_btn_img_url,download_btn_img_url,ftransfer_btn_img_url,details_btn_img_url,cp_btn_img_url,logout_btn_img_url,logfile_btn_img_url,block_btn_img_url;
	Image i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13;
	Image background_img,title_img,menu_img,panel_img,upload_btn_img,download_btn_img,ftransfer_btn_img,details_btn_img,cp_btn_img,logout_btn_img;
	ImageIcon title_img_icon,background_img_icon,menu_img_icon,panel_img_icon,upload_img_btn_icon,download_img_btn_icon,ftransfer_img_btn_icon,details_img_btn_icon,cp_img_btn_icon,logout_img_btn_icon;
	
	
	
	public Server()throws HeadlessException, IOException
	{
		super();
		try
        {
			UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
            UIManager.put("Menu.background", Color.GREEN);
			
        }
        catch(Exception exception1) { }
        setLayout(null);
		initialize();
		
		Thread t1 = new Thread(new PortListener1(Constants.S_FNAME_PORT_NUM));
		t1.setName("Listener-" + Constants.S_FNAME_PORT_NUM);
		t1.start();
		
		Thread t2 = new Thread(new PortListener2(Constants.S_UPLOAD_PORT_NUM));
		t2.setName("Listener-" + Constants.S_UPLOAD_PORT_NUM);
		t2.start();
	}
	
	
	
	public static void setUserID(String xuserid)
	{
		userid=xuserid;
	}

	public static String getUserID()
	{	
		return userid;
	}
	private void initialize() throws IOException 
	{
		this.setSize(new java.awt.Dimension(800,675));
		this.setLocationRelativeTo(null);
		this.setContentPane(getOuterJPanel());
		this.setTitle("Server Frame");
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		
	}

	private JPanel getOuterJPanel() throws IOException 
	{
		if(outerjpanel==null)
		{
			
			title_img_lbl = new JLabel();
			title_img_url = Server.class.getResource("title.jpg");
			i5 = ImageIO.read(title_img_url); 
			title_img = i5.getScaledInstance(750,100, java.awt.Image.SCALE_SMOOTH);
			title_img_icon = new ImageIcon(title_img);
			title_img_lbl.setBounds(-1,-150,1000,400);
			title_img_lbl.setIcon(title_img_icon);
			title_img_lbl.setVisible(true);
			
			background_img_lbl = new JLabel();
			background_img_url = Server.class.getResource("Blue-Widescreen.jpeg");
			i1 = ImageIO.read(background_img_url); 
			background_img = i1.getScaledInstance(1020,900, java.awt.Image.SCALE_SMOOTH);
			background_img_icon = new ImageIcon(background_img);
			background_img_lbl.setBounds(-35,-150,1000,850);
			background_img_lbl.setIcon(background_img_icon);
			background_img_lbl.setVisible(true);
			
			label_Userid = new JLabel();
			label_Userid.setBounds(new java.awt.Rectangle(570,100,800,27));
			label_Userid.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 12));
			label_Userid.setForeground(new Color(22,242,220));
			label_Userid.setText("Welcome, "+getUserID());
			
			outerjpanel = new JPanel();
			outerjpanel.setLayout(null);
			outerjpanel.add(label_Userid);
			outerjpanel.add(getInnerJPanel());
			outerjpanel.add(getuserregjpanel());
			outerjpanel.add(getunblockpanel());
			outerjpanel.add(getCPJPanel());
		outerjpanel.add(title_img_lbl);
			outerjpanel.add(background_img_lbl);
		}
		
		return outerjpanel;
	}


	private JPanel getInnerJPanel() throws IOException 
	{
		if(innerjpanel==null)
		{
			menu_img_lbl = new JLabel();
			menu_img_url = Server.class.getResource("menu.png");
			i2 = ImageIO.read(menu_img_url); 
			menu_img = i2.getScaledInstance(100,750, java.awt.Image.SCALE_SMOOTH);
			menu_img_icon = new ImageIcon(menu_img);
			menu_img_lbl.setBounds(-1,0,90,700);
			menu_img_lbl.setIcon(menu_img_icon);
			menu_img_lbl.setVisible(true);
			
			
			innerjpanel = new JPanel();
			innerjpanel.setLayout(null);
			innerjpanel.setBounds(new java.awt.Rectangle(0,100,90,550));
			innerjpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new java.awt.Color(191,163,159)));
			
			
			innerjpanel.add(getRegisterJButton());
			innerjpanel.add(getViewUserDetJButton());
			innerjpanel.add(getViewFileDetJButton());
			innerjpanel.add(getViewLogDetJButton());
			innerjpanel.add(getViewBlockIPJButton());
			innerjpanel.add(getuserregjpanel());
			innerjpanel.add(getunblockclientsjbutton());
			innerjpanel.add(getChangePwdJButton());
			innerjpanel.add(getLogoutJButton());
			
			innerjpanel.add(menu_img_lbl);
			
			
		}
	
		return innerjpanel;
	}
	
	private JButton getunblockclientsjbutton() throws IOException 
	{
		if(un_block_btn==null)
		{
			URL un_block_btn_url= Server.class.getResource("block1.jpg");
			Image i11 = ImageIO.read(un_block_btn_url);
			//Image logfile_btn_img = i11.getScaledInstance(80,50,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon img_btn_icon = new ImageIcon(i11);
			
			
			un_block_btn = new JButton("",img_btn_icon);
			un_block_btn.setToolTipText("Un-block");
			un_block_btn.setBounds(new java.awt.Rectangle(10,460,70,50));
			un_block_btn.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					block_panel.setVisible(true);
				}
				
			});
		}
		return un_block_btn;
	}

	private JPanel getunblockpanel() throws IOException 
	{
		if(block_panel==null)
		{
			
			panel_img_lbl = new JLabel();
			panel_img_url = Server.class.getResource("panel.png");
			i6 = ImageIO.read(panel_img_url); 
			panel_img = i6.getScaledInstance(1000,900, java.awt.Image.SCALE_SMOOTH);
			panel_img_icon = new ImageIcon(panel_img);
			panel_img_lbl.setBounds(-20,-100,1000,850);
			panel_img_lbl.setIcon(panel_img_icon);
			panel_img_lbl.setVisible(true);
			
			cpjp_uid_lbl = new JLabel();
			cpjp_uid_lbl.setBounds(new java.awt.Rectangle(50,50,100,27));
			cpjp_uid_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_uid_lbl.setForeground(new Color(191,163,159));
			cpjp_uid_lbl.setText("User ID    :");
			
			/*cpjp_uid_tfield = new JTextField();
			cpjp_uid_tfield.setBounds(new java.awt.Rectangle(160,50,90,25));
			cpjp_uid_tfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_uid_tfield.setForeground(new Color(191,163,159));
			cpjp_uid_tfield.setEditable(false);
			*/
			
			Jc_box = new JComboBox();
			Jc_box.setVisible(true);
			ArrayList al =(ArrayList) LoginDao.getblockipdetails();
			
			for(int i = 0;i<al.size();i++)
			{
				String user_id = (String) al.get(i);
				Jc_box.setBounds(160,50,90,25);
				
				System.out.println(" blocked  : "+user_id);
				Jc_box.removeItem(user_id);
				Jc_box.addItem(user_id);
				
				
			}
	        
			block_panel = new JPanel();
			block_panel.setLayout(null);
			block_panel.setBounds(new java.awt.Rectangle(250,170,300,350));
			block_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new java.awt.Color(191,163,159)));
			block_panel.setVisible(false);
			
			block_panel.add(cpjp_uid_lbl);
			block_panel.add(Jc_box);
			
			//block_panel.add(cpjp_uid_tfield);
		
			block_panel.add(getunblockJButton());
			block_panel.add(getunblockcloseJButton());
			block_panel.add(panel_img_lbl);
			
		}
		return block_panel;
	}


	private JButton getunblockcloseJButton() 
	{
		
		if(block_panel_close_btn==null)
		{
			block_panel_close_btn = new JButton("Close");
			block_panel_close_btn.setBounds(new java.awt.Rectangle(120,300,75,25));
			block_panel_close_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
					block_panel.setVisible(false);
				}
				
			});
			
		}
		return block_panel_close_btn;
	}

	private JButton getunblockJButton() 
	{
		
		if(unblock_btn==null)
		{
			unblock_btn=new JButton("unblock");
			
			unblock_btn.setBounds(new java.awt.Rectangle(200,300,75,25));
			unblock_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					String name=Jc_box.getSelectedItem().toString();
					System.out.println("name is "+name);
					boolean flag=LoginDao.unblock(name);
					if(flag)
					{
						Jc_box.removeItem(Jc_box.getSelectedItem().toString());
						JOptionPane.showMessageDialog(null, "Deleted");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Not Deleted");
					}
				}
				
			});
		}
		
		
		
		return unblock_btn;
		
		
		
		
	}



	private JButton getRegisterJButton() throws IOException 
	{
		if(innerjp_register_btn==null)
		{
			upload_btn_img_url = Server.class.getResource("reg.png");
			i8 = ImageIO.read(upload_btn_img_url);
			//upload_btn_img = i8.getScaledInstance(100,60,  java.awt.Image.SCALE_SMOOTH);
			upload_img_btn_icon = new ImageIcon(i8);
			
			
			innerjp_register_btn = new JButton("",upload_img_btn_icon);
			innerjp_register_btn.setToolTipText("User Register");
			innerjp_register_btn.setBounds(new java.awt.Rectangle(10,20,70,50));
			innerjp_register_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					userregjpanel.setVisible(true);
					cpjpanel.setVisible(false);
				}
				
			});
		}
		
		return innerjp_register_btn;
	}

	private JButton getViewUserDetJButton() throws IOException 
	{
		if(innerjp_user_btn==null)
		{
			download_btn_img_url = Server.class.getResource("userdetails.png");
			i9 = ImageIO.read(download_btn_img_url);
			//download_btn_img = i9.getScaledInstance(80,50,  java.awt.Image.SCALE_SMOOTH);
			download_img_btn_icon = new ImageIcon(i9);
			
			
			innerjp_user_btn = new JButton("",download_img_btn_icon);
			innerjp_user_btn.setToolTipText("User Details");
			innerjp_user_btn.setBounds(new java.awt.Rectangle(10,85,70,50));
			innerjp_user_btn.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					userregjpanel.setVisible(false);
					cpjpanel.setVisible(false);
					ViewDetails.viewUserDetails();
					
				}
				
			});
		}
			
		return innerjp_user_btn;
	}

	private JButton getViewFileDetJButton () throws IOException 
	{
		if(innerjp_f_file_btn==null)
		{
			details_btn_img_url = Server.class.getResource("viewfile.png");
			i11 = ImageIO.read(details_btn_img_url);
		//	details_btn_img = i11.getScaledInstance(80,50,  java.awt.Image.SCALE_SMOOTH);
			details_img_btn_icon = new ImageIcon(i11);
			
			
			innerjp_f_file_btn = new JButton("",details_img_btn_icon);
			innerjp_f_file_btn.setToolTipText("View File Details");
			innerjp_f_file_btn.setBounds(new java.awt.Rectangle(10,150,70,50));
			innerjp_f_file_btn.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					userregjpanel.setVisible(false);
					cpjpanel.setVisible(false);
					ViewDetails.viewFileDetails();
				}
				
			});
		}
		return innerjp_f_file_btn;
	}
	
	private JButton getViewLogDetJButton () throws IOException 
	{
		if(innerjp_f_log_btn==null)
		{
			logfile_btn_img_url = Server.class.getResource("log_file_icon.jpg");
			i11 = ImageIO.read(logfile_btn_img_url);
			//Image logfile_btn_img = i11.getScaledInstance(80,50,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon img_btn_icon = new ImageIcon(i11);
			
			
			innerjp_f_log_btn = new JButton("",img_btn_icon);
			innerjp_f_log_btn.setToolTipText("View LogFile Details");
			innerjp_f_log_btn.setBounds(new java.awt.Rectangle(10,215,70,50));
			innerjp_f_log_btn.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					userregjpanel.setVisible(false);
					cpjpanel.setVisible(false);
					ViewDetails.viewLogFileDetails();
				}
				
			});
		}
		return innerjp_f_log_btn;
	}
	
	private JButton getViewBlockIPJButton () throws IOException 
	{
		if(innerjp_f_blockip_btn==null)
		{
			block_btn_img_url= Server.class.getResource("block1.jpg");
			Image i11 = ImageIO.read(block_btn_img_url);
			//Image logfile_btn_img = i11.getScaledInstance(80,50,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon img_btn_icon = new ImageIcon(i11);
			
			
			innerjp_f_blockip_btn = new JButton("",img_btn_icon);
			innerjp_f_blockip_btn.setToolTipText("View Blocked IP Details");
			innerjp_f_blockip_btn.setBounds(new java.awt.Rectangle(10,280,70,50));
			innerjp_f_blockip_btn.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					userregjpanel.setVisible(false);
					cpjpanel.setVisible(false);
					ViewDetails.viewBlockDetails();
				}
				
			});
		}
		return innerjp_f_blockip_btn;
	}

	

	
	private JButton getChangePwdJButton() throws IOException 
	{
		if(innerjp_cp_btn==null)
		{
			
			cp_btn_img_url = Server.class.getResource("cp_btn.png");
			i12 = ImageIO.read(cp_btn_img_url);
			cp_btn_img = i12.getScaledInstance(80,50,  java.awt.Image.SCALE_SMOOTH);
			cp_img_btn_icon = new ImageIcon(cp_btn_img);
			
			innerjp_cp_btn = new JButton("",cp_img_btn_icon);
			innerjp_cp_btn.setToolTipText("Change Password");
			innerjp_cp_btn.setBounds(new java.awt.Rectangle(10,340,70,50));
			innerjp_cp_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					userregjpanel.setVisible(false);
					cpjpanel.setVisible(true);
				}
				
			});
		}
		
		return innerjp_cp_btn;
	}

	private JButton getLogoutJButton() throws IOException
	{
		if(innerjp_logout_btn==null)
		{
			logout_btn_img_url = Server.class.getResource("logout_btn.png");
			i13 = ImageIO.read(logout_btn_img_url);
			logout_btn_img = i13.getScaledInstance(70,80,  java.awt.Image.SCALE_SMOOTH);
			logout_img_btn_icon = new ImageIcon(logout_btn_img);
			
			
			innerjp_logout_btn = new JButton("",logout_img_btn_icon);
			innerjp_logout_btn.setToolTipText("Logout");
			innerjp_logout_btn.setBounds(new java.awt.Rectangle(10,405,70,50));
			innerjp_logout_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					try 
					{
						dispose();
						new Login().setVisible(true);						
					}
					catch (HeadlessException e1) 
					{
						e1.printStackTrace();
					}
					catch (IOException e1) 
					{
						
						e1.printStackTrace();
					}
				}
				
			});
		}
		return innerjp_logout_btn;
	}
	
	
	private JPanel getuserregjpanel() throws IOException 
	{
		if(userregjpanel==null)
		{
			panel_img_lbl = new JLabel();
			panel_img_url = Server.class.getResource("panel.png");
			i6 = ImageIO.read(panel_img_url); 
			panel_img = i6.getScaledInstance(1000,900, java.awt.Image.SCALE_SMOOTH);
			panel_img_icon = new ImageIcon(panel_img);
			panel_img_lbl.setBounds(-20,-100,1000,850);
			panel_img_lbl.setIcon(panel_img_icon);
			panel_img_lbl.setVisible(true);
			
			userreg_p_name_lbl = new JLabel();
			userreg_p_name_lbl.setBounds(new java.awt.Rectangle(50,30,120,27));
			userreg_p_name_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_name_lbl.setForeground(new Color(191,163,159));
			userreg_p_name_lbl.setText("Name");
			
			userreg_p_id_lbl = new JLabel();
			userreg_p_id_lbl.setBounds(new java.awt.Rectangle(50,80,120,27));
			userreg_p_id_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_id_lbl.setForeground(new Color(191,163,159));
			userreg_p_id_lbl.setText("Login Id");
			
			userreg_p_ip_lbl = new JLabel();
			userreg_p_ip_lbl.setBounds(new java.awt.Rectangle(50,230,120,27));
			userreg_p_ip_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_ip_lbl.setForeground(new Color(191,163,159));
			userreg_p_ip_lbl.setText("Ip Address");
			
				
			userreg_p_pwd_lbl = new JLabel();
			userreg_p_pwd_lbl.setBounds(new java.awt.Rectangle(50,130,120,27));
			userreg_p_pwd_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_pwd_lbl.setForeground(new Color(191,163,159));
			userreg_p_pwd_lbl.setText("Password");
	
			userreg_p_email_lbl = new JLabel();
			userreg_p_email_lbl.setBounds(new java.awt.Rectangle(50,180,120,27));
			userreg_p_email_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_email_lbl.setForeground(new Color(191,163,159));
			userreg_p_email_lbl.setText("E-mail");
			
			userreg_p_mac_lbl = new JLabel();
			userreg_p_mac_lbl.setBounds(new java.awt.Rectangle(50,280,120,27));
			userreg_p_mac_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_mac_lbl.setForeground(new Color(191,163,159));
			userreg_p_mac_lbl.setText("MAC Address");
			
			
			userreg_p_name_tfield = new JTextField();
			userreg_p_name_tfield.setBounds(new java.awt.Rectangle(170,30,130,27));
			userreg_p_name_tfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_name_tfield.setForeground(new Color(191,163,159));

			userreg_p_id_tfield = new JTextField();
			userreg_p_id_tfield.setBounds(new java.awt.Rectangle(170,80,130,27));
			userreg_p_id_tfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_id_tfield.setForeground(new Color(191,163,159));

			userreg_p_ip_tfield = new JTextField();
			userreg_p_ip_tfield.setBounds(new java.awt.Rectangle(170,230,130,27));
			userreg_p_ip_tfield.setForeground(new Color(191,163,159));

			
			userreg_p_pwd_pfield = new JPasswordField();
			userreg_p_pwd_pfield.setBounds(new java.awt.Rectangle(170,130,130,27));
			userreg_p_pwd_pfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_pwd_pfield.setForeground(new Color(191,163,159));

			
			
			userreg_p_email_tfield = new JTextField();
			userreg_p_email_tfield.setBounds(new java.awt.Rectangle(170,180,130,27));
			userreg_p_email_tfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_email_tfield.setForeground(new Color(191,163,159));

			
			userreg_p_mac_tfield = new JTextField();
			userreg_p_mac_tfield.setBounds(new java.awt.Rectangle(170,280,130,27));
			userreg_p_mac_tfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			userreg_p_mac_tfield.setForeground(new Color(191,163,159));

			
			userregjpanel = new JPanel();
			userregjpanel.setLayout(null);
			userregjpanel.setBounds(new java.awt.Rectangle(200,130,350,400));
			userregjpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new java.awt.Color(191,163,159)));
			userregjpanel.setVisible(false);
			userregjpanel.add(userreg_p_name_lbl);
			userregjpanel.add(userreg_p_id_lbl);
			userregjpanel.add(userreg_p_pwd_lbl);


			userregjpanel.add(userreg_p_email_lbl);
			//userregjpanel.add(userreg_p_ip_lbl);
			//userregjpanel.add(userreg_p_mac_lbl);

			
			userregjpanel.add(userreg_p_name_tfield);
			userregjpanel.add(userreg_p_id_tfield);

			userregjpanel.add(userreg_p_pwd_pfield);

			userregjpanel.add(userreg_p_email_tfield);
			//userregjpanel.add(userreg_p_ip_tfield);
			//userregjpanel.add(userreg_p_mac_tfield);

			
			userregjpanel.add(getInsertJButton());
			userregjpanel.add(getRegCloseJButton());
			userregjpanel.add(panel_img_lbl);
			
			return userregjpanel;
			
		}
		return userregjpanel;
	}

	private JButton getInsertJButton() 
	{
		if(userregjpanel_insert_btn==null)
		{
			userregjpanel_insert_btn = new JButton("Insert");
			userregjpanel_insert_btn.setBounds(new java.awt.Rectangle(70,330,75,25));
			userregjpanel_insert_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					String name=userreg_p_name_tfield.getText();
					String id=userreg_p_id_tfield.getText();
					String pwd=userreg_p_pwd_pfield.getText().trim();


					String email=userreg_p_email_tfield.getText();
					//String ip=userreg_p_ip_tfield.getText();
					//String mac=userreg_p_mac_tfield.getText();
			
						
					LoginDao u_dao = new LoginDao();
					
					if(name.trim().equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter User Name", "Login Validation",JOptionPane.WARNING_MESSAGE);
					}
					else if(id.trim().equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter Login Id", "Login Validation",JOptionPane.WARNING_MESSAGE);
					}
					else if(pwd.trim().equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter Password", "Login Validation",JOptionPane.WARNING_MESSAGE);
					}
					else if(email.trim().equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter Email Id", "Login Validation",JOptionPane.WARNING_MESSAGE);
					}
								
					else
					{
						boolean userAlreadyExist=u_dao.getExistingId(id);
						if(userAlreadyExist)
						{
							JOptionPane.showMessageDialog(null,"Member Already Exist", "Member Exist Status",JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							try 
							{
								String path="User_details/"+id+"_Keys.txt";
								FileOutputStream fout;
								fout = new FileOutputStream(path);
								PrintStream write = new PrintStream(fout);
								write.println("User ID :"+id+"\n" );
								write.println("User Password :"+pwd+"\n" );
								boolean mail_flag=CL_SendMail.sendPersonalizedMail(email, Constants.FROM_EMAIL, Constants.FROM_EMAIL_PASSWORD, Constants.REGISTERATION_FORM_SUB, Constants.REGISTERATION_FORM_MSG,path, Constants.HOST_NAME, Constants.HOST_PORT_NUMB);
								if(mail_flag)
								{
									boolean insertFlag=u_dao.insertUser(id, pwd, name, email);
									if(insertFlag)
									{
										System.out.println("User Registered with CA Succesfully");
										System.out.println("CA Genernated user public key and private key.");
										
										JOptionPane.showMessageDialog(null,"Member Inserted Succesfully", "Member Insertion Status",JOptionPane.WARNING_MESSAGE);
										userreg_p_name_tfield.setText("");
										userreg_p_id_tfield.setText("");
										userreg_p_pwd_pfield.setText("");
			
										userreg_p_email_tfield.setText("");
										userreg_p_ip_tfield.setText("");
										userreg_p_mac_tfield.setText("");
							
									}
									else
									{
										JOptionPane.showMessageDialog(null,"Member Insertion Failed", "Member Insertion Status",JOptionPane.WARNING_MESSAGE);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Internet connection or Mailid Failed", "Member Insertion Status",JOptionPane.WARNING_MESSAGE);
								}
							} 
							catch (FileNotFoundException e1) {e1.printStackTrace();}
						}
					}
				}
			});
		}
		return userregjpanel_insert_btn;
	}
	
	private JButton getRegCloseJButton() 
	{
		if(userregjpanel_close_btn==null)
		{
			userregjpanel_close_btn = new JButton("Close");
			userregjpanel_close_btn.setBounds(new java.awt.Rectangle(200,330,75,25));
			userregjpanel_close_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					userregjpanel.setVisible(false);
				}
				
			});
			
		}
		return userregjpanel_close_btn;
	}
	
	
	
	private JPanel getCPJPanel() throws IOException 
	{
		if(cpjpanel==null)
		{
			
			panel_img_lbl = new JLabel();
			panel_img_url = Server.class.getResource("panel.png");
			i6 = ImageIO.read(panel_img_url); 
			panel_img = i6.getScaledInstance(1000,900, java.awt.Image.SCALE_SMOOTH);
			panel_img_icon = new ImageIcon(panel_img);
			panel_img_lbl.setBounds(-20,-100,1000,850);
			panel_img_lbl.setIcon(panel_img_icon);
			panel_img_lbl.setVisible(true);
			
			
			cpjp_uid_lbl = new JLabel();
			cpjp_uid_lbl.setBounds(new java.awt.Rectangle(50,50,100,27));
			cpjp_uid_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_uid_lbl.setForeground(new Color(191,163,159));
			cpjp_uid_lbl.setText("User ID    :");
			
			
			cpjp_old_pwd_lbl = new JLabel();
			cpjp_old_pwd_lbl.setBounds(new java.awt.Rectangle(50,100,100,27));
			cpjp_old_pwd_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_old_pwd_lbl.setForeground(new Color(191,163,159));
			cpjp_old_pwd_lbl.setText("Old Pwd   :");
			
			
			cpjp_new_pwd_lbl = new JLabel();
			cpjp_new_pwd_lbl.setBounds(new java.awt.Rectangle(50,150,100,27));
			cpjp_new_pwd_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_new_pwd_lbl.setForeground(new Color(191,163,159));
			cpjp_new_pwd_lbl.setText("New Pwd  :");
			
			
			cpjp_conform_pwd_lbl = new JLabel();
			cpjp_conform_pwd_lbl.setBounds(new java.awt.Rectangle(50,200,100,27));
			cpjp_conform_pwd_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_conform_pwd_lbl.setForeground(new Color(191,163,159));
			cpjp_conform_pwd_lbl.setText("Conform   :");
			
			
			
			cpjp_uid_tfield = new JTextField();
			cpjp_uid_tfield.setBounds(new java.awt.Rectangle(160,50,90,25));
			cpjp_uid_tfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_uid_tfield.setForeground(new Color(191,163,159));
			cpjp_uid_tfield.setEditable(false);
			cpjp_uid_tfield.setText(getUserID());
			
			
			
			cpjp_old_pwd_pfield = new JPasswordField();
			cpjp_old_pwd_pfield.setBounds(new java.awt.Rectangle(160,100,90,25));
			cpjp_old_pwd_pfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_old_pwd_pfield.setForeground(new Color(191,163,159));
			
			
			
			cpjp_new_pwd_pfield = new JPasswordField();
			cpjp_new_pwd_pfield.setBounds(new java.awt.Rectangle(160,150,90,25));
			cpjp_new_pwd_pfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_new_pwd_pfield.setForeground(new Color(191,163,159));
			
			
			
			cpjp_conform_pwd_pfield = new JPasswordField();
			cpjp_conform_pwd_pfield.setBounds(new java.awt.Rectangle(160,200,90,27));
			cpjp_conform_pwd_pfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			cpjp_conform_pwd_pfield.setForeground(new Color(191,163,159));
			
			
			
			
			cpjpanel = new JPanel();
			cpjpanel.setLayout(null);
			cpjpanel.setBounds(new java.awt.Rectangle(250,170,300,350));
			cpjpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new java.awt.Color(191,163,159)));
			cpjpanel.setVisible(false);
			cpjpanel.add(cpjp_uid_lbl);
			cpjpanel.add(cpjp_old_pwd_lbl);
			cpjpanel.add(cpjp_new_pwd_lbl);
			cpjpanel.add(cpjp_conform_pwd_lbl);
			cpjpanel.add(cpjp_uid_tfield);
			cpjpanel.add(cpjp_old_pwd_pfield);
			cpjpanel.add(cpjp_new_pwd_pfield);
			cpjpanel.add(cpjp_conform_pwd_pfield);
			cpjpanel.add(getCPChangeJButton());
			cpjpanel.add(getCPCloseJButton());
			cpjpanel.add(panel_img_lbl);
			
		}
		return cpjpanel;
	}

	

	private JButton getCPChangeJButton() 
	{
		if(cpjp_change_btn==null)
		{
			cpjp_change_btn = new JButton("Change");
			cpjp_change_btn.setBounds(new java.awt.Rectangle(60,290,80,30));
			cpjp_change_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					String userID=cpjp_uid_tfield.getText();
					
					String oldPassword=cpjp_old_pwd_pfield.getText();
					
					String newPassword=cpjp_new_pwd_pfield.getText();
					
					String retypepassword=cpjp_conform_pwd_pfield.getText();
					
					LoginDao dao = new LoginDao();
					
					if(oldPassword.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter Old Password", "Change Password Validation",JOptionPane.WARNING_MESSAGE);
						
					}
					
					else if(newPassword.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter New Password", "Change Password Validation",JOptionPane.WARNING_MESSAGE);
					}
					
					else if(retypepassword.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Conform the Password", "Change Password Validation",JOptionPane.WARNING_MESSAGE);
					}
					
					else
					{
						if(!newPassword.equals(retypepassword))
						{
							JOptionPane.showMessageDialog(null,"New & Conform Password Mismatch", "Password Status",JOptionPane.WARNING_MESSAGE);
							
						}
						
						else
						{
							boolean checkOldPassword=dao.checkOldPasswordExist(userID, oldPassword);
							
							if(checkOldPassword)
							{

		                       boolean flag=dao.changePassword(userID,newPassword);
								
								if(flag)
								{
									JOptionPane.showMessageDialog(null,"Password Changed Succesfully", "Password Status",JOptionPane.WARNING_MESSAGE);
									cpjp_new_pwd_pfield.setText("");
									cpjp_old_pwd_pfield.setText("");
									cpjp_conform_pwd_pfield.setText("");							
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Password Mismatch", "Password Status",JOptionPane.WARNING_MESSAGE);
									cpjp_new_pwd_pfield.setText("");
									cpjp_old_pwd_pfield.setText("");
									cpjp_conform_pwd_pfield.setText("");
								}
							}
							
							else
							{
								JOptionPane.showMessageDialog(null,"Old Password Mismatch", "Password Status",JOptionPane.WARNING_MESSAGE);
								cpjp_old_pwd_pfield.setText("");
							}
						}
					}
					
				}
				
			});
		}
		
		return cpjp_change_btn;
	}

	private JButton getCPCloseJButton() 
	{
		if(cpjp_close_btn==null)
		{
			cpjp_close_btn = new JButton("Close");
			cpjp_close_btn.setBounds(new java.awt.Rectangle(165,290,80,30));
			cpjp_close_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
					cpjpanel.setVisible(false);
				}
				
			});
		}
		return cpjp_close_btn;
	}
	
	class PortListener1 implements Runnable 
	{
		
   		int port1;

   		public PortListener1(int port) 
   		{
   			this.port1 = port;
   			System.out.println(" Port  : "+port);
   		}
   		
   		public void run() 
   		{
   			if(this.port1 == Constants.S_FNAME_PORT_NUM) 
   			{
   			
   				try 
   				{ 
   					ServerSocket server1 = new ServerSocket(Constants.S_FNAME_PORT_NUM); 
   				    while (true)
   			        { 
   				    	 connection1 = server1.accept();
   				    	BufferedReader	server_br1 = new BufferedReader( new InputStreamReader(new BufferedInputStream(connection1.getInputStream())));
   					    String strLine;
   					    StringBuffer buffer = new StringBuffer();
   					    
   					    while ((strLine = server_br1.readLine()) != null)
					    {
					    	 buffer.append(strLine + "\n");
					    }
   					 received_data1=buffer.toString();
   					    System.out.println("  data  : "+received_data1);
   					   
   			
   					    System.out.println("  file Name : "+received_data1);
  					    server_br1.close();
   			        	connection1.close();
   			        	//server1.close();

   			        }
   				}
   				catch(Exception e)
   				{
   					System.out.println(e);
   				}
   			} 
   		}
	}
	
	
	class PortListener2 implements Runnable 
	{
		File f1;
   		int port2;

   		public PortListener2(int port) 
   		{
   			this.port2 = port;
   		}
   		
   		
   		public void run() 
   		{
   			if(this.port2 == Constants.S_UPLOAD_PORT_NUM) 
   			{
   			
   				try 
   				{ 
   					ServerSocket server2 = new ServerSocket(Constants.S_UPLOAD_PORT_NUM); 
   				   
   				 while (true)
   				 {
   					 
   					 LoginDao dao=new LoginDao();
   					 
   					connection2 = server2.accept();
   					String file = received_data1.trim();//file name to save
   					
   					
   					
			        System.out.println("  data : "+received_data1);
   					FileOutputStream  fs=new FileOutputStream("Uploded_Files/"+file);
   					DataInputStream d=new DataInputStream(connection2.getInputStream());
   					String id=d.readUTF();
   					System.out.println("  id :" +id);
   					int c_code=dao.getId(id);
   					String ip=d.readUTF();
   					System.out.println("  ip :" +ip);
   					String data=d.readUTF();
   					System.out.println("  data :" +data);
   					DataOutputStream outStream = new DataOutputStream(fs);
   					outStream.writeUTF(data);    	
   					outStream.flush();
   					 
   					outStream.close();
   					fs.close();
   					String layer="";
   					String status="";
   					
   					String detect=Layers.ProbeLayer(data);
   					if(detect==null)
   					{
   						detect=Layers.DOSLayer(data);
   						if(detect==null)
   	   					{
   	   						detect=Layers.R2LLayer(data);
	   	   					if(detect==null)
	   	   					{
	   	   						detect=Layers.U2RLayer(data);
		   	   					if(detect==null)
		   	   					{
			   	   					layer=null;
		   	   						status="normal";
		   	   						
		   	   					}
		   	   					else
		   	   					{
		   	   						layer="U2RLayer";
		   	   						status="virus";
		   	   					}
	   	   					}
	   	   					else
	   	   					{
	   	   						layer="R2LLayer";
	   	   						status="virus";
	   	   					}
   	   					}
   	   					else
   	   					{
   	   						layer="DOSLayer";
   	   						status="virus";
   	   					}
   					}
   					else
   					{
   						layer="ProbeLayer";
   						status="virus";
   					}
   					Date dt=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
					String date=sdf.format(dt);
					dao.checkusage(ip, date);
					dao.insertLog(ip, date, file, status, detect,layer);
					
					if(status.equals("virus"))
					{
						JOptionPane.showMessageDialog(null, "Virus File Received Suceessfully");
					}
					else
						JOptionPane.showMessageDialog(null, "File Received Suceessfully");
					
   				 }
   			       
   				}
   				catch(Exception e)
   				{
   					System.out.println(e);
   				}
   			} 
   		}
	}

	public static void main(String args[]) throws HeadlessException, IOException
	{
		new Server().setVisible(true);
	}

	public static void setClientID(String name) {
		// TODO Auto-generated method stub
		
	}
	
}
