<%@ page import="java.util.List" %>
<%@ page import="dto.ProductDto" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Products</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        .btn {
            padding: 5px 10px;
            margin: 5px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        .btn-delete {
            background-color: #dc3545;
        }
        .btn-update {
            background-color: #28a745;
        }
    </style>
</head>
<body>

<h2>Product List</h2>

<table>
    <thead>
        <tr>
            <th>Product Image</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Availability</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<ProductDto> productList = (List<ProductDto>) session.getAttribute("sellerProducts1");
            if (productList != null) {
                for (ProductDto product : productList) {
        %>
            <tr data-product-id="<%= product.getP_id() %>">
                <td><img src="<%= request.getContextPath() %>/images/<%= product.getImage() %>" alt="<%= product.getPro_name() %>" width="100" height="100"></td>
                <td><%= product.getPro_name()%></td>
                <td><%= product.getPpp()%></td>
                <td>Qty:<%= product.getQuantity()%><br>Th:<%= product.getThreshold()%></td>
                <td>
                    <button type="submit" class="btn btn-danger" onclick="fun_del(<%= product.getP_id() %>)">Delete</button>
                    <form action="updateProduct.jsp" method="get" style="display:inline;">
                        <input type="hidden" name="productId" value="<%= product.getP_id()%>">
                        <button type="submit" class="btn btn-update">Update</button>
                    </form>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="6">No products available</td>
            </tr>
        <%
            }
        %>
    </tbody>
</table>
<button type="button" class="btn" style="margin-left:50%;background-color:red"onclick="window.history.back()">Cancel</button>
<script>
function fun_del(p_id) {
	console.log(p_id);
    fetch('UpdateProductController?id=' + p_id, { method: 'DELETE' })
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
