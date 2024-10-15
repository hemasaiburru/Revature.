package dao;
import java.sql.*;
import entity.SellerEntity;
import dto.SellerDto;
import util.DbConnect;
public class SellerDao {
	private static Connection con;
	public SellerDao(){
		con=DbConnect.getInstance().getConnection();
	}
	public void addSeller(SellerEntity seller) throws SQLException {
        String sql = "INSERT INTO sellers (name, email, password, phno, bus_name,bus_type,bus_address) VALUES (?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, seller.getName());
            stmt.setString(2, seller.getEmail());
            stmt.setString(3, seller.getPassword());
            stmt.setString(4, seller.getPhno());
            stmt.setString(5, seller.getBus_name());
            stmt.setString(6, seller.getBus_type());
            stmt.setString(7,seller.getBus_address());
            int res_insert=stmt.executeUpdate();
            if(res_insert==1){
            	System.out.println("Successfully Registered...Now Login");
            }
        }
    }
	public int getSellerIdByEmail(String email) throws SQLException {
		String sql="Select s_id from sellers where email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1);
            }
		}
		return 0;
	}
	public SellerEntity getSellerByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM sellers WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	return new SellerEntity(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }

        }
        return null;
	}
	public void updateSellerPwdById(int s_id, SellerEntity sellerEntity) throws SQLException {
		String sql="update sellers set name=?,email=?,password=?,phno=?,bus_name=?,bus_type=?,bus_address=? where s_id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, sellerEntity.getName());
			stmt.setString(2, sellerEntity.getEmail());
			stmt.setString(3, sellerEntity.getPassword());
			stmt.setString(4, sellerEntity.getPhno());
			stmt.setString(5, sellerEntity.getBus_name());
			stmt.setString(6, sellerEntity.getBus_type());
			stmt.setString(7, sellerEntity.getBus_address());
			stmt.setInt(8, s_id);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Updated....Seller");
			}
		}
	}
	public void updateSellerById(SellerDto updatedSeller) throws SQLException {
		String sql="update sellers set name=?,email=?,phno=?,bus_name=?,bus_type=?,bus_address=? where s_id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, updatedSeller.getName());
			stmt.setString(2, updatedSeller.getEmail());
			stmt.setString(3, updatedSeller.getPhno());
			stmt.setString(4, updatedSeller.getBus_name());
			stmt.setString(5, updatedSeller.getBus_type());
			stmt.setString(6, updatedSeller.getBus_address());
			stmt.setInt(7, updatedSeller.getS_id());
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Updated....Seller.....Without Pswd");
			}
		}
	}
}