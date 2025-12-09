package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO vehicles " +
                             "(VIN, make, model, year, SOLD, color, " +
                             "vehicleType, odometer, price) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, vehicle.getVin());
            ps.setString(2, vehicle.getMake());
            ps.setString(3, vehicle.getModel());
            ps.setInt(4, vehicle.getYear());
            ps.setBoolean(5, vehicle.isSold());
            ps.setString(6, vehicle.getColor());
            ps.setString(7, vehicle.getVehicleType());
            ps.setInt(8, vehicle.getOdometer());
            ps.setDouble(9, vehicle.getPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An SQLException Occurred: " + e.getMessage());
        }
    }

    public void removeVehicle(String VIN) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM vehicles WHERE VIN = ?")) {

            ps.setString(1, VIN);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An SQLException Occurred: " + e.getMessage());
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?")) {

            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("An SQLException Occurred: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * " + "FROM vehicles WHERE make = ? AND model = ?")) {

            ps.setString(1, make);
            ps.setString(2, model);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("An SQLException Occurred: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?")) {

            ps.setInt(1, minYear);
            ps.setInt(2, maxYear);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("An SQLException Occurred: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM vehicles WHERE color = ?")) {

            ps.setString(1, color);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("An SQLException Occurred: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?")) {

            ps.setInt(1, minMileage);
            ps.setInt(2, maxMileage);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("An SQLException Occurred: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM vehicles WHERE vehicleType = ?")) {

            ps.setString(1, type);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("An SQLException Occurred: " + e.getMessage());
        }

        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}