package red.sukun1899.shishamo.model.json;

import red.sukun1899.shishamo.model.Index;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author su-kun1899
 */
public class IndexDetail {
    private String name;
    private List<ColumnOverview> columns;
    private Index.Category category;

    public IndexDetail(Index index) {
        this.name = index.getName();
        this.columns = index.getColumns().stream()
                .map(ColumnOverview::new)
                .collect(Collectors.toList());
        this.category = index.getCategory();
    }

    public String getName() {
        return name;
    }

    public List<ColumnOverview> getColumns() {
        return columns;
    }

    public Index.Category getCategory() {
        return category;
    }
}
