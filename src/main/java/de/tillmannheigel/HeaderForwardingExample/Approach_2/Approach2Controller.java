package de.tillmannheigel.HeaderForwardingExample.Approach_2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Approach2Controller {

    @Autowired
    private HeadersByRequest headersByRequest;

    @RequestMapping("/approach_2")
    public String example() {
        return "Approach 2:<br>" + headersByRequest.getHeaders("x-custom");
    }
}
