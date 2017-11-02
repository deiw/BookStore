package pl.majorczyk.mybookstore.dao;

public class MySqlFactoryDAO extends FactoryDAO {
    @Override
    public UserDAO getUserDAO() {
        return new MysqlUserDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new MySqlBookDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MySqlOrderDAO();
    }
}
