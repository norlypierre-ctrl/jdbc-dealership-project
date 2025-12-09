package com.pluralsight.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private final DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO inventory (vin, dealership_id) VALUES (?, ?)")) {

            ps.setString(1, vin);
            ps.setInt(2, dealershipId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error adding Vehicle " + vin + " to Inventory for Dealership " + dealershipId, e
            );
        }
    }

    public void removeVehicleFromInventory(String vin) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM inventory WHERE vin = ?")) {

            ps.setString(1, vin);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error removing Vehicle " + vin + " from Inventory", e
            );
        }
    }
}