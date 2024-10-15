package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.BuyerDto;
import dto.ProductDto;
import service.WishListService;

/**
 * Servlet implementation class FavServlet
 */
@WebServlet("/FavServlet")
public class FavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public WishListService wishListService_obj; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) {
    	wishListService_obj=new WishListService();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    List<ProductDto> products = (List<ProductDto>) session.getAttribute("products");
	    List<Boolean> favorites = (List<Boolean>) session.getAttribute("favorites");
		BuyerDto buyer=(BuyerDto)session.getAttribute("buyer");
		int productId = Integer.parseInt(request.getParameter("productId"));
        String action = request.getParameter("action");
        if (products == null || favorites == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if necessary session attributes are missing
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getP_id() == productId) {
                if ("add".equals(action)) {
                    favorites.set(i, true);
                    try {
            			wishListService_obj.addItemToWishList(buyer.getB_id(), productId);
            		} catch (SQLException e) {
            			System.out.println(e.getMessage());
            		}
            		response.sendRedirect(request.getContextPath() + "/ServerController?userType=buyer");
                } else if ("remove".equals(action)) {
                    favorites.set(i, false);
                    try {
            			wishListService_obj.removeItemToWishList(buyer.getB_id(), productId);
            		} catch (SQLException e) {
            			System.out.println(e.getMessage());
            		}
            		response.sendRedirect(request.getContextPath() + "/ServerController?userType=buyer");
                }
                break;
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		BuyerDto buyer=(BuyerDto) session.getAttribute("buyer");
		if(buyer==null) {
			response.sendRedirect("/ecommProject/");
			return;
		}
		System.out.println(request.getParameter("id"));
		int p_id=Integer.parseInt(request.getParameter("id"));
		try {
			wishListService_obj.deleteBybidAndPid(buyer.getB_id(),p_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}
