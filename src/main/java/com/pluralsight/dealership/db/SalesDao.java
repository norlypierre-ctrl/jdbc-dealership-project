package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private final DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO sales_contracts (vin, sale_date, price) " +
                             "VALUES (?, ?, ?)")) {

            ps.setString(1, salesContract.getVin());
            ps.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            ps.setDouble(3, salesContract.getPrice());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding Sales Contract for VIN: "
                    + salesContract.getVin(), e);
        }
    }
}