package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.SalesContract;

import javax.sql.DataSource;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
    }
}
