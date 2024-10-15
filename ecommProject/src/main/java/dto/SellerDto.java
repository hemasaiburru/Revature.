package dto;

public class SellerDto {
	private int s_id;
	private String name;
	private String email;
	private String phno;
	private String bus_type;
	private String bus_address;
	private String bus_name;
	public SellerDto(int s_id, String name, String email, String phno, String bus_name,String bus_type,String bus_address) {
		super();
		this.bus_address=bus_address;
		this.bus_name=bus_name;
		this.s_id = s_id;
		this.name = name;
		this.email = email;
		this.phno = phno;
		this.bus_type = bus_type;
	}
	public String getBus_address() {
		return bus_address;
	}
	public void setBus_address(String bus_address) {
		this.bus_address = bus_address;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getBus_type() {
		return bus_type;
	}
	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}
	public String getBus_name() {
		return bus_name;
	}
	public void setBus_name(String bus_name) {
		this.bus_name = bus_name;
	}
	
}