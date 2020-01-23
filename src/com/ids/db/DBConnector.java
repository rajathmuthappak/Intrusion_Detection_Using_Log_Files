package com.ids.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import java.sql.DriverManager;

public class DBConnector 
{
	
	public static Connection db_connector() 
	{
		Connection con =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nw_ids_log","root","admin");
			
		}
		catch(Exception e)
		{
			System.out.println("--- DBConnector.java File ---> Exception in db_connector---> "+e); 
		}
		return con;
	}
	
	public static void db_connection_close(Connection con,Statement st,ResultSet rs) 
	{
		try
		{
			if(rs!=null)
			{
				rs.close();
				rs=null;
			}
			else if(st!=null)
			{
				st.close();
				st=null;
			}
			else if(con!=null)
			{
				con.close();
				con=null;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("--- DBConnector.java File ---> Exception in db_connector---> "+e); 
		}	
		
	}

}
