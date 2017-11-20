/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pepe
 */
public class PhoneBookDB {

    Connection con;
    private static final String JDBC_URL = "jdbc:derby:database;create=true";

    public PhoneBookDB() throws SQLException {
        this.con = DriverManager.getConnection(JDBC_URL);

        if (con != null) {
            System.out.println("connected to database");
        }

    }

    public void createTable() {
        String query = "CREATE TABLE PhoneBookTable (id int, name varchar(30),number varchar(30))";
        try {
            con.createStatement().execute(query);
        } catch (SQLException exc) {
            System.out.println(exc);
        }
    }

    public void addToTable(String name, String number) {
        int iD = 1;
        String query = "INSERT INTO PhoneBookTable values(" + iD + ", '" + name + "','" + number + "')";

        try {
            con.createStatement().execute(query);
        } catch (SQLException exc) {
            System.out.println("des" + exc);
        }
    }

    public void printTable() {
        String query = "SELECT * FROM PhoneBookTable";
        try {
            ResultSet result = this.con.createStatement().executeQuery(query);
            while (result.next()) {
                    int iD = result.getInt("id");
                    String name= result.getString("name");
                    String number = result.getString("number");
                    System.out.printf("%d %s %s %n",iD,name,number);
            }
        } catch (SQLException exc) {
            System.out.println("des" + exc);
        }
    }
    public ResultSet getEntries(){
         String query = "SELECT * FROM PhoneBookTable";
          ResultSet result=null;
        try {
             result = this.con.createStatement().executeQuery(query);
            
        } catch (SQLException exc) {
            System.out.println("des" + exc);
        }
        return result;
    }

}
