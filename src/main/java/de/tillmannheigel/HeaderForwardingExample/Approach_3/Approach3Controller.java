package de.tillmannheigel.HeaderForwardingExample.Approach_3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.tillmannheigel.HeaderForwardingExample.interceptors.HeaderClientHttpRequestInterceptor;
import de.tillmannheigel.HeaderForwardingExample.model.Quote;

@RestController
public class Approach3Controller {

    @Autowired
    private HeaderClientHttpRequestInterceptor headerClientHttpRequestInterceptor;

    @RequestMapping("/approach_3")
    public String example() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(headerClientHttpRequestInterceptor);
        restTemplate.setInterceptors(interceptors);
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return "Approach 3:<br>" + quote;
    }
}