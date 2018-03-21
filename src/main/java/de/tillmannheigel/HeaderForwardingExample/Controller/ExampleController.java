package de.tillmannheigel.HeaderForwardingExample.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @RequestMapping("/example")
    public String example(){
        return "example";
    }
}
