package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.model.registermodel;
import com.util.dbutil;

public class registerdao 
{
	Connection cn=null;
	
	public int customerRegistration(registermodel rmodel)
	{
		int x=0;
		cn=new dbutil().getConnectionData();
		
//		String Query=;
		try 
		{
			PreparedStatement ps=cn.prepareStatement("insert into register(firstname , lastname , email , mobile , address , gender , password , confirmpassword) values (?,?,?,?,?,?,?,?)");
			ps.setString(1, rmodel.getFirstname());
			ps.setString(2, rmodel.getLastname());
			ps.setString(3, rmodel.getEmail());
			ps.setString(4, rmodel.getMobile());
			ps.setString(5, rmodel.getAddress());
			ps.setString(6, rmodel.getGender());
			ps.setString(7, rmodel.getPassword());
			ps.setString(8, rmodel.getConfirmpassword());
			
			x=ps.executeUpdate();
			cn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return x;
		
	}
}
