package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DbConnect;

public class CartDao {
	private static Connection con;
	public CartDao() {
		con=DbConnect.getInstance().getConnection();
	}
	public void addToCart(int b_id, int p_id, int quant, double pdummy) throws SQLException {
		String sql="insert into cart(b_id,p_id,quantity,price_dummy)values(?,?,?,?)";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			stmt.setInt(2, p_id);
			stmt.setInt(3, quant);
			stmt.setDouble(4, pdummy);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Added to Cart");
			}
		}
	}
	public List<Integer> getPids(int i) throws SQLException {
		List<Integer> li_productIds=new ArrayList<>();
		String sql="select p_id from cart where b_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, i);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				li_productIds.add(rs.getInt(1));
			}
		}
		return li_productIds;
	}
	public void removeFromCart(int b_id, int productId) throws SQLException {
		String sql="delete from cart where p_id=? and b_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, productId);
			stmt.setInt(2, b_id);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Deleted");
			}
		}
	}
	public List<Integer> getCidsByBid(int b_id) throws SQLException {
		List<Integer> cid=new ArrayList<>();
		String sql="select c_id from cart where b_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				cid.add(rs.getInt(1));
			}
		}
		return cid;
	}
}
