package com.solution.allcups.reportbuilder.controller;

import com.solution.allcups.reportbuilder.entity.Report;
import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import com.solution.allcups.reportbuilder.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping("/create-report")
    public ResponseEntity<?> createReport(@RequestBody Report report) throws NotAcceptableException {
        reportService.createReport(report);

        return new ResponseEntity<>(String.valueOf(HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping("/get-report-by-id/{id}")
    public ResponseEntity<?> getReportById(@PathVariable("id") Integer id) throws NotAcceptableException {
        return new ResponseEntity<>(reportService.getReportById(id), HttpStatus.CREATED);
    }
}
