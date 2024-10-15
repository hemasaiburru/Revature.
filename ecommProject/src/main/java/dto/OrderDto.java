package dto;

import java.sql.Timestamp;

public class OrderDto {
	private int o_id;
	private int b_id;
	private int s_id;
	private int p_id;
	private double total_price;
	private String shipping_address;
	private String status;
	private Timestamp order_date;
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
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
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public OrderDto(int o_id, int b_id, int s_id, int p_id, double total_price, String shipping_address, String status,
			Timestamp order_date) {
		super();
		this.o_id = o_id;
		this.b_id = b_id;
		this.s_id = s_id;
		this.p_id = p_id;
		this.total_price = total_price;
		this.shipping_address = shipping_address;
		this.status = status;
		this.order_date = order_date;
	}
	
}
