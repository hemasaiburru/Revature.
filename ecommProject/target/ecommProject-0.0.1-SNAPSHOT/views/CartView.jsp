<%@ page import="java.util.List" %>
<%@ page import="dto.ProductDto" %>
<%
	HttpSession session1 = request.getSession();
	List<ProductDto> pdo = (List<ProductDto>) session1.getAttribute("cartProductDtoObjs");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Cart</title>
    <style>
        body {
            background: linear-gradient(135deg, #43cea2, #185a9d);
        }
        .container {
            margin-top: 30px;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 900px;
            margin-left: auto;
            margin-right: auto;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .cart-table {
            width: 100%;
            margin: auto;
        }
        .cart-table th {
            background-color: black;
            color: white;
            text-align: center;
            font-size:20px;
        }
        .cart-table td {
            text-align: center;
            vertical-align: middle;
        }
        .cart-table tbody tr:nth-child(even) {
            background-color: #f2f2f2; /* Light gray rows */
        }
        .cart-table img {
            max-width: 100px;
            height: auto;
        }
        .cart-actions {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }
        .btn-danger {
            background-color: red;
            border: none;
        }
        .btn-danger:hover {
            background-color: #cc0000;
        }
        .btn-secondary {
            background-color: gray;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #666;
        }
        .btn-primary {
            background-color: green;
            border: none;
        }
        .btn-primary:hover {
            background-color: #005500;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Shopping Cart</h2>
        <form action="/ecommProject/processCart?action=buyNow" method="post">
            <table class="table cart-table">
                <thead>
                    <tr>
                        <th>Select</th>
                        <th>Image</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Discounted Price</th>
                        <th>Delete</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < pdo.size(); i++) {
                            ProductDto pdo_obj = pdo.get(i);
                    %>
                        <tr data-product-id="<%= pdo_obj.getP_id() %>">
                            <td><input type="checkbox" name="selectedItems" value="<%= pdo_obj.getP_id() %>"></td>
                            <td><img src="<%= request.getContextPath() %>/images/<%= pdo_obj.getImage() %>" alt="<%= pdo_obj.getPro_name() %>"></td>
                            <td><%= pdo_obj.getPro_name() %></td>
                            <td>$<%= pdo_obj.getPpp() %></td>
                            <td>$<%= pdo_obj.getDpp() %></td>
                            <td>
                                <button type="button" class="btn btn-danger" onclick="fun_del(<%= pdo_obj.getP_id() %>)">Delete</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-secondary btn-sm" onclick="updateQuantity('<%= pdo_obj.getP_id() %>', -1)">-</button>
                                <input type="text" name="quantity_<%= pdo_obj.getP_id() %>" value="1" size="3" readonly>
                                <button type="button" class="btn btn-secondary btn-sm" onclick="updateQuantity('<%= pdo_obj.getP_id() %>', 1)">+</button>
                            </td>
                        </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
            <div class="cart-actions">
                <button type="button" class="btn btn-secondary" onclick="window.history.back()">Cancel</button>
                <button type="submit" name="action" value="buyNow" class="btn btn-primary">Buy Now</button>
            </div>
        </form>
    </div>

    <script>
        function updateQuantity(productId, change) {
            var quantityInput = document.querySelector('input[name="quantity_' + productId + '"]');
            var currentQuantity = parseInt(quantityInput.value);
            var newQuantity = currentQuantity + change;
            if (newQuantity < 1) newQuantity = 1; 
            quantityInput.value = newQuantity;
        }

        function fun_del(p_id) {
        	console.log(p_id);
            fetch('CartServlet?id=' + p_id, { method: 'DELETE' })
                .then(response => {
                    if (response.status === 204) {
                    	var row = document.querySelector('tr[data-product-id="' + p_id + '"]');
                        if (row) {
                            row.remove();
                        }
                        alert("Item deleted successfully!");
                    } else {
                        alert("Failed to delete item.");
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    </script>
</body>
</html>
