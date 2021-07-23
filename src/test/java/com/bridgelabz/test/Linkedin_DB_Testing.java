package com.bridgelabz.test;

import com.bridgelabz.database.base.BaseClass;
import com.bridgelabz.database.pages.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;

public class Linkedin_DB_Testing extends BaseClass {

    public Connection connection;
    public static int rowCount;
    public int noOfRowsAffected;

    @Test(priority = 1)
    public void getTableData() throws SQLException {

        connection = this.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from login_Details");
        System.out.println("======== Table Data =======");
        System.out.println(" username " + " password ");
        System.out.println("=================================");
        while (resultSet.next()) {
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            System.out.println( username + " " + password );
            rowCount++;
            System.out.println("================================");
        }
    }

    @Test(priority = 2)
    public void insert_RecordTest() throws SQLException {

        connection = this.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into login_Details (username,password)values(?,?)");

        preparedStatement.setString(1, "lina@gmail.com");
        preparedStatement.setString(2, "pass@123");
        preparedStatement.executeUpdate();

        noOfRowsAffected = preparedStatement.executeUpdate();
      //assertion the query is executed or not
        Assert.assertEquals(noOfRowsAffected, 1);
        getTableData();
    }

    @Test(priority = 3)
    public void update_record_whereProvidedCondition() {

        connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update login_Details set username=? where password=?");
            preparedStatement.setString(1, "lina");
            preparedStatement.setString(2, "Pass");

            noOfRowsAffected = preparedStatement.executeUpdate();
//assertion the query is executed or not
            Assert.assertEquals(noOfRowsAffected, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void delete_row_from_employeeTable() throws SQLException {

        connection = this.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from login_Details where username=?");
        preparedStatement.setString(1, "lina");
        preparedStatement.executeUpdate();
    }

    @Test(priority = 5)
    public void login_to_application_using_DB_data() throws InterruptedException, SQLException, IOException {

        setUpBrowserLaunching();
        ResultSet resultSet;
        String username;
        String password;
        connection = this.getConnection();
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from login_Details LIMIT 1");
        while (resultSet.next()) {
            username = resultSet.getString(1);
            password = resultSet.getString(2);
            Login login = new Login(driver);
            login.loginTo_Application(username,password);
         //gmail and password from database employee table, store in string variable
            String expectedEmail = "ankitakhairnar10@gmail.com";
            Assert.assertEquals(username, expectedEmail);
            driver.close();
        }
    }
}
