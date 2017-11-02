package pl.majorczyk.mybookstore.listener;

import pl.majorczyk.mybookstore.util.ConnectionProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContexListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnectionProvider.getDataSource();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
