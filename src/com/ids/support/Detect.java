package com.ids.support;
import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.lang.String;

import com.ids.dao.LoginDao;



public class Detect
{
	String stringarray;
	public static String virus;
	ArrayList index_data= new ArrayList();
	String array_data[];
	static String virus_data[];
//	static String virus="smruf:neptune:ipsweep:pod:nmap";
	public static String detected_virus;
	 public  String detected=null;
	 public static boolean found;

  
  public String dataToProcess(String data , ArrayList index, String layer) 
  {
	  
	  ArrayList data_send = new ArrayList();
	  String flag = null;  
	  try
	  {
		  array_data=data.split(",");
		  index_data=arrayToString(array_data,index);
		  flag =detectionProcess(index_data,layer);
	  }
	  catch(Exception e) 
	  {
		  
	  }
	return flag; 
  }
  
  public ArrayList arrayToString(String[] stringarray, ArrayList index) 
  {
	  ArrayList data = new ArrayList();
	  
	  String str = " ";
	  for(int i =0;i<index.size();i++)
	  {
		  Object indx = index.get(i); 
		  int st = Integer.parseInt(indx.toString()); 
		  str =  stringarray[st];
		  data.add(str);
		  
		  
	  }
	  
//	  detectionProcess(data);
	  
		  
	  return data;
  }
  
  private String detectionProcess(ArrayList v_index_data, String layer_name)   
  { 
	  
	  for(int i=0;i<v_index_data.size();i++ )
	  {
		   System.out.println("----------> " +v_index_data.get(i)); 
	  }

	  System.out.println("-----=========----------------==============-------------------------- " + layer_name);
//	  virus_data= virus.split(":");
//	  for(int j=0;j<virus_data.length;j++)
//		{
//			int flag = indexData.indexOf(virus_data[j]);
//			if(flag>=0)
//			{
//				detected=true;
//				found=true;
//				System.out.println("Found -----> "+virus_data[j]);
//				detected_virus=virus_data[j];
//				
//			}
//		}
	  LoginDao ml = new LoginDao();
	  detected= ml.CheckVirus(v_index_data,layer_name); 
	  
//	  if(!(virus.equals("")))
//	  {
//		  detected=true;
//		  
//	  }
			  
	return detected; 
	
  }

 
}