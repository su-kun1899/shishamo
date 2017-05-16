package red.sukun1899.model;

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
}
