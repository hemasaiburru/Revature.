package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.OrderDto;
import entity.OrderEntity;
import util.DbConnect;
public class OrderDao {
	private static Connection con;
	public OrderDao() {
		con=DbConnect.getInstance().getConnection();
	}
	public void insertOrder(OrderEntity orderEntity) throws SQLException {
		String sql="insert into orders(b_id,s_id,p_id,total_price,shipping_address,status) values(?,?,?,?,?,?)";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, orderEntity.getB_id());
			stmt.setInt(2, orderEntity.getS_id());
			stmt.setInt(3, orderEntity.getP_id());
			stmt.setDouble(4, orderEntity.getTotal_price());
			stmt.setString(5, orderEntity.getShipping_address());
			stmt.setString(6, orderEntity.getStatus());
			int res=stmt.executeUpdate();
			if(res==1) {
				System.out.println("Order inserted");
			}
		}
	}
	public OrderDto display(int b_id, int p_id) throws SQLException {
		String sql="select * from orders where b_id = ? and p_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			stmt.setInt(2, p_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				return new OrderDto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getTimestamp(8));
			}
		}
		return null;
	}
	public List<OrderDto> display(int b_id) throws SQLException {
		List<OrderDto> orderDto_objs=new ArrayList<>();
		String sql="select * from orders where b_id = ?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, b_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				orderDto_objs.add(new OrderDto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getTimestamp(8)));
			}
		}
		return orderDto_objs;
	}
	public List<OrderDto> displayBySid(int s_id) throws SQLException {
		List<OrderDto> orderDto_objs=new ArrayList<>();
		String sql="select * from orders where s_id = ?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, s_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				orderDto_objs.add(new OrderDto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getTimestamp(8)));
			}
		}
		return orderDto_objs;
	}

}
