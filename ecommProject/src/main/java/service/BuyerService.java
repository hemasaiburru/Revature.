package service;

import java.sql.SQLException;

import dao.BuyerDao;
import dto.BuyerDto;
import entity.BuyerEntity;
import exception.InvalidInputException;
public class BuyerService {
	private BuyerDao buyerdao_obj=new BuyerDao();
	public void register(BuyerEntity buyerEntity_obj) throws SQLException {
		buyerdao_obj.addBuyer(buyerEntity_obj);
	}
	public BuyerDto login(String email, String pwd) throws InvalidInputException{
		BuyerEntity buyerEntity_obj=buyerdao_obj.getBuyerByEmail(email);
		if (buyerEntity_obj == null || !buyerEntity_obj.getPassword().equals(pwd)) {
			throw new InvalidInputException("Invalid Email or Password.....User Not Found");
        }
		else {
			int id_noob=buyerdao_obj.getBuyerIdByEmail(email);
			return new BuyerDto(id_noob,buyerEntity_obj.getName(),buyerEntity_obj.getEmail(),
					buyerEntity_obj.getPhno(),buyerEntity_obj.getAddress());
		}
	}
	public void updateBuyerPwdById(int b_id, BuyerEntity buyerEntity_obj) throws SQLException {
		buyerdao_obj.updateBuyerPwdById(b_id,buyerEntity_obj);
	}
	public void updateBuyerById(BuyerDto bdo) throws SQLException {
		buyerdao_obj.updateBuyerById(bdo);
	}
	
}

