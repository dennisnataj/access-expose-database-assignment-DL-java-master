package com.chinook.connection;

import com.dataaccess.models.Customer;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionManager {
    //connection url
    static final String URL = "jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite";

    static private ConnectionManager instance;
    private Connection connection;

    //1. read all customers in the database
    public ArrayList<Customer> selectAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try{
            connection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT CustomerId,FirstName,LastName," +
                    "Phone,Email,Country,PostalCode FROM customer");

            ResultSet  resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                customers.add(new Customer(
                        resultSet.getString("customerId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("country"),
                        resultSet.getString("postalCode")


                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    //2. Read specific customer from database by id
    public Customer selectSpecificCustomer(int customerId){
        Customer customer = null;

        try {
            //connection
            connection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT CustomerId,FirstName,LastName,Phone,Email,Country,PostalCode FROM customer WHERE CustomerId = ?");
            preparedStatement.setString(1, String.valueOf(customerId));
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                customer = new Customer(
                        resultSet.getString("customerId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("country"),
                        resultSet.getString("postalCode")


                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    //3. Read specific customer by name
    public Customer selectCustomerByName(String firstName){
        Customer customer = null;

        try{
            connection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE FirstName LIKE 'Leonie' ");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                customer = new Customer(
                        resultSet.getString("customerId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("country"),
                        resultSet.getString("postalCode")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    //4.
    public ArrayList<Customer> selectPageOfCustomers(){

        ArrayList<Customer> customers = new ArrayList<Customer>();

        try{
            connection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT CustomerId,FirstName,LastName," +
                    "Phone,Email,Country,PostalCode FROM customer LIMIT 10 OFFSET 50");

            ResultSet  resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                customers.add(new Customer(
                        resultSet.getString("customerId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("country"),
                        resultSet.getString("postalCode")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    //singleton pattern. There can only be one instance of this.
    public static ConnectionManager getInstance(){
        if(instance == null){
            instance = new ConnectionManager();

        }
        return instance;
    }

    //manage connection object
    public ConnectionManager(){
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            System.exit(-1);
        }
    }

    public Connection getConnection(){
        return connection;
    }

}