package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CategoryDto;
import entity.CategoryEntity;
import util.DbConnect;
public class CategoryDao {
	private static Connection con;
	public CategoryDao(){
		con=DbConnect.getInstance().getConnection();
	}
	public void addCategory(CategoryEntity categoryEntity_obj) {
		String sql="insert into category(s_id,cname) values(?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, categoryEntity_obj.getS_id());
			stmt.setString(2, categoryEntity_obj.getCname());
			int res_insert=stmt.executeUpdate();
			if(res_insert==1) {
				System.out.println("Continue");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<CategoryDto> diplayCategories() throws SQLException {
		List<CategoryDto> categoryDto_objs=new ArrayList<>();
		String sql="select * from category";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(3));
				categoryDto_objs.add(new CategoryDto(rs.getInt(1),rs.getString(3)));
			}
		}
		return categoryDto_objs;
	}
}

