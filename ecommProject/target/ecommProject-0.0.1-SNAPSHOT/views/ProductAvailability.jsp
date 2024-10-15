<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.ProductDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Availability</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(to right, #f0f0f0, #e0e0e0);
        margin: 0;
        padding: 0;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin: 20px auto;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    th {
        background-color: black;
        color: white;
        padding: 10px;
        text-align: left;
    }
    tr:nth-child(even) {
        background-color: #d3d3d3;
    }
    tr:nth-child(odd) {
        background-color: #f0f0f0;
    }
    td {
        padding: 10px;
        text-align: left;
    }
    img {
        max-width: 100px;
        height: auto;
    }
    .cancel-btn {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 20px;
        background-color: red;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        text-align: center;
        margin-left:50%;
    }
    .cancel-btn:hover {
        background-color: darkred;
    }
</style>
</head>
<body>

<h2 style="text-align:center;">Product Availability</h2>

<table>
    <thead>
        <tr>
            <th>Image</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Threshold</th>
            <th>Updated At</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<ProductDto> sellerProducts = (List<ProductDto>) session.getAttribute("sellerProducts");
            if (sellerProducts != null) {
                for (int i = 0; i < sellerProducts.size(); i++) {
                    ProductDto product = sellerProducts.get(i);
                    String rowColor = (i % 2 == 0) ? "#d3d3d3" : "#f0f0f0";
        %>
        <tr style="background-color: <%= rowColor %>;">
            <td><img src="<%= request.getContextPath() %>/images/<%=product.getImage() %>" alt="<%= product.getPro_name()%>"/></td>
            <td><%= product.getPro_name()%></td>
            <td><%= product.getQuantity()%></td>
            <td><%= product.getThreshold()%></td>
            <td><%= product.getUpdated_at() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" style="text-align:center;">No products available</td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>

<a href="/ecommProject/ServerController?userType=seller" class="cancel-btn">Cancel</a>

</body>
</html>
