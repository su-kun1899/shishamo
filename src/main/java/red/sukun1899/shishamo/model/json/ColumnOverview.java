package red.sukun1899.shishamo.model.json;

import red.sukun1899.shishamo.model.Column;

/**
 * @author su-kun1899
 */
public class ColumnOverview {
    private String name;

    public ColumnOverview(Column column) {
        this.name = column.getName();
    }

    public String getName() {
        return name;
    }
}
