package de.tillmannheigel.HeaderForwardingExample.Approach_2;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class HeadersByRequest {

    @Autowired
    private HttpServletRequest request;

    public String getHeaders(String prefix) {
        String allHeaders = "";
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String currentHeader = headerNames.nextElement();
            if (currentHeader.startsWith(prefix)) {
                allHeaders += currentHeader;
                allHeaders += ": ";
                allHeaders += request.getHeader(currentHeader);
                allHeaders += "<br>";
            }
        }
        return allHeaders;
    }

}
