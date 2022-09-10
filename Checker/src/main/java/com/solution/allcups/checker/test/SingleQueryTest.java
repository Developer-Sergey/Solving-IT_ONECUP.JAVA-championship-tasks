package com.solution.allcups.checker.test;

import static com.solution.allcups.checker.util.Client.delete;
import static com.solution.allcups.checker.util.Client.get;
import static com.solution.allcups.checker.util.Client.postObject;
import static com.solution.allcups.checker.util.Client.post;
import static com.solution.allcups.checker.util.Client.putObject;
import static com.solution.allcups.checker.util.Client.put;

import org.springframework.stereotype.Component;

import com.solution.allcups.checker.object.SingleQuery;
import com.solution.allcups.checker.object.result.Result;

@Component
public class SingleQueryTest {
    private final String singleQueryURL = "/single-query";

    private final Result result200 = new Result(1, 200);
    private final Result result201 = new Result(2, 201);
    private final Result result202 = new Result(3, 202);
    private final Result result400 = new Result(4, 400);
    private final Result result406 = new Result(5, 406);
    private final Result result500 = new Result(6, 500);

    public void test() {
        addAllResults();

        SingleQuery singleQuery1 = new SingleQuery(1, "nothing");
        SingleQuery singleQuery2 = new SingleQuery(2, "select * from Artistsselect * from Artistsselect * from Artistsselect * from Artistsselect * from Artistsselect * from Artists");
        SingleQuery singleQuery3 = new SingleQuery(3, "to delete");
        SingleQuery singleQuery4 = new SingleQuery(4, "some");

        postObject(getAddQueryURL(result201.getResultId()), singleQuery1);
        postObject(getAddQueryURL(result201.getResultId()), singleQuery3);
        postObject(getAddQueryURL(result201.getResultId()), singleQuery4);
        postObject(getAddQueryURL(result400.getResultId()), singleQuery1);
        postObject(getAddQueryURL(result400.getResultId()), singleQuery2);
        post(getAddQueryURL(result400.getResultId()), "{'queryId':'wrong','query:''}");

        singleQuery1.setQuery("select * from Artists");

        putObject(getModifyURL(result200.getResultId()), singleQuery1);
        putObject(getModifyURL(result406.getResultId()), singleQuery2);
        putObject(getModifyURL(result406.getResultId()), new SingleQuery(406, ""));
        put(getModifyURL(result406.getResultId()), "{'queryId':'Wrong','query:''}");

        get(getExecuteByIdURL(singleQuery1.getQueryId(), result201.getResultId()));
        get(getExecuteByIdURL(singleQuery4.getQueryId(), result406.getResultId()));
        get(getExecuteByIdURL(406, result406.getResultId()));
        get("/execute-single-query-by-id/wronG?resultId=" + result406.getResultId());

        delete(getDeleteByIdURL(singleQuery3.getQueryId(), result202.getResultId()));
        delete(getDeleteByIdURL(singleQuery3.getQueryId(), result406.getResultId()));
        delete(getDeleteByIdURL(406, result406.getResultId()));
        delete("/delete-single-query-by-id/WronG?resultId=" + result406.getResultId());

        get(getGetByIdURL(singleQuery1.getQueryId(), result200.getResultId()));
        get(getGetByIdURL(406, result500.getResultId()));
    }

    private void addAllResults() {
        postObject(getAddQueryResultURL(), result201);
        postObject(getAddQueryResultURL(), result400);

        postObject(getAddModifyResultURL(), result200);
        postObject(getAddModifyResultURL(), result406);

        postObject(getAddDeleteResultURL(), result202);
        postObject(getAddDeleteResultURL(), result406);

        postObject(getAddExecuteResultURL(), result201);
        postObject(getAddExecuteResultURL(), result406);

        postObject(getAddGetByIdResultURL(), result200);
        postObject(getAddGetByIdResultURL(), result500);
    }

    public String getAddQueryResultURL() {
        return singleQueryURL + "/add-new-query-result";
    }

    public String getAddQueryURL(Integer resultId) {
        return singleQueryURL + "/add-new-query" + "?resultId=" + resultId;
    }

    public String getAddModifyResultURL() {
        return singleQueryURL + "/add-modify-result";
    }

    public String getModifyURL(Integer resultId) {
        return singleQueryURL + "/modify-single-query" + "?resultId=" + resultId;
    }

    public String getAddDeleteResultURL() {
        return singleQueryURL + "/add-delete-result";
    }

    public String getDeleteByIdURL(Integer id, Integer resultId) {
        return singleQueryURL + "/delete-single-query-by-id/" + id + "?resultId=" + resultId;
    }

    public String getAddExecuteResultURL() {
        return singleQueryURL + "/add-execute-result";
    }

    public String getExecuteByIdURL(Integer id, Integer resultId) {
        return singleQueryURL + "/execute-single-query-by-id/" + id + "?resultId=" + resultId;
    }

    public String getAddGetByIdResultURL() {
        return singleQueryURL + "/add-get-single-query-by-id-result";
    }

    public String getGetByIdURL(Integer id, Integer resultId) {
        return singleQueryURL + "/get-single-query-by-id/" + id + "?resultId=" + resultId;
    }

    public String getGetAllURL() {
        return singleQueryURL + "/get-all-single-queries";
    }
}
