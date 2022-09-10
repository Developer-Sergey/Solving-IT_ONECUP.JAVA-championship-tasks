package com.solution.allcups.checker.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleQuery implements SystemObject {
    private Integer queryId;
    private String query;
}
