package com.solution.allcups.checker.service;

import com.solution.allcups.checker.test.ReportTest;
import com.solution.allcups.checker.test.SingleQueryTest;
import com.solution.allcups.checker.test.TableQueryTest;
import com.solution.allcups.checker.test.TableTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartService {
    @Autowired
    private TableTest tableTest;

    @Autowired
    private TableQueryTest tableQueryTest;

    @Autowired
    private SingleQueryTest singleQueryTest;

    @Autowired
    private ReportTest reportTest;

    public void start() {
        tableTest.test();

        singleQueryTest.test();

        tableQueryTest.test();

        tableTest.testWithTableQuery();

        tableTest.addMoreTableToTestReports();

        reportTest.test();
    }
}
