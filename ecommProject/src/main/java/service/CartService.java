package service;

import java.sql.SQLException;
import java.util.List;

import dao.CartDao;

public class CartService {
	private CartDao cartDao_obj=new CartDao();
	public void addToCart(int b_id,int p_id,int quant,double pdummy)throws SQLException {
		cartDao_obj.addToCart(b_id,p_id, quant, pdummy);
		System.out.println("Hey");
	}
	public List<Integer> getPids(int i) throws SQLException {
		return cartDao_obj.getPids(i);
	}
	public void removeFromCart(int b_id, int productId) throws SQLException {
		cartDao_obj.removeFromCart(b_id, productId);
	}
	public List<Integer> getCidsByBid(int b_id) throws SQLException {
		return cartDao_obj.getCidsByBid(b_id);
	}
	
}
