<%@ page import="java.util.List" %>
<%@ page import="dto.ReviewDto" %>

<!DOCTYPE html>
<html>
<head>
    <title>Reviews</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .review-card {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 5px;
        }
        .review-rating {
            font-size: 1.2em;
            font-weight: bold;
            color: #f0ad4e; /* Gold color for rating stars */
        }
        .review-comment {
            margin-top: 10px;
        }
        .review-date {
            font-size: 0.9em;
            color: #777;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2>Product Reviews</h2>

        <%
            HttpSession session1 = request.getSession();
            List<ReviewDto> reviews = (List<ReviewDto>) session1.getAttribute("reviews");
            if (reviews == null || reviews.isEmpty()) {
        %>
            <div class="alert alert-warning" role="alert">
                No reviews available for this product yet.
            </div>
        <% } else { %>
            <% for (ReviewDto review : reviews) { %>
                <div class="review-card">
                    <div class="review-rating">
                        Rating: <%= review.getRating() %> / 5
                    </div>
                    <div class="review-comment">
                        <strong>Review:</strong> <%= review.getComment() %>
                    </div>
                    <div class="review-date">
                        Posted on: <%= review.getCreated_at() %>
                    </div>
                </div>
            <% } %>
        <% } %>

        <a href="/ecommProject/ServerController?userType=buyer" class="btn btn-secondary">Return</a>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
