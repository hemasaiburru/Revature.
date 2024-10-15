<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="dto.ProductDto" %>
<%
    String productId = request.getParameter("productId");
    if(productId==null){
    	response.sendRedirect("/ecommProject/");
    	return ;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enter Quantity</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand">EcommRev</a>
    </nav>

    <div class="container mt-4">
        <h3>Enter Quantity</h3>
        <form action="/ecommProject/OrderServlet?process=buyNow" method="post">
            <input type="hidden" name="productId" value="<%= productId %>">
            <div class="form-group">
                <label for="quantity">Quantity</label>
                <input type="number" class="form-control" id="quantity" name="quantity" min="1" required>
            </div>
            <button type="submit" class="btn btn-primary">OK</button>
            <a href="javascript:history.back()" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
