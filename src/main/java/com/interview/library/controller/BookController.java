package com.interview.library.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.interview.library.dto.BookListDto;
import com.interview.library.dto.BookRequestDto;
import com.interview.library.dto.ResponseDto;
import com.interview.library.service.BookServiceImpl;

/**
 * 
 * @author Shankar.K
 * @version 1.0
 * @since 25-10-2020
 */

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
public class BookController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookServiceImpl bookServiceImpl;

	/**
	 * This method is used to ADD the Book
	 * 
	 * @param BookRequestDto
	 * @return ResponseDto
	 */
	@PostMapping("/addBook")
	public ResponseEntity<ResponseDto> addBook(@RequestBody BookRequestDto bookRequestDto) {
		LOGGER.info("add book "+bookRequestDto.getAuthor()+" "+bookRequestDto.getTitle());
		return new ResponseEntity<>(bookServiceImpl.addBook(bookRequestDto), HttpStatus.OK);
	}

	/**
	 * This method is used to find the book by author, title or isdn
	 * 
	 * @return BookListDto
	 */
	@GetMapping("/findByProperty")
	public ResponseEntity<BookListDto> findBook(String authorName, String title, String isdn) {
		LOGGER.info("Author Name "+authorName+" ,"+title+", "+isdn);
		return new ResponseEntity<>(bookServiceImpl.findBookByProperty(authorName, title, isdn), HttpStatus.OK);
	}

	/**
	 * This method is used to Delete the Book
	 * 
	 * @return ResponseDto
	 */

	@DeleteMapping("/deleteMapping")
	public ResponseEntity<ResponseDto> deleteBook(String isbn, String title) {
		return new ResponseEntity<>(bookServiceImpl.deleteBook(isbn, title), HttpStatus.OK);
	}

}
