package com.ids.constants;


public interface Constants 
{
	public static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	public static final String JDBC_HOST_URL_WITH_DBNAME="jdbc:mysql://localhost:3306/nw_honeypot";
	public static final String DATABASE_USERNAME="root";
	public static final String DATABASE_PASSWORD="admin";

	//----------------------------------------------------------------------------------------------------------------------//
		public static final String FROM_EMAIL ="dhsinfoblr";
		public static final String FROM_EMAIL_PASSWORD ="blrindia123";
		
		public static final String REGISTERATION_FORM_SUB ="Pseudonym  : Keys";
		public static final String REGISTERATION_FORM_MSG ="Dear User,"+"\n"+"Thank You for registering with us. Here we have attached your keys "+"\n\n"+"Regards and Thanks"+"\n"+"Location Proof";

		public static final String HOST_NAME ="smtp.gmail.com";
		public static final String HOST_PORT_NUMB ="465";
		
	//----------------------------------------------------------------------------------------------------------------------//

		public static final int S_FNAME_PORT_NUM = 5001;
		public static final int S_UPLOAD_PORT_NUM = 5002;
	
		//----------------------------------------------------------------------------------------------------------------------//
		public static final String Server_name="192.168.1.8";
		
		public static final String Layer1 = "probe_layer";
		public static final String Layer2 = "Dos_layer";
		public static final String Layer3 = "R2L_layer";
		public static final String Layer4 = "U2R_layer";
		public static final String DESTINATION = "Destination";
		
		
		
		public static final String PASS = "Virus Free";
		public static final String FAIL = "Virus Detected";

	
}
