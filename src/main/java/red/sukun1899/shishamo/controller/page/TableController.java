package red.sukun1899.shishamo.controller.page;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import red.sukun1899.shishamo.model.CreateTableStatement;
import red.sukun1899.shishamo.model.Index;
import red.sukun1899.shishamo.model.Table;
import red.sukun1899.shishamo.service.IndexService;
import red.sukun1899.shishamo.service.TableService;

import java.util.List;
import java.util.Map;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("tables")
public class TableController {
    private final DataSourceProperties dataSourceProperties;
    private final TableService tableService;
    private final IndexService indexService;

    public TableController(DataSourceProperties dataSourceProperties, TableService tableService, IndexService indexService) {
        this.tableService = tableService;
        this.dataSourceProperties = dataSourceProperties;
        this.indexService = indexService;
    }

    @GetMapping
    public String get(Model model) {
        List<Table> tables = tableService.get();
        model.addAttribute("tables", tables);

        Map<String, Long> parentTableCounts = tableService.getParentTableCountsByTableName();
        model.addAttribute("parentTableCounts", parentTableCounts);

        Map<String, Long> childTableCounts = tableService.getChildTableCountsByTableName();
        model.addAttribute("childTableCounts", childTableCounts);

        Map<String, Long> columnCounts = tableService.getColumnCountsByTableName();
        model.addAttribute("columnCounts", columnCounts);

        model.addAttribute("schemaName", dataSourceProperties.getSchema());

        return "tables";
    }

    @GetMapping(path = "{tableName}")
    public String get(@PathVariable String tableName, Model model) {
        Table table = tableService.get(tableName);
        model.addAttribute("table", table);

        CreateTableStatement createTableStatement = tableService.getCreateTableStatement(table);
        model.addAttribute("createTableStatement", createTableStatement);

        List<Index> indices = indexService.get(tableName);
        model.addAttribute("indices", indices);

        model.addAttribute("schemaName", dataSourceProperties.getSchema());

        return "table";
    }
}
