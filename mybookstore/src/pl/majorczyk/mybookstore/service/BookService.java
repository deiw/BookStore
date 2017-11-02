package pl.majorczyk.mybookstore.service;

import pl.majorczyk.mybookstore.dao.BookDAO;
import pl.majorczyk.mybookstore.dao.FactoryDAO;
import pl.majorczyk.mybookstore.model.Book;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private BookDAO getBookDAO(){
        FactoryDAO factory=FactoryDAO.getFactory(FactoryDAO.MY_SQL);
        BookDAO bookDAO=factory.getBookDAO();
        return bookDAO;
    }

    public  void addBook(String title,String author,String genre,String description,Part imagePart,double price,String directory){
        Book book=new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setDescription(description);
        book.setPrice(price);
        String url=getUrl(imagePart);
        book.setUrl(url);
        book.setBdate(new Timestamp(new Date().getTime()));

        BookDAO bookDAO=getBookDAO();
        bookDAO.create(book);

       storeImage(imagePart,url,directory);

    }
    private void storeImage(Part part, String url, String directory){
        try {
            BufferedImage image= ImageIO.read(part.getInputStream());
            File file=new File(directory+"/images/"+url);
            ImageIO.write(image,"jpg",file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getUrl(Part part){
        String url=String.valueOf(part.getSize())+".jpg";
        return url;
    }

    public List<Book> getAllBooks(){
        BookDAO bookDAO=getBookDAO();
        List<Book> bookList=bookDAO.readAllBooks();
        return bookList.stream()
                .sorted(Comparator.comparing(Book::getBdate).reversed())
                .collect(Collectors.toList());
    }
    public Book getBookById(String id){
        Book book=null;
        if(id!=null){
            long bookId=Long.valueOf(id);
            BookDAO bookDAO=getBookDAO();
            book=bookDAO.readBookById(bookId);
        }
        return book;
    }
    public List<Book> getBooksBySearch(String search){
        List<Book> bookList=getAllBooks();
        List<Book> filteredList=new ArrayList<>();
        for(Book book:bookList){
            if(book.getTitle().toLowerCase().contains(search.toLowerCase())){
                filteredList.add(book);
            }
        }
        return filteredList;
    }
    public List<Book> getBooksByAuthor(String author){
        List<Book> bookList=getBookDAO().readBooksByAuthor(author);
        return bookList;
    }
    public List<Book> getBooksByGenre(String genre){
        List<Book> bookList=getBookDAO().readBooksByGenre(genre);
        return bookList;
    }
    public List<Book> getBooksToPage(String page,int limit){
         int pageNr=Integer.valueOf(page)-1;

         List<Book>bookList=getBookDAO().readBooksToPage(pageNr*limit);
         return bookList.stream()
                 .sorted(Comparator.comparing(Book::getBdate).reversed())
                 .collect(Collectors.toList());
    }


}
