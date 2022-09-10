package com.solution.allcups.reportbuilder.controller;

import com.solution.allcups.reportbuilder.entity.SingleQuery;
import com.solution.allcups.reportbuilder.exception.InternalServerErrorException;
import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import com.solution.allcups.reportbuilder.service.SingleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/single-query")
public class SingleQueryController {
    @Autowired
    private SingleQueryService singleQueryService;

    @GetMapping("/get-single-query-by-id/{id}")
    public ResponseEntity<?> getQueryById(@PathVariable("id") Integer id) throws InternalServerErrorException {
        return ResponseEntity.ok(singleQueryService.getQueryById(id));
    }

    @GetMapping("/get-all-single-queries")
    public ResponseEntity<?> getAllQueries() {
        return ResponseEntity.ok(singleQueryService.getAllQueries());
    }

    @GetMapping("/execute-single-query-by-id/{id}")
    public ResponseEntity<?> executeQueryById(@PathVariable("id") Integer id) throws NotAcceptableException {
        singleQueryService.executeQueryById(id);

        return new ResponseEntity<>(String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @PostMapping("/add-new-query")
    public ResponseEntity<?> addNewQuery(@RequestBody SingleQuery query) throws NotAcceptableException {
        singleQueryService.addNewQuery(query);

        return new ResponseEntity<>(String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @PutMapping("/modify-single-query")
    public ResponseEntity<?> modifyQuery(@RequestBody SingleQuery query) throws NotAcceptableException {
        singleQueryService.modifyQuery(query);

        return new ResponseEntity<>(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK);
    }

    @DeleteMapping("/delete-single-query-by-id/{id}")
    public ResponseEntity<?> deleteQueryById(@PathVariable("id") Integer id) throws NotAcceptableException {
        singleQueryService.deleteQueryById(id);

        return new ResponseEntity<>(String.valueOf(HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
    }
}
