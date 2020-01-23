package com.ids.client;

import java.awt.Color;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.ids.constants.Constants;
import com.ids.dao.LoginDao;
import com.ids.login.Login;
import com.ids.support.CurrentTimeDate;
import com.ids.support.GetProperty;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;



public class Client extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String userid="";
	private JPanel outerjpanel;
	private JPanel innerjpanel;
	private JPanel uploadjpanel;

	private JPanel requestjpanel;
	private JPanel cpjpanel;
	private JPanel transpassjpanel;
	
	private JButton innerjp_upload_btn;


	private JButton innerjp_status_btn;
	private JButton innerjp_cp_btn;
	private JButton innerjp_logout_btn;
	
	private JButton ticketjp_requestbtn;
	private JButton ticketjp_savebtn;
	private JButton ticketjp_closebtn;
	
	private JButton uploadjp_upload_btn;
	private JButton uploadjp_browse1_btn;
	private JButton uploadjp_browse2_btn;
	private JButton uploadjp_close_btn;
	

	
	private JButton cpjp_change_btn;
	private JButton cpjp_close_btn;
	
	private JButton transcation_ok_tbtn;
	private JButton transcation_close_tbtn;
	
	private JTextArea ticketjp_textarea;
	

	public JTextField uplaodjp_file_tfield; 
	
	
	private JTextField transcation_pass_tfield; 

	
	byte server_byteArray[] = new byte[1024];
	private JLabel cpjp_uid_lbl;
	private JLabel cpjp_old_pwd_lbl;
	private JLabel cpjp_new_pwd_lbl,menu_img_lbl1,menu_img_lbl2;
	private JLabel cpjp_conform_pwd_lbl;
	
	private JTextField cpjp_uid_tfield;
	private JPasswordField cpjp_old_pwd_pfield;
	private JPasswordField cpjp_new_pwd_pfield;
	private JPasswordField cpjp_conform_pwd_pfield;

	JRadioButton up_radio_btn,dw_radio_btn;
	
	private boolean userstatus;
	private JLabel background_img_lbl;
	private JLabel title_img_lbl;
	private JLabel menu_img_lbl;
	private JLabel panel_img_lbl;
	private JLabel label_Userid=null;
	
	public JFileChooser chooser,chooser1,chooser2 ;
	public File curFile,curFile1,curFile2;
	
	public static String server_IP;
	public static int server_port;
	
	public static String server_IP1;
	public static int server_port1;
	static String  dw_fname,up_fname,File_Use,file_path_upload;
	
	public Socket user;
	int count=0;
	BufferedOutputStream server_bos = null;
	BufferedOutputStream file_bos = null;
	
//	byte server_byteArray[] = new byte[1024];
	byte file_byteArray[] = new byte[1024];
	
	InetAddress ip;
	String user_ip,user_mac;
	String fl_req_time;
	String fl_req_date;
	String fl_transfer_group;
	
	String select_file_name;
	boolean ticket_flag,tpass_flag; 
	  
	URL background_img_url,title_img_url,menu_img_url,panel_img_url,upload_btn_img_url,download_btn_img_url,ftransfer_btn_img_url,details_btn_img_url,cp_btn_img_url,logout_btn_img_url,menu_img_url1,menu_img_url2;
	Image i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13;
	Image background_img,title_img,menu_img,panel_img,upload_btn_img,download_btn_img,ftransfer_btn_img,details_btn_img,cp_btn_img,logout_btn_img;
	ImageIcon title_img_icon,background_img_icon,menu_img_icon,panel_img_icon,upload_img_btn_icon,download_img_btn_icon,ftransfer_img_btn_icon,details_img_btn_icon,cp_img_btn_icon,logout_img_btn_icon;
	
	
	
	public Client()throws HeadlessException, IOException
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
		
		Thread t1 = new Thread(new PortListener1());
		t1.setName("Listener-" );
		t1.start();
		
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
		this.setSize(new java.awt.Dimension(750,575));
		this.setLocationRelativeTo(null);
		this.setContentPane(getOuterJPanel());
		this.setTitle("Client Frame");
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		 ip = InetAddress.getLocalHost();
			user_ip=ip.getHostAddress();
		//	System.out.println("Current IP address : " + user_ip);
	 
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	 
			byte[] mac = network.getHardwareAddress();
	 
			System.out.println("user id "+getUserID().trim());
	 
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
				user_mac=sb.toString();
			//	System.out.print("Current MAC address : "+user_mac);
		
	}

	private JPanel getOuterJPanel() throws IOException 
	{
		if(outerjpanel==null)
		{
			
			title_img_lbl = new JLabel();
			title_img_url = Client.class.getResource("title.jpg");
			i5 = ImageIO.read(title_img_url); 
			title_img = i5.getScaledInstance(750,100, java.awt.Image.SCALE_SMOOTH);
			title_img_icon = new ImageIcon(title_img);
			title_img_lbl.setBounds(-1,-150,1000,400);
			title_img_lbl.setIcon(title_img_icon);
			title_img_lbl.setVisible(true);
			
			background_img_lbl = new JLabel();
			background_img_url = Client.class.getResource("blue_back.jpg");
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
			label_Userid.setText("Welcome "+getUserID());
			
			outerjpanel = new JPanel();
			outerjpanel.setLayout(null);
			outerjpanel.add(label_Userid);
			outerjpanel.add(getInnerJPanel());
			outerjpanel.add(getUploadJPanel());

			outerjpanel.add(getTransPassJPanel());
			outerjpanel.add(getRequestJPanel());
			outerjpanel.add(getCPJPanel());
			//outerjpanel.add(title_img_lbl);
			outerjpanel.add(background_img_lbl);
		}
		
		return outerjpanel;
	}

	private JPanel getTransPassJPanel() throws IOException 
	{
		if(transpassjpanel==null)
		{
			JLabel type_of_file_lbl=new JLabel("Trans Password");
			type_of_file_lbl.setBounds(new java.awt.Rectangle(30,50,200,30));
			type_of_file_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			type_of_file_lbl.setForeground(Color.WHITE);
			
			panel_img_lbl = new JLabel();
			panel_img_url = Client.class.getResource("panel.png");
			i6 = ImageIO.read(panel_img_url); 
			panel_img = i6.getScaledInstance(1000,900, java.awt.Image.SCALE_SMOOTH);
			panel_img_icon = new ImageIcon(panel_img);
			panel_img_lbl.setBounds(-20,-100,1000,850);
			panel_img_lbl.setIcon(panel_img_icon);
			panel_img_lbl.setVisible(true);
			
			transcation_pass_tfield = new JTextField();
			transcation_pass_tfield.setBounds(new java.awt.Rectangle(200,50,150,30));
			transcation_pass_tfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			transcation_pass_tfield.setForeground(new Color(191,163,159));
	
			
			transpassjpanel = new JPanel();
			transpassjpanel.setLayout(null);
			transpassjpanel.setBounds(new java.awt.Rectangle(230,230,400,200));
			transpassjpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new java.awt.Color(191,163,159)));
			transpassjpanel.setVisible(false);
			transpassjpanel.add(type_of_file_lbl);
			transpassjpanel.add(getOkPassJButton());
			transpassjpanel.add(getClosePassJButton());
			transpassjpanel.add(transcation_pass_tfield);
			transpassjpanel.add(panel_img_lbl);
			
		}
		return transpassjpanel;
	}

	private JButton getOkPassJButton() 
	{
		if(transcation_ok_tbtn==null)
		{
			transcation_ok_tbtn = new JButton("Ok");
			transcation_ok_tbtn.setBounds(new java.awt.Rectangle(120,120,70,30));
			transcation_ok_tbtn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
				}
				
			});
		}
		
		return transcation_ok_tbtn;
	}
	private JButton getClosePassJButton() 
	{
		if(transcation_close_tbtn==null)
		{
			transcation_close_tbtn = new JButton("Close");
			transcation_close_tbtn.setBounds(new java.awt.Rectangle(220,120,70,30));
			transcation_close_tbtn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					transpassjpanel.setVisible(false);
				}
				
			});
		}
		
		return transcation_close_tbtn;
	}

	private JPanel getInnerJPanel() throws IOException 
	{
		if(innerjpanel==null)
		{
			menu_img_lbl = new JLabel();
			menu_img_url = Client.class.getResource("menu.png");
			i2 = ImageIO.read(menu_img_url); 
			menu_img = i2.getScaledInstance(100,750, java.awt.Image.SCALE_SMOOTH);
			menu_img_icon = new ImageIcon(menu_img);
			menu_img_lbl.setBounds(-1,0,90,700);
			menu_img_lbl.setIcon(menu_img_icon);
			menu_img_lbl.setVisible(true);
			
			
			innerjpanel = new JPanel();
			innerjpanel.setLayout(null);
			innerjpanel.setBounds(new java.awt.Rectangle(25,145,90,285));
			innerjpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new java.awt.Color(191,163,159)));
			
			
			innerjpanel.add(getFileUploadJButton());
			
			innerjpanel.add(getUserStatusJButton());
			innerjpanel.add(getChangePwdJButton());
			innerjpanel.add(getLogoutJButton());
			innerjpanel.add(menu_img_lbl);
			
			
		}
	
		return innerjpanel;
	}
	
	private JButton getFileUploadJButton() throws IOException 
	{
		if(innerjp_upload_btn==null)
		{
			upload_btn_img_url = Client.class.getResource("upload_btn.png");
			i8 = ImageIO.read(upload_btn_img_url);
			upload_btn_img = i8.getScaledInstance(100,60,  java.awt.Image.SCALE_SMOOTH);
			upload_img_btn_icon = new ImageIcon(upload_btn_img);
			
			
			innerjp_upload_btn = new JButton("",upload_img_btn_icon);
			innerjp_upload_btn.setToolTipText("File Upload");
			innerjp_upload_btn.setBounds(new java.awt.Rectangle(10,20,70,50));
			innerjp_upload_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					File_Use="Upload";
					uploadjpanel.setVisible(true);
					cpjpanel.setVisible(false);
					requestjpanel.setVisible(false);

				}
				
			});
		}
		
		return innerjp_upload_btn;
	}

	
	
	
	public JButton getUserStatusJButton () throws IOException 
	{
		if(innerjp_status_btn==null)
		{
			details_btn_img_url = Client.class.getResource("status.jpg");
			i11 = ImageIO.read(details_btn_img_url);
			details_btn_img = i11.getScaledInstance(80,50,  java.awt.Image.SCALE_SMOOTH);
			details_img_btn_icon = new ImageIcon(details_btn_img);
			
			
			innerjp_status_btn = new JButton("",details_img_btn_icon);
			innerjp_status_btn.setToolTipText("User Status");
			innerjp_status_btn.setBounds(new java.awt.Rectangle(10,85,70,50));
			innerjp_status_btn.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
					
					uploadjpanel.setVisible(false);
					cpjpanel.setVisible(false);
					requestjpanel.setVisible(true);
					
				}
				
			});
		}
		return innerjp_status_btn;
	}



	
	
	private JButton getChangePwdJButton() throws IOException 
	{
		if(innerjp_cp_btn==null)
		{
			
			cp_btn_img_url = Client.class.getResource("cp_btn.png");
			i12 = ImageIO.read(cp_btn_img_url);
			cp_btn_img = i12.getScaledInstance(80,50,  java.awt.Image.SCALE_SMOOTH);
			cp_img_btn_icon = new ImageIcon(cp_btn_img);
			
			innerjp_cp_btn = new JButton("",cp_img_btn_icon);
			innerjp_cp_btn.setToolTipText("Change Password");
			innerjp_cp_btn.setBounds(new java.awt.Rectangle(10,150,70,50));
			innerjp_cp_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					uploadjpanel.setVisible(false);
					cpjpanel.setVisible(true);
					requestjpanel.setVisible(false);
			
				}
				
			});
		}
		
		return innerjp_cp_btn;
	}

	private JButton getLogoutJButton() throws IOException
	{
		if(innerjp_logout_btn==null)
		{
			logout_btn_img_url = Client.class.getResource("logout_btn.png");
			i13 = ImageIO.read(logout_btn_img_url);
			logout_btn_img = i13.getScaledInstance(70,80,  java.awt.Image.SCALE_SMOOTH);
			logout_img_btn_icon = new ImageIcon(logout_btn_img);
			
			
			innerjp_logout_btn = new JButton("",logout_img_btn_icon);
			innerjp_logout_btn.setToolTipText("Logout");
			innerjp_logout_btn.setBounds(new java.awt.Rectangle(10,215,70,50));
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
						// TODO Auto-generated catch block
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
	
	
	private JPanel getUploadJPanel() throws IOException 
	{
		if(uploadjpanel==null)
		{
			uplaodjp_file_tfield  = new JTextField();
		
			uplaodjp_file_tfield.setBounds(new java.awt.Rectangle(150,80,200,30));
			uplaodjp_file_tfield.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 14));
			uplaodjp_file_tfield.setForeground(new Color(191,163,159));
			uplaodjp_file_tfield.setEditable(false);
			
			panel_img_lbl = new JLabel();
			panel_img_url = Client.class.getResource("panel.png");
			i6 = ImageIO.read(panel_img_url); 
			panel_img = i6.getScaledInstance(1000,900, java.awt.Image.SCALE_SMOOTH);
			panel_img_icon = new ImageIcon(panel_img);
			panel_img_lbl.setBounds(-20,-100,1000,850);
			panel_img_lbl.setIcon(panel_img_icon);
			panel_img_lbl.setVisible(true);
			
			JLabel label=new JLabel("File Upload");
			label.setBounds(new java.awt.Rectangle(150,20,200,30));
			label.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 16));
			label.setForeground(Color.WHITE);
		
			uploadjpanel = new JPanel();
			uploadjpanel.setLayout(null);
			uploadjpanel.setBounds(new java.awt.Rectangle(230,230,400,200));
			uploadjpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new java.awt.Color(191,163,159)));
			uploadjpanel.setVisible(false);
			uploadjpanel.add(getUploadActionJButton());
			uploadjpanel.add(getBrowseUp1JButton());
	
			uploadjpanel.add(getCloseUpJButton());
			uploadjpanel.add(uplaodjp_file_tfield);
			uploadjpanel.add(label);
			uploadjpanel.add(panel_img_lbl);
			
		}
		return uploadjpanel;
	}

	private JButton getBrowseUp1JButton() 
	{
		if(uploadjp_browse1_btn==null)
		{
			uploadjp_browse1_btn = new JButton("Browse");
			uploadjp_browse1_btn.setBounds(new java.awt.Rectangle(30,80,100,30));
			uploadjp_browse1_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					chooser1 = new JFileChooser();
	    			try 
	    			{
	    			   File f = new File(new File("filename.txt").getCanonicalPath());
					   chooser1.setSelectedFile(f);
	    			}
	    			catch (IOException e1) 
	    			{
	    			}

	    			int retval = chooser1.showOpenDialog(uploadjp_browse1_btn);
	    			if (retval == JFileChooser.APPROVE_OPTION)
	    			{
	    			    File field = chooser1.getSelectedFile();
	    			    uplaodjp_file_tfield.setText(field.toString()); 
	    			    	
	    			}		    
	    			curFile1 = chooser1.getSelectedFile();
				}
				
			});
		}
		return uploadjp_browse1_btn;
	}
	
	
	private JButton getUploadActionJButton() 
	{
		if(uploadjp_upload_btn==null)
		{
			uploadjp_upload_btn = new JButton("Upload");
			uploadjp_upload_btn.setBounds(new java.awt.Rectangle(100,150,100,30));
			uploadjp_upload_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					try 
					{
					
					LoginDao dao=new LoginDao();
					if(!dao.getBlockIP(user_ip))
					{	
						String pkt;
						Random rand=new Random();
						String file="";
						String fl=GetProperty.getProperty("flag");
						if(fl.equals("f"))
						{
							int r=rand.nextInt(8);
							while(r==0)
							{
								r=rand.nextInt(8);
							}
							System.out.println("its came to virus block");
							 file="Virus_Files/"+r+".txt";
						}
						else if(fl.equals("t"))
						{
							int r=rand.nextInt(4);
							while(r==0)
							{
								r=rand.nextInt(4);
							}
							System.out.println("its in normal file block");
							 file="Normal_Files/"+r+".txt";
						}
						
	
				    	BufferedReader  br=new BufferedReader (new FileReader(file));
				    	StringBuffer content=new StringBuffer();
				    	while (br.ready()) 
				    	{
				    		  String s = br.readLine();
				    		  content.append(s);
				    	}
				    	pkt=content.toString();
				    	System.out.println(uplaodjp_file_tfield.getText());
						file_path_upload=uplaodjp_file_tfield.getText().trim().replace("\\","/");
						up_fname=curFile1.getName();
						System.out.println(up_fname);
						sendFileNameToServer(up_fname);
						server_IP1 = Constants.Server_name; 
			    		server_port1 = Constants.S_UPLOAD_PORT_NUM;
			    		
			    		System.out.println(" file Ip : "+server_IP1+" Port : "+server_port1);
			    		Socket dos_client1;
						
							dos_client1 = new Socket(server_IP1, server_port1);
							 File f=new File(file_path_upload.trim());  
							    if(f.exists())
							    { 
							    	BufferedReader  br1=new BufferedReader (new FileReader(f));
							    	StringBuffer content1=new StringBuffer();
							    	while (br1.ready()) 
							    	{
							    		  String s = br1.readLine();
							    		  content1.append(s);
							    	}
							    	String data=content1.toString();
							    	/*if(fl.equals("f"))
									{
							    		data=pkt+"~"+data;
									}*/
							    	
							    //	data=pkt+"~"+data;
							    	DataOutputStream d=new DataOutputStream(dos_client1.getOutputStream());
							    	d.writeUTF(getUserID());
							    	d.writeUTF(user_ip);
							    	d.writeUTF(data);
							    	d.close();
							    	dos_client1.close();
							    	
							    }
							    System.out.println("  File Uploaded ");
						
						
							String fl_transfer_time = CurrentTimeDate.getCurrentTime().toString();
							String fl_transfer_date = CurrentTimeDate.getCurrentDate().toString();
							 String f_datetime=fl_transfer_date.trim()+" "+fl_transfer_time.trim();
							 int c_code=dao.getId(getUserID());
							 boolean flag= dao.uploadFile(up_fname, f_datetime, c_code);
							 if(flag)
							    {
							    	JOptionPane.showMessageDialog(null,"File Uploaded Successfully... !!", "File Upload Status",JOptionPane.WARNING_MESSAGE);
							    }
							    else
							    {
							    	JOptionPane.showMessageDialog(null,"Problem occured while File Uploading to Server ", "File Upload Status",JOptionPane.WARNING_MESSAGE);
							    }  
						 }
						else
					    {
					    	JOptionPane.showMessageDialog(null,"Sorry your IP is blocked ", "File Upload Status",JOptionPane.WARNING_MESSAGE);
					    }  
					
					}
					catch (Exception e1) 
					{
						e1.printStackTrace();
					} 
				}
				
			});
		}
		
		return uploadjp_upload_btn;
	}

	

	private JButton getCloseUpJButton() 
	{
		if(uploadjp_close_btn==null)
		{
			uploadjp_close_btn = new JButton("Close");
			uploadjp_close_btn.setBounds(new java.awt.Rectangle(220,150,70,30));
			uploadjp_close_btn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					uploadjpanel.setVisible(false);
				}
				
			});
		}
		
		return uploadjp_close_btn;
	}
	private JPanel getRequestJPanel() throws IOException 
	{
		if(requestjpanel==null)
		{
			userstatus=LoginDao.getuserstatus(getUserID().trim().toString());
			JLabel type_of_file_lbl=new JLabel();
			type_of_file_lbl.setBounds(new java.awt.Rectangle(30,20,200,30));
			type_of_file_lbl.setFont(new java.awt.Font("verdana", java.awt.Font.BOLD, 16));
			type_of_file_lbl.setForeground(Color.WHITE);
			
			//String userip=LoginDao.getuserip(Client.getUserID().trim());
			
			System.out.println("STatus "+userstatus);
			menu_img_lbl1 = new JLabel();
			menu_img_lbl2 = new JLabel();
			menu_img_lbl1.setVisible(false);
			menu_img_lbl2.setVisible(false);
			if(userstatus)
			{
				menu_img_lbl1 = new JLabel();
				menu_img_url1 = Client.class.getResource("blocked.png");
				Image i21 = ImageIO.read(menu_img_url1); 
				//Image menu_img1 = i21.getScaledInstance(100,750, java.awt.Image.SCALE_SMOOTH);
				ImageIcon menu_img_icon1 = new ImageIcon(i21);
				menu_img_lbl1.setBounds(130,20,200,100);
				menu_img_lbl1.setIcon(menu_img_icon1);
				menu_img_lbl1.setVisible(true);
				type_of_file_lbl.setText("Blocked");
			}
			else
			{
				menu_img_lbl2 = new JLabel();
				menu_img_url2 = Client.class.getResource("unblocked.png");
				Image i21 = ImageIO.read(menu_img_url2); 
				//Image menu_img1 = i21.getScaledInstance(100,750, java.awt.Image.SCALE_SMOOTH);
				ImageIcon menu_img_icon2 = new ImageIcon(i21);
				menu_img_lbl2.setBounds(130,20,200,100);
				menu_img_lbl2.setIcon(menu_img_icon2);
				menu_img_lbl2.setVisible(true);
				type_of_file_lbl.setText("Not Blocked");
			}
			System.out.println("USer status "+userstatus);
			
			panel_img_lbl = new JLabel();
			panel_img_url = Client.class.getResource("panel.png");
			i6 = ImageIO.read(panel_img_url); 
			panel_img = i6.getScaledInstance(1000,900, java.awt.Image.SCALE_SMOOTH);
			panel_img_icon = new ImageIcon(panel_img);
			panel_img_lbl.setBounds(-20,-100,1000,850);
			panel_img_lbl.setIcon(panel_img_icon);
			panel_img_lbl.setVisible(true);
			
			
			
			requestjpanel = new JPanel();
			requestjpanel.setLayout(null);
			requestjpanel.setBounds(new java.awt.Rectangle(200,170,450,300));
			requestjpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new java.awt.Color(191,163,159)));
			requestjpanel.setVisible(false);
			requestjpanel.add(type_of_file_lbl);
			requestjpanel.add(menu_img_lbl1);
			requestjpanel.add(menu_img_lbl2);
			
		
			requestjpanel.add(getReqCloseActionJButton());

			requestjpanel.add(panel_img_lbl);
			
		}
		return requestjpanel;
	}

	private JButton getRequestActionJButton() 
	{
		if(ticketjp_requestbtn==null)
		{
			ticketjp_requestbtn = new JButton("Send Req");
			ticketjp_requestbtn.setBounds(new java.awt.Rectangle(270,73,110,30));
			ticketjp_requestbtn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
				}
			});
		}
		return ticketjp_requestbtn;
	}
	
	
	
	
	private JButton getReqCloseActionJButton() 
	{
		if(ticketjp_closebtn==null)
		{
			ticketjp_closebtn = new JButton("Close");
			ticketjp_closebtn.setBounds(new java.awt.Rectangle(250,250,70,30));
			ticketjp_closebtn.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
					requestjpanel.setVisible(false);
				}
				
			});
		}
		
		return ticketjp_closebtn;
	}

	
	

	

	
	private JPanel getCPJPanel() throws IOException 
	{
		if(cpjpanel==null)
		{
			
			panel_img_lbl = new JLabel();
			panel_img_url = Client.class.getResource("panel.png");
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

//		                       boolean flag=dao.changePassword(userID, oldPassword, newPassword);
//								
//								if(flag)
//								{
//									JOptionPane.showMessageDialog(null,"Password Changed Succesfully", "Password Status",JOptionPane.WARNING_MESSAGE);
//									cpjp_new_pwd_pfield.setText("");
//									cpjp_old_pwd_pfield.setText("");
//									cpjp_conform_pwd_pfield.setText("");							
//								}
//								else
//								{
//									JOptionPane.showMessageDialog(null,"Password Mismatch", "Password Status",JOptionPane.WARNING_MESSAGE);
//									cpjp_new_pwd_pfield.setText("");
//									cpjp_old_pwd_pfield.setText("");
//									cpjp_conform_pwd_pfield.setText("");
//								}
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
	
	public void sendFileNameToServer(String f_name) 
	{
		System.out.println("============> "+f_name);
		
		server_IP =Constants.Server_name;
		server_port =Constants.S_FNAME_PORT_NUM; 
		System.out.println(" Ip : "+server_IP+" Port : "+server_port);
		
		try
		{
			user = new Socket(server_IP, server_port);
			server_bos = new BufferedOutputStream(user.getOutputStream());
			String data1 = f_name.trim(); 
			 
			server_byteArray = (data1).getBytes();
			server_bos.write(server_byteArray, 0, server_byteArray.length);
			server_bos.flush();
			server_bos.close();
			user.close();
			
			System.out.println("  File Name Send Suuceesfully ");
		}
		catch(Exception e1)
		{
			
		}
		
	}

	class PortListener1 implements Runnable 
	{
		
   		int port1;	
   		public void run() 
   		{
   			
   				
   		}
	}
	
	public static void main(String args[]) throws HeadlessException, IOException
	{
		new Client().setVisible(true);
		
	}
	
}
