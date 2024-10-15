package controllers;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ProductService;
import dto.SellerDto;
import dto.BuyerDto;
import dto.ProductDto;
import java.util.*;
import jakarta.servlet.*;
/**
 * Servlet implementation class UpdateProductController
 */
//@WebServlet("/UpdateProductController")
//public class UpdateProductController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//    private static ProductService productService_obj;
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public UpdateProductController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    public void init(ServletConfig config) {
//    	productService_obj=new ProductService();
//    }
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		try {
//			HttpSession session=request.getSession();
//			SellerDto sdo=(SellerDto) session.getAttribute("seller");
//			String r=request.getParameter("rev");
//			if(r.equals("checkMate")) {
//				List<ProductDto> sellerProducts=productService_obj.displayProductsBySellerId(sdo.getS_id());
//				session.setAttribute("sellerProducts", sellerProducts);
//				request.getRequestDispatcher("/views/ProductAvailability.jsp").forward(request, response);
//			}
//			if(r.equals("manage")) {
//				List<ProductDto> sellerProducts1=productService_obj.displayProductsBySellerId(sdo.getS_id());
//				session.setAttribute("sellerProducts1", sellerProducts1);
//				request.getRequestDispatcher("/views/viewProducts.jsp").forward(request, response);
//				return;
//			}
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session=request.getSession();
//		SellerDto seller=(SellerDto) session.getAttribute("seller");
//		if(seller==null) {
//			response.sendRedirect("/ecommProject/");
//			return;
//		}
//		int p_id=Integer.parseInt(request.getParameter("id"));
//		try {
//			productService_obj.deleteProductById(p_id);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//	}
//}
@WebServlet("/UpdateProductController")
public class UpdateProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ProductService productService_obj;

    public UpdateProductController() {
        super();
    }

    public void init(ServletConfig config) {
        productService_obj = new ProductService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            SellerDto sdo = (SellerDto) session.getAttribute("seller");
            String r = request.getParameter("rev");

            if (r.equals("checkMate")) {
                // Logic for checking availability
                List<ProductDto> sellerProducts = productService_obj.displayProductsBySellerId(sdo.getS_id());
                session.setAttribute("sellerProducts", sellerProducts);
                request.getRequestDispatcher("/views/ProductAvailability.jsp").forward(request, response);
            } else if (r.equals("manage")) {
                // Manage the products after adding or updating
                List<ProductDto> sellerProducts1 = productService_obj.displayProductsBySellerId(sdo.getS_id());
                session.setAttribute("sellerProducts1", sellerProducts1);
                request.getRequestDispatcher("/views/viewProducts.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SellerDto seller = (SellerDto) session.getAttribute("seller");
        if (seller == null) {
            response.sendRedirect("/ecommProject/");
            return;
        }
        int p_id = Integer.parseInt(request.getParameter("id"));
        try {
            productService_obj.deleteProductById(p_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}