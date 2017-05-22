package red.sukun1899.shishamo.model;

/**
 * @author su-kun1899
 */
public class ReferencedColumn {
    private String name;
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
