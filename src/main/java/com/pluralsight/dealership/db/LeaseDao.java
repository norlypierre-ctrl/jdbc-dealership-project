package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class LeaseDao {
    private final DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO lease_contracts " +
                             "(vin, lease_start, lease_end, monthly_payment) " +
                             "VALUES (?, ?, ?, ?)")) {

            ps.setString(1, leaseContract.getVin());
            ps.setDate(2, Date.valueOf(leaseContract.getLeaseStart()));
            ps.setDate(3, Date.valueOf(leaseContract.getLeaseEnd()));
            ps.setDouble(4, leaseContract.getMonthlyPayment());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error adding Lease Contract for VIN: " + leaseContract.getVin(), e
            );
        }
    }
}
