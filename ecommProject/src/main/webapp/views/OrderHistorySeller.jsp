<%@ page import="java.util.List" %>
<%@ page import="dto.OrderDto" %>
<%@ page import="dto.ProductDto" %>
<%@ page import="java.util.ArrayList" %>
<%
    HttpSession session1 = request.getSession();
    List<OrderDto> orderDtoObjs = (List<OrderDto>) session1.getAttribute("orderHistory_OrderDto_objs");
    List<ProductDto> productDtoObjs = (List<ProductDto>) session1.getAttribute("orderHistory_ProductDto_objs");
    List<String> paymentMethods = (List<String>) session1.getAttribute("orderHistory_PaymentMethods");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <link rel="stylesheet" href="path/to/your/css/bootstrap.min.css">
    <style>
        /* Overall font and background */
        body {
            font-family: 'Montserrat', sans-serif;
            background: linear-gradient(90deg, #ff4e50, #f9d423, #69d2e7, #a1ffce, #f8b195);
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* Container for the content */
        .container {
            margin-top: 30px;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        /* Heading */
        h2 {
            color: #ff6b6b;
            margin-bottom: 30px;
            text-align: center;
            font-weight: bold;
            font-size: 2em;
        }

        /* Table styling */
        .order-table {
            width: 100%;
            margin-bottom: 20px;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            border: 2px solid #ffcc00;
        }

        /* Table header */
        .order-table th {
            background-color: #ff6b6b;
            color: #fff;
            text-align: left;
            padding: 15px;
        }

        /* Table rows and cells */
        .order-table td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }

        /* Alternating row colors */
        .order-table tr:nth-child(even) {
            background-color: #ffecd2;
        }

        .order-table tr:nth-child(odd) {
            background-color: #f9f9f9;
        }

        /* Product image inside the order table */
        .order-item img {
            max-width: 80px;
            height: auto;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        /* Order status style */
        .order-status {
            font-weight: bold;
            color: #ffcc00;
        }

        /* Message when no orders are available */
        .no-orders {
            text-align: center;
            font-style: italic;
            color: #ff6b6b;
        }

        /* Responsive design for images */
        @media (max-width: 768px) {
            .order-table img {
                max-width: 60px;
            }
        }

        /* Button container styling */
        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 30px;
        }

        /* Button styling */
        .btn {
            background-color: #69d2e7;
            border: none;
            color: white;
            font-size: 18px;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        /* Hover effect on buttons */
        .btn:hover {
            background-color: #1a85a0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Order History</h2>
        <% if (orderDtoObjs != null && !orderDtoObjs.isEmpty()) { %>
            <table class="table order-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Status</th>
                        <th>Total Amount</th>
                        <th>Product Details</th>
                        <th>Payment Method</th>
                        <th>Shipping Address</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (int i = 0; i < orderDtoObjs.size(); i++) { 
                        OrderDto order = orderDtoObjs.get(i);
                        ProductDto product = productDtoObjs.get(i);
                        String paymentMethod = paymentMethods.get(i);
                    %>
                    <tr>
                        <td><%= order.getO_id() %></td>
                        <td><%= order.getOrder_date() %></td>
                        <td class="order-status"><%= order.getStatus() %></td>
                        <td>$<%= order.getTotal_price() %></td>
                        <td>
                            <div class="order-item">
                                <img src="<%= request.getContextPath() %>/images/<%= product.getImage() %>" alt="<%= product.getPro_name() %>"/><br>
                                <strong><%= product.getPro_name() %></strong> <br>Qty: 1 - $<%= product.getPpp() %>
                            </div>
                        </td>
                        <td><%= paymentMethod %></td>
                        <td><%= order.getShipping_address() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <div class="no-orders">
                No items in your order history.
            </div>
        <% } %>
        <div class="button-container">
            <a href="/ecommProject/ServerController?userType=buyer" class="btn btn-secondary">Cancel</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
