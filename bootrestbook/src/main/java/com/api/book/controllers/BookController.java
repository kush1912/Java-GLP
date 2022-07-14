package com.api.book.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookServices;

//@Controller//For normal response or returning some page use this annotation
@RestController// used for the rest API and in this scenario ResponseBody annotation is not needed
public class BookController {
	
	@Autowired
	private BookServices bookServices;

	//@RequestMapping(value="/books",method = RequestMethod.GET)//For RequestMapping annotation, method is important but there are some annotation in which method is not important
	//@ResponseBody //this will return as string otherwise it will return as view or the page should exist by the return method in this case "this is testing book first"			
	@GetMapping("/books")//This annotation combines RequestMapping + method For Post method --> PostMapping for Delete method --> DeleteMapping
	public ResponseEntity<List<Book>> getBooks() {
		/*
		Book book = new Book();
		
		book.setId(1234);
		book.setTitle(" Java Complete Reference");
		book.setAuthor("vinay kushwaha");
		*/
		
		List<Book> list= bookServices.getAllBooks();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id ) {
		
		Book book =bookServices.getBookById(id);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(book));
		
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {//@RequestBody helps in getting data in JSON format
		//Book b=null;
		try {
			this.bookServices.addBook(book);
			//return ResponseEntity.status(HttpStatus.CREATED).build();
			//return ResponseEntity.of(Optional.of(b));
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		//return book;
	}
	
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		try {
			this.bookServices.deleteBook(bookId);
			//return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId) {
		try {
			this.bookServices.updateBook(book,bookId);
			return ResponseEntity.ok().body(book);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		//return book;
	}
	
}
