package red.sukun1899.shishamo.model;

/**
 * @author su-kun1899
 */
public class CreateTableStatement {
    private String tableName;
    private String ddl;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }
}
