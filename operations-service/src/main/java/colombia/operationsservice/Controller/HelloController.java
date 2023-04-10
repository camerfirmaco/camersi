package colombia.operationsservice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/operations")
public class HelloController {
    
    @GetMapping(value="/")
    public String getMethodName() {
        return "Hello, este es el sevicio de operaciones";
    }
    
}
