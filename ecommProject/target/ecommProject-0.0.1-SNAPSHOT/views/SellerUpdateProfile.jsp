<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="dto.SellerDto" %>
<%
    HttpSession session1 = request.getSession();
    SellerDto seller = (SellerDto) session1.getAttribute("seller");
    if (seller == null) {
        response.sendRedirect("/ecommProject/");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Profile</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(180deg, #003366, #e0e0e0);
            min-height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .form-container {
            margin: 50px;
            width: 600px;
            padding: 20px;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .btn-container {
            margin-top: 20px;
        }
        .btn-container .btn {
            width: 150px;
            margin: 0 10px;
        }
        .error-message,.er{
        	color: red;
            font-size: 12px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Update Profile</h2>
        <form id="updateProfileForm" action="/ecommProject/UpdateSellerServlet" method="post" onsubmit="return fun_valid()">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="<%= seller.getName() %>" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= seller.getEmail() %>" required>
            </div>
            <div class="form-group">
                <label for="phno">Phone Number</label>
                <input type="text" class="form-control" id="phno" name="phno" value="<%= seller.getPhno() %>" required>
                <span class="error"></span>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <textarea class="form-control" id="businessAddress" name="businessAddress" rows="3" required><%=seller.getBus_address()%></textarea>
            </div>
            <div class="form-group">
                <label for="address">BusinessName</label>
                <textarea class="form-control" id="businessAddress" name="businessName" rows="3" required><%=seller.getBus_name()%></textarea>
            </div>
            <div class="form-group">
                <input type="checkbox" id="updatePassword" name="updatePassword">
                <label for="updatePassword">Update Password</label>
            </div>
            <div id="passwordFields" style="display: none;">
                <div class="form-group">
                    <label for="newPassword">New Password</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword">
                    <span class="er"></span>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                    <p id="error-message" class="error-message"></p>
                </div>
            </div>
            <div class="btn-container">
                <a href="/ecommProject/ServerController?userType=profile" class="btn btn-secondary">Cancel</a>
                <input type="submit" class="btn btn-primary" value="Update">
            </div>
        </form>
    </div>

    <script>
        // Show/Hide password fields when checkbox is selected
        document.getElementById('updatePassword').addEventListener('change', function() {
            document.getElementById('passwordFields').style.display = this.checked ? 'block' : 'none';
        });

        // Validation function
        function fun_valid() {
            var phno = document.getElementById("phno").value;
            var phoneRegex = /^\d{10}$/;

            // Phone number validation
            if (!phoneRegex.test(phno)) {
                var phoneError = document.getElementsByClassName("error")[0];
                phoneError.textContent = "Phone number must be exactly 10 digits.";
                return false;
            }

            // Check if update password is selected
            var updatePasswordChecked = document.getElementById('updatePassword').checked;

            if (updatePasswordChecked) {
                var newPassword = document.getElementById('newPassword').value;
                var confirmPassword = document.getElementById('confirmPassword').value;
                var passwordError = document.getElementsByClassName("er")[0];
                var pwdRegex = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
                if (!pwdRegex.test(newPassword)) {
                    passwordError.textContent = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long.";
                    return false;
                }
                if (newPassword !== confirmPassword) {
                    document.getElementById('error-message').textContent = 'Passwords do not match';
                    return false;
                } else {
                    document.getElementById('error-message').textContent = '';
                }
            }
            return true;
        }
    </script>
</body>
</html>
