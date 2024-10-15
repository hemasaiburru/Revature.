<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession session1=request.getSession();
    int quantity = (Integer) session1.getAttribute("quantity");
    double price = (Double) session1.getAttribute("price");
    double discountPrice = (Double) session1.getAttribute("discountPrice");
    int productId = (Integer) session1.getAttribute("productId");
    double total_price=(Double)session1.getAttribute("Total_Price");
    if(productId==0 ){
    	response.sendRedirect("/ecommProject/");
    	return ;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Order Summary</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Field</th>
                    <th>Details</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Price</td>
                    <td>$<%= price %></td>
                </tr>
                <tr>
                    <td>Discounted Price</td>
                    <td>$<%= discountPrice %></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><%= quantity %></td>
                </tr>
                <tr>
                    <td>Total Amount to Pay</td>
                    <td><%= total_price %></td>
                </tr>
            </tbody>
        </table>
        <form action="/ecommProject/OrderServlet" method="post">
            <input type="hidden" name="productId" value="<%= productId %>">
            <input type="hidden" name="quantity" value="<%= quantity %>">
            <input type="hidden" name="productPrice" value="<%= price %>">
            <input type="hidden" name="discountPrice" value="<%= discountPrice %>">
            
            <!-- Payment Method Selection -->
            <div class="form-group">
                <label for="paymentMethod">Choose a Payment Method:</label>
                <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                    <option value="Credit Card">Credit Card</option>
                    <option value="Debit Card">DebitCard</option>
                    <option value="UPI">UPI</option>
                    <option value="Net Banking">Net Banking</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary" name="process" value="completePayment">Proceed to Payment</button>
            <button type="submit" class="btn btn-danger" name="process" value="cancelPayment">Cancel Payment</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
