package com.solution.allcups.checker.object.result;

import com.solution.allcups.checker.object.SystemObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result implements SystemObject {
    private Integer resultId;
    private Integer code;
}
