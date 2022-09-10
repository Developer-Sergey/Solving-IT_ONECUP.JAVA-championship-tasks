package com.solution.allcups.reportbuilder.service;

import com.solution.allcups.reportbuilder.database.DatabaseData;
import com.solution.allcups.reportbuilder.database.DatabaseManager;
import com.solution.allcups.reportbuilder.entity.Column;
import com.solution.allcups.reportbuilder.entity.Table;
import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import com.solution.allcups.reportbuilder.repository.TableQueryRepository;
import com.solution.allcups.reportbuilder.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {
    @Autowired
    private DatabaseData databaseData;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TableQueryRepository tableQueryRepository;

    public Table getTableByName(String name) throws NotAcceptableException {
        DatabaseManager databaseManager = new DatabaseManager(
                databaseData.getUrl(), databaseData.getUsername(), databaseData.getPassword());

        if (!isTablePresent(databaseManager, name)) return null;

        String primaryKey = getPrimaryKey(databaseManager, name);
        List<Column> columnInfos = getColumnInfos(databaseManager, name);

        databaseManager.close();

        return new Table(name, columnInfos.size(), primaryKey, columnInfos);
    }

    public void createTable(Table table) throws NotAcceptableException {
        if (table.getColumnInfos().size() != table.getColumnsAmount()) {
            throw new NotAcceptableException();
        }

        DatabaseManager databaseManager = new DatabaseManager(
                databaseData.getUrl(), databaseData.getUsername(), databaseData.getPassword());

        String sql = "create table " + table.getTableName() +
                "(" + table.getColumnInfos().toString().substring(1, table.getColumnInfos().toString().length() - 1) +
                ", PRIMARY KEY (" + table.getPrimaryKey() + "))";

        databaseManager.execute(sql);

        databaseManager.close();

        tableRepository.save(table);
    }

    public void dropTableByName(String name) throws NotAcceptableException {
        DatabaseManager databaseManager = new DatabaseManager(
                databaseData.getUrl(), databaseData.getUsername(), databaseData.getPassword());

        String sql = "drop table " + name;

        databaseManager.execute(sql);

        databaseManager.close();

        tableRepository.deleteById(name);

        tableQueryRepository.deleteAllByTableName(name);
    }

    private boolean isTablePresent(DatabaseManager databaseManager, String name) throws NotAcceptableException {
        String sql = "show tables";

        boolean isExist = false;

        try(ResultSet tables = databaseManager.executeQuery(sql)) {
            while (tables.next()) {
                if (tables.getString("TABLE_NAME").equals(name.toUpperCase())) {
                    isExist = true;
                    break;
                }
            }
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }

        return isExist;
    }

    private List<Column> getColumnInfos(DatabaseManager databaseManager, String name) throws NotAcceptableException {
        String sql = "show columns from " + name;

        List<Column> columnInfos = new ArrayList<>();

        try (ResultSet columns = databaseManager.executeQuery(sql)) {
            while (columns.next()) {
                Column column = new Column(columns.getString("FIELD"), columns.getString("TYPE"));

                columnInfos.add(column);
            }
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }

        return columnInfos;
    }

    private String getPrimaryKey(DatabaseManager databaseManager, String name) throws NotAcceptableException {
        String sql = "show columns from " + name;

        String primaryKey = null;

        try (ResultSet columns = databaseManager.executeQuery(sql)) {
            while (columns.next()) {
                if (columns.getString("KEY").equals("PRI")) {
                    primaryKey = columns.getString("FIELD").toLowerCase();
                    break;
                }
            }
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }

        return primaryKey;
    }
}
