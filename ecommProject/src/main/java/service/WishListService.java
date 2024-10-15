package service;

import java.sql.SQLException;
import java.util.List;

import dao.WishListDao;

public class WishListService {
	private WishListDao wishlistDao_obj=new WishListDao();
	public void addItemToWishList(int b_id,int p_id) throws SQLException {
		wishlistDao_obj.addItemToWishList(b_id, p_id);
	}
	public List<Integer> getPids(int i) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(i);
		return wishlistDao_obj.getPids(i);
	}
	public void removeItemToWishList(int b_id, int productId) throws SQLException {
		wishlistDao_obj.removeItemToWishList(b_id, productId);
	}
	public List<Integer> display(int b_id) throws SQLException {
		return wishlistDao_obj.display(b_id);
	}
	public void deleteBybidAndPid(int b_id, int p_id) throws SQLException {
		wishlistDao_obj.deleteBybidAndPid(b_id,p_id);
	}
}
