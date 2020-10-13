package com.kcm;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLOutput;

@EnableRabbit
@EnableScheduling
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@MapperScan(basePackages = {"com.**.dao"})
public class ProjectdemoApplication extends SpringBootServletInitializer {

    /**
     * 打war包
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjectdemoApplication.class);
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ProjectdemoApplication.class, args);
        System.out.println(
                "************************************************************\n" +
                "**                                                        **\n" +
                "**                系统启动成功！     SUCCESS！               **\n" +
                "**                                                        **\n" +
                "************************************************************\n");

    }
    /**
     * mybatis-plus分页拦截器
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}