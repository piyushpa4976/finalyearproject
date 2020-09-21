package com.LM.api.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.LM.api.model.Book;

@Transactional(readOnly = true)
@Repository
public class BookDaoImp implements BookDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public long importexcel(List<Book> importExcel) {
		long m = 0;
		for (Book student : importExcel) {

			boolean exits = false;
//	if (student.getStudent_email() == null)  {
//		
//		return m;
//	}

			Session hsession = sessionFactory.getCurrentSession();
			try {
				exits = hsession.createQuery("select 1 from Book sm where sm.book_isbn='" + student.getBook_isbn()
						+ "' and sm.book_name='" + student.getBook_bookname() + "'").list().size() != 0;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				m = 400;
			}
			if (exits) {
				m = 400;
			} else {
				long s = (long) hsession.save(student);
				if (s != 0) {
					m = 200;
				} else {
					m = 400;
				}
			}
		}
		return m;
	}

	@Transactional
	@Override
	public long save(Book book) {
		sessionFactory.getCurrentSession().save(book);
		return book.getBookId();
	}

	@Override
	public Book get(long id) {
		return sessionFactory.getCurrentSession().get(Book.class, id);
	}

	
	@Override
	public List<Book> list() {
		
		Session session = sessionFactory.getCurrentSession();
		// Create CriteriaBuilder
		CriteriaBuilder builder = session.getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<Book> criteria = builder.createQuery(Book.class);

		// Specify criteria root
		criteria.from(Book.class);

		// Execute query
		List<Book> entityList = session.createQuery(criteria).getResultList();
		
		return entityList;
		
	
	
	}

	@Transactional
	@Override
	public void update(long id, Book book) {
		Session session = sessionFactory.getCurrentSession();
		Book book2 = session.byId(Book.class).load(id);
		book2.setBook_title(book.getBook_title());
		book2.setBook_author(book.getBook_author());
		session.flush();
	}

	@Transactional
	@Override
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.byId(Book.class).load(id);
		session.delete(book);
	}
}
