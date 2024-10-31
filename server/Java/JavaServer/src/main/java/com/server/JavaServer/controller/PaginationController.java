package com.server.JavaServer.controller;

import com.server.JavaServer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class PaginationController {

    @Autowired
    private PaginationService paginationService;

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getCount(
        @RequestParam String tableName,
        @RequestParam(defaultValue = "10") int items
    ) {
        try {
            int totalPages = paginationService.getTotalPages(tableName, items);

            Map<String, Object> response = new HashMap<>();
            response.put("index", totalPages);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "Internal Server Error", "details", e.getMessage()));
        }
    }
}
