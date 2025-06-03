package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.dao.registerdao;
import com.model.registermodel;


@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException
	{
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		registermodel rmodel=new registermodel();
		rmodel.setFirstname(request.getParameter("firstname"));
		rmodel.setLastname(request.getParameter("lastname"));
		rmodel.setEmail(request.getParameter("email"));
		rmodel.setMobile(request.getParameter("mobile"));
		rmodel.setAddress(request.getParameter("address"));
		rmodel.setGender(request.getParameter("gender"));
		rmodel.setPassword(request.getParameter("password"));
		rmodel.setConfirmpassword(request.getParameter("confirmpassword"));
		
		int x=new registerdao().customerRegistration(rmodel);
		
			if(x>0)
			{
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<h3 style='color:Green'> User Registration Successfully...</h3>");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else
			{
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<h3 style='color:red'> User Registration Not Successfully...</h3>");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
		
	}

}
