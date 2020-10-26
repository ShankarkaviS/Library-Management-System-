package com.interview.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.interview.library.model.Book;

public class BookListDto {

	private List<Book> books = new ArrayList<Book>();
	private String message;
	private String status;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
