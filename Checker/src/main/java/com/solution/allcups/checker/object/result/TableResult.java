package com.solution.allcups.checker.object.result;

import com.solution.allcups.checker.object.SystemObject;
import com.solution.allcups.checker.object.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableResult implements SystemObject {
    private Integer resultId;
    private Integer code;
    private Table table;
}
