package com.ids.server;

import java.awt.*;

import java.sql.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.table.*;

import com.ids.dao.LoginDao;





public class ViewDetails 
{
	static JTable table;
	static TableColumn col;
	static JTableHeader header;
	static Vector columnNames;
	static Vector data;
	static JPanel p;
	
	static Dimension dim = new Dimension(10,1);
	
	public static void main(String[] args) {
		viewBlockDetails();
	}
	
	public static void viewUserDetails() 
	{
		try 
		{
			p=new JPanel();
			columnNames = new Vector();
			data = new Vector();
			LoginDao dao = new LoginDao();
			
			data= dao.userDetails();
			columnNames.add("C_code");
			columnNames.add("Id");
			columnNames.add("Name");
			columnNames.add("Mail");
			columnNames.add("IP Address");
			columnNames.add("MAC Address");
			
		
			table = new JTable(data, columnNames);
			header = table.getTableHeader();
			header.setBackground(Color.ORANGE);
			
			table.setIntercellSpacing(new Dimension(dim));
			SetRowHight(table);
			int row_count =  table.getRowCount();
			
			System.out.println(row_count);
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(500);
			table.getColumnModel().getColumn(4).setPreferredWidth(250);
			table.getColumnModel().getColumn(5).setPreferredWidth(400);
			
			
			
			for (int i = 0; i < table.getColumnCount(); i++) 
			{
				col = table.getColumnModel().getColumn(i);
				col.setMaxWidth(700);
			}
			JScrollPane scrollPane = new JScrollPane( table );
			scrollPane.setBounds(0, 0, 600, 300);
			p.setLayout(null);
			p.add( scrollPane );
			p.setBounds(0, 0, 600, 300);
			JFrame f=new JFrame();
			f.setContentPane(p);
			f.setVisible(true);
			f.setSize(600,300);
			f.setLayout(null);
			f.setLocation(280, 250);
			f.setResizable(false);
			f.setTitle("View User Details");
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public static void SetRowHight(JTable table)
	{
		  int height = table.getRowHeight();
		  
		  table.setRowHeight(height+10);
		  table.setBackground(new Color(196,143,207));
		  
	}
	public static void viewFileDetails() 
	{
		try 
		{
			p=new JPanel();
			columnNames = new Vector();
			data = new Vector();
			LoginDao dao = new LoginDao();
			
			data= dao.fileDetails();
			columnNames.add("FNo");
			columnNames.add("FName");
			columnNames.add("FDatetime");
			columnNames.add("User Id");
			
		
			table = new JTable(data, columnNames);
			header = table.getTableHeader();
			header.setBackground(Color.ORANGE);
			
			table.setIntercellSpacing(new Dimension(dim));
			SetRowHight(table);
			int row_count =  table.getRowCount();
			
			System.out.println(row_count);
			table.getColumnModel().getColumn(0).setPreferredWidth(80);
			table.getColumnModel().getColumn(1).setPreferredWidth(300);
			table.getColumnModel().getColumn(2).setPreferredWidth(250);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);
			
			
			
			for (int i = 0; i < table.getColumnCount(); i++) 
			{
				col = table.getColumnModel().getColumn(i);
				col.setMaxWidth(500);
			}
			JScrollPane scrollPane = new JScrollPane( table );
			scrollPane.setBounds(0, 0, 500, 300);
			p.setLayout(null);
			p.add( scrollPane );
			p.setBounds(0, 0, 500, 300);
			JFrame f=new JFrame();
			f.setContentPane(p);
			f.setVisible(true);
			f.setSize(500,300);
			f.setLocation(300, 250);
			f.setResizable(false);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setTitle("View File Details");
			f.setVisible(true);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public static void viewLogFileDetails() 
	{
		try 
		{
			p=new JPanel();
			columnNames = new Vector();
			data = new Vector();
			LoginDao dao = new LoginDao();
			
			data= dao.logfileDetails();
			columnNames.add("TNo");
			columnNames.add("TDatetime");
			columnNames.add("ClientIP");
			columnNames.add("FileName");
			columnNames.add("Status");
			columnNames.add("Layer");
			columnNames.add("AttackerName");
			
		
			table = new JTable(data, columnNames);
			header = table.getTableHeader();
			header.setBackground(Color.ORANGE);
			
			table.setIntercellSpacing(new Dimension(dim));
			SetRowHight(table);
			int row_count =  table.getRowCount();
			
			System.out.println(row_count);
			table.getColumnModel().getColumn(0).setPreferredWidth(80);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			table.getColumnModel().getColumn(3).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(200);
			
			
			
			for (int i = 0; i < table.getColumnCount(); i++) 
			{
				col = table.getColumnModel().getColumn(i);
				col.setMaxWidth(700);
			}
			JScrollPane scrollPane = new JScrollPane( table );
			scrollPane.setBounds(0, 0, 600, 300);
			p.setLayout(null);
			p.add( scrollPane );
			p.setBounds(0, 0, 600, 300);
			JFrame f=new JFrame();
			f.setContentPane(p);
			f.setVisible(true);
			f.setSize(600,300);
			f.setLocation(300, 250);
			f.setResizable(false);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setTitle("View LogFile Details");
			f.setVisible(true);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public static void viewBlockDetails() 
	{
		try 
		{
			p=new JPanel();
			columnNames = new Vector();
			data = new Vector();
			LoginDao dao = new LoginDao();
			
			data= dao.BlockedDetails();
			columnNames.add("SNo");
			columnNames.add("Block_IpAddress");

			
		
			table = new JTable(data, columnNames);
			header = table.getTableHeader();
			header.setBackground(Color.ORANGE);
			
			table.setIntercellSpacing(new Dimension(dim));
			SetRowHight(table);
			int row_count =  table.getRowCount();
			
			System.out.println(row_count);
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(300);
		
			
			
			
			for (int i = 0; i < table.getColumnCount(); i++) 
			{
				col = table.getColumnModel().getColumn(i);
				col.setMaxWidth(500);
			}
			JScrollPane scrollPane = new JScrollPane( table );
			scrollPane.setBounds(0, 0, 500, 300);
			p.setLayout(null);
			p.add( scrollPane );
			p.setBounds(0, 0, 500, 300);
			JFrame f=new JFrame();
			f.setContentPane(p);
			f.setVisible(true);
			f.setSize(500,300);
			f.setLocation(300, 250);
			f.setResizable(false);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setTitle("View BlockIP Details");
			f.setVisible(true);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
	
	
		 
	
}