package com.love;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

//import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.love.*.dao")
@EnableTransactionManagement
public class LoveApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LoveApplication.class, args);
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "false");
        properties.setProperty("dialect", "mysql");  //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
