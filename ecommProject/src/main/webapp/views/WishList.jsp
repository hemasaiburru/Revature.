<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.ProductDto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My WishList</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #005f73, #0a9396, #94d2bd);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            font-size: 2rem;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            text-align: center;
            font-size: 1.2rem;
        }
        th, td {
            padding: 15px;
            text-align: center;
            vertical-align: middle;
        }
        td img {
            max-width: 100px;
            border-radius: 10px;
        }
        .btn-danger {
            font-size: 1rem;
            padding: 8px 16px;
        }
        /* Styling the price column with green color */
        td.price {
            color: green;
            font-weight: bold;
        }
        .btn-secondary {
            display: block;
            width: 150px;
            margin: 20px auto 0;
            font-size: 1rem;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2>My WishList</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<ProductDto> wishList = (List<ProductDto>) request.getAttribute("products");
                    if (wishList != null && !wishList.isEmpty()) {
                        for (ProductDto item : wishList) {
                %>
                <tr>
                    <td><img src="<%= request.getContextPath() %>/images/<%=item.getImage() %>" alt="<%= item.getPro_name() %>" width="100"></td>
                    <td><%= item.getPro_name() %></td>
                    <td><%= item.getPdesc()%></td>
                    <td class="price">$<%= item.getPpp()%></td>
                    <td>
                    	<button type="submit" class="btn btn-danger" onclick="fun_del(<%= item.getP_id() %>)">Delete</button>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No items in your Wish List.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <a href="/ecommProject/ServerController?userType=buyer" class="btn btn-secondary">Cancel</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
    	function fun_del(p_id){
    		fetch('FavServlet?id='+p_id, {method:'DELETE'}) .then(response => {
    	        if (response.status === 204) {
    	            alert("Item deleted successfully!");
    	            window.location.reload();  
    	        } else {
    	            alert("Failed to delete item.");
    	        }
    	    })
    	    .catch(error => console.error('Error:', error));
    	}
    </script>
</body>
</html>
