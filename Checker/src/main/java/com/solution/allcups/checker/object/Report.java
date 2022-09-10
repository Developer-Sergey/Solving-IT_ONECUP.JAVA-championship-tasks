package com.solution.allcups.checker.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report implements SystemObject {
    private String reportId;
    private Integer tableAmount;
    private List<ReportTable> tables;
}
