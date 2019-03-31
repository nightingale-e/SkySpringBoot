package com.nightingalee;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@ComponentScan(basePackages = {"com.nightingalee"})
@EnableJpaRepositories(basePackages = "com.nightingalee.repository")
public class TestContext {

}