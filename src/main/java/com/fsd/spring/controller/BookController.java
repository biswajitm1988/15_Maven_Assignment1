package com.fsd.spring.controller;

import com.fsd.spring.entity.Book;
import com.fsd.spring.service.BookService;
import com.fsd.spring.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping({"/","/home"})
    public String index() throws IOException {
        System.out.println("Book Controller index");
        return "home";
    }

    @GetMapping("/searchWithSubTitle")
    @ResponseBody
    public String searchWithSubTitle(@RequestParam(name = "searchSubTitle") String subTitle) throws Exception {
        System.out.println("Book Controller searchWithSubTitle >> "+subTitle);
        List<Book> bookList = subjectService.searchBySubject(subTitle);
        return bookService.transformToHtml(bookList);
    }

    @GetMapping("/searchWithBookTitle")
    @ResponseBody
    public String searchWithBookTitle(@RequestParam(name = "searchBookTitle") String bookTitle) throws Exception {
        System.out.println("Book Controller searchWithBookTitle >> "+bookTitle);
        List<Book> bookList = bookService.searchByBook(bookTitle);
        return bookService.transformToHtml(bookList);
    }

    @PostMapping("/deleteWithSubjectTitle")
    @ResponseBody
    public String deleteWithSubjectTitle(@RequestParam(name = "deleteSubTitle") String deleteSubTitle) throws Exception {
        System.out.println("Book Controller deleteWithSubjectTitle >> "+deleteSubTitle);
        int count = subjectService.deleteSubject(deleteSubTitle);
        return String.valueOf(count).concat(" records deleted");
    }

    @PostMapping("/deleteWithBookTitle")
    @ResponseBody
    public String deleteWithBookTitle(@RequestParam(name = "deleteBookTitle") String deleteBookTitle) throws Exception {
        System.out.println("Book Controller deleteWithBookTitle >> "+deleteBookTitle);
        int count = bookService.deleteBook(deleteBookTitle);
        return String.valueOf(count).concat(" records deleted");
    }

    @PostMapping("/addBook")
    @ResponseBody
    public String addBook(@ModelAttribute Book book) throws Exception {
        System.out.println("Book Controller deleteWithBookTitle >> "+book);
        long bookId = bookService.addBook(book);
        return "Number ".concat(String.valueOf(bookId)).concat(" book added");
    }
}