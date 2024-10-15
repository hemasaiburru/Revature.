<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.SellerDto"%>
<%
    HttpSession sellerSession = request.getSession();
    SellerDto seller = (SellerDto) sellerSession.getAttribute("seller");
    
    if (seller == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EcommRev</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
    <style>
        /* Navbar customization */
        .navbar {
            background-color: #343a40; /* Dark background */
            padding: 1rem; /* Padding for more space */
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Add shadow */
        }
        .navbar-brand {
            color: #ffffff; /* White text for brand */
        }
        .navbar-nav .nav-link {
            color: #ffffff; /* White text for links */
        }
        .navbar-nav .nav-link:hover {
            color: #d3d3d3; /* Light grey hover color */
        }
        .navbar-toggler-icon {
            background-color: #ffffff; /* White color for the toggle icon */
        }

        /* Dropdown menu customization */
        .dropdown-menu {
            background-color: #343a40; /* Dark dropdown */
            border: none; /* Remove borders */
        }
        .dropdown-item {
            color: #ffffff; 
        }
        .dropdown-item:hover {
            background-color: #495057;
        }
        .alert {
            border-radius: 10px; 
        }
    </style>
</head>
<body>
	<% 
		 String updateSuccess = (String) sellerSession.getAttribute("updateSuccess");
		    if (updateSuccess != null) {
		%>
		    <script>
		        alert("<%= updateSuccess %>");
		    </script>
		<%
				sellerSession.removeAttribute("updateSuccess");
		    }
		%>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="#">
            <img src="<%= request.getContextPath() %>/images/cartoon.webp" width="30" height="30" alt="Cartoon Logo"> EcommRev
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/ServerController?userType=product_add">Add Product</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Manage Products
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/ecommProject/UpdateProductController?rev=manage">View Products</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/ecommProject/UpdateProductController?rev=checkMate">Check Product Availability</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/ecommProject/OrderServlet?process=orderHistorySeller">Order History</a>
                </li>>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="https://img.icons8.com/ios-filled/50/ffffff/user-male-circle.png" alt="Profile" width="30" height="30">
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="profileDropdown">
                        <a class="dropdown-item" href="/ecommProject/ServerController?userType=SellerProfile">Profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/ecommProject/">Logout</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Welcome Message -->
    <div class="container mt-3">
        <div class="alert alert-info text-center" role="alert">
            <h4 class="alert-heading">Welcome, <%= seller.getName() %>!</h4>
            <p>We're glad to have you back on your dashboard. Let's manage your products and sales!</p>
        </div>
        <img src="<%= request.getContextPath() %>/images/sellerDashboard.jpg" alt="Login Picture" style="width:100%;height:100%"/>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
