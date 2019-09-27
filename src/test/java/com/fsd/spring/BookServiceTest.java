package com.fsd.spring;

import com.fsd.spring.entity.Book;
import com.fsd.spring.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AssertionErrors;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private Book book;

    @Before
    public void init(){
        book.setTitle("JUNIT Spring Book");
        book.setPrice(10d);
        book.setVolume(1);
        book.setPublishDate(new Date());
    }

    @Test
    public void addBookTest() throws Exception {
       Assert.assertTrue(bookService.addBook(book)!=0l);
    }

    @Test
    public void bookSearchTest() throws Exception {
        Assert.assertEquals(book.getTitle(), bookService.searchByBook("junit").get(0).getTitle());
    }

    @Test
    public void deleteBookTest() throws Exception {
        Assert.assertTrue(bookService.deleteBook(book.getTitle())!=0);
    }
}
