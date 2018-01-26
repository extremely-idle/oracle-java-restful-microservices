/* Copyright © 2017 Oracle and/or its affiliates. All rights reserved. */
package com.example.rest;

import java.util.Optional;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    // Get PORT and HOST from Environment or set default
    public static final Optional<String> host;
    public static final Optional<String> port;
    public static final Properties myProps = new Properties();

    public static final String HOSTNAME_ENVIRONMENT_VARIABLE_NAME = "HOSTNAME";
    public static final String PORT_ENVIRONMENT_VARIABLE_NAME = "PORT";
    public static final String DEFAULT_HOST = "localhost";
    public static final String DEFAULT_PORT = "8090";
    public static final String SERVER_ADDRESS_PROPERTY_NAME = "server.address";
    public static final String SERVER_PORT_PROPERTY_NAME = "server.port";

    static {
        host = Optional.ofNullable(System.getenv(HOSTNAME_ENVIRONMENT_VARIABLE_NAME));
        port = Optional.ofNullable(System.getenv(PORT_ENVIRONMENT_VARIABLE_NAME));
    }

    public static void main(String[] args) {
        // Set properties
        myProps.setProperty(SERVER_ADDRESS_PROPERTY_NAME, host.orElse(DEFAULT_HOST));
        myProps.setProperty(SERVER_PORT_PROPERTY_NAME, port.orElse(DEFAULT_PORT));

        SpringApplication app = new SpringApplication(App.class);
        app.setDefaultProperties(myProps);
        app.run(args);
    }
}