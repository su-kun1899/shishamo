package red.sukun1899.shishamo.controller.page;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import red.sukun1899.shishamo.model.View;
import red.sukun1899.shishamo.service.ViewService;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("/views")
public class ViewController {
    private final ViewService viewService;
    private final DataSourceProperties dataSourceProperties;

    public ViewController(ViewService viewService, DataSourceProperties dataSourceProperties) {
        this.viewService = viewService;
        this.dataSourceProperties = dataSourceProperties;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("views", viewService.getAll());
        model.addAttribute("schemaName", dataSourceProperties.getSchema());

        return "views";
    }

    @GetMapping(path = "{viewName}")
    public String get(@PathVariable String viewName, Model model) {
        View view = viewService.getByName(viewName);
        model.addAttribute("view", view);
        model.addAttribute("schemaName", dataSourceProperties.getSchema());

        return "view";
    }
}

