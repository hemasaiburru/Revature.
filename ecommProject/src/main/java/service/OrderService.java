package service;

import entity.OrderEntity;

import java.sql.SQLException;
import java.util.List;

import dao.OrderDao;
import dto.OrderDto;
public class OrderService {
	private static OrderDao orderDao_obj=new OrderDao();
	public void insertOrder(OrderEntity orderEntity) throws SQLException {
		orderDao_obj.insertOrder(orderEntity);
	}
	public OrderDto display(int b_id, int p_id) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao_obj.display(b_id,p_id);
	}
	public List<OrderDto> display(int b_id) throws SQLException {
		return orderDao_obj.display(b_id);
	}
	public List<OrderDto> displayBySid(int s_id) throws SQLException {
		return orderDao_obj.displayBySid(s_id);
	}

}
