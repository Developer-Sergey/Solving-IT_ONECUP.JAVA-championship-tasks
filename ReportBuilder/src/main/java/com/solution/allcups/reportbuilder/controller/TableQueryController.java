package com.solution.allcups.reportbuilder.controller;

import com.solution.allcups.reportbuilder.entity.TableQuery;
import com.solution.allcups.reportbuilder.exception.InternalServerErrorException;
import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import com.solution.allcups.reportbuilder.service.TableQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/table-query")
public class TableQueryController {
    @Autowired
    private TableQueryService tableQueryService;

    @GetMapping("/get-table-query-by-id/{id}")
    public ResponseEntity<?> getQueryById(@PathVariable("id") Integer id) throws InternalServerErrorException {
        return ResponseEntity.ok(tableQueryService.getQueryById(id));
    }

    @GetMapping("/get-all-table-queries")
    public ResponseEntity<?> getAllQueries() {
        return ResponseEntity.ok(tableQueryService.getAllQueries());
    }

    @GetMapping("/get-all-queries-by-table-name/{name}")
    public ResponseEntity<?> getAllQueriesByTableName(@PathVariable("name") String tableName) throws NotAcceptableException {
        return ResponseEntity.ok(tableQueryService.getAllQueriesByTableName(tableName));
    }

    @GetMapping("/execute-table-query-by-id/{id}")
    public ResponseEntity<?> executeQueryById(@PathVariable("id") Integer id) throws NotAcceptableException {
        tableQueryService.executeQueryById(id);

        return new ResponseEntity<>(String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @PostMapping("/add-new-query-to-table")
    public ResponseEntity<?> addNewQueryToTable(@RequestBody TableQuery query) throws NotAcceptableException {
        tableQueryService.addNewQueryToTable(query);

        return new ResponseEntity<>(String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @PutMapping("/modify-query-in-table")
    public ResponseEntity<?> modifyQueryInTable(@RequestBody TableQuery query) throws NotAcceptableException {
        tableQueryService.modifyQueryInTable(query);

        return new ResponseEntity<>(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK);
    }

    @DeleteMapping("/delete-table-query-by-id/{id}")
    public ResponseEntity<?> deleteQueryById(@PathVariable("id") Integer id) throws NotAcceptableException {
        tableQueryService.deleteQueryById(id);

        return new ResponseEntity<>(String.valueOf(HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
    }
}
