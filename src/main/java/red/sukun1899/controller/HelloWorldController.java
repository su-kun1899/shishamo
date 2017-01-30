package red.sukun1899.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import red.sukun1899.repository.TableRepository;

import java.util.Map;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("hello-world")
public class HelloWorldController {

    // TODO Serviceを噛ませる
    private TableRepository tableRepository;

    public HelloWorldController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @GetMapping
    public String index(Model model) {
        final Map<String, Object> all = tableRepository.findAll();

        return "hello-world";
    }
}
