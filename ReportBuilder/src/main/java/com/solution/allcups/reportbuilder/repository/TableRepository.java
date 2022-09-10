package com.solution.allcups.reportbuilder.repository;

import com.solution.allcups.reportbuilder.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, String> {
}
