package red.sukun1899.shishamo.model;

import java.util.List;

/**
 * @author su-kun1899
 */
public class Index {
    private String name;
    private List<Column> columns;
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public enum Category {
        PRIMARY(10),
        UNIQUE(20),
        PERFORMANCE(30);

        private int order;

        Category(int order) {
            this.order = order;
        }

        public int order() {
            return order;
        }
    }
}
