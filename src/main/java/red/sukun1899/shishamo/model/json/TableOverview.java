package red.sukun1899.shishamo.model.json;

/**
 * @author su-kun1899
 */
public class TableOverview {
    private String name;
    private String comment;
    private Long countOfRows;
    private Long countOfChildren;
    private Long countOfParents;
    private Long countOfColumns;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCountOfRows() {
        return countOfRows;
    }

    public void setCountOfRows(Long countOfRows) {
        this.countOfRows = countOfRows;
    }

    public Long getCountOfChildren() {
        return countOfChildren;
    }

    public void setCountOfChildren(Long countOfChildren) {
        this.countOfChildren = countOfChildren;
    }

    public Long getCountOfParents() {
        return countOfParents;
    }

    public void setCountOfParents(Long countOfParents) {
        this.countOfParents = countOfParents;
    }

    public Long getCountOfColumns() {
        return countOfColumns;
    }

    public void setCountOfColumns(Long countOfColumns) {
        this.countOfColumns = countOfColumns;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
