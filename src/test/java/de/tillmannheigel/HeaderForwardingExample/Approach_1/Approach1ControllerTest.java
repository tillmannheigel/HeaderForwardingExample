package de.tillmannheigel.HeaderForwardingExample.Approach_1;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Approach1ControllerTest {

    @InjectMocks
    private Approach1Controller controller;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Test
    public void shouldDisplayHeaderInResponse() {
        //given
        HashSet<String> headers = new HashSet<>();
        headers.add("x-custom-header");
        Enumeration<String> headerNames = Collections.enumeration(headers);
        when(httpServletRequest.getHeaderNames()).thenReturn(headerNames);

        //when
        String response = controller.example();

        //then
        Assert.assertTrue(response.contains("x-custom"));
        Assert.assertFalse(response.contains("not-existing-header"));
    }

    @Test
    public void shouldNotDisplayOtherHeaderInResponse() {
        //given
        HashSet<String> headers = new HashSet<>();
        headers.add("x-another-header");
        Enumeration<String> headerNames = Collections.enumeration(headers);
        when(httpServletRequest.getHeaderNames()).thenReturn(headerNames);

        //when
        String response = controller.example();

        //then
        Assert.assertFalse(response.contains("x-custom"));
        Assert.assertFalse(response.contains("x-another-header"));
    }

}