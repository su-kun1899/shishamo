package red.sukun1899.shishamo.controller.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import red.sukun1899.shishamo.model.Table;
import red.sukun1899.shishamo.service.TableService;

import java.util.List;

/**
 * @author su-kun1899
 */
@RestController
@RequestMapping("v1/tables")
public class TablesRestController {
    private final TableService tableService;

    public TablesRestController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public List<Table> get() {
        return tableService.get();
    }

    @GetMapping(path = "{tableName}")
    public Table get(@PathVariable String tableName) {
        return tableService.get(tableName);
    }
}
