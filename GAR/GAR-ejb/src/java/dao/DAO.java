/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Vasilisa
 */
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilisa
 */
public abstract class DAO<E> {
    public abstract List<E> getAll();
    public abstract void update(E object);
    public abstract void create(E object);
    public abstract void delete(int id);

    public static Connection connection_DataSource() {
        Connection Conn = null;
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("GAR_BD"); //если бд по скирту то не трогай!
        dataSource.setUser("root"); //логин
        dataSource.setPassword("1997"); //пароль на свой
        try {
            Conn = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Conn;
    }
   
    public static PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            Connection conn = connection_DataSource();
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
}
