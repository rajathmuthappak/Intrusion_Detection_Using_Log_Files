package com.ids.support;

import java.util.ArrayList;

import com.ids.constants.Constants;


public class Layers
{
	static Detect dtct = new Detect();
	public static String ProbeLayer(String data)
	{
		  ArrayList index = new ArrayList();
		  index.add(1);
		  index.add(2);
		  index.add(3);
		  index.add(4);
		  index.add(5);
		  
		  String detect = dtct.dataToProcess(data,index,Constants.Layer1);
		return detect; 
	}
	
	public static String R2LLayer(String data)
	{
		 ArrayList index = new ArrayList();
		    index.add(1);
		    index.add(2);
		    index.add(3);
		    index.add(4);
		    index.add(5);
		    index.add(10);
		    index.add(11);
		    index.add(12);
		    index.add(13);
		    index.add(17);
		    index.add(18);
		    index.add(19);
		    index.add(21); 
		    index.add(22);
		    String detect = dtct.dataToProcess(data,index,Constants.Layer3);
		  
		return detect;
		  
		  
	}
	
	public static String DOSLayer(String data)
	{
		  ArrayList index = new ArrayList();
		    index.add(1);
		    index.add(2);
		    index.add(4);
		    index.add(5);
		    index.add(23);
		    index.add(34);
		    index.add(38);
		    index.add(39);
		    index.add(40);
		    String detect = dtct.dataToProcess(data,index,Constants.Layer2);
		    
		return detect;
		  
		  
	}
	
	public static String U2RLayer(String data)
	{
	    ArrayList index = new ArrayList();
	    index.add(10);
	    index.add(13);
	    index.add(14);
	    index.add(16);
	    index.add(17);
	    index.add(18);
	    index.add(19);
	    index.add(21);
	    
	    String detect = dtct.dataToProcess(data,index,Constants.Layer4);
			return detect;
		  
		  
	}


}
