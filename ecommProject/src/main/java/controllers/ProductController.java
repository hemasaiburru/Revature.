package controllers;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.SellerDto;
import entity.CategoryEntity;
import entity.InventoryEntity;
import entity.ProductEntity;
import exception.InvalidInputException;
import service.CategoryService;
import service.InventoryService;
import service.ProductService;
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService_obj;
    private CategoryService categoryService_obj;
    private InventoryService inventoryService_obj;
    public void init(ServletConfig config) throws ServletException {
    	productService_obj=new ProductService();
    	categoryService_obj=new CategoryService();
    	inventoryService_obj=new InventoryService();
    }
    public ProductController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sellerSession=request.getSession();
		SellerDto seller = (SellerDto) sellerSession.getAttribute("seller");
		String pro_name=request.getParameter("product_name");
		String pdesc=request.getParameter("pdesc");
		String image=request.getParameter("image");
		int quant=(Integer.parseInt(request.getParameter("quant")));
		int th=(Integer.parseInt(request.getParameter("threshold")));
		Double ppp=(Double.parseDouble(request.getParameter("price_per_piece")));
		String sdpp=request.getParameter("discount_per_piece");
		Double dpp=0.0;
		if(sdpp.equals(null)) {
			dpp=0.0;
		}
		else {
			dpp=Double.parseDouble(sdpp);
		}
		ProductEntity productEntity_obj=new ProductEntity(seller.getS_id(), pro_name, pdesc, image, seller.getBus_type(), quant, th, ppp, dpp);
		try {
			productService_obj.add_product(productEntity_obj);
			CategoryEntity categoryEntity_obj=new CategoryEntity(seller.getS_id(),seller.getBus_type());
			categoryService_obj.add_category(categoryEntity_obj);
			add_to_inventory(productEntity_obj,seller);
			request.setAttribute("errorMessage", "Product Added Successfully...Continue");
			request.getRequestDispatcher("/views/addProduct.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/views/addProduct.jsp").forward(request, response);
		}
	}
	private void add_to_inventory(ProductEntity productEntity_obj,SellerDto seller) {
		int pid=productService_obj.getProductIdByImage(productEntity_obj.getImage());
		InventoryEntity inventoryEntity_obj=new InventoryEntity(pid,productEntity_obj.getProduct_name(),seller.getS_id(),productEntity_obj.getQuant(),productEntity_obj.getThreshold());
		inventoryService_obj.addInventory(inventoryEntity_obj);
	}

}