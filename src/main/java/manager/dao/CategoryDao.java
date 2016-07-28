package manager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import manager.models.Category;

@Transactional
@Repository("categoryDao")
public class CategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	
	public int save(Category category) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
		return category.getCategoryID();
	}

	public void delete(Category category) {
		session = sessionFactory.getCurrentSession();
		session.delete(category);
	}

	public Category get(int categoryID) {
		session = sessionFactory.getCurrentSession();
		return (Category) session.get(Category.class, categoryID);
	}

	@SuppressWarnings("unchecked")
	public List<Category> list() {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Category.class);
		cr.addOrder(Order.asc("name"));
		return cr.list();
	}

}
