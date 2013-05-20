package com.packt.springsecurity.backend.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
// @ImportResource({ "classpath*:backendSecurityConfig.xml", "classpath*:beans-security-acl.xml" })
@ImportResource({ "classpath*:backendSecurityConfig.xml" })
public class BackendSecurityConfig {

    public BackendSecurityConfig() {
        super();
    }

}
