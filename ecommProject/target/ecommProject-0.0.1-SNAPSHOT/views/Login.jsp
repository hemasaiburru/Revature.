<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
	    body {
		    font-family: Arial, sans-serif;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
		    margin: 0;
		    background: linear-gradient(135deg, #1d2b64, #f8cdda);
		    background-attachment: fixed;
		    color: #333;
		}
		.welcome-bar {
		    width: 100%;
		    padding: 20px 10px;
		   	background: linear-gradient(to right, #da4453, #89216b);
		    color: white;
		    text-align: center;
		    font-size: 28px;
		    font-weight: bold;
		    letter-spacing: 1px;
		    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
		    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
		    border-radius: 10px;
		    position: absolute;
		    top: 10px;
		    left: 10px;
		    right: 10px;
		    transition: all 0.4s ease; 
		}
		.welcome-bar:hover {
		    background: linear-gradient(90deg, #66bb6a, #43a047);
		    color: #f8f8f8; 
		    text-shadow: 2px 2px 6px rgba(0, 0, 0, 0.3); 
		    box-shadow: 0 6px 14px rgba(0, 0, 0, 0.2);
		    transform: scale(1.02);
		}
		.container {
		    width: 500px;
		    margin: 0 auto;
		    border: 1px solid #ccc;
		    padding: 20px;
		    background-color: white;
		    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
		    border-radius: 8px;
		}
		.tabs {
		    display: flex;
		    justify-content: space-around;
		    margin-bottom: 20px;
		    cursor: pointer;
		}		
		.tab {
		    width: 50%;
		    text-align: center;
		    padding: 10px;
		    background-color: #f1f1f1;
		    border: 1px solid #f4f4f4;
		}
		.tab.active {
		    background-color: #28a745;
		    color: white;
		}
		.form-container {
		    display: none;
		    padding: 20px;
		}
		.form-container.active {
		    display: block;
		}
		input[type="text"], input[type="password"] {
		    width: 100%;
		    padding: 10px;
		    margin: 15px 0;
		    border: 1px solid #ccc;
		    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
		    border-radius: 4px;
		}
		input[type="submit"] {
		    width: 100%;
		    padding: 10px;
		    background-color: #28a745;
		    color: white;
		    border: none;
		    cursor: pointer;
		    border-radius: 4px;
		}
		input[type="submit"]:hover {
		    background-color: #218838; 
		}
		.register-link {
		    display: block;
		    text-align: center;
		    margin-top: 20px;
		}
    </style>
</head>
<body>
	<div class="welcome-bar">
        Welcome to RevShop - Your Trusted eCommerce Platform
    </div>
	
    <div class="container">
        <div class="tabs">
            <div class="tab active" id="buyer-tab">Buyer Login</div>
            <div class="tab" id="seller-tab">Seller Login</div>
        </div>
        <div class="form-container active" id="buyer-form">
            <form action="/ecommProject/LoginServlet" method="post">
            	<input type="hidden" name="userType" value="buyer">
                <h2>Buyer Login</h2>
                <input type="text" name="username" placeholder="Enter Username" required>
                <input type="password" name="password" placeholder="Enter Password" required>
                <input type="submit" value="Login as Buyer">
            </form>
            <a href="<%= request.getContextPath() %>/ServerController?userType=buyer_register" class="register-link">Don't have an account? Register here</a>
        </div>
        <div class="form-container" id="seller-form">
            <form action="/ecommProject/LoginServlet" method="post">
            	<input type="hidden" name="userType" value="seller">
                <h2>Seller Login</h2>
                <input type="text" name="username" placeholder="Enter Username" required>
                <input type="password" name="password" placeholder="Enter Password" required>
                <input type="submit" value="Login as Seller">
            </form>
            <a href="<%= request.getContextPath() %>/ServerController?userType=seller_register" class="register-link">Don't have an account? Register here</a>
        </div>
    </div>
    <script>
        document.getElementById("buyer-tab").addEventListener("click", function() {
            document.getElementById("buyer-form").classList.add("active");
            document.getElementById("seller-form").classList.remove("active");
            this.classList.add("active");
            document.getElementById("seller-tab").classList.remove("active");
        });

        document.getElementById("seller-tab").addEventListener("click", function() {
            document.getElementById("seller-form").classList.add("active");
            document.getElementById("buyer-form").classList.remove("active");
            this.classList.add("active");
            document.getElementById("buyer-tab").classList.remove("active");
        });
    </script>
    <% 
	    String errorMessage = (String) request.getAttribute("errorMessage");
	    if (errorMessage != null) { 
	%>
	    <script>
	        alert("<%= errorMessage %>");
	    </script>
	<% 
	    } 
	%>
	<%@ include file="Footer.jsp" %>
</body>
</html>
