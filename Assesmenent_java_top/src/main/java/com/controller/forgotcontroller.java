package com.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.dao.ForgotDao;
import com.model.registermodel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/ForgotController")
public class forgotcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		
		if(action.equalsIgnoreCase("submit"))
		{
			String email=request.getParameter("email");
			 registermodel model=new registermodel();
			ForgotDao dao=new ForgotDao();
			model=dao.checkEmail(email);
			if(model==null)
			{
				request.setAttribute("invalidemail", "Email Address Not Valid");
				request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
			}
			else
			{
				
				String emailid=model.getEmail();
				String username=model.getFirstname()+" "+model.getLastname();
				final String Senderid = "bharwadmk22@gmail.com";
				final String password = "bqhq sbul zvxv hdzo";

				Properties props = new Properties();

				String host = "smtp.gmail.com";

				
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "true");
				props.put("mail.smtp.ssl.protocols", "TLSv1.2");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.debug", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", "465");
				props.put("mail.smtp.user", Senderid);
				props.put("mail.smtp.password", password);
				
				Session session = Session.getInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(Senderid, password);
					}
				});

				try {
					Random rand = new Random();
					int otp = rand.nextInt(900000) + 100000;
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(Senderid));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailid));// to
																									// mail
																									// address.
					message.setSubject("OTP Request");
					String msg1 = "<!DOCTYPE html><html><head></head><body><center><div style='background-color:#f8f8f8; width:500px; height:200px'><div style='background-color:#00e58b; width:500px; height:50px'><h3 style='color:white; padding-top:10px;'>EmailDemo.com </h3></div><p style='color:gray; margin-left:-300px;'>Dear "
							+ username + ",</p><br><p style='color:gray; margin-top:-10px;'>" + otp
							+ "  is your One Time Password.Do NOT share this code with anyone for security reasons.this is valid for 10 minutes.</p><div></center></body></html>";
					message.setContent(msg1, "text/html; charset=utf-8");
			
					Transport.send(message);

					HttpSession otpsession = request.getSession();
					otpsession.setAttribute("otp", otp);
					otpsession.setMaxInactiveInterval(10 * 60); /*Session Set for 10 minutes*/
					otpsession.setAttribute("UserData",model);
					request.getRequestDispatcher("SendOTP.jsp").forward(request, response);

				} catch (Exception e) {
					request.setAttribute("SendOtpError", "Otp Not Send");
					request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
					e.printStackTrace();
				}
			}
			
		}
		
		else if(action.equalsIgnoreCase("Verify"))
		{
			HttpSession session=request.getSession(false);
			String G_otp=String.valueOf(session.getAttribute("otp"));
			String E_otp=request.getParameter("enterotp");
			
			if(G_otp.equalsIgnoreCase(E_otp))
			{
				request.setAttribute("Otpmatch", "OTP Match");
				request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("notmatch", "OTP Not Match");
				request.getRequestDispatcher("SendOTP.jsp").forward(request, response);
			}
			
			
			
		}
		else if(action.equalsIgnoreCase("confirm"))
		{
			String pswd=request.getParameter("newpassword");
			 registermodel model=new registermodel();
			HttpSession session=request.getSession(false);
			model=(registermodel)session.getAttribute("UserData");
			model.setPassword(pswd);
			model.setCustomerid(model.getCustomerid());
			ForgotDao dao=new ForgotDao();
			int r=dao.resetPassword(model);
			if(r>0)
			{
				response.sendRedirect("index.jsp");
				System.out.println("Password Recovery Success!");
			}
			else
			{
				System.out.println("Error");
			}
			
			
		}
		
	}

}
