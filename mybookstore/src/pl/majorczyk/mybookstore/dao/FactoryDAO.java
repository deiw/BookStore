package pl.majorczyk.mybookstore.dao;

public abstract class FactoryDAO {
    public static final int MY_SQL=1;
    private static FactoryDAO factory;

    public abstract UserDAO getUserDAO();
    public abstract BookDAO getBookDAO();
    public abstract OrderDAO getOrderDAO();

    public static FactoryDAO getFactory(int type){
        if(factory==null){
            if(type==MY_SQL){
                factory=new MySqlFactoryDAO();
            }
        }
        return factory;
    }
}
