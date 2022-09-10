package com.solution.allcups.reportbuilder.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Report {
    @Id
    private Integer reportId;

    private Integer tableAmount;

    @OneToMany(mappedBy = "report")
    private List<ReportTable> tables;
}
