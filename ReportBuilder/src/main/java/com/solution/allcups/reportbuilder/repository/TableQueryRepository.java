package com.solution.allcups.reportbuilder.repository;

import com.solution.allcups.reportbuilder.entity.TableQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TableQueryRepository extends JpaRepository<TableQuery, Integer> {
    List<TableQuery> findAllByTableName(String tableName);

    @Transactional
    void deleteAllByTableName(String tableName);
}
