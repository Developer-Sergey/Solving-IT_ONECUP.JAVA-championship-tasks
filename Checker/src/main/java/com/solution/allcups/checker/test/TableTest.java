package com.solution.allcups.checker.test;

import static com.solution.allcups.checker.util.Client.delete;
import static com.solution.allcups.checker.util.Client.get;
import static com.solution.allcups.checker.util.Client.postObject;

import java.util.List;

import org.springframework.stereotype.Component;

import com.solution.allcups.checker.object.Column;
import com.solution.allcups.checker.object.Table;
import com.solution.allcups.checker.object.result.Result;
import com.solution.allcups.checker.object.result.TableResult;

@Component
public class TableTest {
    private final String tableURL = "/table";

    private final Result result201 = new Result(1, 201);
    private final Result result406 = new Result(2, 406);

    private final TableResult tableResult200_1 = new TableResult(3, 200, 
        new Table("Artists", 3, "id", List.of(
            new Column("id", "INTEGER"), new Column("name", "CHARACTER VARYING"), new Column("age", "INTEGER")))
    );

    private final TableResult tableResult200_2 = new TableResult(4, 200, 
        new Table("WRongName", 1, "id", List.of(new Column("id", "INTEGER")))
    );

    private final TableResult tableResult200_3 = new TableResult(5, 200, 
        new Table("Customer", 6, "customerid", List.of(
            new Column("CustomerId", "BIGINT"), new Column("FirstName", "CHARACTER VARYING"), 
            new Column("LastName", "CHARACTER VARYING"), new Column("Company", "CHARACTER VARYING"),
            new Column("Address", "CHARACTER VARYING"), new Column("Phone", "CHARACTER VARYING")
        ))
    );

    private final TableResult tableResult200_4 = new TableResult(6, 200, 
        new Table("Car", 4, "carid", List.of(
            new Column("CarId", "INTEGER"), new Column("name", "CHARACTER VARYING"), 
            new Column("EngineType", "CHARACTER VARYING"), new Column("number", "BIGINT")
        ))
    );

    public void test() {
        addAllResults();

        Table table1 = new Table("Artists", 3, "id", List.of(
            new Column("id", "int4"), new Column("name", "varchar"), new Column("age", "int4")
            ));

        Table table2 = new Table("Customer", 6, "CustomerId", List.of(
            new Column("CustomerId", "int4"), new Column("FirstName", "varchar"), 
            new Column("LastName", "varchar"), new Column("Company", "varchar"),
            new Column("Address", "varchar"), new Column("Phone", "varchar")
            ));

        Table table3 = new Table("Car", 4, "CarId", List.of(
            new Column("CarId", "int4"), new Column("name", "varchar"), 
            new Column("EngineType", "varchar"), new Column("number", "int8")
            ));

        Table wrongTable1 = new Table("WrongPrimaryKey", 2, "id", List.of(
            new Column("CustomerId", "int4"), new Column("FirstName", "varchar")
            ));

        Table wrongTable2 = new Table("WrongType", 2, "CustomerId", List.of(
            new Column("CustomerId", "int4"), new Column("FirstName", "someType")
            ));

        Table wrongTable3 = new Table("wrongColumnAmount", 100, "CarId", List.of(
            new Column("CarId", "int4"), new Column("name", "varchar"), 
            new Column("EngineType", ""), new Column("number", "int4")
            ));

        // ????????????????????????
        Table wrongTable4 = new Table("124", 3, "id", List.of(
            new Column("id", "int4"), new Column("name", "varchar"), new Column("age", "int4")
            ));

        postObject(getCreateTableURL(result201.getResultId()), table1);
        postObject(getCreateTableURL(result201.getResultId()), table2);
        postObject(getCreateTableURL(result201.getResultId()), table3);
        postObject(getCreateTableURL(result406.getResultId()), table1);
        postObject(getCreateTableURL(result406.getResultId()), wrongTable1);
        postObject(getCreateTableURL(result406.getResultId()), wrongTable2);
        postObject(getCreateTableURL(result406.getResultId()), wrongTable3);
        postObject(getCreateTableURL(result406.getResultId()), wrongTable4);

        delete(getDropURL(table2.getTableName(), result201.getResultId()));
        delete(getDropURL("SoMeWrongName", result406.getResultId()));

        get(getGetByNameURL(table1.getTableName(), tableResult200_1.getResultId()));
        get(getGetByNameURL("WRongName", tableResult200_2.getResultId()));
        get(getGetByNameURL(table2.getTableName(), tableResult200_3.getResultId()));
        get(getGetByNameURL(table3.getTableName(), tableResult200_4.getResultId()));
    }

    public void testWithTableQuery() {
        delete(getDropURL("Artists", result201.getResultId()));

        get("table-query/get-table-query-by-id/1?resultId=12");
    }

    public void addMoreTableToTestReports() {
        Table table1 = new Table("Artists", 3, "id", List.of(
            new Column("id", "int4"), new Column("name", "varchar"), new Column("age", "int4")
            ));

        Table table2 = new Table("Customer", 6, "CustomerId", List.of(
            new Column("CustomerId", "int8"), new Column("FirstName", "VARCHAR(40)"), 
            new Column("LastName", "VARCHAR(20)"), new Column("Company", "VARCHAR(80)"),
            new Column("Address", "varchar"), new Column("Phone", "VARCHAR(24)")
            ));

        postObject(getCreateTableURL(result201.getResultId()), table1);
        postObject(getCreateTableURL(result201.getResultId()), table2);

        get(getGetByNameURL(table1.getTableName(), tableResult200_1.getResultId()));
        get(getGetByNameURL(table1.getTableName(), tableResult200_3.getResultId()));
    }

    private void addAllResults() {
        postObject(getAddCreateTableResultURL(), result201);
        postObject(getAddCreateTableResultURL(), result406);

        postObject(getAddDropResultURL(), result201);
        postObject(getAddDropResultURL(), result406);

        postObject(getAddGetByNameResultURL(), tableResult200_1);
        postObject(getAddGetByNameResultURL(), tableResult200_2);
        postObject(getAddGetByNameResultURL(), tableResult200_3);
        postObject(getAddGetByNameResultURL(), tableResult200_4);
    }

    public String getAddCreateTableResultURL() {
        return tableURL + "/add-create-table-result";
    }

    public String getCreateTableURL(Integer resultId) {
        return tableURL + "/create-table" + "?resultId=" + resultId;
    }

    public String getAddDropResultURL() {
        return tableURL + "/add-drop-table-result";
    }

    public String getDropURL(String name, Integer resultId) {
        return tableURL + "/drop-table/" + name + "?resultId=" + resultId;
    }

    public String getAddGetByNameResultURL() {
        return tableURL + "/add-get-table-by-name-result";
    }

    public String getGetByNameURL(String name, Integer resultId) {
        return tableURL + "/get-table-by-name/" + name + "?resultId=" + resultId;
    }
}
