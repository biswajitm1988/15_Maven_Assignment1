package com.fsd.spring.service;

import com.fsd.spring.dao.BookDao;
import com.fsd.spring.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;

	public List<Book> searchByBook(String bookTitle) throws Exception {
		int count = bookDao.getAllBooks().size();
		List<Book> bookDetailsList = new ArrayList<Book>();
		if (count == 0) {
			System.out.println("There are no books in the system");
			return bookDetailsList;
		}
		System.out.println("Book Title To be Searched : " + bookTitle);
		bookDetailsList = bookDao.searchForBooks(bookTitle.toLowerCase());
		if (CollectionUtils.isEmpty(bookDetailsList)) {
			System.out.println("no books found for your search : " + bookTitle);
		} else {
			System.out.println("Matching Books :\n" + bookDetailsList);
		}
		return bookDetailsList;
	}

	public int deleteBook(String deleteBookTitle) throws Exception {
		int count = bookDao.getAllBooks().size();
		if (count == 0) {
			System.out.println("There are no books in the system");
			return count;
		}
		System.out.println("Book Title To be deleted : " + deleteBookTitle);
		int rowsDeleted = bookDao.deleteBook(deleteBookTitle.toLowerCase());
		System.out.println("Number of records deleted : " + rowsDeleted);
		return rowsDeleted;
	}

	public long addBook(Book newBook) throws Exception {
		boolean status = bookDao.addBook(newBook);
		System.out.println("Book Added " + status + " New Book=" + newBook);
		return newBook.getBookId();
	}

	public String transformToHtml(List<Book> bookList) {
		if(CollectionUtils.isEmpty(bookList)){
			return "No Book Found for the Search Key";
		}
		StringBuilder buf = new StringBuilder();
		buf.append("<html>" +
				"<body>" +
				"<table>" +
				"<tr>" +
				"<th>Book ID</th>" +
				"<th>Book Title</th>" +
				"<th>Price</th>" +
				"<th>Volume</th>" +
				"<th>Published Date</th>" +
				"</tr>");
		bookList.forEach(book ->{
			buf.append("<tr><td>")
					.append(book.getBookId())
					.append("</td><td>")
					.append(book.getTitle())
					.append("</td><td>")
					.append(book.getPrice())
					.append("</td><td>")
					.append(book.getVolume())
					.append("</td><td>")
					.append(book.getPublishDate())
					.append("</td></tr>");
		});
		buf.append("</table>" +
				"</body>" +
				"</html>");
		return buf.toString();
	}
}
