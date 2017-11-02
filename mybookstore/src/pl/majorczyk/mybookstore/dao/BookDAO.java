package pl.majorczyk.mybookstore.dao;

import pl.majorczyk.mybookstore.model.Book;

import java.util.List;

public interface BookDAO {
    Book create(Book book);
    List<Book> readAllBooks();
    List<Book> readBooksByAuthor(String author);
    List<Book> readBooksByGenre(String genre);
    List<Book> readBooksToPage(int page);
    Book readBookById(long id);
}
