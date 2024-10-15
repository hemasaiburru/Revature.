package service;
import java.sql.SQLException;
import java.util.List;

import dao.ReviewDao;
import dto.ReviewDto;
public class ReviewService {
	private ReviewDao reviewDao_obj=new ReviewDao();;
	public void addToReview(int b_id, int p_id, double rating, String review) throws SQLException {
		reviewDao_obj.addToReview(b_id,p_id,rating,review);
	}
	public List<ReviewDto> diplayAll() throws SQLException {
		return reviewDao_obj.displayAll();
	}
	
}
