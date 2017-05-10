package red.sukun1899.controller.page;

import com.sun.org.apache.xpath.internal.operations.Mod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import red.sukun1899.model.Table;
import red.sukun1899.service.TableService;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("tables")
public class TableController {
  private final TableService tableService;

  public TableController(TableService tableService) {
    this.tableService = tableService;
  }

  @GetMapping
  public String get(Model model) {
    List<Table> tables = tableService.get();
    model.addAttribute("tables", tables);

    return "tables";
  }

  @GetMapping(path = "{tableName}")
  public String get(@PathVariable String tableName, Model model) {
    Table table = tableService.get(tableName);
    model.addAttribute("table", table);

    return "table";
  }
}
