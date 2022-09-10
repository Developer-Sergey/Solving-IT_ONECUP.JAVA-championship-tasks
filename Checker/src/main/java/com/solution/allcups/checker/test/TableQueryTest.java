package com.solution.allcups.checker.test;

import static com.solution.allcups.checker.util.Client.delete;
import static com.solution.allcups.checker.util.Client.get;
import static com.solution.allcups.checker.util.Client.postObject;
import static com.solution.allcups.checker.util.Client.putObject;
import static com.solution.allcups.checker.util.Client.put;

import org.springframework.stereotype.Component;

import com.solution.allcups.checker.object.TableQuery;
import com.solution.allcups.checker.object.result.Result;
import com.solution.allcups.checker.object.result.TableQueryResult;

@Component
public class TableQueryTest {
    private final String tableQueryURL = "/table-query";

    private final Result result200 = new Result(1, 200);
    private final Result result201 = new Result(2, 201);
    private final Result result202 = new Result(3, 202);
    private final Result result406 = new Result(4, 406);

    private final TableQueryResult tableQueryResult200_1 = new TableQueryResult(5, 200, new TableQuery("1","Car", "nothing"));
    private final TableQueryResult tableQueryResult201_1 = new TableQueryResult(6, 201, new TableQuery("1","Car", "nothing"));
    private final TableQueryResult tableQueryResult201_2 = new TableQueryResult(7, 201, new TableQuery("3", "Customer", "to delete"));
    private final TableQueryResult tableQueryResult406_1 = new TableQueryResult(8, 406, new TableQuery("1","Car", "nothing"));
    private final TableQueryResult tableQueryResult406_2 = new TableQueryResult(9, 406, new TableQuery("2", "Artists", "select * from Artistsselect * from Artistsselect * from Artistsselect * from Artistsselect * from Artistsselect * from Artists"));
    private final TableQueryResult tableQueryResult406_3 = new TableQueryResult(10, 406, new TableQuery("5", "SomeWRonGName", ""));
    private final TableQueryResult tableQueryResult406_4 = new TableQueryResult(11, 406, new TableQuery("wrong", "Artists", ""));
    private final TableQueryResult tableQueryResult500_1 = new TableQueryResult(12, 500, new TableQuery("406", "Artists", ""));

    public void test() {
        addAllResults();

        TableQuery tableQuery1 = new TableQuery("1","Car", "nothing");
        TableQuery tableQuery2 = new TableQuery("2", "Artists", "select * from Artistsselect * from Artistsselect * from Artistsselect * from Artistsselect * from Artistsselect * from Artists");
        TableQuery tableQuery3 = new TableQuery("3", "Customer", "to delete");

        postObject(getAddQueryURL(tableQueryResult201_1.getResultId()), tableQuery1);
        postObject(getAddQueryURL(tableQueryResult201_2.getResultId()), tableQuery3);
        postObject(getAddQueryURL(tableQueryResult406_1.getResultId()), tableQuery1);
        postObject(getAddQueryURL(tableQueryResult406_2.getResultId()), tableQuery2);
        postObject(getAddQueryURL(tableQueryResult406_3.getResultId()), new TableQuery("5", "SomeWRonGName", ""));
        postObject(getAddQueryURL(tableQueryResult406_4.getResultId()), new TableQuery("wrong", "Artists", ""));

        // tableQuery1.setTableName("Car");
        tableQuery1.setQuery("insert into Car (CarId, name, EngineType, number) values (1, 'SomeCar', 'Some engine type', 214211)");

        putObject(getModifyURL(result200.getResultId()), tableQuery1);
        putObject(getModifyURL(result406.getResultId()), tableQuery2);
        putObject(getModifyURL(result406.getResultId()), new TableQuery("406", "", ""));
        put(getModifyURL(result406.getResultId()), "{'queryId':'Wrong','tableName':'Artists','query:''}");

        get(getExecuteByIdURL(tableQuery1.getQueryId(), result201.getResultId()));
        get(getExecuteByIdURL(tableQuery3.getQueryId(), result406.getResultId()));
        get(getExecuteByIdURL("406", result406.getResultId()));
        get(tableQueryURL + "/execute-table-query-by-id/wronG?resultId=" + result406.getResultId());

        delete(getDeleteByIdURL(tableQuery3.getQueryId(), result201.getResultId()));
        delete(getDeleteByIdURL(tableQuery3.getQueryId(), result406.getResultId()));
        delete(tableQueryURL + "/delete-table-query-by-id/WronG?resultId=" + result406.getResultId());

        get(getGetByIdURL(tableQuery1.getQueryId(), tableQueryResult200_1.getResultId()));
        get(getGetByIdURL("406", tableQueryResult500_1.getResultId()));
    }

    public void addAllResults() {
        postObject(getAddQueryResultURL(), tableQueryResult201_1);
        postObject(getAddQueryResultURL(), tableQueryResult201_2);
        postObject(getAddQueryResultURL(), tableQueryResult406_1);
        postObject(getAddQueryResultURL(), tableQueryResult406_2);
        postObject(getAddQueryResultURL(), tableQueryResult406_3);
        postObject(getAddQueryResultURL(), tableQueryResult406_4);

        postObject(getAddModifyResultURL(), result200);
        postObject(getAddModifyResultURL(), result406);

        postObject(getAddDeleteResultURL(), result201);
        postObject(getAddDeleteResultURL(), result406);

        postObject(getAddExecuteResultURL(), result201);
        postObject(getAddExecuteResultURL(), result406);

        postObject(getAddGetByIdResultURL(), tableQueryResult200_1);
        postObject(getAddGetByIdResultURL(), tableQueryResult500_1);

        postObject(getAddGetAllByTableNameResultURL(), result200);
    }

    public String getAddQueryResultURL() {
        return tableQueryURL + "/add-new-query-to-table-result";
    }

    public String getAddQueryURL(Integer resultId) {
        return tableQueryURL + "/add-new-query-to-table" + "?resultId=" + resultId;
    }

    public String getAddModifyResultURL() {
        return tableQueryURL + "/modify-query-in-table-result";
    }

    public String getModifyURL(Integer resultId) {
        return tableQueryURL + "/modify-query-in-table" + "?resultId=" + resultId;
    }

    public String getAddDeleteResultURL() {
        return tableQueryURL + "/delete-table-query-by-id-result";
    }

    public String getDeleteByIdURL(String id, Integer resultId) {
        return tableQueryURL + "/delete-table-query-by-id/" + id + "?resultId=" + resultId;
    }

    public String getAddExecuteResultURL() {
        return tableQueryURL + "/execute-table-query-by-id-result";
    }

    public String getExecuteByIdURL(String id, Integer resultId) {
        return tableQueryURL + "/execute-table-query-by-id/" + id + "?resultId=" + resultId;
    }

    public String getAddGetByIdResultURL() {
        return tableQueryURL + "/get-table-query-by-id-result";
    }

    public String getGetByIdURL(String id, Integer resultId) {
        return tableQueryURL + "/get-table-query-by-id/" + id + "?resultId=" + resultId;
    }

    // 
    public String getAddGetAllByTableNameResultURL() {
        return tableQueryURL + "/get-all-queries-by-table-name-result";
    }

    public String getGetAllByTableNameURL(String name, Integer resultId) {
        return tableQueryURL + "/get-all-queries-by-table-name/" + name + "?resultId=" + resultId;
    }

    // public String getGetAllURL() {
    //     return tableQueryURL + "/get-all-single-queries";
    // }
}
