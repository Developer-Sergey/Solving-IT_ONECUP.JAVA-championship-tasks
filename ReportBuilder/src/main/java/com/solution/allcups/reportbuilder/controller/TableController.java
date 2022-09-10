package com.solution.allcups.reportbuilder.controller;

import com.solution.allcups.reportbuilder.entity.Table;
import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import com.solution.allcups.reportbuilder.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/get-table-by-name/{name}")
    public ResponseEntity<?> getTableByName(@PathVariable("name") String name) throws NotAcceptableException {
        return ResponseEntity.ok(tableService.getTableByName(name));
    }

    @PostMapping("/create-table")
    public ResponseEntity<?> createTable(@RequestBody Table table) throws NotAcceptableException {
        tableService.createTable(table);

        return new ResponseEntity<>(String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @DeleteMapping("/drop-table/{name}")
    public ResponseEntity<?> dropTableByName(@PathVariable("name") String name) throws NotAcceptableException {
        tableService.dropTableByName(name);

        return new ResponseEntity<>(String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }
}
