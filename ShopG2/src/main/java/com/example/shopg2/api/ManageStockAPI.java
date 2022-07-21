package com.example.shopg2.api;

import com.example.shopg2.entity.QuantityInStock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ManageStockAPI {
    @GetMapping("/manageStock/api")
    public ResponseEntity<List<QuantityInStock>> mSA () {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
            List<QuantityInStock> list = new ArrayList<>();
            String select = "SELECT * FROM quantityInStock";
            Statement statement = connection.createStatement();
            ResultSet resultSet ;

            statement.execute(select);
            resultSet = statement.getResultSet()  ;
            while (resultSet.next()) {
                list.add(new QuantityInStock(
                        resultSet.getString("shoeId"),
                        resultSet.getInt("shoeSIze"),
                        resultSet.getInt("quantity")
                ));
            }
            connection.close();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/manageStock/api/{shoeId}/{shoeSize}")
    public  ResponseEntity<QuantityInStock> sss (
            @PathVariable(name = "shoeId") String shoeId,
            @PathVariable(name = "shoeSize") int shoeSize
    ) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopG2", "root", "1");
        String select = "SELECT * FROM quantityInStock where shoeId = '" + shoeId + "' and shoeSize = " + shoeSize;
        Statement statement = connection.createStatement();
        statement.execute(select);
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            return  new ResponseEntity<>(new QuantityInStock( shoeId, shoeSize, resultSet.getInt("quantity")),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(new QuantityInStock(null, 0 , -1), HttpStatus.OK);
        }
    }
}
