<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Failed</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #ffe5e5, #ffd6d6);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .content-box {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 100%;
            max-width: 500px;
        }
        .content-box h2 {
            color: #dc3545;
            margin-bottom: 20px;
        }
        .content-box i {
            font-size: 100px;
            color: #dc3545;
            margin-bottom: 20px;
        }
        .content-box p {
            font-size: 18px;
            color: #333;
            margin-bottom: 30px;
        }
        .content-box .btn {
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            background-color: #dc3545;
            border: none;
        }
        .content-box .btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="content-box">
        <h2>Order Failed!</h2>
        <i class="fas fa-times-circle"></i>
        <p>Your payment failed. Please try again or contact support.</p>
		<a href="/ecommProject/OrderServlet?process=buyer" class="btn btn-success">Return to Home</a>   
	 </div>
</body>
</html>
