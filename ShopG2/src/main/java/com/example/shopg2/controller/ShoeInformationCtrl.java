package com.example.shopg2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;

@Controller
public class ShoeInformationCtrl {
    @GetMapping(value = "/product/{shoeId}")
    public String renderShoeInformation(@PathVariable String shoeId){
        return "product";
    }
//
//    @GetMapping(value = "/insert")
//    void  insert() throws SQLException {
//        System.out.println("INSERT INTO purchasebill (shoeId, shoeSize, quantity, cost, billDate) VALUES");
//        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
//        String string = "SELECT shoeId , price  FROM shoeinformation";
//        Statement statement = connection.createStatement();
//        ResultSet resultSet ;
//
//        statement.execute(string);
//        resultSet = statement.getResultSet();
//        while (resultSet.next()) {
//            for (int size = 31; size <= 42 ; size++) {
//                System.out.printf("(\'%s\', %d, 100, %d, \'2022-04-01 08:00:00\'), ",
//                        resultSet.getString("shoeId"),
//                        size,
//                        resultSet.getInt("price")*100
//                );
//            }
//            System.out.println();
//        }
//
//        connection.close();
//    }

    @PostMapping(value = "/")
    public void addSuccess() {

    }
}
