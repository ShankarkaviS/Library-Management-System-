package com.interview.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.interview.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query(value="SELECT * FROM book b WHERE b.author_name=? OR b.title=? OR b.isbn=?",nativeQuery=true)
	public List<Book> findByProperty(String authorName,String title, String isbn);
	
	@Query(value="SELECT * FROM book b WHERE b.isbn=? OR b.title=?",nativeQuery=true)
	public List<Book> findByIsbnOrTitle(String isbn,String title);

}
