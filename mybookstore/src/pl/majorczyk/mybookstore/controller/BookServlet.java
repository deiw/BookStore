package pl.majorczyk.mybookstore.controller;

import pl.majorczyk.mybookstore.model.Book;
import pl.majorczyk.mybookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId=request.getParameter("id");
        BookService bookService=new BookService();
        Book book=bookService.getBookById(bookId);
        request.setAttribute("book",book);
        request.getRequestDispatcher("WEB-INF/book.jsp").forward(request,response);
    }
}
