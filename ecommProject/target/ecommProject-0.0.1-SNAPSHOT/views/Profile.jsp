<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="dto.BuyerDto" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession session1 = request.getSession();
    BuyerDto buyer = (BuyerDto) session1.getAttribute("buyer");
    if(buyer==null){
		response.sendRedirect("/ecommProject/");
        return;
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <style>
        body {
         background: linear-gradient(to right, #fc5c7d, #6a82fb); 
        }
        .profile-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .profile-img {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
            margin: 0 auto 20px;
        }
        .profile-details {
            margin-bottom: 20px;
            text-align: left;
        }
        .profile-details label {
            font-weight: bold;
        }
        .btn-container {
            margin-top: 20px;
        }
        .btn-container .btn {
            width: 150px;
            margin: 0 10px;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <img src="<%= request.getContextPath() %>/images/buyer.png" alt="Profile Picture" class="profile-img">
        <div class="profile-details">
            <p><label for="name">Name:</label> <%= buyer.getName() %></p>
            <p><label for="email">Email:</label> <%= buyer.getEmail() %></p>
            <p><label for="phno">Phone Number:</label> <%= buyer.getPhno() %></p>
            <p><label for="address">Address:</label> <%= buyer.getAddress() %></p>
        </div>
        <div class="btn-container">
            <a href="/ecommProject/ServerController?userType=buyer" class="btn btn-secondary">Cancel</a>
            <a href="/ecommProject/ServerController?userType=update" class="btn btn-primary">Update Profile</a>
        </div>
    </div>
    
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
