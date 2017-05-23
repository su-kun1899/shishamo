package red.sukun1899.shishamo.controller.page;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("data-source")
public class DataSourceController {
    private final DataSourceProperties dataSourceProperties;

    public DataSourceController(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("dataSourceProperties", dataSourceProperties);
        return "data-source";
    }
}
