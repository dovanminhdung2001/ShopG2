package com.example.shopg2.api;

import com.example.shopg2.entity.QuantityInStock;
import com.example.shopg2.entity.ShoeInformation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@CrossOrigin
public class ShoeInformationAPI {
    @GetMapping(value = "/product/api/{shoeId}")
    public ResponseEntity<ShoeInformation> productGetShoeInformation (@PathVariable("shoeId") String shoeId) {
        try {
            Connection connection = DriverManager.getConnection( "jdbc:mariadb://localhost:3306/shopG2", "root", "1" );

            ShoeInformation shoeInformation = createShoeInformation(connection, shoeId);

            return new ResponseEntity<>(shoeInformation, HttpStatus.OK);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private ShoeInformation createShoeInformation(Connection connection, String shoeId) throws SQLException {
        String select = "SELECT s.*, m.manufactName " +
                "FROM shoeInformation s JOIN manufact m ON s.manufactId = m.manufactId  " +
                "WHERE shoeId = '" + shoeId + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        ShoeInformation shoeInformation = new ShoeInformation();

        statement.execute(select);
        resultSet = statement.getResultSet();
        while (resultSet.next()) {
            shoeInformation = new ShoeInformation(
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
            );
        }
        return shoeInformation;
    }

    @GetMapping(value = "/product/api/{shoeId}/{size}")
    public ResponseEntity<QuantityInStock> productGetQuantityInStock (
            @PathVariable("shoeId") String shoeId,
            @PathVariable("size") int size
    ){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/shopG2",
                    "root", "1"
            );
            return new ResponseEntity<>(createQuantityInStock(connection, shoeId, size), HttpStatus.OK) ;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private QuantityInStock createQuantityInStock(Connection connection, String shoeId, int size) throws SQLException {
        QuantityInStock quantityInStock = new QuantityInStock();
        String select = String.format("SELECT *  FROM  quantityInStock   WHERE shoeId = '%s' AND shoeSize = %d", shoeId, size);
        Statement statement = connection.createStatement();
        ResultSet resultSet ;

        statement.execute(select);
        resultSet = statement.getResultSet();
        while (resultSet.next()) {
            quantityInStock.setShoeId(resultSet.getString("shoeId"));
            quantityInStock.setShoeSize(resultSet.getInt("shoeSize"));
            quantityInStock.setQuantity(resultSet.getInt("quantity"));
        }
        return quantityInStock;
    }

}
