<%@ page import="java.util.List" %>
<%@ page import="dto.OrderDto" %>
<%@ page import="dto.ProductDto" %>
<%@ page import="java.util.ArrayList" %>
<%
    HttpSession session1= request.getSession();
    List<OrderDto> orderDtoObjs = (List<OrderDto>) session1.getAttribute("orderHistorySeller_OrderDto_objs");
    List<ProductDto> productDtoObjs = (List<ProductDto>) session1.getAttribute("orderHistorySeller_ProductDto_objs");
    List<String> paymentMethods = (List<String>) session1.getAttribute("orderHistorySeller_PaymentMethods");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <link rel="stylesheet" href="path/to/your/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #005f73, #0a9396, #94d2bd);
        }
        .container {
            margin-top: 30px;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-bottom: 30px;
            text-align: center;
        }
        .order-table {
            width: 100%;
            margin-bottom: 20px;
        }
        .order-table th, .order-table td {
            text-align: left;
            padding: 12px;
        }
        .order-table th {
            background-color: #343a40;
            color: white;
        }
        .order-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .order-item img {
            max-width: 100px;
            height: auto;
            border-radius: 5px;
        }
        .order-actions button {
            margin-right: 10px;
        }
        .order-status {
            font-weight: bold;
        }
        .no-orders {
            text-align: center;
            font-style: italic;
        }
        /* Add responsiveness */
        @media (max-width: 768px) {
            .order-table img {
                max-width: 80px;
            }
        }
        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px; /* Adjust as needed */
        }

        .btn {
            background-color: gray;
            border: 1px solid black;
            color: white;
            font-size: 20px;
            text-decoration: none;
            width: 100px;
            height: 40px;
            padding-top: 9px;
            padding-left: 9px;
            text-align: center;
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
                        <td><%= order.getOrder_date()%></td>
                        <td class="order-status"><%= order.getStatus() %></td>
                        <td>$<%= order.getTotal_price()%></td>
                        <td>
                            <div class="order-item">
                                <img src="<%= request.getContextPath() %>/images/<%= product.getImage() %>" alt="<%= product.getPro_name() %>"/><br>
                                <strong><%= product.getPro_name() %></strong> <br>Qty: 1 - $<%= product.getPpp()%>
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
            <a href="/ecommProject/ServerController?userType=seller" class="btn">Cancel</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
