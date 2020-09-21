package com.LM.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "lmbook")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    
    private String book_author;
    
    private String book_bookname;
    
   
    private String book_currentStatus;
    
    private String book_isbn;
     
    private int book_numberOfCopies;
    
    private String book_publisher;
    
    private String book_title;
    
    private String book_yearOfPublication;
    
    private String book_price;
    
    private String book_totalpages;
    
    private String book_language;

    
    
	public String getBook_bookname() {
		return book_bookname;
	}

	public void setBook_bookname(String book_bookname) {
		this.book_bookname = book_bookname;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_currentStatus() {
		return book_currentStatus;
	}

	public void setBook_currentStatus(String book_currentStatus) {
		this.book_currentStatus = book_currentStatus;
	}

	public String getBook_isbn() {
		return book_isbn;
	}

	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}

	public int getBook_numberOfCopies() {
		return book_numberOfCopies;
	}

	public void setBook_numberOfCopies(int book_numberOfCopies) {
		this.book_numberOfCopies = book_numberOfCopies;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_yearOfPublication() {
		return book_yearOfPublication;
	}

	public void setBook_yearOfPublication(String book_yearOfPublication) {
		this.book_yearOfPublication = book_yearOfPublication;
	}

	public String getBook_price() {
		return book_price;
	}

	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}

	public String getBook_totalpages() {
		return book_totalpages;
	}

	public void setBook_totalpages(String book_totalpages) {
		this.book_totalpages = book_totalpages;
	}

	public String getBook_language() {
		return book_language;
	}

	public void setBook_language(String book_language) {
		this.book_language = book_language;
	}

    
    
}
