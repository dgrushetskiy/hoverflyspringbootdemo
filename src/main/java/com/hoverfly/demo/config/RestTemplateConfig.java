package com.hoverfly.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

@Configuration
public class RestTemplateConfig {

    private static final int HOVERFLY_PORT = 8500;
    private static final String HOVERFLY_HOST = "localhost";
    private static final String PROXY = "proxy";

    @Autowired
    private Environment env;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {


        String mode = env.getProperty("appmode") ;

        System.out.println("##################### Mode ################# " + mode);

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(HOVERFLY_HOST, HOVERFLY_PORT));
        requestFactory.setProxy(proxy);
        RestTemplate template = null;

        if (mode != null && mode.equalsIgnoreCase(PROXY)) {
            System.out.println("######### Running in PROXY mode");
            template = new RestTemplate(requestFactory);
        } else {
            System.out.println("######### Running in PRODUCTION mode");
            template = new RestTemplate();
        }

        return template;
    }
}