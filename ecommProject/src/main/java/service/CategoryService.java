package service;

import java.sql.SQLException;
import java.util.List;

import dao.CategoryDao;
import dto.CategoryDto;
import entity.CategoryEntity;

public class CategoryService {
	private CategoryDao categoryDao_obj=new CategoryDao();
	public void add_category(CategoryEntity categoryEntity_obj) {
		categoryDao_obj.addCategory(categoryEntity_obj);
	}
	public List<CategoryDto> diplayCategories() throws SQLException {
		return categoryDao_obj.diplayCategories();
	}
}
