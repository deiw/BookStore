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

@WebServlet("")
public class HomeServlet extends HttpServlet {
    private final static int LIMIT=10;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService=new BookService();
        String page=request.getParameter("page");
        if(page==null) page="1";
        List<Book> list=bookService.getBooksToPage(page,LIMIT);
        request.setAttribute("size",list.size());
        request.setAttribute("page",page);
        request.setAttribute("books",list);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request,response);
    }
}
