package com.solution.allcups.reportbuilder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    private String tableName;
    private Integer columnsAmount;
    private String primaryKey;

    @ElementCollection
    @CollectionTable(
            name="TABLE_COLUMN_INFOS",
            joinColumns=@JoinColumn(name="TABLE_NAME")
    )
    private List<Column> columnInfos;
}
