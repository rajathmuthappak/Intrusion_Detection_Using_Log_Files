package com.ids.support;


import java.text.SimpleDateFormat;
import java.util.*;

public class CurrentTimeDate
{
  

  public static String getCurrentTime()
  {
	  String timeformat = "HH:MM:ss";
	  SimpleDateFormat obDateFormat = new SimpleDateFormat(timeformat);
	  Calendar cal = Calendar.getInstance();
	  String time= obDateFormat.format(cal.getTime());
	  
	  return time;
  }
  
  
  public static String getCurrentDate()
  {
	  String dateformat = "yyyy-dd-mm";
	  SimpleDateFormat obDateFormat = new SimpleDateFormat(dateformat);
	  Calendar cal = Calendar.getInstance();
	  String date= obDateFormat.format(cal.getTime());
	  System.out.println("Date is "+date);
	  
	  return date;
  }
  public static int getCurrentTimeInSec(String time)
  {
	  
	  String time1[]=time.split(":");
	  for(int i=0;i<time1.length;i++)
		{
			System.out.println("  time1 ["+i+"]  "+time1[i]);
		}
		
	  int hour=Integer.parseInt(time1[0]);
	  int min=Integer.parseInt(time1[1]);
	  int sec=Integer.parseInt(time1[2]);
	  int seconds=hour*3600+min*60+sec;
	
	  return seconds;
 }

 
}