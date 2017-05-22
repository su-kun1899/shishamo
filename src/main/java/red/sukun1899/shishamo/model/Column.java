package red.sukun1899.shishamo.model;

import java.util.Collection;
import java.util.List;

/**
 * @author su-kun1899
 */
public class Column {
    private String name;
    private String defaultValue;
    private boolean nullable;
    private String type;
    private String comment;
    private ReferencedColumn parentColumn;
    private List<ReferencedColumn> childColumns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReferencedColumn getParentColumn() {
        return parentColumn;
    }

    public void setParentColumn(ReferencedColumn parentColumn) {
        this.parentColumn = parentColumn;
    }

    public List<ReferencedColumn> getChildColumns() {
        return childColumns;
    }

    public void setChildColumns(List<ReferencedColumn> childColumns) {
        this.childColumns = childColumns;
    }

    public Index.Category getIndexCategory(Collection<Index> indices) {
        return indices.stream()
                .filter(index -> index.getColumns().stream().anyMatch(column -> column.getName().equals(this.getName())))
                .map(Index::getCategory)
                .findFirst()
                .orElse(null);
    }
}
