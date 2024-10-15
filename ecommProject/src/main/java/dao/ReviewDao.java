package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import dto.ReviewDto;
import util.DbConnect;

public class ReviewDao {
	private static Connection con;
	public ReviewDao() {
		con=DbConnect.getInstance().getConnection();
	}
	public void addToReview(int b_id, int p_id, double rating, String review) throws SQLException {
		String sql="insert into reviews(b_id,p_id,rating,comments) values(?,?,?,?)";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			stmt.setInt(2, p_id);
			stmt.setDouble(3, rating);
			stmt.setString(4, review);
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Added Review");
			}
		}
	}
	public List<ReviewDto> displayAll() throws SQLException {
		String sql="select * from reviews";
		List<ReviewDto> reviewDto_objs=new ArrayList<>();
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				reviewDto_objs.add(new ReviewDto(rs.getDouble(4),rs.getString(5),rs.getTimestamp(6)));
			}
		}
		return reviewDto_objs;
	}
}
