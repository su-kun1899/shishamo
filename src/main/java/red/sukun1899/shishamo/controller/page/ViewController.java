package red.sukun1899.shishamo.controller.page;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import red.sukun1899.shishamo.model.Column;
import red.sukun1899.shishamo.model.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("/views")
public class ViewController {
    private final DataSourceProperties dataSourceProperties;

    public ViewController(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @GetMapping
    public String get(Model model) {
        List<View> views = new ArrayList<View>() {
            {
                add(new View() {
                    {
                        setName("view1");
                        setColumns(new ArrayList<Column>() {{
                            add(new Column());
                        }});
                    }
                });
                add(new View() {
                    {
                        setName("view2");
                        setColumns(new ArrayList<Column>() {{
                            add(new Column());
                            add(new Column());
                        }});
                    }
                });
                add(new View() {
                    {
                        setName("view3");
                        setColumns(new ArrayList<Column>() {{
                            add(new Column());
                            add(new Column());
                            add(new Column());
                        }});
                    }
                });
            }
        };
        model.addAttribute("views", views);

        model.addAttribute("schemaName", dataSourceProperties.getSchema());

        return "views";
    }
}

