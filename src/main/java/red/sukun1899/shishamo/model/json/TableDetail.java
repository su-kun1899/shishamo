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
    private String url;
    // TODO columns
//    private List<Column> columns;
    // TODO Indices

    public TableDetail(Table table) {
        this.table = table;
        this.name = table.getName();
        this.comment = table.getComment();
        this.countOfRows = table.getRowCount();
        this.url = new TableOverview(table).getUrl();
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

    public String getUrl() {
        return url;
    }
}
