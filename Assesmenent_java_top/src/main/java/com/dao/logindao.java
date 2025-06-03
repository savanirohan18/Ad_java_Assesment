package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.registermodel;
import com.util.dbutil;

public class logindao 
{
	Connection cn=null;
	
	public registermodel userLogin(registermodel lmodel)
	{
		registermodel model=null;
		cn=new dbutil().getConnectionData();
		
		String qry="select * from register where email=? and password=?";
		
		try 
		{
			PreparedStatement ps=cn.prepareStatement(qry);
			ps.setString(1, lmodel.getEmail());
			ps.setString(2, lmodel.getPassword());
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				model=new registermodel();
				model.setCustomerid(rs.getInt(1));
				model.setFirstname(rs.getString(2));
				model.setLastname(rs.getString(3));
				model.setEmail(rs.getString(4));
				model.setMobile(rs.getString(5));
				model.setAddress(rs.getString(6));
				model.setGender(rs.getString(7));
				model.setPassword(rs.getString(8));
				model.setConfirmpassword(rs.getString(9));

			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return model;
		
	}
	
	
}
