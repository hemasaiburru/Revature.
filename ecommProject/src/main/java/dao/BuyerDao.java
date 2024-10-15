package dao;

import util.DbConnect;
import java.sql.*;

import dto.BuyerDto;
import entity.BuyerEntity;

public class BuyerDao {
    private static Connection con;

    public BuyerDao() {
        con = DbConnect.getInstance().getConnection();
    }

    // Add a new buyer to the buyers table
    public void addBuyer(BuyerEntity buyer) throws SQLException {
        String sql = "INSERT INTO buyers (name, email, password, phno, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, buyer.getName());
            stmt.setString(2, buyer.getEmail());
            stmt.setString(3, buyer.getPassword());
            stmt.setString(4, buyer.getPhno());
            stmt.setString(5, buyer.getAddress());
            
            int res_insert = stmt.executeUpdate();
            if (res_insert == 1) {
                System.out.println("Successfully Registered... Now Login");
            }
        }
    }

    // Get a buyer by email from the buyers table
    public BuyerEntity getBuyerByEmail(String email) {
        String sql = "SELECT * FROM buyers WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Constructing BuyerEntity with columns (name, email, password, phno, address)
                return new BuyerEntity(rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), rs.getString("phno"), rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get buyer ID by email
    public int getBuyerIdByEmail(String email) {
        String sql = "SELECT b_id FROM buyers WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("b_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Update buyer information by buyer ID (with password)
    public void updateBuyerPwdById(int b_id, BuyerEntity buyerEntity_obj) throws SQLException {
        String sql = "UPDATE buyers SET name = ?, email = ?, password = ?, phno = ?, address = ? WHERE b_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, buyerEntity_obj.getName());
            stmt.setString(2, buyerEntity_obj.getEmail());
            stmt.setString(3, buyerEntity_obj.getPassword());
            stmt.setString(4, buyerEntity_obj.getPhno());
            stmt.setString(5, buyerEntity_obj.getAddress());
            stmt.setInt(6, b_id);

            int res = stmt.executeUpdate();
            if (res == 1) {
                System.out.println("Updated....");
            }
        }
    }

    // Update buyer information by buyer ID (without password)
    public void updateBuyerById(BuyerDto bdo) throws SQLException {
        String sql = "UPDATE buyers SET name = ?, email = ?, phno = ?, address = ? WHERE b_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, bdo.getName());
            stmt.setString(2, bdo.getEmail());
            stmt.setString(3, bdo.getPhno());
            stmt.setString(4, bdo.getAddress());
            stmt.setInt(5, bdo.getB_id());

            int res = stmt.executeUpdate();
            if (res == 1) {
                System.out.println("Updated....");
            }
        }
    }
}
