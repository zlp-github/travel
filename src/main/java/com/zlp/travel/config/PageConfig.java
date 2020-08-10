package com.zlp.travel.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author: zlp
 * Date: 2020-08-05 22:14
 * Description:张立朋，写点注释吧!!
 */
@EnableTransactionManagement
@Configuration
@ComponentScan("com.zlp.travel.dao")
public class PageConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
