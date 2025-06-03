package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//import java.io.PrintWriter;

import com.dao.logindao;
import com.model.registermodel;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() 
    {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		registermodel lmodel=new registermodel();
		lmodel.setEmail(request.getParameter("email"));
		lmodel.setPassword(request.getParameter("password"));
		
		registermodel model=new logindao().userLogin(lmodel);
//		if(model != null)
//		{
//			HttpSession session=request.getSession(true);
//			session.setAttribute("model", model);
//			response.setContentType("text/html");
//			PrintWriter out=response.getWriter();
//			out.println("<h3 style> Login Successfully..</h3> ");
//			request.getRequestDispatcher("index.jsp").forward(request, response);
//		}
//		else
//		{
//			response.setContentType("text/html");
//			PrintWriter out=response.getWriter();
//			out.println("<h3 style='color:red'> Email And Password didnot matched </h3> ");
//			request.getRequestDispatcher("login.jsp").include(request, response);
//		}
		
		if(model != null)
		{
			HttpSession session=request.getSession(true);
			session.setAttribute("model", model);
			session.setAttribute("firstname", model.getFirstname());
			session.setAttribute("lastname", model.getLastname());

			response.sendRedirect("cust-home.jsp");
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
		
		
	}

}
