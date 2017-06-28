package red.sukun1899.shishamo.model.json;

import red.sukun1899.shishamo.model.Table;

/**
 * @author su-kun1899
 */
public class TableDetail {
    private Table table;

    private String name;
    private String comment;
    private long countOfRows;
    private long countOfColumns;
    private String url;
// TODO    private List<Column> columns;
    // TODO Indices

    public TableDetail(Table table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public long getCountOfRows() {
        return countOfRows;
    }

    public long getCountOfColumns() {
        return countOfColumns;
    }

    public String getUrl() {
        return url;
    }
}
