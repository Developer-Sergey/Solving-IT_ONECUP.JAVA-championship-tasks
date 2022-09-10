package com.solution.allcups.checker.test;

import static com.solution.allcups.checker.util.Client.get;
import static com.solution.allcups.checker.util.Client.postObject;

import java.util.List;

import org.springframework.stereotype.Component;

import com.solution.allcups.checker.object.Report;
import com.solution.allcups.checker.object.ReportColumn;
import com.solution.allcups.checker.object.ReportTable;
import com.solution.allcups.checker.object.result.ReportResult;
import com.solution.allcups.checker.object.result.Result;

@Component
public class ReportTest {
    private final String reportURL = "/report";

    private final Result result201 = new Result(1, 201);
    private final Result result406 = new Result(2, 406);

    private final ReportResult reportResult201_1 = new ReportResult(3, 201, new Report("1", 2, List.of(
        new ReportTable("Artists", List.of(
            new ReportColumn("id", "int4", "0"), new ReportColumn("name", "varchar", "0"), 
            new ReportColumn("age", "int4", "0")
        )),
        new ReportTable("Car", List.of(
            new ReportColumn("CarId", "int4", "1"), new ReportColumn("name", "varchar", "1"), 
            new ReportColumn("EngineType", "varchar", "1"), new ReportColumn("number", "int8", "1")
        ))
    )));
    private final ReportResult reportResult201_2 = new ReportResult(4, 201, new Report("2", 1, List.of(
        new ReportTable("Customer", List.of(
            new ReportColumn("CustomerId", "int4", "0"), new ReportColumn("FirstName", "varchar", "0"), 
            new ReportColumn("LastName", "varchar", "0"), new ReportColumn("Company", "varchar", "0"),
            new ReportColumn("Address", "varchar", "0"), new ReportColumn("Phone", "varchar", "0")
        ))
    )));
    private final ReportResult reportResult406_1 = new ReportResult(5, 406, 
                                                                    new Report("406", 0, List.of()));
    private final ReportResult reportResult406_2 = new ReportResult(6, 406, 
                                                                    new Report("wRong", 0, List.of()));

    public void test() {
        addAllResults();

        Report report1 = new Report("1", 2, List.of(
            new ReportTable("Artists", List.of(
                new ReportColumn("id", "int4", "0"), new ReportColumn("name", "varchar", "0"), 
                new ReportColumn("age", "int4", "0")
            )),
            new ReportTable("Car", List.of(
                new ReportColumn("CarId", "int4", "0"), new ReportColumn("name", "varchar", "0"), 
                new ReportColumn("EngineType", "varchar", "0"), new ReportColumn("number", "int8", "0")
            ))
        ));

        Report report2 = new Report("2", 1, List.of(
            new ReportTable("Customer", List.of(
                new ReportColumn("CustomerId", "int4", "0"), new ReportColumn("FirstName", "varchar", "0"), 
                new ReportColumn("LastName", "varchar", "0"), new ReportColumn("Company", "varchar", "0"),
                new ReportColumn("Address", "varchar", "0"), new ReportColumn("Phone", "varchar", "0")
            ))
        ));

        Report wrongReport1 = new Report("3", 4, List.of(
            new ReportTable("Artists", List.of(
                new ReportColumn("id", "int4", "0"), new ReportColumn("name", "varchar", "0"), 
                new ReportColumn("age", "int4", "0")
            ))
        ));

        Report wrongReport2 = new Report("4", 1, List.of(
            new ReportTable("WRONG", List.of(
                new ReportColumn("id", "int4", "0"), new ReportColumn("name", "varchar", "0"), 
                new ReportColumn("age", "int4", "0")
            ))
        ));

        Report wrongReport3 = new Report("5", 1, List.of(
            new ReportTable("Artists", List.of(
                new ReportColumn("wrongColumn", "int4", "0"), new ReportColumn("name", "varchar", "0"), 
                new ReportColumn("age", "int4", "0")
            ))
        ));

        Report wrongReport4 = new Report("6", 1, List.of(
            new ReportTable("Artists", List.of(
                new ReportColumn("id", "wrongType", "0"), new ReportColumn("name", "varchar", "0"), 
                new ReportColumn("age", "int4", "0")
            ))
        ));

        Report wrongReport5 = new Report("wrong", 1, List.of(
            new ReportTable("Artists", List.of(
                new ReportColumn("id", "int4", "0"), new ReportColumn("name", "varchar", "0"), 
                new ReportColumn("age", "int4", "0")
            ))
        ));
        
        postObject(getCreateReportURL(result201.getResultId()), report1);
        postObject(getCreateReportURL(result201.getResultId()), report2);
        postObject(getCreateReportURL(result406.getResultId()), report1);
        postObject(getCreateReportURL(result406.getResultId()), wrongReport1);
        postObject(getCreateReportURL(result406.getResultId()), wrongReport2);
        postObject(getCreateReportURL(result406.getResultId()), wrongReport3);
        postObject(getCreateReportURL(result406.getResultId()), wrongReport4);
        postObject(getCreateReportURL(result406.getResultId()), wrongReport5);

        get(getGetByIdURL(report1.getReportId(), reportResult201_1.getResultId()));
        get(getGetByIdURL(report2.getReportId(), reportResult201_2.getResultId()));
        get(getGetByIdURL("406", reportResult406_1.getResultId()));
        get(getGetByIdURL("wRong", reportResult406_2.getResultId()));
    }

    private void addAllResults() {
        postObject(getAddCreateReportResultURL(), result201);
        postObject(getAddCreateReportResultURL(), result406);

        // postObject(getAddGetByIdResultURL(), reportResult201_1);
        // postObject(getAddGetByIdResultURL(), reportResult201_2);
        // postObject(getAddGetByIdResultURL(), reportResult406_1);
        // postObject(getAddGetByIdResultURL(), reportResult406_2);
    }

    public String getAddCreateReportResultURL() {
        return reportURL + "/add-create-report-result";
    }

    public String getCreateReportURL(Integer resultId) {
        return reportURL + "/create-report" + "?resultId=" + resultId;
    }

    public String getAddGetByIdResultURL() {
        return reportURL + "/add-get-report-by-id-result";
    }

    public String getGetByIdURL(String id, Integer resultId) {
        return reportURL + "/get-report-by-id/" + id + "?resultId=" + resultId;
    }
}
