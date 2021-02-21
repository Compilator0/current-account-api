package com.capgemini.amazingbank.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Component
public class TestsSharedHandler {
    @Autowired
    private Environment environment;

    public String getApiUrlPrefix() {
        return "http://localhost:" + this.environment.getProperty("local.server.port") + "/api";
    }

}
