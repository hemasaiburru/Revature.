package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.ProductEntity;
import dto.ProductDto;
import util.DbConnect;
public class ProductDao {
	private static Connection con;
	public ProductDao(){
		con=DbConnect.getInstance().getConnection();
	}
	public void addProduct(ProductEntity productEntity_obj) throws SQLException {
		String sql="insert into products(s_id,pro_name,pro_desc,image,cname,quantity,price_per_piece,discount_per_piece,threshold) values(?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, productEntity_obj.getS_id());
			stmt.setString(2,productEntity_obj.getProduct_name());
			stmt.setString(3,productEntity_obj.getPdesc());
			stmt.setString(4,productEntity_obj.getImage());
			stmt.setString(5,productEntity_obj.getCname());
			stmt.setInt(6, productEntity_obj.getQuant());
			//stmt.setInt(7, productEntity_obj.getThreshold());
			stmt.setDouble(7, productEntity_obj.getPrice_per_piece());
			stmt.setDouble(8, productEntity_obj.getDiscount_per_piece());
			stmt.setInt(9, productEntity_obj.getThreshold());
			int res_insert=stmt.executeUpdate();
			if(res_insert==1) {
				System.out.println("Successfully Added.....");
			}
		} 
	}
	public int getProductIdByImage(String name) {
		String sql="select p_id from products where image = ?";
		int dup=0;
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setString(1, name);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				dup=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dup;
	}
	public List<ProductDto> displayProducts() throws SQLException {
		List<ProductDto> productDto_objs=new ArrayList<>();
		String sql="select * from products ";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				productDto_objs.add(new ProductDto(rs.getInt(1), rs.getInt(2), rs.getString(3),  rs.getString(4),  rs.getString(5),  rs.getString(6),  rs.getInt(7), rs.getInt(8), rs.getDouble(9), rs.getDouble(10), rs.getTimestamp(11), rs.getTimestamp(12)));
			}
		}
		return productDto_objs;
	}
	public ProductDto getProductById(int productId) throws SQLException {
		String sql="select * from products where p_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, productId);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				return new ProductDto(rs.getInt(1), rs.getInt(2), rs.getString(3),  rs.getString(4),  rs.getString(5),  rs.getString(6),  rs.getInt(7), rs.getInt(8), rs.getDouble(9), rs.getDouble(10), rs.getTimestamp(11), rs.getTimestamp(12));
			}
		}
		return null;
	}
	public void updateQuantity(int newQuant, int productId) throws SQLException {
		String sql="update products set quantity = ? where p_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, newQuant);
			stmt.setInt(2, productId);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Quantity Updated....");
			}
		}
	}
	public List<ProductDto> displayProductsBySellerId(int s_id) throws SQLException {
		List<ProductDto> productDto_objs=new ArrayList<>();
		String sql="select * from products where s_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, s_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				productDto_objs.add(new ProductDto(rs.getInt(1), rs.getInt(2), rs.getString(3),  rs.getString(4),  rs.getString(5),  rs.getString(6),  rs.getInt(7), rs.getInt(8), rs.getDouble(9), rs.getDouble(10), rs.getTimestamp(11), rs.getTimestamp(12)));
			}
		}
		return productDto_objs;
	}
	public void deleteProductById(int p_id) throws SQLException {
		String sql="delete from products where p_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, p_id);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Product Deleted....");
			}
		}
	}
	
}