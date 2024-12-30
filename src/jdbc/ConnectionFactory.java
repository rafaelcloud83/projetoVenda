package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ConnectionFactory {  
    private static Connection con = null;
    private static final String URL = "jdbc:sqlite:vendas.db";
    
    private ConnectionFactory(){}
    
    public static Connection getConnection() {
        if (con == null) {
            synchronized (ConnectionFactory.class) {
                if (con == null) {
                    try {
                        con = DriverManager.getConnection(URL);
                    } catch (SQLException e) {
                        System.err.println("Erro ao conectar no banco de dados: " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return con;
    } 
}
