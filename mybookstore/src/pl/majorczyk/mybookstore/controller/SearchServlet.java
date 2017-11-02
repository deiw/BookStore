package pl.majorczyk.mybookstore.controller;

import pl.majorczyk.mybookstore.model.Book;
import pl.majorczyk.mybookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String search=request.getParameter("search");
        String author=request.getParameter("author");
        String genre=request.getParameter("genre");
        BookService bookService=new BookService();
        List<Book> filteredList=null;
        if(author!=null){
            filteredList=bookService.getBooksByAuthor(author);
        }
        else if(search!=null){
            filteredList=bookService.getBooksBySearch(search);
        }
        else {
            filteredList=bookService.getBooksByGenre(genre);
        }
        request.setAttribute("books",filteredList);
        request.getRequestDispatcher("WEB-INF/search.jsp").forward(request,response);
    }
}
