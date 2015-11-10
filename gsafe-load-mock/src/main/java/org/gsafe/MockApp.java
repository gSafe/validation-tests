package org.gsafe;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class MockApp extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new MockApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {

    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        MockResource resource = new MockResource();
        environment.jersey().register(resource);
    }
}
