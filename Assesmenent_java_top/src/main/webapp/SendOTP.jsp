<%@page import="com.model.registermodel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white text-center">
                    <h3>Forgot Password Page</h3>
                    ${notmatch }
                                                 </div>
    <%
String email=null;
if(session.getAttribute("otp")==null) 
{
response.sendRedirect("forgotpassword.jsp");
}
else	
{
	registermodel f=(registermodel)session.getAttribute("UserData");
	email=f.getEmail();
}
%>
                <div class="card-body">
                    <form action="ForgotController" method="post">
                        <div class="mb-3">
                                <label>Email Send On Email Address &emsp;<%=email%></label>
                            <input type="text" name="email" class="form-control" placeholder="enter your otp" required>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" name="action" value="verify" class="btn btn-primary">verify</button>
                        </div>
                    </form>  
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
