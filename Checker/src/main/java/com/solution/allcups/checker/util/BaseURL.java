package com.solution.allcups.checker.util;

public class BaseURL {
    private String baseURL = System.getenv("rs.endpoint");

    public String getBaseURL() {
        return baseURL;
    }
}
