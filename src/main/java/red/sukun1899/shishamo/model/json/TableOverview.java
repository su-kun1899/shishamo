package red.sukun1899.shishamo.model.json;

import red.sukun1899.shishamo.model.Table;

/**
 * @author su-kun1899
 */
public class TableOverview {
    private String name;
    private String comment;
    private long countOfRows;
    private long countOfChildren;
    private long countOfParents;
    private long countOfColumns;
    private String url;

    public TableOverview(Table table) {
        name = table.getName();
        comment = table.getComment();
        countOfRows = table.getRowCount() == null ? 0 : table.getRowCount();
        url = "/api/v1/tables/" + name;
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

    public long getCountOfChildren() {
        return countOfChildren;
    }

    public void setCountOfChildren(long countOfChildren) {
        this.countOfChildren = countOfChildren;
    }

    public long getCountOfParents() {
        return countOfParents;
    }

    public void setCountOfParents(long countOfParents) {
        this.countOfParents = countOfParents;
    }

    public long getCountOfColumns() {
        return countOfColumns;
    }

    public void setCountOfColumns(long countOfColumns) {
        this.countOfColumns = countOfColumns;
    }

    public String getUrl() {
        return url;
    }

}
