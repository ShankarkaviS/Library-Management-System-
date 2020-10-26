package com.interview.library.service;

import com.interview.library.dto.BookListDto;
import com.interview.library.dto.BookRequestDto;
import com.interview.library.dto.ResponseDto;

public interface BookService {
	public ResponseDto addBook(BookRequestDto bookRequestDto);
public BookListDto findBookByProperty(String authorName,String title,String isdn);
public ResponseDto deleteBook(String isbn,String title);

}
