package com.interview.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.interview.library.dto.BookListDto;
import com.interview.library.dto.BookRequestDto;
import com.interview.library.dto.ResponseDto;
import com.interview.library.model.Book;
import com.interview.library.repository.BookRepository;
import com.interview.library.util.BookUtil;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookRepository bookRepository;

	/**
	 * @author Shankar This Method is used to add the book
	 * @param BookingRequestDto
	 * @return ResponseDto
	 */

	@Override
	public ResponseDto addBook(BookRequestDto bookRequestDto) {
		Book book = new Book();
		ResponseDto responseDto = new ResponseDto();
		try {
			if (bookRequestDto.getAuthor().isEmpty() && bookRequestDto.getAuthor() != ""
					&& bookRequestDto.getiSBN() != "" && bookRequestDto.getTitle() != "") {
				log.info("Author : " + bookRequestDto.getAuthor());
				book.setTitle(bookRequestDto.getTitle());
				book.setAuthorName(bookRequestDto.getAuthor());
				book.setiSBN(bookRequestDto.getiSBN());
				book = bookRepository.save(book);
			} else {
				responseDto.setMessage(BookUtil.BOOK_ADD_FAILED);
				responseDto.setStatusCode(BookUtil.FAILED_CODE);
			}
		} catch (NullPointerException exception) {
			responseDto.setMessage(BookUtil.BOOK_ADD_FAILED);
			responseDto.setStatusCode(BookUtil.FAILED_CODE);
		}
		if (book.getBookId() != null) {
			responseDto.setMessage(BookUtil.BOOK_ADD_SUCCESS);
			responseDto.setStatusCode(BookUtil.SUCCESS_CODE);
		} else {
			responseDto.setMessage(BookUtil.BOOK_ADD_FAILED);
			responseDto.setStatusCode(BookUtil.FAILED_CODE);
		}
		return responseDto;
	}

	/**
	 * @author Shankar This Method is used to get the book List based on author,
	 *         title and isdn
	 * @return BookListDto
	 */

	@Override
	public BookListDto findBookByProperty(String authorName, String title, String isdn) {
		List<Book> books = bookRepository.findByProperty(authorName, title, isdn);
		BookListDto bookListDto = new BookListDto();
		if (books.size() > 0) {
			log.info("Author : " + authorName + " Title : " + title + " ,isdn : " + isdn);
			bookListDto.setBooks(books);
			bookListDto.setMessage(BookUtil.SUCCESS);
			bookListDto.setStatus(BookUtil.SUCCESS_CODE);
		} else {
			bookListDto.setMessage(BookUtil.NO_BOOK);
			bookListDto.setStatus(BookUtil.SUCCESS_CODE);
		}
		return bookListDto;
	}

	/**
	 * @author Shankar This Method is used to delete the book List based on isbn and
	 *         title
	 * @return ResponseDto
	 */

	@Override
	public ResponseDto deleteBook(String isbn, String title) {
		List<Book> books = bookRepository.findByIsbnOrTitle(isbn, title);
		ResponseDto responseDto = new ResponseDto();
		if (books.size() > 0) {
			try {
				for (Book book : books) {
					bookRepository.delete(book);
				}
				log.info("Title : " + title + " ,ISBN : " + isbn);
				responseDto.setMessage(BookUtil.BOOK_DELETE_SUCCESS);
				responseDto.setStatusCode(BookUtil.SUCCESS_CODE);
			} catch (Exception e) {
				responseDto.setMessage(BookUtil.BOOK_DELETE_FAILED);
				responseDto.setStatusCode(BookUtil.FAILED_CODE);
			}
		} else {
			responseDto.setMessage(BookUtil.NO_BOOK);
			responseDto.setStatusCode(BookUtil.INVALID);
		}
		return responseDto;
	}
}
