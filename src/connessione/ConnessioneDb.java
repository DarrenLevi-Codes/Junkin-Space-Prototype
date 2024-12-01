package connessione;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnessioneDb {

    // Parametri di configurazione del DB
    private static final String NOME_DB = "junkin_space"; // nome database
    private static final int PORT = 3306; // porta
    private static final String SERVER_NAME = "localhost"; // indirizzo server MySqlWorkbench
    private static final String USERNAME = "root"; // username
    private static final String PASSWORD = "XXXXXXX"; // password

    // Metodo per ottenere una nuova connessione al database
    public static Connection getCon() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(NOME_DB);
        dataSource.setPortNumber(PORT);
        dataSource.setServerName(SERVER_NAME);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource.getConnection();
    }
}
