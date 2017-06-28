package red.sukun1899.shishamo.model.json;

import red.sukun1899.shishamo.model.Index;
import red.sukun1899.shishamo.model.Table;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author su-kun1899
 */
public class TableDetail {
    private String name;
    private String comment;
    private long countOfRows;
    private String url;
    private List<ColumnDetail> columns;
    private List<IndexDetail> Indices;

    public TableDetail(Table table, List<Index> indices) {
        this.name = table.getName();
        this.comment = table.getComment();
        this.countOfRows = table.getRowCount();
        this.url = new TableOverview(table).getUrl();
        this.columns = table.getColumns().stream()
                .map(ColumnDetail::new)
                .collect(Collectors.toList());
        this.Indices = indices == null ? null : indices.stream()
                .map(IndexDetail::new)
                .collect(Collectors.toList());
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

    public List<ColumnDetail> getColumns() {
        return columns;
    }

    public List<IndexDetail> getIndices() {
        return Indices;
    }
}