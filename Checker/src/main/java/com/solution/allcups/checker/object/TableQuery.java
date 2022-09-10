package com.solution.allcups.checker.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableQuery implements SystemObject {
    private String queryId;
    private String tableName;
    private String query;
}
