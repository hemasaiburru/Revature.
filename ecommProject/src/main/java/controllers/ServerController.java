package controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ServerController")
public class ServerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s=request.getContextPath();
		String t=request.getParameter("userType");
		String final_page="";
		if(s.equals("/ecommProject") && t.equals("buyer")) {
			final_page="/views/BuyerDashboard.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("seller")) {
			final_page="/views/SellerDashboard.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("product_add")) {
			final_page="/views/addProduct.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("seller_register")) {
			final_page="/views/SellerRegister.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("buyer_register")) {
			final_page="/views/BuyerRegister.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("serverDown")) {
			final_page="/views/ServerDown.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("profile")) {
			final_page="/views/Profile.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("update")) {
			final_page="/views/UpdateProfile.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("displayWish")) {
			final_page="/views/WishList.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("success")) {
			final_page="/views/PaySuccess.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("fail")) {
			final_page="/views/PayFail.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("SellerProfile")) {
			final_page="/views/SellerProfile.jsp";
		}
		else if(s.equals("/ecommProject") && t.equals("updateSeller")) {
			final_page="/views/SellerUpdateProfile.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(final_page);
        dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
