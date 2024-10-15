package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ProductService;
import dto.BuyerDto;
import dto.ProductDto;
import service.CartService;
/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static CartService cartService_obj;
	public static ProductService productService_obj;
	public void init(ServletConfig config) {
		cartService_obj=new CartService();
		productService_obj=new ProductService();
	}
    public CartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		BuyerDto buyer=(BuyerDto) session.getAttribute("buyer");
		String t=request.getParameter("userType");
		if(t.equals("cart")) {
			try {
				List<Integer> cids=cartService_obj.getCidsByBid(buyer.getB_id());
				List<Integer> pids=cartService_obj.getPids(buyer.getB_id());
				List<ProductDto> productDto_objs=new ArrayList<>();
				for(Integer i:pids) {
					ProductDto pdo=productService_obj.getProductById(i.intValue());
					productDto_objs.add(pdo);
				}
				session.setAttribute("cartProductDtoObjs", productDto_objs);
				session.setAttribute("cids", cids);
				//response.sendRedirect("/ecommProject/BuyerController?userType=cart");
				request.getRequestDispatcher("/cartView.jsp").forward(request, response);
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		List<ProductDto> products = (List<ProductDto>) session.getAttribute("products");
		List<Boolean> cart=(List<Boolean>)session.getAttribute("cart");
		BuyerDto buyer=(BuyerDto)session.getAttribute("buyer");
		System.out.println(request.getParameter("productId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
        String action = request.getParameter("action");
        if (products == null || cart == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if necessary session attributes are missing
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getP_id() == productId) {
                if ("addcart".equals(action)) {
                    cart.set(i, true);
                    try {
                    	cartService_obj.addToCart(buyer.getB_id(), productId,1,products.get(i).getPpp());
            		} catch (SQLException e) {
            			System.out.println(e.getMessage());
            		}
            		response.sendRedirect(request.getContextPath() + "/ServerController?userType=buyer");
                } else if ("removecart".equals(action)) {
                    cart.set(i, false);
                    try {
                    	cartService_obj.removeFromCart(buyer.getB_id(), productId);
            		} catch (SQLException e) {
            			System.out.println(e.getMessage());
            		}
            		response.sendRedirect(request.getContextPath() + "/ServerController?userType=buyer");
                }
                break;
            }
        }
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		BuyerDto buyer=(BuyerDto) session.getAttribute("buyer");
		if(buyer==null) {
			response.sendRedirect("/ecommProject/");
			return;
		}
		int p_id=Integer.parseInt(request.getParameter("id"));
		try {
			cartService_obj.removeFromCart(buyer.getB_id(),p_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}
