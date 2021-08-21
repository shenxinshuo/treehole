package com.xinshuo.treehole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 */
@Cacheable
@EnableScheduling
@SpringBootApplication
public class TreeholeApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TreeholeApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TreeholeApplication.class, args);
    }


}
