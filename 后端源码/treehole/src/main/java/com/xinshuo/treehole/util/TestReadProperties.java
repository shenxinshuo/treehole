package com.xinshuo.treehole.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestReadProperties {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties"));
        String aaa = properties.getProperty("aaa");
        System.out.println("aaa:"+aaa);
    }
}
