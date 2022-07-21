package com.example.shopg2.api;

import com.example.shopg2.entity.QuantityInStock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class GainAPI {
    @GetMapping(path = "/admin/analytic/gainMonth")
    public ResponseEntity<QuantityInStock> getGainMonth () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
        Statement satement = connection.createStatement();
        String select = "SELECT sum(cost) AS gain FROM salebill s  WHERE billDate LIKE '"
                + LocalDateTime.now().toString().substring(0, 7) + "%'";
        satement.execute(select);
        ResultSet resultSet = satement.getResultSet();
        if (resultSet.next()) {
            QuantityInStock rs = new QuantityInStock();
            rs.setQuantity(resultSet.getInt("gain"));
            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/admin/analytic/gainYear")
    public ResponseEntity<QuantityInStock> getGainYear () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
        Statement satement = connection.createStatement();
        String select = "SELECT sum(cost) AS gain FROM salebill s  WHERE billDate LIKE '"
                + LocalDateTime.now().toString().substring(0, 5) + "%'";
        satement.execute(select);
        ResultSet resultSet = satement.getResultSet();
        if (resultSet.next()) {
            QuantityInStock rs = new QuantityInStock();
            rs.setQuantity(resultSet.getInt("gain"));
            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
