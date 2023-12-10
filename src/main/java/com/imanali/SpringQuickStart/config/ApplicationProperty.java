package com.imanali.SpringQuickStart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationProperty {
    @Autowired
    private Environment env;
    public String getJwtSecretKey() {
        return env.getProperty("security.jwt.secret-key");
    }
}
