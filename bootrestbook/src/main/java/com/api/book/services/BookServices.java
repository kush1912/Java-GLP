package com.api.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;

@Component//This will help in autowire annotation
public class BookServices {
	@Autowired
	private BookRepository bookRepository;
	
	//private static List<Book> list=new ArrayList<>();
	
//	static {
//		
//		list.add(new Book(12,"Java Complete Reference","XYZ") );
//		list.add(new Book(13,"Python Complete Reference","ABC") );
//		list.add(new Book(14,"CPP Complete Reference","DEF") );
//		list.add(new Book(15,"C Complete Reference","GHI") );
//		list.add(new Book(16,"JavaScript Complete Reference","JKL") );
//		list.add(new Book(17,"PHP Complete Reference","MNO") );
//		list.add(new Book(18,"CSharp Complete Reference","PQR") );
//		list.add(new Book(19,"Angular Complete Reference","STU") );
//	}
	
	
	//get all books
	public List<Book> getAllBooks(){
		
		List<Book> list=(List<Book>)this.bookRepository.findAll();
		
		return list;
	}
	
	//get single book by id
	public Book getBookById(int id) {
		Book book=null;
		try {
			//book=list.stream().filter(e->e.getId()==id).findFirst().get();
			book=this.bookRepository.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	//Adding the book
	public void addBook(Book b) {
		//list.add(b);
		this.bookRepository.save(b);
	}
	
	//Delet book
	public void deleteBook(int bid) {
		
		/*list=list.stream().filter(book->{
			if(book.getId()!=bid ) {
				return true;
			}else {
				return false;
			}
		}).collect(Collectors.toList());*/
		this.bookRepository.deleteById(bid);
		
	}
	
	
	//Update the Book	
	public void updateBook(Book book,int bookId) {
		book.setId(bookId);
		bookRepository.save(book);
		/*list=list.stream().map(b->{
			if(b.getId()==bookId) {
				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
			}
			return b;
		}).collect(Collectors.toList());*/
	}

}
