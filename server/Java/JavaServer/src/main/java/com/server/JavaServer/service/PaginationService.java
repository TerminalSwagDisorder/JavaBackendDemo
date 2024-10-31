package com.server.JavaServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getTotalPages(String tableName, int itemsPerPage) throws IllegalArgumentException {
        if (itemsPerPage <= 0) {
            throw new IllegalArgumentException("Number of items cannot be below 1");
        }

        String countQuery = "SELECT COUNT(*) FROM " + tableName;
        int totalItems = jdbcTemplate.queryForObject(countQuery, Integer.class);

        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }
}
