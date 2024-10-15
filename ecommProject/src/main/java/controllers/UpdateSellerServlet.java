package controllers;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.SellerDto;
import entity.SellerEntity;
import service.SellerService;
import util.PasswordUtils;

@WebServlet("/UpdateSellerServlet")
public class UpdateSellerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static SellerService sellerService;

    public UpdateSellerServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        sellerService = new SellerService();  // Initialize the SellerService
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SellerDto seller = (SellerDto) session.getAttribute("seller");
        
        if (seller == null) {
            response.sendRedirect("/ecommProject/");
            return;
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phno = request.getParameter("phno");
        String businessAddress = request.getParameter("businessAddress");
        String businessName = request.getParameter("businessName");
        boolean updatePassword = request.getParameter("updatePassword") != null;
        try {
            if (updatePassword) {
                String newPassword = PasswordUtils.hashPassword(request.getParameter("newPassword"));
                SellerEntity sellerEntity = new SellerEntity(name, email, newPassword, phno, businessName,seller.getBus_type(), businessAddress);
                sellerService.updateSellerPwdById(seller.getS_id(), sellerEntity);
            } else {
                SellerDto updatedSeller = new SellerDto(seller.getS_id(), name, email, phno, businessName,seller.getBus_type(),businessAddress );
                sellerService.updateSellerById(updatedSeller);
            }
            session.setAttribute("updateSuccess", "Profile updated successfully!");
            response.sendRedirect("/ecommProject/ServerController?userType=seller");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("updateSuccess", "An error occurred while updating the profile.");
            request.getRequestDispatcher("/ecommProject/ServerController?userType=seller").forward(request, response);
        }
    }
}
