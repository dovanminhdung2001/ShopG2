package com.example.shopg2.api;

import com.example.shopg2.entity.PurchaseBill;
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
public class ManagePurchaseBillAPI {
    @GetMapping(value = "/managePurchaseBill/api")
    public ResponseEntity<List<PurchaseBill>> s(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
            List<PurchaseBill> list = new ArrayList<>();
            String select = "Select * from purchaseBill";
            Statement  statement = connection.createStatement();
            ResultSet resultSet;

            statement.execute(select);
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                list.add(new PurchaseBill(
                        resultSet.getInt("billId"),
                        resultSet.getString("shoeId"),
                        resultSet.getInt("shoeSize"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("cost"),
                        resultSet.getTimestamp("billDate").toLocalDateTime()
                ));
            }
            connection.close();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
