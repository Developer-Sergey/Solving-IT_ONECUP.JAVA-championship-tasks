package com.solution.allcups.checker.object.result;

import com.solution.allcups.checker.object.SystemObject;
import com.solution.allcups.checker.object.TableQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableQueryResult implements SystemObject {
    private Integer resultId;
    private Integer code;
    private TableQuery tableQuery;
}
