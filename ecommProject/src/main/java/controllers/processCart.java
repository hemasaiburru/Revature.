package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletConfig;
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

@WebServlet("/processCart")
public class processCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ProductService productService_obj;
    private static OrderService orderService_obj;
    private static PayService payService_obj;

    public processCart() {
        super();
    }

    public void init(ServletConfig config) {
        productService_obj = new ProductService();
        orderService_obj = new OrderService();
        payService_obj = new PayService(); // Initialize PayService
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
	        if ("buyNow".equals(action)) {
	            HttpSession session = request.getSession();
	            List<Integer> selectedProductIds = new ArrayList<>();
	            List<Integer> quantities = new ArrayList<>();
	            List<Double> prices = new ArrayList<>();
	            List<Double> discountPrices = new ArrayList<>();
	            double totalPrice = 0.0;
	
	            // Get selected products and their quantities
	            String[] selectedItems = request.getParameterValues("selectedItems");
	            if (selectedItems != null) {
	                for (String itemId : selectedItems) {
	                    int productId = Integer.parseInt(itemId);
	                    int quantity = Integer.parseInt(request.getParameter("quantity_" + productId));
	                    selectedProductIds.add(productId);
	                    quantities.add(quantity);
	
	                    ProductDto product = productService_obj.getProductById(productId);
	                    double price = product.getPpp();
	                    double discountPrice = product.getDpp();
	
	                    prices.add(price);
	                    discountPrices.add(discountPrice);
	                    totalPrice += (price * quantity-discountPrice*quantity);
	                }
	            } else {
	                request.setAttribute("CartBuyStatus", "Failed: No items selected.");
	                response.sendRedirect("/ecommProject/CartServlet?userType=cart");
	                return;
	            }
	
	            boolean allQuantitiesAvailable = true;
	
	            for (int i = 0; i < selectedProductIds.size(); i++) {
	                int productId = selectedProductIds.get(i);
	                int requestedQuantity = quantities.get(i);
	                ProductDto product = productService_obj.getProductById(productId);
	                int currentQuantity = product.getQuantity();
	
	                if (requestedQuantity > currentQuantity) {
	                    allQuantitiesAvailable = false;
	                    break;
	                }
	            }
	
	            if (!allQuantitiesAvailable) {
	                request.setAttribute("CartBuyStatus", "Failed: Not enough quantity for one or more items.");
	                response.sendRedirect("/ecommProject/CartServlet?userType=cart");
	                return;
	            }
	
	            BuyerDto buyer = (BuyerDto) session.getAttribute("buyer");
	            if (buyer == null) {
	                response.sendRedirect("/ecommProject/");
	                return;
	            }
	
	            // Store lists in session
	            session.setAttribute("selectedProductIds", selectedProductIds);
	            session.setAttribute("quantities", quantities);
	            session.setAttribute("prices", prices);
	            session.setAttribute("discountPrices", discountPrices);
	            session.setAttribute("totalPrice", totalPrice);
	
	            response.sendRedirect("/ecommProject/views/orderSummary.jsp");
	        }
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        } 
    }
}
