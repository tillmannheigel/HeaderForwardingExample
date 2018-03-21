package de.tillmannheigel.HeaderForwardingExample.Approach_1.Controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Approach1Controller {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/approach_1")
    public String example(){
        Enumeration<String> headerNames = request.getHeaderNames();
        String allHeaders = "";
        while (headerNames.hasMoreElements()){
            String currentHeader = headerNames.nextElement();
            if(currentHeader.startsWith("x-custom")) {
                allHeaders += currentHeader;
                allHeaders += ": ";
                allHeaders += request.getHeader(currentHeader);
                allHeaders += "<br>";
            }
        }
        return "Approach 1:<br>" + allHeaders;
    }
}
