package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.PasswordUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dto.BuyerDto;
import dto.CategoryDto;
import dto.ProductDto;
import dto.SellerDto;
import exception.InvalidInputException;
import exception.UserNotFoundException;
import service.BuyerService;
import service.CartService;
import service.CategoryService;
import service.ProductService;
import service.SellerService;
import service.WishListService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static BuyerService buyerService_obj;
	public static SellerService sellerService_obj;
	public static ProductService productService_obj;
	public static CategoryService categoryService_obj;
	public static WishListService wishListService_obj;
	public static CartService cartService_obj;
    public LoginServlet() {
        super();
    }
    public void init(ServletConfig config) throws ServletException{
    	buyerService_obj=new BuyerService();
    	sellerService_obj=new SellerService();
    	productService_obj=new ProductService();
    	categoryService_obj=new CategoryService();
    	wishListService_obj=new WishListService();
    	cartService_obj=new CartService();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String username = request.getParameter("username");
        String password="";
		try {
			password = PasswordUtils.hashPassword(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        String userType = request.getParameter("userType");
        if ("buyer".equals(userType)) {
        	try {
        		BuyerDto buyerDto_obj=buyerService_obj.login(username,password);
				HttpSession session = request.getSession();
				session.setAttribute(userType,buyerDto_obj);
				List<ProductDto> productDto_obj=productService_obj.displayProducts();
				List<CategoryDto> categoryDto_obj=categoryService_obj.diplayCategories();
				List<Integer> fav=wishListService_obj.getPids(buyerDto_obj.getB_id());
				List<Integer> cart=cartService_obj.getPids(buyerDto_obj.getB_id());
				List<Boolean> cart_bool=new ArrayList<>();
				List<Boolean> fav_bool=new ArrayList<>();
				for(ProductDto i:productDto_obj) {
					cart_bool.add(cart.contains(Integer.parseInt(String.valueOf(i.getP_id()))));
				}
				session.setAttribute("cart", cart_bool);
				for(ProductDto i:productDto_obj) {
					fav_bool.add(fav.contains(Integer.parseInt(String.valueOf(i.getP_id()))));
				}
				session.setAttribute("favorites", fav_bool);
				if(productDto_obj.isEmpty()) {
					response.sendRedirect("/ecommProject/ServerController?userType=serverDown");
				}
				else {
					session.setAttribute("products", productDto_obj);
					session.setAttribute("categories", categoryDto_obj);
					response.sendRedirect("/ecommProject/ServerController?userType=buyer");
					return;
				}
			} catch (InvalidInputException | SQLException e) {
				request.setAttribute("errorMessage", e.getMessage());
				request.getRequestDispatcher("/").forward(request, response);
			}
        }
        else if ("seller".equals(userType)) {
        	String password1="";
    		try {
    			password1 = PasswordUtils.hashPassword(request.getParameter("password"));
    		} catch (NoSuchAlgorithmException e) {
    			e.printStackTrace();
    		}
            try {
				SellerDto sellerDto_obj=sellerService_obj.login(username,password1);
				HttpSession session = request.getSession();
				session.setAttribute(userType,sellerDto_obj);
				response.sendRedirect("/ecommProject/ServerController?userType=seller");
			} catch (UserNotFoundException | SQLException e) {
				request.setAttribute("errorMessage", e.getMessage());
				request.getRequestDispatcher("/").forward(request, response);
			}
        }
    }
		
}

