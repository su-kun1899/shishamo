package red.sukun1899.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import red.sukun1899.AppConfig;
import red.sukun1899.model.Table;
import red.sukun1899.service.TableService;

import java.util.List;
import java.util.Map;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("tables")
public class TableController {
  private final AppConfig appConfig;
  private final TableService tableService;

  public TableController(AppConfig appConfig, TableService tableService) {
    this.tableService = tableService;
    this.appConfig = appConfig;
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

    model.addAttribute("schemaName", appConfig.getSchemaName());

    return "tables";
  }

  @GetMapping(path = "{tableName}")
  public String get(@PathVariable String tableName, Model model) {
    Table table = tableService.get(tableName);
    model.addAttribute("table", table);

    model.addAttribute("schemaName", appConfig.getSchemaName());

    return "table";
  }
}
