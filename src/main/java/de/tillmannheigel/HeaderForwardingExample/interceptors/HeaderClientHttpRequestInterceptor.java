package de.tillmannheigel.HeaderForwardingExample.interceptors;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import de.tillmannheigel.HeaderForwardingExample.components.HeadersByRequest;

import javafx.util.Pair;

@Component
public class HeaderClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private HeadersByRequest headersByRequest;

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        List<Pair<String, String>> headers = headersByRequest.getHeaders("x-custom");
        headers.forEach(header -> request.getHeaders().set(header.getKey(), header.getValue()));
        return execution.execute(request, body);
    }
}
