package red.sukun1899.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("hello-world")
public class HelloWorldController {
    @GetMapping
    public String index(Model model) {
        return "hello-world";
    }
}
