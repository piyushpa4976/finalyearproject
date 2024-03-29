package com.LM.api.dao;

import java.util.List;

import com.LM.api.model.Book;

public interface BookDao {

	long importexcel(List<Book> importExcel);

	long save(Book book);

	Book get(long id);

	List<Book> list();

	void update(long id, Book book);

	void delete(long id);
}
