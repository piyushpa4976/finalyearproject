package com.LM.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "lmbookissued")
public class BookIssued {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long issue_id;
	
	private long book_id;
    
    private long user_id;
    
    @Column(name = "bookissue_date", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date bookissue_date = new Date();

    
    
//    private List<User> user;
//    
//    private List<Book> book;
    
    
    
    
    
    
    
	public long getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(long issue_id) {
		this.issue_id = issue_id;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public Date getBookissue_date() {
		return bookissue_date;
	}

	public void setBookissue_date(Date bookissue_date) {
		this.bookissue_date = bookissue_date;
	}

    

}
