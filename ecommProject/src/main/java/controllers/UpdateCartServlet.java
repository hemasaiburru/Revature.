package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dto.BuyerDto;
import dto.OrderDto;
import dto.ProductDto;
import entity.OrderEntity;
import service.OrderService;
import service.PayService;
import service.ProductService;

@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ProductService productService_obj;
    private static OrderService orderService_obj;
    private static PayService payService_obj;

    public UpdateCartServlet() {
        super();
    }

    public void init() {
        productService_obj = new ProductService();
        orderService_obj = new OrderService();
        payService_obj = new PayService();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String process = request.getParameter("process");

        if ("completePayment".equals(process)) {
            HttpSession session = request.getSession();
            List<Integer> productIds = (List<Integer>) session.getAttribute("selectedProductIds");
            List<Integer> quantities = (List<Integer>) session.getAttribute("quantities");
            List<Double> discountPrices = (List<Double>) session.getAttribute("discountPrices");
            double totalPrice = (Double) session.getAttribute("totalPrice");
            String paymentMethod = request.getParameter("paymentMethod");
            BuyerDto buyer = (BuyerDto) session.getAttribute("buyer");

            if (productIds == null || productIds.isEmpty() || buyer == null) {
                response.sendRedirect("/ecommProject/");
                return;
            }

            try {
                for (int i = 0; i < productIds.size(); i++) {
                    int productId = productIds.get(i);
                    int quantity = quantities.get(i);
                    double discountPrice = discountPrices.get(i);
                    
                    // Update product quantity
                    ProductDto product = productService_obj.getProductById(productId);
                    int newQuantity = product.getQuantity() - quantity;
                    productService_obj.updateQuantity(newQuantity, productId);

                    // Insert order
                    OrderEntity order = new OrderEntity(
                        buyer.getB_id(),
                        product.getS_id(),
                        productId,
                        discountPrice * quantity,
                        buyer.getAddress(),
                        "Delivered"
                    );
                    orderService_obj.insertOrder(order);
                    OrderDto odo=orderService_obj.display(buyer.getB_id(), productId);
                    payService_obj.insertPay(odo.getO_id(), "Completed", paymentMethod);
                }
               
                // Clear session attributes after successful payment
                session.removeAttribute("selectedProductIds");
                session.removeAttribute("quantities");
                session.removeAttribute("prices");
                session.removeAttribute("discountPrices");
                session.removeAttribute("totalPrice");
                response.sendRedirect("/ecommProject/ServerController?userType=success");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("CartBuyStatus", "Failed: Error processing your payment.");
                response.sendRedirect("/ecommProject/orderSummary.jsp");
            }
        }
        	else if ("cancelPayment".equals(process)) {
        	    HttpSession session = request.getSession();
        	    List<Integer> productIds = (List<Integer>) session.getAttribute("selectedProductIds");
        	    List<Integer> quantities = (List<Integer>) session.getAttribute("quantities");
        	    List<Double> discountPrices = (List<Double>) session.getAttribute("discountPrices");
        	    BuyerDto buyer = (BuyerDto) session.getAttribute("buyer");

        	    if (productIds == null || productIds.isEmpty() || buyer == null) {
        	        response.sendRedirect("/ecommProject/");
        	        return;
        	    }

        	    try {
        	        for (int i = 0; i < productIds.size(); i++) {
        	            int productId = productIds.get(i);
        	            double discountPrice = discountPrices.get(i);

        	            // Insert failed order
        	            ProductDto product = productService_obj.getProductById(productId);
        	            OrderEntity order = new OrderEntity(
        	                buyer.getB_id(),
        	                product.getS_id(),
        	                productId,
        	                discountPrice,
        	                buyer.getAddress(),
        	                "Cancelled"
        	            );
        	            orderService_obj.insertOrder(order);
        	            OrderDto odo = orderService_obj.display(buyer.getB_id(), productId);

        	            // Insert failed payment
        	            payService_obj.insertPay(odo.getO_id(), "Failed", "N/A");
        	        }

        	        // Clear session attributes after cancelled payment
        	        session.removeAttribute("selectedProductIds");
        	        session.removeAttribute("quantities");
        	        session.removeAttribute("prices");
        	        session.removeAttribute("discountPrices");
        	        session.removeAttribute("totalPrice");

        	        request.setAttribute("CartBuyStatus", "Your payment has been cancelled.");
        	        response.sendRedirect("/ecommProject/orderSummary.jsp");
        	    } catch (SQLException e) {
        	        e.printStackTrace();
        	        request.setAttribute("CartBuyStatus", "Failed: Error during payment cancellation.");
        	        response.sendRedirect("/ecommProject/orderSummary.jsp");
        	    }
           
        } else {
            response.sendRedirect("/ecommProject/");
        }
    }
}
