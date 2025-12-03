package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.LeaseContract;

import javax.sql.DataSource;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
    }
}
