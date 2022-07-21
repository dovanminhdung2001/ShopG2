package com.example.shopg2.api;

import com.example.shopg2.entity.SaleBill;
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
public class ManageSaleBillAPI {
    @GetMapping(value = "/manageSaleBill/api")
    public ResponseEntity<List<SaleBill>> ppp () {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
            List<SaleBill> list = new ArrayList<>();
            String select = "SELECT * FROM saleBill";
            Statement statement = connection.createStatement();
            ResultSet resultSet ;

            statement.execute(select);
            resultSet = statement.getResultSet()  ;
            while (resultSet.next()) {
                list.add(new SaleBill(
                        resultSet.getInt("billId"),
                        resultSet.getString("shoeId"),
                        resultSet.getInt("shoeSize"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("cost"),
                        resultSet.getTimestamp("billDate").toLocalDateTime() ,
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("customerName"),
                        resultSet.getString("email"),
                        resultSet.getString("address")
                ));
            }
            connection.close();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
