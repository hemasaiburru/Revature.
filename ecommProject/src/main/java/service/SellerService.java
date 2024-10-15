package service;
import exception.InvalidInputException;
import exception.UserNotFoundException;
import entity.SellerEntity;

import java.sql.SQLException;

import dao.SellerDao;
import dto.SellerDto;
public class SellerService {
	private SellerDao sellerdao_obj =new SellerDao();
	public void register(SellerEntity sellerEntity_obj) throws SQLException{
		sellerdao_obj.addSeller(sellerEntity_obj);
	}
	public SellerDto login(String email, String pwd) throws UserNotFoundException, SQLException {
		SellerEntity sellerEntity_obj=sellerdao_obj.getSellerByEmail(email);
		if (sellerEntity_obj == null || !sellerEntity_obj.getPassword().equals(pwd)) {
			throw new UserNotFoundException("Invalid Email or Password");
        }
		else {
			int id_noob=sellerdao_obj.getSellerIdByEmail(email);
			return new SellerDto(id_noob,sellerEntity_obj.getName(),sellerEntity_obj.getEmail(),sellerEntity_obj.getPhno(),sellerEntity_obj.getBus_name(),sellerEntity_obj.getBus_type(),sellerEntity_obj.getBus_address());
		}
	}
	public void updateSellerPwdById(int s_id, SellerEntity sellerEntity) throws SQLException {
		sellerdao_obj.updateSellerPwdById(s_id,sellerEntity);
	}
	public void updateSellerById(SellerDto updatedSeller) throws SQLException {
		sellerdao_obj.updateSellerById(updatedSeller);
	}
}
