package com.solution.allcups.checker.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportColumn implements SystemObject {
    private String title;
    private String type;
    private String size;
}
