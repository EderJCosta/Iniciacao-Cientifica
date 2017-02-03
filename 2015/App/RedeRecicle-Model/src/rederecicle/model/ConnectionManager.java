package rederecicle.model;

import java.sql.Connection;
import org.postgresql.ds.PGPoolingDataSource;

public class ConnectionManager {

    private PGPoolingDataSource datasource;
    private static ConnectionManager instance;

    public Connection getConnection() throws Exception {
        Connection conn = datasource.getConnection();
        conn.setAutoCommit(false);
        return conn;
    }

    private ConnectionManager() {
        datasource = new PGPoolingDataSource();
        datasource.setDataSourceName("RedeRecicle");
        datasource.setServerName("localhost");
        datasource.setDatabaseName("RedeRecicle");
        datasource.setUser("postgres");
        datasource.setPassword("postgres");
        datasource.setMaxConnections(30);
        datasource.setInitialConnections(10);

    }
    public static ConnectionManager getInstance(){
        if(instance == null){
            instance = new ConnectionManager();
        }
    return instance;
    }
}
