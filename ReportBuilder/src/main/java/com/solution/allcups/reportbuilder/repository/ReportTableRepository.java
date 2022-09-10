package com.solution.allcups.reportbuilder.repository;

import com.solution.allcups.reportbuilder.entity.ReportTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportTableRepository extends JpaRepository<ReportTable, Integer> {
}
