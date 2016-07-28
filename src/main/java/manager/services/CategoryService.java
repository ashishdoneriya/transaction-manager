package manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manager.dao.CategoryDao;
import manager.models.Category;

@Transactional
@Service("categoryService")
public class CategoryService {
	
	@Autowired CategoryDao categoryDao;
	
	public int createCategory(String name) {
		return categoryDao.save(new Category(name));
	}
	

}
