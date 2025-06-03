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
                    ${invalidemail }
                    <%if(request.getAttribute("Otpmatch")!=null) {%>
                </div>
                <div class="card-body">
                    <form action="ForgotController" method="post">
                        <div class="mb-3">
                            <input type="password" name="newpassword" class="form-control" placeholder="Enter your password" required>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" name="action" value="confirm"  class="btn btn-primary">Confirmed</button>
                        </div>
                    </form>  
                     <%}
else
{
	response.sendRedirect("SendOTP.jsp");
}
%>
                    
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
