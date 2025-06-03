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
        <div class="col-md-8">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white text-center">
                    <h3>User Registration</h3>
                </div>
                <div class="card-body">
                    <form action="SignUpServlet" method="post">
                        <!-- First & Last Name -->
                        <div class="row mb-3">
                            <div class="col">
                                <label>First Name</label>
                                <input type="text" name="firstname" class="form-control" placeholder="Enter your first name" required>
                            </div>
                            <div class="col">
                                <label>Last Name</label>
                                <input type="text" name="lastname" class="form-control" placeholder="Enter your last name" required>
                            </div>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label>Email</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="Enter your email address" required onblur="checkEmail()">
                            <div id="emailMsg" class="form-text text-danger"></div>
                        </div>

                        <!-- Mobile -->
                        <div class="mb-3">
                            <label>Mobile</label>
                            <input type="text" name="mobile" class="form-control" placeholder="10-digit mobile number" required pattern="[0-9]{10}">
                        </div>

                        <!-- Address -->
                        <div class="mb-3">
                            <label>Address</label>
                            <textarea name="address" class="form-control" placeholder="Enter your address" rows="2" required></textarea>
                        </div>

                        <!-- Gender -->
                        <div class="mb-3">
                            <label>Gender</label><br>
                            <div class="form-check form-check-inline">
                                <input type="radio" name="gender" value="Male" class="form-check-input" required>
                                <label class="form-check-label">Male</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" name="gender" value="Female" class="form-check-input">
                                <label class="form-check-label">Female</label>
                            </div>
                        </div>

                        <!-- Password and Confirm Password -->
                        <div class="row mb-3">
                            <div class="col">
                                <label>Password</label>
                                <input type="password" name="password" id="password" class="form-control" placeholder="Create a password" required>
                            </div>
                            <div class="col">
                                <label>Confirm Password</label>
                                <input type="password" name="confirmpassword" id="confirmPassword" class="form-control" placeholder="Re-enter your password" required>
                            </div>
                        </div>

                        <!-- Submit Button -->
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
