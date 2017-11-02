package pl.majorczyk.mybookstore.controller;

import org.springframework.dao.DuplicateKeyException;
import pl.majorczyk.mybookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/createbook")
@MultipartConfig
public class CreateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        String description = request.getParameter("description");
        Part imagePart = request.getPart("image");
        double price = Double.valueOf(request.getParameter("price"));
        String directory = getServletContext().getRealPath("/");

        try {
            BookService bookService = new BookService();
            bookService.addBook(title, author, genre, description, imagePart, price, directory);
            request.getRequestDispatcher("WEB-INF/create_book.jsp").forward(request, response);
        } catch (DuplicateKeyException e) {
            request.setAttribute("message", "Book already exists!");
            request.getRequestDispatcher("WEB-INF/create_book.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/create_book.jsp").forward(request,response);
    }
}
