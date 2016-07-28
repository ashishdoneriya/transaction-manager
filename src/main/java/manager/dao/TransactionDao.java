package manager.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import manager.models.Category;
import manager.models.Transaction;

@Transactional
@Repository("transactionDao")
public class TransactionDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	public int save(Transaction transction) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(transction);
		return transction.getTransactionID();
	}

	public void delete(Transaction transction) {
		session = sessionFactory.getCurrentSession();
		session.delete(transction);
	}

	public Transaction get(int transctionID) {
		session = sessionFactory.getCurrentSession();
		return (Transaction) session.get(Transaction.class, transctionID);
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> list() {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Transaction.class);
		cr.addOrder(Order.desc("date"));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> list(Date from, Date to) {

		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Transaction.class);
		cr.add(Restrictions.gt("date", from));
		cr.add(Restrictions.lt("date", to));
		cr.addOrder(Order.asc("date"));
		return cr.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Transaction> listThisMonth() {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Transaction.class);
		cr.add(Restrictions.gt("date", thisMonthStartingDate()));
		cr.addOrder(Order.asc("date"));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> list(Category category) {
		if (category == null) {
			return new ArrayList<Transaction>();
		}
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Transaction.class);
		cr.add(Restrictions.eq("category", category));
		cr.addOrder(Order.desc("date"));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> list(Category category, int monthsBefore) {
		if (category == null) {
			return new ArrayList<Transaction>();
		}
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Transaction.class);
		cr.add(Restrictions.eq("category", category));
		cr.add(Restrictions.gt("date", getMonthsBeforeDate(monthsBefore)));
		cr.addOrder(Order.asc("date"));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> list(Category category, Date from, Date to) {

		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Transaction.class);
		cr.add(Restrictions.eq("category", category));
		cr.add(Restrictions.gt("date", from));
		cr.add(Restrictions.lt("date", to));
		cr.addOrder(Order.asc("date"));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> listThisMonth(Category category) {

		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Transaction.class);
		cr.add(Restrictions.eq("category", category));
		cr.add(Restrictions.gt("date", thisMonthStartingDate()));
		cr.addOrder(Order.asc("date"));
		return cr.list();
	}
	
	private Date getMonthsBeforeDate(int months) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1 * months);
		return cal.getTime();
	}

	private Date thisMonthStartingDate() {
		Calendar calendar = getCalendarForNow();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		setTimeToBeginningOfDay(calendar);
		return calendar.getTime();
	}

	private Calendar getCalendarForNow() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		return calendar;
	}

	private void setTimeToBeginningOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

}
