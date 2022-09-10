package com.solution.allcups.reportbuilder.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ReportColumn {
    private String title;
    private String type;
    private String size;

    @Override
    public String toString() {
        return title + " " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportColumn that = (ReportColumn) o;

        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
