package com.ids.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.ids.constants.Constants;
import com.ids.db.DBConnector;
import com.ids.support.GetProperty;





public class LoginDao 
{
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	public static String v_name = null; 
	public boolean verifyLogin(String v_uid, String v_pwd,String tb)
	{
		boolean flag = false;
		try
		{
			String query = "select * from "+tb+" where id = '"+v_uid+"'and pwd='"+v_pwd+"' ";
			System.out.println(query);
			connection = DBConnector.db_connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{	
				flag=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	public boolean insertUser(String id, String pwd, String name,String email)
	{
		boolean flag = false;
		try
		{
			String query = "insert into m_client(id,pwd,user_name,user_emailid)values('"+id+"','"+pwd+"','"+name+"','"+email+"')";
			System.out.println();
			System.out.println(query);
			connection = DBConnector.db_connector();
			statement = connection.createStatement();
			int update = statement.executeUpdate(query);
			if(update==1)
			{
				flag = true;
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnector.db_connection_close(connection, statement, resultSet);
		}	
		return flag;
	}
	
	public boolean uploadFile(String f_name,String f_datetime, int u_code)
	{
		boolean flag = false;
		try
		{
			String query = "insert into m_file_upload(file_name,file_datetime,user_code)values('"+f_name+"','"+f_datetime+"','"+u_code+"')";
			System.out.println(query);
			connection = DBConnector.db_connector();
			statement = connection.createStatement();
			int update = statement.executeUpdate(query);
			if(update==1)
			{
				flag = true;	
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return flag;
		
	}
	
	public boolean getExistingId(String v_existing_uid)
	{
		boolean flag = false;		
		try
		{
			String query="select id from m_client where id='"+v_existing_uid+"'";
			connection= DBConnector.db_connector();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(query);
			
			while(resultSet.next())
			{
				flag = true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnector.db_connection_close(connection, statement, resultSet);
		}	
		return flag;
	}
	
	
	public boolean changePassword(String v_userID,String v_newPassword)
	{
		boolean flag=false;
		
		try
		{
			connection=DBConnector.db_connector();
			
			statement=connection.createStatement();
			
			
			
			String updateQuery="update m_client set pwd ='"+v_newPassword+"' where id='"+v_userID+"'";
						
			System.out.println(updateQuery);
			
			int update=statement.executeUpdate(updateQuery);
			
			if(update==1)
			{
				flag=true;
			}
			resultSet.close();
			statement.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
		
	}
	
	public boolean checkOldPasswordExist(String v_userID, String v_oldPassword)
	{

		boolean flag=false;
		String pwd="";
		try
		{
			connection=DBConnector.db_connector();
			
			statement=connection.createStatement();
			
			String query="select * from m_client where id='"+v_userID+"' and pwd='"+v_oldPassword+"'";
			
			System.out.println(query);
			
			resultSet=statement.executeQuery(query);
			
			if(resultSet.next())
			{
                flag=true;
			}
			
			statement.close();
			resultSet.close();

		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return flag;
	}
	

	
	public Vector userDetails()
	{
		Vector data = new Vector();
		try
		{
			String query = "select c_code,id,user_name,user_emailid,Ip_address,Mac_address from m_client  ";
			System.out.println(query);
			connection = DBConnector.db_connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			int columns = md.getColumnCount();
			
			while (resultSet.next()) 
			{
				Vector row = new Vector(3);
				
					row.addElement( resultSet.getInt(1) );
					row.addElement( resultSet.getString(2) );
					row.addElement( resultSet.getString(3) );
					row.addElement( resultSet.getString(4) );
					row.addElement( resultSet.getString(5) );
					row.addElement( resultSet.getString(6) );
					
					//System.out.println(row);
				data.addElement( row );
				
			}
			resultSet.close();
			statement.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
		
	}
	
	public Vector fileDetails()
	{
		Vector data = new Vector();
		try
		{
			String query = "Select a.file_no,a.file_name,a.file_datetime,b.user_name from m_file_upload a join m_client b on a.user_code=b.c_code";
			
			connection = DBConnector.db_connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			int columns = md.getColumnCount();
			
			while (resultSet.next()) 
			{
				Vector row = new Vector(columns);
				for (int i = 1; i <= columns; i++)
				{
					row.addElement( resultSet.getObject(i) );
				}
				data.addElement( row );
				
			}
			resultSet.close();
			statement.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
		
	}
	
	public Vector logfileDetails()
	{
		Vector data = new Vector();
		try
		{
			String query = "select * from m_log";
			
			connection = DBConnector.db_connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			int columns = md.getColumnCount();
			
			while (resultSet.next()) 
			{
				Vector row = new Vector(columns);
				for (int i = 1; i <= columns; i++)
				{
					row.addElement( resultSet.getObject(i) );
				}
				data.addElement( row );
				
			}
			resultSet.close();
			statement.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
		
	}
	
	
	public String CheckVirus(ArrayList vIndexData,String v_layerName)
	{
		
		 
		boolean flag=false; 
		try
		{
			connection= DBConnector.db_connector();
			statement = connection.createStatement();
			
			
			
			if(v_layerName.trim().equals(Constants.Layer1))
			{
				String query1="select f_v_name from probe_layer_virus where f1='"+vIndexData.get(0)+"' or f2='"+vIndexData.get(1)+"' or f3='"+vIndexData.get(2)+"' or f4='"+vIndexData.get(3)+"' or f5='"+vIndexData.get(4)+"'";
				resultSet=statement.executeQuery(query1); 
				System.out.println("layer1=====>"+query1);
			}
			
			else if(v_layerName.trim().equals(Constants.Layer2))
			{
				String query2="select f_v_name from dos_layer_virus where f1='"+vIndexData.get(0)+"' or f2='"+vIndexData.get(1)+"' or f4='"+vIndexData.get(2)+"' or f5='"+vIndexData.get(3)+"' or f23='"+vIndexData.get(4)+"' or f34='"+vIndexData.get(5)+"' or f38='"+vIndexData.get(6)+"' or f39='"+vIndexData.get(7)+"' or f40='"+vIndexData.get(8)+"'";
				
				resultSet=statement.executeQuery(query2); 
				System.out.println("layer2=====>"+query2);
			}
			
			else if(v_layerName.trim().equals(Constants.Layer3))
			{
				String query3="select f_v_name from r2l_layer_virus where f1='"+vIndexData.get(0)+"' or f2='"+vIndexData.get(1)+"' or f3='"+vIndexData.get(2)+"' or f4='"+vIndexData.get(3)+"' or f5='"+vIndexData.get(4)+"' or f10='"+vIndexData.get(5)+"' or f11='"+vIndexData.get(6)+"' or f12='"+vIndexData.get(7)+"' or f13='"+vIndexData.get(8)+"' or f17='"+vIndexData.get(9)+"' or f18='"+vIndexData.get(10)+"' or f19='"+vIndexData.get(11)+"' or f21='"+vIndexData.get(12)+"' or f22='"+vIndexData.get(13)+"' ";
				
				resultSet=statement.executeQuery(query3);
				System.out.println("layer3=====>"+query3);
			}
			
			else if(v_layerName.trim().equals(Constants.Layer4))
			{
				String query4="select f_v_name from u2r_layer_virus where f10='"+vIndexData.get(0)+"' or f13='"+vIndexData.get(1)+"' or f14='"+vIndexData.get(2)+"' or f16='"+vIndexData.get(3)+"' or f17='"+vIndexData.get(4)+"' or f18='"+vIndexData.get(5)+"' or f19='"+vIndexData.get(6)+"' or f21='"+vIndexData.get(7)+"'";
				
				resultSet=statement.executeQuery(query4);
				System.out.println("layer4=====>"+query4);
			}
			
			
			
			while(resultSet.next())
			{
				v_name= resultSet.getString("f_v_name"); 
				flag = true;
				
			}
			System.out.println("-----------@@@@---> " + v_name); 
			
		}
		
		catch(Exception e)
		{
			
		}
		return v_name;
		
	}
	
	public int getId(String v_existing_uid)
	{
		int c_code=0;
		try
		{
			String query="select c_code from m_client where id='"+v_existing_uid+"'";
			connection= DBConnector.db_connector();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(query);
			
			while(resultSet.next())
			{
				c_code = resultSet.getInt(1);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnector.db_connection_close(connection, statement, resultSet);
		}	
		return c_code;
	}
	
	public boolean getBlockIP(String ip)
	{
		boolean flag=false;
		try
		{
			String query="select * from m_block where Block_IpAddress='"+ip+"'";
			connection= DBConnector.db_connector();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(query);
			
			while(resultSet.next())
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnector.db_connection_close(connection, statement, resultSet);
		}	
		return flag;
	}
	
	public Vector BlockedDetails()
	{
		Vector data = new Vector();
		try
		{
			String query = "select * from m_block";
			
			connection = DBConnector.db_connector();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			int columns = md.getColumnCount();
			
			while (resultSet.next()) 
			{
				Vector row = new Vector(columns);
				for (int i = 1; i <= columns; i++)
				{
					row.addElement( resultSet.getObject(i) );
				}
				data.addElement( row );
				
			}
			resultSet.close();
			statement.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
		
	}
	
	
	public  boolean checkusage(String ip,String date)
	{
		boolean flag=false; 
		int count=0;
	String th=GetProperty.getProperty("count");
	int count_val=Integer.parseInt(th);
		
		try{
			connection= DBConnector.db_connector();
			statement=connection.createStatement();
			
			String sql="select no_acess from m_transfer where ipaddress='"+ip+"' ";
			System.out.println(sql);
			resultSet=statement.executeQuery(sql);
			while(resultSet.next())
			{
							
				count=resultSet.getInt(1);					
				
			}
			System.out.println("   count value  ============>"+count);
	
				if(count==0)
				{
					statement.executeUpdate("insert into m_transfer (t_datetime,ipaddress,no_acess) values('"+date+"','"+ip+"',1)");
					
					flag=true;
				}
				else if(count<count_val)
				{
					count=count+1;
					statement.executeUpdate("update m_transfer set no_acess="+count+" where  ipaddress='"+ip+"' ");
					flag=true;
				}
				else if(count==count_val)
				{
					statement.executeUpdate("insert into m_block (Block_IpAddress) values('"+ip+"')");
				}
							
		}catch (Exception e) 
		{
		e.printStackTrace();	
		}
		
		
		
		return flag;
	}
	
	public  boolean insertLog(String ip,String date,String fnme,String status,String virus,String layer)
	{
		boolean flag=false; 

		
		try{
			connection= DBConnector.db_connector();
			statement=connection.createStatement();
			
			String sql="insert into m_log (T_datetime,ClientIp,Filename,file_status,Layer,AttackName) values('"+date+"','"+ip+"','"+fnme+"','"+status+"','"+layer+"','"+virus+"')";
			System.out.println(sql);
			statement.executeUpdate(sql);
			
							
		}catch (Exception e) 
		{
		e.printStackTrace();	
		}
		
		
		
		return flag;
	}

	public static boolean getuserstatus(String name)
	{
		boolean flag=false;
		try
		{
		
		Connection connection= DBConnector.db_connector();
		Statement statement=connection.createStatement();
		String str="select Block_IpAddress from m_block where Block_IpAddress in(select Ip_address from m_client where user_name='"+name+"');";
		System.out.println(str);
		ResultSet rs=statement.executeQuery(str);
		while(rs.next())
		{
			flag=true;
		}
		
		}catch (Exception e) 
		{
		e.printStackTrace();	
		}
		System.out.println("Ip is "+flag);
		
		return flag;
	}

	
	public static ArrayList getblockipdetails()
	{
		 ArrayList<String> list = new ArrayList<String>();
			try
			{
				String query = "SELECT Block_IpAddress FROM m_block";
				System.out.println("query is "+query);
				Connection con=DBConnector.db_connector();
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				
				
					while (rs.next()) 
					{
						list.add(rs.getString(1));
					}
				
				
				
				
				
			}
			catch(Exception e)
			{
				System.out.println(e); 
			}
			
			System.out.println(list.toString());
			return list;
		
	}

	public static boolean unblock(String name)
	{
		boolean flag=false; 

		
		try{
			Connection connection= DBConnector.db_connector();
			Statement statement=connection.createStatement();
			
			String sql="delete from m_block where Block_IpAddress='"+name+"'";
			System.out.println(sql);
			int a=statement.executeUpdate(sql);
			
						if(a>0)
						{
							flag=true;
						}
		}catch (Exception e) 
		{
		e.printStackTrace();	
		}
		
		
		
		return flag;
		
	}

	
	
}
