package dto;

import java.sql.Timestamp;

public class ReviewDto {
	private double rating;
	private String comment;
	private Timestamp created_at;
	public ReviewDto(double rating, String comment, Timestamp created_at) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.created_at = created_at;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
}
