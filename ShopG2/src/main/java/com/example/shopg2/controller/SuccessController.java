package com.example.shopg2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.time.LocalDateTime;

@Controller
public class SuccessController {
    @PostMapping(path = "/admin/addShoeInformationSuccess")
    public  String addShoe2 (
            @RequestParam(name = "id", defaultValue = "AR0456") String shoeId,
            @RequestParam(name = "name") String shoeName,
            @RequestParam(name = "price") int price,
            @RequestParam(name = "manufact") String manufactId,
            @RequestParam(name = "url1") String url1,
            @RequestParam(name = "url2") String url2,
            @RequestParam(name = "url3") String url3,
            @RequestParam(name = "url4") String url4,
            @RequestParam(name = "url5") String url5,
            @RequestParam(name = "information") String information
    ) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
        Statement statement = connection.createStatement();
        String select = "select shoeId from shoeInformation where shoeId = \'"+shoeId + "\'";
        statement.execute(select);
        ResultSet resultSet = statement.getResultSet();
        if (!resultSet.next()){
            select = String.format("INSERT INTO shoeinformation (shoeId, shoeName, price, manufactId, information, url1, url2, url3, url4, url5) VALUES " +
                            "\n(\n'%s',  '%s',  %d,   '%s', \n'%s', \n'%s', \n'%s', \n'%s', \n'%s', \n'%s'\n)",
                    shoeId, shoeName, price, manufactId.toLowerCase().substring(0, 3), information, url1, url2, url3, url4, url5
            );
            statement.execute(select);
        }
        return "admin/ok";
    }

    @GetMapping(path = "/admin/addShoeInformationSuccess")
    public String admin ()  { return "admin/ok"; }

    @PostMapping(path = "/admin/changeShoeInformationSuccess")
    public String changeShoe2 (
            @RequestParam(name = "id", defaultValue = "AR0456") String shoeId,
            @RequestParam(name = "name") String shoeName,
            @RequestParam(name = "price") int price,
            @RequestParam(name = "manufact") String manufactId,
            @RequestParam(name = "url1") String url1,
            @RequestParam(name = "url2") String url2,
            @RequestParam(name = "url3") String url3,
            @RequestParam(name = "url4") String url4,
            @RequestParam(name = "url5") String url5,
            @RequestParam(name = "information") String information
    ) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
        Statement statement = connection.createStatement();
        String select = "select shoeId from shoeInformation where shoeId = \'"+shoeId + "\'";
        statement.execute(select);
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()){
            select = String.format(
                    "UPDATE shoeinformation SET \n" +
                            "shoeName = '%s', \n" +
                            "price = %d, \n" +
                            "manufactId = '%s', \n" +
                            "information = '%s', \n" +
                            "url1 = '%s', \n" +
                            "url2 = '%s', \n" +
                            "url3 = '%s', \n" +
                            "url4 = '%s', \n" +
                            "url5 = '%s' \n" +
                            "where shoeId = '%s'",
                    shoeName, price, manufactId.toLowerCase().substring(0, 3),
                    information, url1, url2, url3, url4, url5, shoeId
            );
            statement.execute(select);
        }
        return "admin/ok";
    }

    @GetMapping(path = "/admin/changeShoeInformationSuccess")
    public String changeSI () { return "admin/ok"; }

    @PostMapping("/admin/manageStock/stockInSuccess")
    public String stocInPost (
            @RequestParam(name = "shoeId", defaultValue = "AR0456") String shoeId,
            @RequestParam(name = "shoeSize", defaultValue = "42") int shoeSize,
            @RequestParam(name = "quantity", defaultValue = "0") int quantity
    )throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
        Statement statement = connection.createStatement();
        String select = "select * from quantityInStock where shoeId = '" + shoeId+ "'and shoeSize = "+ shoeSize;
        statement.execute(select);
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            int oldQuantity  = resultSet.getInt("quantity");
            select = "update quantityInStock set quantity =" + (quantity + oldQuantity)
                    + " where shoeId = '" + shoeId + "' and shoeSize = " + shoeSize;
            statement.execute(select);
        } else {
            select = "insert into quantityInStock values ('" + shoeId  +"', " + shoeSize +", " +quantity + ")";
            statement.execute(select);
        }
        select = "select price FROM shoeinformation WHERE  shoeId = '"+  shoeId  +"'";
        statement.execute(select);
        resultSet = statement.getResultSet();
        int price = 0;
        if (resultSet.next() ) {
            price =  resultSet.getInt("price");
        }
        select = String.format("INSERT INTO purchasebill (shoeId, shoeSize, quantity, cost, billDate) VALUES" +
                "('%s', %d, %d, %d, '%s')",
                shoeId, shoeSize, quantity, quantity*price, LocalDateTime.now().toString().substring(0, 19).replace('T', ' ')
        );
        statement.execute(select);
        return "admin/ok";
    }

    @RequestMapping("/admin/manageStock/stockInSuccess")
    public String stockInSuccess () { return "admin/ok"; }

}
