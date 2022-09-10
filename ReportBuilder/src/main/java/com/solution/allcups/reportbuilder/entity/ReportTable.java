package com.solution.allcups.reportbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "REPORT_TABLES")
public class ReportTable {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String tableName;

    @ElementCollection
    @CollectionTable(
            name="REPORT_COLUMNS",
            joinColumns=@JoinColumn(name="REPORT_TABLE_ID")
    )
    private List<ReportColumn> columns;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "REPORT_ID")
    private Report report;
}
