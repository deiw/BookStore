package pl.majorczyk.mybookstore.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.majorczyk.mybookstore.model.Book;
import pl.majorczyk.mybookstore.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlBookDAO implements BookDAO {

    private static final String CREATE="INSERT INTO book(title,author,genre,description,url,price,bdate) " +
            "VALUES(:title,:author,:genre,:description,:url,:price,:bdate);";
    private static final String READ_ALL_BOOKS="SELECT * FROM book;";
    private static final String READ_BOOK_BY_ID="SELECT * FROM book WHERE book_id=:book_id;";
    private static final String READ_BOOK_BY_AUTHOR="SELECT * FROM book WHERE author=:author;";
    private static final String READ_BOOK_BY_GENRE="SELECT * FROM book WHERE genre=:genre;";
    private static final String READ_BOOK_TO_PAGE="SELECT * FROM book WHERE book_id>:book_id LIMIT 10;";

    private NamedParameterJdbcTemplate template;

    public MySqlBookDAO(){
        template=new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Book create(Book book) {
        Book resultBook=new Book(book);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        SqlParameterSource param=new BeanPropertySqlParameterSource(book);
        int rowAffected=template.update(CREATE,param,keyHolder);
        if(rowAffected>0){
            resultBook.setId((long)keyHolder.getKey());
        }
        return resultBook;
    }

    @Override
    public List<Book> readAllBooks() {
        List<Book> bookList=template.query(READ_ALL_BOOKS, new BookRowMapper());
        return bookList;
    }

    @Override
    public List<Book> readBooksByAuthor(String author) {
        SqlParameterSource parameterSource=new MapSqlParameterSource("author",author);
        List<Book> bookList=template.query(READ_BOOK_BY_AUTHOR,parameterSource,new BookRowMapper());
        return bookList;
    }

    @Override
    public List<Book> readBooksByGenre(String genre) {
        SqlParameterSource parameterSource=new MapSqlParameterSource("genre",genre);
        List<Book> bookList=template.query(READ_BOOK_BY_GENRE,parameterSource,new BookRowMapper());
        return bookList;
    }

    @Override
    public Book readBookById(long id) {
        Book book=null;
        SqlParameterSource param=new MapSqlParameterSource("book_id",id);
        List<Book> bookList=template.query(READ_BOOK_BY_ID,param, new BookRowMapper());
        if(bookList.get(0)!=null){
            book=bookList.get(0);
        }
        return book;
    }

    @Override
    public List<Book> readBooksToPage(int page) {
        SqlParameterSource param=new MapSqlParameterSource("book_id",page);
        List<Book> bookList=template.query(READ_BOOK_TO_PAGE,param,new BookRowMapper());
        return bookList;
    }

    private class BookRowMapper implements RowMapper<Book>{
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book=new Book();
            book.setId(resultSet.getLong("book_id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setGenre(resultSet.getString("genre"));
            book.setDescription(resultSet.getString("description"));
            book.setUrl(resultSet.getString("url"));
            book.setPrice(resultSet.getDouble("price"));
            book.setBdate(resultSet.getTimestamp("bdate"));
            return book;
        }
    }
}
