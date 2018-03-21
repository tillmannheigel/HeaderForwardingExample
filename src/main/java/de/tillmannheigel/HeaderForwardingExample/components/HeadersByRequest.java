package de.tillmannheigel.HeaderForwardingExample.components;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javafx.util.Pair;

@Component
@RequestScope
public class HeadersByRequest {

    @Autowired
    private HttpServletRequest request;

    public String printHeaders(String prefix) {
        String headers = "";
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String currentHeader = headerNames.nextElement();
            if (currentHeader.startsWith(prefix)) {
                headers += currentHeader;
                headers += ": ";
                headers += request.getHeader(currentHeader);
                headers += "<br>";
            }
        }
        return headers;
    }

    public List<Pair<String, String>> getHeaders(String prefix) {
        List<Pair<String, String>> headers = new LinkedList<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            if (header.startsWith(prefix)) {
                Pair<String, String> headerKV = new Pair<>(header, request.getHeader(header));
                headers.add(headerKV);
            }
        }
        return headers;
    }

}
