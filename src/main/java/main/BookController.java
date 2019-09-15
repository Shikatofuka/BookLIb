package main;

import main.model.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;



    @GetMapping("/books/")
    public List<Book> list() {
        Iterable<Book> bookIterable = bookRepo.findAll();
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : bookIterable) {
            books.add(book);
        }
        return books;
    }

    @PostMapping("/books/")
    public int add(Book book) {
        Book newBook = bookRepo.save(book);
        return newBook.getId();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalBook.get(), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public void del(@PathVariable("id") Book book) {
        bookRepo.delete(book);
    }

}
