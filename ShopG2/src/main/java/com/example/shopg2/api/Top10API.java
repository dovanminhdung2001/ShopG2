package com.example.shopg2.api;

import com.example.shopg2.entity.QuantityInStock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class Top10API {
    @GetMapping(value = "/admin/analytic/top10Shoe/API")
    public ResponseEntity<List<QuantityInStock>> top10Shoe () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
        List<QuantityInStock>  list = new ArrayList<>();
        String select = "SELECT shoeId , sum(quantity) AS 'quantity'  FROM salebill GROUP BY shoeId ORDER BY quantity desc LIMIT 10 ";
        Statement statement = connection.createStatement();
        statement.execute(select);
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            list.add(new QuantityInStock(resultSet.getString("shoeId"), 0, resultSet.getInt("quantity")));
        }
        connection.close();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/analytic/top10Size/API")
    public ResponseEntity<List<QuantityInStock>> top10Size () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
        List<QuantityInStock>  list = new ArrayList<>();
        String select = "select shoeSize , sum(quantity) AS 'quantity'  FROM salebill  GROUP BY shoeSize  ORDER BY quantity DESC  LIMIT 10";
        Statement statement = connection.createStatement();
        statement.execute(select);
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            list.add(new QuantityInStock("null", resultSet.getInt("shoeSize"), resultSet.getInt("quantity")));
        }
        connection.close();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
