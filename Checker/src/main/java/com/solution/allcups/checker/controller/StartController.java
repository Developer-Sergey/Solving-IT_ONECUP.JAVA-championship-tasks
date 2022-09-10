package com.solution.allcups.checker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solution.allcups.checker.service.StartService;

@RestController
@RequestMapping("/api")
public class StartController {
    @Autowired
    private StartService startService;

    @GetMapping("/start")
    public void start() {
        startService.start();
    }
}
