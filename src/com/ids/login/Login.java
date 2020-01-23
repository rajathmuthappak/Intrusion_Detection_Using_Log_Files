package com.ids.login;



import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.ids.client.Client;
import com.ids.dao.LoginDao;
import com.ids.server.Server;



import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;



public class Login extends JFrame 
{

	Image myImage,cp,delMemb,gps;
	
	public float alpha;
	public static int clicked;
	
	
	public static String filepath;
	private JPanel outerjpanel;

	private JLabel os_in_p_password_lbl;
	private JLabel os_in_p_user_id_lbl;

	
	private JButton os_login_btn ;

	
	
	public JTextField os_in_p_user_id_tfld;
	public JPasswordField os_in_p_password_tfld;
	public JTextField ad_in_p_user_id_tfld;
	public JPasswordField ad_in_p_password_tfld;
	
	public JRadioButton serv_radio_btn;
	public JRadioButton us_radio_btn;

	private JLabel in_p_user_img_lbl;

	
	URL background_img_url;
	Image i1;
	Image background_img;
	ImageIcon background_img_icon;

	
	public Login() throws HeadlessException, IOException
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
	
	}
	
	private void initialize() throws IOException 
	{
		
		this.setSize(new java.awt.Dimension(568,380));
		this.setLocationRelativeTo(null);
		this.setContentPane(getOuterJPanel());
		this.setTitle("Login Frame");
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}
	
	
	private JPanel getOuterJPanel() throws IOException 
	{
		
      if(outerjpanel==null)
		{
    	    in_p_user_img_lbl = new JLabel();
			background_img_url = Login.class.getResource("Login-form1.png");
			i1= ImageIO.read(background_img_url); 
			background_img = i1.getScaledInstance(560,350, java.awt.Image.SCALE_SMOOTH);
			background_img_icon = new ImageIcon(background_img);
			in_p_user_img_lbl.setBounds(0,0,560,350);
			in_p_user_img_lbl.setIcon(background_img_icon);
			in_p_user_img_lbl.setVisible(true);

			JLabel login_lbl=new JLabel("Login!");
			login_lbl.setBounds(new java.awt.Rectangle(100,60,200,100));
			login_lbl.setFont(new java.awt.Font("Calibri", java.awt.Font.BOLD, 28));
			login_lbl.setForeground(new Color(255,255,255));
			
			ImageIcon start = new ImageIcon(getClass().getResource("530751.png"));
			os_in_p_user_id_lbl = new JLabel(start);
			os_in_p_user_id_lbl.setBounds(new java.awt.Rectangle(30,100,200,100));
			os_in_p_user_id_lbl.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
			os_in_p_user_id_lbl.setForeground(new Color(255,255,255));
			
			
			ImageIcon start1 = new ImageIcon(getClass().getResource("pass.png"));
			os_in_p_password_lbl = new JLabel(start1);
			
			os_in_p_password_lbl.setBounds(new java.awt.Rectangle(30,150,200,100));
			os_in_p_password_lbl.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
			os_in_p_password_lbl.setForeground(new Color(255,255,255));
		
			
			
			
			os_in_p_user_id_tfld = new JTextField();
			os_in_p_user_id_tfld.setBounds(new java.awt.Rectangle(180,130,200,28));
			os_in_p_user_id_tfld.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			os_in_p_user_id_tfld.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
			//os_in_p_user_id_tfld.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
			os_in_p_user_id_tfld.setForeground(new Color(0,0,0));
			
			os_in_p_password_tfld = new JPasswordField();
			os_in_p_password_tfld.setBounds(new java.awt.Rectangle(185,185,190,28));
			os_in_p_password_tfld.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			os_in_p_password_tfld.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
			
			os_in_p_password_tfld.setForeground(new Color(0,0,0));
			
			serv_radio_btn=new JRadioButton("Server Login");
			serv_radio_btn.setBounds(100, 225, 120, 30);
			serv_radio_btn.setBackground(new Color(55,55,55));
			serv_radio_btn.setForeground(Color.WHITE);
			serv_radio_btn.setFont(new java.awt.Font("arial",java.awt.Font.PLAIN,14));
			
			us_radio_btn=new JRadioButton("User Login");
			us_radio_btn.setBounds(250, 225, 120, 30);
			us_radio_btn.setBackground(new Color(55,55,55));
			us_radio_btn.setForeground(Color.WHITE);
			us_radio_btn.setFont(new java.awt.Font("arial",java.awt.Font.PLAIN,14));
			
			ButtonGroup group=new ButtonGroup();
			group.add(serv_radio_btn);
			group.add(us_radio_btn);
			
				
			outerjpanel = new JPanel();
			outerjpanel.setLayout(null);
			outerjpanel.setBounds(new java.awt.Rectangle(0,100,560,350));
		
			
			
			outerjpanel.setVisible(true);
			outerjpanel.add(login_lbl);
			outerjpanel.add(os_in_p_user_id_lbl);
			outerjpanel.add(os_in_p_password_lbl);
			outerjpanel.add(os_in_p_user_id_tfld);
			outerjpanel.add(os_in_p_password_tfld);
			outerjpanel.add(getLoginJButton());
			outerjpanel.add(serv_radio_btn);
			outerjpanel.add(us_radio_btn);
			//innerjpanel.add(getResetJButton());
			outerjpanel.add(in_p_user_img_lbl);
		
			
		//	outerjpanel.add(background_img_lbl);
			
		}
		return outerjpanel;
	}
	

	private JButton getLoginJButton() throws IOException 
	{
		if(os_login_btn ==null)
		{
			ImageIcon start = new ImageIcon(getClass().getResource("LogIn_Button.png"));
			os_login_btn  = new JButton(start);
			Border emptyBorder = BorderFactory.createEmptyBorder();
			os_login_btn.setBorder(emptyBorder);
			os_login_btn.setToolTipText("Login");
			os_login_btn.setForeground(new Color(111,143,237));
			os_login_btn .setBounds(new java.awt.Rectangle(400,155,61,30));
			
			os_login_btn .addActionListener(new java.awt.event.ActionListener() 
			{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					String name=os_in_p_user_id_tfld.getText();
					String pass=os_in_p_password_tfld.getText().trim();
					LoginDao dao=new LoginDao();
					if(name.trim().equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter  Name", " Validation",JOptionPane.WARNING_MESSAGE);
						
					}
					else if(pass.trim().equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter Passworrd", " Validation",JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						if(us_radio_btn.isSelected())
						{
							boolean f=dao.verifyLogin(name, pass,"m_client");
							if(f)
							{
								JOptionPane.showMessageDialog(null,"Login Successfully", "Status",JOptionPane.WARNING_MESSAGE);
								dispose();
								Client.setUserID(name);
								try 
								{
									new Client().setVisible(true);
									
									
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
							else
							{
								os_in_p_password_tfld.setText("");
								JOptionPane.showMessageDialog(null,"Invalid User Id or Password", "Status",JOptionPane.WARNING_MESSAGE);
							}
						}
						else if(serv_radio_btn.isSelected())
						{
							boolean f=dao.verifyLogin(name, pass,"m_server");
							if(f)
							{
								JOptionPane.showMessageDialog(null,"Login Successfully", "Status",JOptionPane.WARNING_MESSAGE);
								dispose();
								Server.setUserID(name);
								try 
								{
									new Server().setVisible(true);
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
							else
							{
								os_in_p_password_tfld.setText("");
								JOptionPane.showMessageDialog(null,"Invalid User Id or Password", "Status",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Appropriate Type Selestion Required ... !!","Warning",JOptionPane.WARNING_MESSAGE);
						}
						
					}
					
				}
			});
		}
		return os_login_btn ;
	}
	
	
	
	public static void main(String args[]) throws HeadlessException, IOException
	{
		new Login().setVisible(true);
		
	}


}

