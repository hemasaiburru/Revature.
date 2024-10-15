<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
    	body {
		    background-color: #E7E8D1;
		    background: linear-gradient(135deg, #43cea2, #185a9d);
		}

		.container {
		    background-color:#f4f4f4;
		    border-radius: 8px;
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		    padding: 20px;
		    max-width: 800px;
		    margin: 20px auto;
		}
		
		h2 {
		    margin-bottom: 20px;
		    color: #333333;
		    text-align: center;
		}
		
		.form-group label {
		    font-weight: bold;
		    margin-bottom: 10px;
		    display: block;
		}
		
		.form-control, .form-control-file {
		    border-radius: 4px;
		    border: 1px solid #ced4da;
		    transition: border-color 0.3s ease, box-shadow 0.3s ease;
		}
		
		.form-control:focus, .form-control-file:focus {
		    border-color: #80bdff;
		    box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
		}
		
		.btn-primary {
		    background-color: #007bff;
		    border-color: #007bff;
		    color: #ffffff;
		    font-size: 16px;
		    font-weight: bold;
		    border-radius: 4px;
		    transition: background-color 0.3s ease, border-color 0.3s ease;
		}
		
		.btn-primary:hover {
		    background-color: #0056b3;
		    border-color: #004085;
		    color: #ffffff;
		}
		
		.btn-primary:focus, .btn-primary.focus {
		    box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.5);
		}
		
		.form-group {
		    margin-bottom: 1.5rem;
		}
		
		.file-input-container {
		    position: relative;
		    display: inline-block;
		    width: 100%;
		}
		
		.file-input-label {
		    display: block;
		    padding: 10px;
		    background-color: #e9ecef;
		    border: 1px solid #ced4da;
		    border-radius: 4px;
		    cursor: pointer;
		    text-align: center;
		    color: #495057;
		}
		.file-input-label:hover {
		    background-color: #dee2e6;
		}
		.file-input-label {
		    width: 100%;
		    cursor: pointer;
		}
		.row {
		    display: flex;
		    flex-wrap: wrap;
		    gap: 1rem;
		}
		
		.col {
		    flex: 1;
		    min-width: 200px;
		}
		@media (max-width: 768px) {
		    .col {
		        min-width: 100%;
		    }
		}
    	
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2>Add Product</h2>
        <form action="/ecommProject/ProductController" method="post">
            <div class="form-group">
                <label for="product_name">Product Name:</label>
                <input type="text" class="form-control" id="product_name" name="product_name" required>
            </div>
            <div class="form-group">
                <label for="pdesc">Product Description:</label>
                <textarea class="form-control" id="pdesc" name="pdesc" rows="4" required></textarea>
            </div>
            <div class="form-group file-input-container">
                <label for="image">Product Image</label>
                <input type="file" class="file-input-label" id="image" name="image" accept="image/*" required>
            </div>
            <div class="form-group row">
                <div class="col">
                    <label for="quant">Quantity:</label>
                    <input type="number" class="form-control" id="quant" name="quant" required>
                </div>
                <div class="col">
                    <label for="threshold">Threshold:</label>
                    <input type="number" class="form-control" id="threshold" name="threshold" required>
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <label for="price_per_piece">Price per Piece:</label>
                    <input type="number" step="0.01" class="form-control" id="price_per_piece" name="price_per_piece" required>
                </div>
                <div class="col">
                    <label for="discount_per_piece">Discount per Piece:</label>
                    <input type="number" step="0.01" class="form-control" id="discount_per_piece" name="discount_per_piece">
                </div>
            </div>
            <div class="form-group row" >
            	<div class="col"><input type="submit" class="btn btn-primary" value="Add Product"></div>
            	<div class="col"><button class="btn btn-primary"><a class="text-white" href="<%= request.getContextPath() %>/ServerController?userType=seller" style="text-decoration:none">Cancel</a></button></div>
            </div>
        </form>
    </div>
	<% 
	    String errorMessage = (String) request.getAttribute("errorMessage");
	    if (errorMessage != null) { 
	%>
	    <script>
	        alert("<%= errorMessage %>");
	    </script>
	<% 
	    } 
	%>
    </script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
