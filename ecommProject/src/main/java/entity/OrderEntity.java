package entity;

public class OrderEntity {
	private int b_id;
	private int s_id;
	private int p_id;
	private double total_price;
	private String shipping_address;
	private String status;
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OrderEntity(int b_id, int s_id, int p_id, double total_price, String shipping_address, String status) {
		super();
		this.b_id = b_id;
		this.s_id = s_id;
		this.p_id = p_id;
		this.total_price = total_price;
		this.shipping_address = shipping_address;
		this.status = status;
	}
	
}
