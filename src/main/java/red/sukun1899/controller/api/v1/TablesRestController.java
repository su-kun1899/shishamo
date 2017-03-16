package red.sukun1899.controller.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import red.sukun1899.model.Column;
import red.sukun1899.model.Table;
import red.sukun1899.service.TableService;

import java.util.ArrayList;
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
        // FIXME dummy
        final Table dummy = new Table();
        dummy.setName(tableName);

        final ArrayList<Column> dummyColumns = new ArrayList<>();
        final Column dummyColumn1 = new Column();
        dummyColumn1.setName("columnA");
        dummyColumn1.setDefaultValue("default_sample");
        dummyColumn1.setNullable(true);
        dummyColumn1.setType("varchar(40)");
        dummyColumn1.setComment("comment_sample");
        dummyColumns.add(dummyColumn1);
        final Column dummyColumn2 = new Column();
        dummyColumn2.setName("columnB");
        dummyColumns.add(dummyColumn2);

        dummy.setColumns(dummyColumns);

        return dummy;
    }
}
