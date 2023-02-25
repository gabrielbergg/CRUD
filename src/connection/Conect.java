package connection;

import java.sql.*;

public class Conect {
    
    public static String URL = "jdbc:mysql://localhost:3306/empresa";
    public static String USER = "root";
    public static String PWD = "root";


    private static Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;


    public static Connection getConnection() {

        if(conn == null) {
           try {
                conn = DriverManager.getConnection(URL, USER, PWD);
            }
            catch(SQLException e) {
                throw new DataException(e.getMessage());
            }
        }

        return conn;
    }


    public static void closeConnection() {

        if (conn != null) {
            try {
                conn.close();
            } 
            catch (Exception e) {
                throw new DataException(e.getMessage());
            }
        }
    }


    public static void closeStatement(Statement st) {
        if(st != null) {
            try {
                st.close();
            } 
            catch (Exception e) {
                throw new DataException(e.getMessage());
            }
        }
    }


    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } 
            catch (Exception e) {
                throw new DataException(e.getMessage());
            }
        }
    }

}

