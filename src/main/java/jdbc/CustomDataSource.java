package jdbc;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Getter
@Setter
public class CustomDataSource implements DataSource {
    private static volatile CustomDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    private Connection connection;

    private CustomDataSource(String driver, String url, String password, String name) throws SQLException {
        this.driver = driver;
        this.url = url;
        this.name = name;
        this.password = password;
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Something is wrong with the DB connection String : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
    public Connection getConnection(String name, String password) {
        return connection;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public static CustomDataSource getInstance()  throws SQLException {
//        if (instance == null) {
//            instance = new CustomDataSource();
//        } else if (instance.getConnection().isClosed()) {
//            instance = new CustomDataSource();
//        }
        return instance;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
