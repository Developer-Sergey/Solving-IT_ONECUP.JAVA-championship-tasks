package com.solution.allcups.reportbuilder.repository;

import com.solution.allcups.reportbuilder.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
