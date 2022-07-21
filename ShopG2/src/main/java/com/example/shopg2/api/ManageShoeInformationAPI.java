package com.example.shopg2.api;

import com.example.shopg2.entity.ShoeInformation;
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
public class ManageShoeInformationAPI {
    @GetMapping(value = "/manageShoeInformation/api")
    public ResponseEntity<List<ShoeInformation>>  getAllShoeInformation () {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/shopG2",
                    "root", "1"
            );

            List<ShoeInformation> shoeInformationList = new ArrayList<>();
            String select = "SELECT  m.manufactName, s.* FROM shoeinformation s JOIN manufact m ON s.manufactId = m.manufactId ";
            Statement statement = connection.createStatement();
            ResultSet resultSet ;

            statement.execute(select);
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                shoeInformationList.add(new ShoeInformation(
                        resultSet.getString("shoeId"),
                        resultSet.getString("shoeName"),
                        resultSet.getInt("price"),
                        resultSet.getString("manufactName"),
                        resultSet.getString("information"),
                        resultSet.getString("url1"),
                        resultSet.getString("url2"),
                        resultSet.getString("url3"),
                        resultSet.getString("url4"),
                        resultSet.getString("url5")
                ));
            }
            connection.close();
            return new ResponseEntity<>(shoeInformationList, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
