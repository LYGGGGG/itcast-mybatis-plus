package cn.itcast.mp;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@author YG
@create 2023/1/1   1:59
*/
@Configuration
@MapperScan("cn.itcast.mp.mapper")
public class MyBatisPlusConfig {
    @Bean // 配置分页插件
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
