package com.solution.allcups.reportbuilder.repository;

import com.solution.allcups.reportbuilder.entity.SingleQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleQueryRepository extends JpaRepository<SingleQuery, Integer> {}
