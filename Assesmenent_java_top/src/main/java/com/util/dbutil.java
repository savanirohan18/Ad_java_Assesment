package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbutil 
{
	//	private final static String URL="jdbc:mysql://localhost:3306/assesment";
//	private final static String USERNAME="root";
//	private final static String PASSWORD="";
	
	  Connection cn=null;

	public  Connection getConnectionData()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/assesment","root","");
			System.out.println("Connection established");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return cn;
	}
	
}
