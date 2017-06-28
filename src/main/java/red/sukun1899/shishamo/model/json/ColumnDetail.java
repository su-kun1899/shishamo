package red.sukun1899.shishamo.model.json;

import red.sukun1899.shishamo.model.Column;

/**
 * @author su-kun1899
 */
public class ColumnDetail {
    private String name;
    private String defaultValue;
    private boolean nullable;
    private String type;
    private String comment;
    // TODO
//    private ReferencedColumn parentColumn;
//    private List<ReferencedColumn> childColumns;

    public ColumnDetail(Column column) {
        this.name = column.getName();
        this.defaultValue = column.getDefaultValue();
        this.nullable = column.isNullable();
        this.type = column.getType();
        this.comment = column.getComment();
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean isNullable() {
        return nullable;
    }

    public String getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }
}
