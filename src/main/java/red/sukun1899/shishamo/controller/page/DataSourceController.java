package red.sukun1899.shishamo.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import red.sukun1899.shishamo.DataSourceConfig;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("data-source")
public class DataSourceController {
    private DataSourceConfig dataSourceConfig;

    public DataSourceController(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("dataSourceConfig", dataSourceConfig);
        return "data-source";
    }
}
