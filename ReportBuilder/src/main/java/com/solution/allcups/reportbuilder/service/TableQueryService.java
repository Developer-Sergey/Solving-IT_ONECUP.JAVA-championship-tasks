package com.solution.allcups.reportbuilder.service;

import com.solution.allcups.reportbuilder.database.DatabaseData;
import com.solution.allcups.reportbuilder.database.DatabaseManager;
import com.solution.allcups.reportbuilder.entity.TableQuery;
import com.solution.allcups.reportbuilder.exception.InternalServerErrorException;
import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import com.solution.allcups.reportbuilder.repository.TableQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TableQueryService {
    @Autowired
    private DatabaseData databaseData;

    @Autowired
    private TableQueryRepository tableQueryRepository;

    public TableQuery getQueryById(Integer id) throws InternalServerErrorException {
        Optional<TableQuery> query = tableQueryRepository.findById(id);

        if (query.isPresent()) {
            return query.get();
        }

        throw new InternalServerErrorException();
    }

    public List<TableQuery> getAllQueries() {
        return tableQueryRepository.findAll();
    }

    public List<TableQuery> getAllQueriesByTableName(String tableName) throws NotAcceptableException {
        if (!isTablePresent(tableName)) {
            return null;
        }

        return tableQueryRepository.findAllByTableName(tableName);
    }

    public void executeQueryById(Integer id) throws NotAcceptableException {
        Optional<TableQuery> query = tableQueryRepository.findById(id);

        if (query.isPresent()) {
            DatabaseManager databaseManager = new DatabaseManager(
                    databaseData.getUrl(), databaseData.getUsername(), databaseData.getPassword());

            databaseManager.execute(query.get().getQuery());

            databaseManager.close();

            return;
        }

        throw new NotAcceptableException();
    }

    public void addNewQueryToTable(TableQuery query) throws NotAcceptableException {
        if (!isTablePresent(query.getTableName())) {
            throw new NotAcceptableException();
        }

        if (query.getQuery().length() > 120) {
            throw new NotAcceptableException();
        }

        tableQueryRepository.save(query);
    }

    public void modifyQueryInTable(TableQuery query) throws NotAcceptableException {
        if (!isTablePresent(query.getTableName())) {
            throw new NotAcceptableException();
        }

        Optional<TableQuery> queryFromRepo = tableQueryRepository.findById(query.getQueryId());

        if (queryFromRepo.isPresent()) {
            if (query.getQuery().length() > 120) {
                query.setQuery("");

                tableQueryRepository.save(query);

                throw new NotAcceptableException();
            }

            tableQueryRepository.save(query);

            return;
        }

        throw new NotAcceptableException();
    }

    public void deleteQueryById(Integer id) throws NotAcceptableException {
        Optional<TableQuery> query = tableQueryRepository.findById(id);

        if (query.isPresent()) {
            tableQueryRepository.deleteById(id);

            return;
        }

        throw new NotAcceptableException();
    }

    private boolean isTablePresent(String name) throws NotAcceptableException {
        DatabaseManager databaseManager = new DatabaseManager(
                databaseData.getUrl(), databaseData.getUsername(), databaseData.getPassword());

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
}
