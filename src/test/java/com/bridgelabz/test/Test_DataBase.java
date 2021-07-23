package com.bridgelabz.test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test_DataBase {
    static Connection connection;
    static Statement statement;
    static String sqlQuery;
    public static String DB_URL = "jdbc:mysql://localhost/person_data";
    public static String DB_USERNAME = "root";
    public static String DB_PASSWORD = "root";

    @BeforeTest
    public void setUp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void data_to_be_insert() {
        try {
            sqlQuery = "insert into data_table (firstname, lastname, id) " +
                    "values('Rushi', 'aher', '11'), ('Shreya', 'khaire', '10')";

            int noOfRowsAffected = statement.executeUpdate(sqlQuery);
            Assert.assertEquals(noOfRowsAffected, 2);
            System.out.println("Data is inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void data_to_be_update() {
        try {
            sqlQuery = "update data_table set lastname = 'singh' where firstname = 'Rushi';";

            int noOfRowsAffected = statement.executeUpdate(sqlQuery);
            Assert.assertEquals(noOfRowsAffected, 1);
            System.out.println("Data is updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void data_to_be_retrieved() {
        try {
            List<Object[]> retrievedData = new ArrayList<>();
            sqlQuery = "select * from data_table";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String firstname = resultSet.getString(1);
                String lastname = resultSet.getString(2);
                int id = resultSet.getInt(3);

                System.out.println(" "+firstname+" " +" "+lastname+" "+id+" ");

                Object[] set = new Object[]{firstname, lastname, id};
                retrievedData.add(set);
            }
            Assert.assertEquals(retrievedData.size(), 4);
            System.out.println("Data is retrieved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void data_to_be_delete() {
        try {
            sqlQuery = "delete from data_table where firstname = 'Shreya'";

            int noOfRowsAffected = statement.executeUpdate(sqlQuery);
            Assert.assertEquals(noOfRowsAffected, 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @AfterTest
    public void tearDown() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
