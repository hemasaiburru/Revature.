<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%
    HttpSession session1 = request.getSession();
    List<Integer> productIds = (List<Integer>) session1.getAttribute("selectedProductIds");
    List<Integer> quantities = (List<Integer>) session1.getAttribute("quantities");
    List<Double> prices = (List<Double>) session1.getAttribute("prices");
    List<Double> discountPrices = (List<Double>) session1.getAttribute("discountPrices");
    Double totalPrice = (Double) session.getAttribute("totalPrice");

    if (productIds == null || productIds.isEmpty()) {
        response.sendRedirect("/ecommProject/");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Summary</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Order Summary</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Price</th>
                    <th>Discounted Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < productIds.size(); i++) {
                        int productId = productIds.get(i);
                        double price = prices.get(i);
                        double discountPrice = discountPrices.get(i);
                        int quantity = quantities.get(i);
                        double total = price * quantity;
                %>
                <tr>
                    <td><%= productId %></td>
                    <td>$<%= price %></td>
                    <td>$<%= discountPrice %></td>
                    <td><%= quantity %></td>
                    <td>$<%= total %></td>
                </tr>
                <% } %>
                <tr>
                    <td colspan="4" class="text-right"><strong>Total Amount to Pay</strong></td>
                    <td>$<%= totalPrice %></td>
                </tr>
            </tbody>
        </table>
        <form action="/ecommProject/UpdateCartServlet" method="post">
            <div class="form-group">
                <label for="paymentMethod">Choose a Payment Method:</label>
                <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                    <option value="Credit Card">Credit Card</option>
                    <option value="Debit Card">Debit Card</option>
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
