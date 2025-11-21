package br.ufrn.tads.repository.imp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static String senha;
    private static String user;
    private static final String URL_DB = "jdbc:postgresql://localhost:5432/Enem_crud";


    public DbConnection(){
        try {
            Properties props = new Properties();    
            FileInputStream fis = new FileInputStream("varAmbiente/config.properties");
            props.load(fis);

            // System.out.println(fis);

            this.senha = props.getProperty("db.password");
            this.user = props.getProperty("db.user");

            fis.close();            
        } catch (IOException e) {
            System.out.println("File Not Founded");
        }
        
    }

    public static Connection getConnection(){
                Connection conn = null;
                try{
                    conn = DriverManager.getConnection(DbConnection.URL_DB, DbConnection.user, DbConnection.senha);
                    if(conn != null){
                        System.out.println("Connection Sucess");
                    }
                } catch(SQLException e){
                    System.out.println("Error: "+e);
                } catch(Exception e){
                    System.out.println("Error "+ e);
                }
                return conn;
            }
            
    
}
