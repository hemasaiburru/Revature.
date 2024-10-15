package dao;

import java.sql.*;
import java.util.*; 
import util.DbConnect;

public class WishListDao {
	private static Connection con;
	public WishListDao() {
		con=DbConnect.getInstance().getConnection();
	}
	public void addItemToWishList(int b_id,int p_id) throws SQLException {
		String sql="insert into wishlist(b_id,p_id) values(?,?)";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			stmt.setInt(2, p_id);
			int res_insert=stmt.executeUpdate();
			if(res_insert==1) {
				System.out.println("Added to WishList...");
			}
		}
	}
	public List<Integer> getPids(int i) throws SQLException {
		List<Integer> li_productIds=new ArrayList<>();
		String sql="select p_id from wishlist where b_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, i);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				li_productIds.add(rs.getInt(1));
			}
		}
		return li_productIds;
	}
	public void removeItemToWishList(int b_id, int productId) throws SQLException {
		String sql="delete from wishlist where p_id=? and b_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			stmt.setInt(2, productId);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Deleted");
			}
		}
	}
	public List<Integer> display(int b_id) throws SQLException {
		List<Integer> p_id=new ArrayList<>();
		String sql="select p_id from wishlist where b_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				p_id.add(rs.getInt(1));
			}
		}
		return p_id;
	}
	public void deleteBybidAndPid(int b_id, int p_id) throws SQLException {
		String sql="delete from wishlist where b_id =? and p_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			stmt.setInt(2, p_id);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Yeah Deleted...");
			}
		}
	}
}
