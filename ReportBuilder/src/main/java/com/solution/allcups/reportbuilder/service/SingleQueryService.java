package com.solution.allcups.reportbuilder.service;

import com.solution.allcups.reportbuilder.database.DatabaseData;
import com.solution.allcups.reportbuilder.database.DatabaseManager;
import com.solution.allcups.reportbuilder.entity.SingleQuery;
import com.solution.allcups.reportbuilder.exception.InternalServerErrorException;
import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import com.solution.allcups.reportbuilder.repository.SingleQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingleQueryService {
    @Autowired
    private DatabaseData databaseData;

    @Autowired
    private SingleQueryRepository singleQueryRepository;

    public SingleQuery getQueryById(Integer id) throws InternalServerErrorException {
        Optional<SingleQuery> query = singleQueryRepository.findById(id);

        if (query.isPresent()) {
            return query.get();
        }

        throw new InternalServerErrorException();
    }

    public List<SingleQuery> getAllQueries() {
        return singleQueryRepository.findAll();
    }

    public void executeQueryById(Integer id) throws NotAcceptableException {
        Optional<SingleQuery> query = singleQueryRepository.findById(id);

        if (query.isPresent()) {
            DatabaseManager databaseManager = new DatabaseManager(
                    databaseData.getUrl(), databaseData.getUsername(), databaseData.getPassword());

            databaseManager.execute(query.get().getQuery());

            databaseManager.close();

            return;
        }

        throw new NotAcceptableException();
    }

    public void addNewQuery(SingleQuery query) throws NotAcceptableException {
        if (query.getQuery().length() > 120) {
            throw new NotAcceptableException();
        }

        singleQueryRepository.save(query);
    }

    public void modifyQuery(SingleQuery query) throws NotAcceptableException {
        Optional<SingleQuery> queryFromRepo = singleQueryRepository.findById(query.getQueryId());

        if (queryFromRepo.isPresent()) {
            if (query.getQuery().length() > 120) {
                query.setQuery("");

                singleQueryRepository.save(query);

                throw new NotAcceptableException();
            }

            singleQueryRepository.save(query);

            return;
        }

        throw new NotAcceptableException();
    }

    public void deleteQueryById(Integer id) throws NotAcceptableException {
        Optional<SingleQuery> query = singleQueryRepository.findById(id);

        if (query.isPresent()) {
            singleQueryRepository.deleteById(id);

            return;
        }

        throw new NotAcceptableException();
    }
}
