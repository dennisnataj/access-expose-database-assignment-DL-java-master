package com.example.accessexposedatabaseassignmentdl;

import com.chinook.connection.ConnectionManager;
import com.dataaccess.models.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.util.ArrayList;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Connection conn = ConnectionManager.getInstance().getConnection();

        ConnectionManager connectionManager = new ConnectionManager();

        ArrayList<Customer> customers = connectionManager.selectAllCustomers();

        ArrayList<Customer> customersPage = connectionManager.selectPageOfCustomers();

        Customer customer = connectionManager.selectSpecificCustomer(1);
        Customer customerName = connectionManager.selectCustomerByName("");


        printCustomers(customers);
        printCustomer(customer);
        printCustomerName(customerName);
        printCustomerPage(customersPage);
    }

    private static void printCustomerPage(ArrayList<Customer> customersPage) {
        if(customersPage.size() != 0) {
            for(Customer c : customersPage) {
                System.out.println("--------------PRINTCUSTOMERPAGE-----------------");
                System.out.println(c.getCustomerId());
                System.out.println(c.getFirstName());
                System.out.println(c.getLastName());
                System.out.println(c.getPhone());
                System.out.println(c.getEmail());
            }
        } else {
            System.out.println("No customers found!");
        }
    }

    public static void printCustomers(ArrayList<Customer> customers) {
        if(customers.size() != 0) {
            for(Customer c : customers) {
                System.out.println(c.getCustomerId());
                System.out.println(c.getFirstName());
                System.out.println(c.getLastName());
                System.out.println(c.getPhone());
                System.out.println(c.getEmail());
            }
        } else {
            System.out.println("No customers found!");
        }
    }

    public static void printCustomer(Customer customer){
        if(customer != null){
            System.out.println(customer.getCustomerId());
            System.out.println(customer.getFirstName());
            System.out.println(customer.getLastName());
            System.out.println(customer.getPhone());
            System.out.println(customer.getEmail());
        }else{
            System.out.println("No customer Id found!");

        }
    }

    public static void printCustomerName(Customer customerName){
        if(customerName != null){
            System.out.println(customerName.getFirstName());
        }else{
            System.out.println("No customer Id found!");
        }


    }

}
