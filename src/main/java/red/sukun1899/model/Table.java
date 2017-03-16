package red.sukun1899.model;

import java.util.List;

/**
 * @author su-kun1899
 */
public class Table {
    private String name;
    private List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
