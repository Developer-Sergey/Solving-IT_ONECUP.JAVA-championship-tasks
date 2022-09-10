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
public class Table implements SystemObject {
    private String tableName;
    private Integer columnsAmount;
    private String primaryKey;
    private List<Column> columnInfos;
}
